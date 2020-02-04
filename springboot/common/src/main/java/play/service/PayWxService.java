package play.service;

import com.alibaba.fastjson.JSON;
import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConstants;
import com.github.wxpay.sdk.WXPayUtil;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import constants.PayConstants;
import play.dto.PayDownlad;
import play.dto.PayDto;
import play.dto.RefundDto;
import play.entity.WxPayNotfy;
import play.entity.WxReFund;
import play.repository.WxPayRepository;
import play.repository.WxReFundRepository;
import utils.DateUtil;
import utils.WebUtils;
import play.wxpay.OurWxPayConfig;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * 微信服务类
 */
@Service
public class PayWxService {

    //定义一个全局的记录器，通过LoggerFactory获取
    private final static Logger logger = LoggerFactory.getLogger(PayWxService.class);

    /**
     * 微信
     */
    @Autowired
    private WxPayRepository wxPayRepository;

    /**
     * 微信退款
     */
    @Autowired
    private WxReFundRepository wxReFundRepository;


    /**
     * 调用微信SDK统一下单接口
     * @param payDto
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public Map unifiedOrder(PayDto payDto){
        logger.info("微信支付入参:{}", JSON.toJSONString(payDto));
        //入参
        Map<String, String> data = Maps.newHashMap();
        //结果
        Map<String, String> resp = Maps.newHashMap();
        //二次签名
        Map<String,String> tworesp = Maps.newHashMap();
        try {
            OurWxPayConfig config = new OurWxPayConfig();
            WXPay wxpay = new WXPay(config, WXPayConstants.SignType.MD5);
            data.put("body",payDto.getBody());
            data.put("out_trade_no",payDto.getOutTradeNo());   //交易号
            data.put("total_fee",payDto.getTotalAmount());       //订单总金额
            data.put("spbill_create_ip", "123.12.12.123");             //终端ip
            data.put("notify_url", PayConstants.WXNOTIFYURL);                     //回调地址
            data.put("trade_type","APP");                 //支付场景 APP 微信app支付 JSAPI 公众号支付  NATIVE 扫码支付
            logger.info("微信统一下单接口入参:{}",JSON.toJSONString(data));

            resp = wxpay.unifiedOrder(data);

            //保存微信记录
            saveWxPayNotfy(payDto,resp);
            //二次签名
            tworesp = twoSign(resp,config.getKey());


        }catch (Exception e){
            logger.error("微信统一下单失败");
        }


        return tworesp;
    }

    /**
     * 微信异步通知处理
     * @param strXml
     * @return
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public String wxnotify(String strXml) throws Exception{
        Map<String,String> result = Maps.newHashMap();
        OurWxPayConfig config = new OurWxPayConfig();
        WXPay wxpay = new WXPay(config);
        Map<String, String> stringStringMap = WXPayUtil.xmlToMap(strXml);
        logger.info("微信回调参数:{}",JSON.toJSONString(stringStringMap));
        if(wxpay.isResponseSignatureValid(stringStringMap)){
            if(stringStringMap.get("result_code").equals("SUCCESS")){
                try {
                    WxPayNotfy wxPayNotfy = wxPayRepository.findByOutTradeNo(stringStringMap.get("out_trade_no"));
                    wxPayNotfy.setNotify_transaction_id(stringStringMap.get("transaction_id"));
                    wxPayNotfy.setNotify_nonce_str(stringStringMap.get("nonce_str"));
                    wxPayNotfy.setNotify_result_code(stringStringMap.get("result_code"));
                    wxPayNotfy.setNotify_sign(stringStringMap.get("sign"));
                    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddhhmmss");
                    Date date = (Date) sdf1.parse(stringStringMap.get("time_end"));
                    wxPayNotfy.setTimestamp(date);
                    wxPayNotfy.setWx_status(PayConstants.TradeStatus.TWO);
                    wxPayRepository.save(wxPayNotfy);

                    logger.info("微信调用订单接口~~~~~~~~~~");
                    logger.info("订单接口路径：{}",PayConstants.UPDATEORDERURL);
                    Map<String,Object> param = Maps.newHashMap();
                    param.put("id",stringStringMap.get("out_trade_no"));
                    param.put("type",PayConstants.OrderType.WXTYPE);

                    String post = WebUtils.post(PayConstants.UPDATEORDERURL, param);
                    logger.info("微信订单接口返回结果:{}",post);

                    result.put("return_msg","OK");
                    result.put("return_code","SUCCESS");

                }catch (Exception e){
                    result.put("return_code","ERROR");
                    result.put("return_msg","NO");
                }

            }

        }else {
            result.put("return_code","ERROR");
            result.put("return_msg","NO");
        }

        return WXPayUtil.mapToXml(result);
    }


    /**
     * 微信退款操作
     * @param refundDto
     * @return
     */

    public String wxretreatPayOrderStr(RefundDto refundDto){

        try {
            WxPayNotfy byOutTradeNo = wxPayRepository.findByOutTradeNo(refundDto.getOutTradeNo());

            if (byOutTradeNo==null){
                return PayConstants.ERROR;
            }

            if (!byOutTradeNo.getNotify_result_code().equals("SUCCESS")){
                return PayConstants.FAIL;
            }
            OurWxPayConfig config = new OurWxPayConfig();
            WXPay wxpay = new WXPay(config, WXPayConstants.SignType.MD5);
            Map<String,String> data = Maps.newHashMap();
            data.put("out_trade_no",byOutTradeNo.getOutTradeNo());
            data.put("out_refund_no",byOutTradeNo.getOutTradeNo());
            data.put("total_fee",byOutTradeNo.getTotal_fee().toString());
            data.put("refund_fee",byOutTradeNo.getTotal_fee().toString());
            Map<String, String> refund = wxpay.refund(data);

            refund.put("out_trade_no",byOutTradeNo.getOutTradeNo());
            //保存微信退款操作
            saveWxReFund(refund);
            byOutTradeNo.setWx_status(PayConstants.TradeStatus.ONE);
            wxPayRepository.save(byOutTradeNo);
            logger.info("退款返回结果:{}",JSON.toJSONString(refund));
            return JSON.toJSONString(refund);
        }catch (Exception e){
            e.printStackTrace();
        }


        return "";
    }


    /**
     * 获取微信支付时间
     * @param payDto
     * @return
     */
    public Map<String,Object> getPayDate(PayDto payDto){
        logger.info("获取微信支付时间:{}",JSON.toJSONString(payDto));
        Map<String,Object> result = Maps.newHashMap();
        WxPayNotfy byOutTradeNo = wxPayRepository.findByOutTradeNo(payDto.getOutTradeNo());
        if (byOutTradeNo!=null){
            if (byOutTradeNo.getWx_status().equals(PayConstants.TradeStatus.TWO)){
                result.put("date",byOutTradeNo.getTimestamp());
                result.put(PayConstants.Result.STATUS,true);
            }else {
                result.put(PayConstants.Result.MESSAGE,"支付订单未支付！");
                result.put(PayConstants.Result.STATUS,false);
            }

        }else {
            result.put(PayConstants.Result.MESSAGE,"支付订单不存在！");
            result.put(PayConstants.Result.STATUS,false);
        }
        return result;
    }

    /**
     * 微信对账单下载
     * @param payDownlad
     * @return
     */
    public String downloadbill(PayDownlad payDownlad){
        Map<String,String> param = Maps.newHashMap();
        param.put("bill_date",payDownlad.getBill_date());
        param.put("bill_type",payDownlad.getBill_type());
        try{
            OurWxPayConfig config = new OurWxPayConfig();
            WXPay wxpay = new WXPay(config, WXPayConstants.SignType.MD5);
            Map<String, String> stringStringMap = wxpay.downloadBill(param);
            logger.info("下载对账单接口:{}",JSON.toJSONString(stringStringMap));
            String str = stringStringMap.get("data");// 获取对账报文
            String newStr = str.replaceAll(",", " "); // 去空格
            String[] tempStr = newStr.split("`"); // 数据分组
            String[] t = tempStr[0].split(" ");// 分组标题
            int k = 1; // 纪录数组下标
            int j = tempStr.length / t.length; // 计算循环次数
            for (int i = 0; i < j; i++) {
                for (int l = 0; l < t.length; l++) {
                    System.out.println(t[l] + ":" + tempStr[l + k]);
                }
                System.out.println("---------");// 摘取有用数据存入数据库
                k = k + t.length;
            }
        }catch (Exception e){

        }

        return "";
    }

    /**
     * 保存微信交易记录
     * @param resp
     */
    private void saveWxPayNotfy(PayDto payDto,Map<String,String> resp){
        WxPayNotfy wxPayNotfy = wxPayRepository.findByOutTradeNo(payDto.getOutTradeNo());
        if(wxPayNotfy==null){
            wxPayNotfy = new WxPayNotfy();
        }
        wxPayNotfy.setOutTradeNo(payDto.getOutTradeNo());
        wxPayNotfy.setTime_start(DateUtil.now());
        wxPayNotfy.setBody(payDto.getBody());
        wxPayNotfy.setNotify_url(PayConstants.WXNOTIFYURL);
        wxPayNotfy.setMch_id(resp.get("mch_id"));
        wxPayNotfy.setLimit_pay(resp.get("limit_pay"));
        wxPayNotfy.setFee_type("APP");
        wxPayNotfy.setAppid(resp.get("appid"));
        wxPayNotfy.setSign(resp.get("sign"));
        wxPayNotfy.setTotal_fee(Integer.parseInt(payDto.getTotalAmount()));
        wxPayNotfy.setPrepay_id(resp.get("prepay_id"));
        wxPayNotfy.setResult_code(resp.get("result_code"));
        wxPayNotfy.setNonce_str(resp.get("nonce_str"));
        wxPayRepository.save(wxPayNotfy);
    }

    /**
     * 保存微信退款记录
     * @param refund
     */
    private void saveWxReFund(Map<String,String> refund){
        WxReFund wxReFund = wxReFundRepository.findByOutTradeNo(refund.get("out_trade_no"));

        if (wxReFund==null){
            wxReFund = new WxReFund();
        }
        if(refund.get("result_code").equals("SUCCESS")){
            wxReFund.setCash_fee(Integer.parseInt(refund.get("cash_fee")==""?"0":refund.get("cash_fee")));
            wxReFund.setRefund_fee(Integer.parseInt(refund.get("refund_fee").equals("")?"0":refund.get("refund_fee")));
            wxReFund.setTotal_fee(Integer.parseInt(refund.get("total_fee").equals("")?"0":refund.get("total_fee")));
        }
        wxReFund.setNonce_str(refund.get("nonce_str"));
        wxReFund.setTransaction_id(refund.get("transaction_id"));
        wxReFund.setOut_refund_no(refund.get("out_refund_no"));
        wxReFund.setSign(refund.get("sign"));
        wxReFund.setMch_id(refund.get("mch_id"));
        wxReFund.setRefund_id(refund.get("refund_id"));
        wxReFund.setOutTradeNo(refund.get("out_trade_no"));
        wxReFund.setAppid(refund.get("appid"));
        wxReFund.setResult_code(refund.get("result_code"));
        wxReFund.setRefund_data(DateUtil.now());
        wxReFundRepository.save(wxReFund);
        WxPayNotfy wxPayNotfy = wxPayRepository.findByOutTradeNo(refund.get("out_trade_no"));

        wxPayNotfy.setWx_status(PayConstants.TradeStatus.ONE);
        wxPayRepository.save(wxPayNotfy);
    }


    /**
     * 微信支付需要二次签名
     * @param resp
     * @return
     */
    private Map twoSign(Map<String,String> resp, String key){
        Map<String,String> tworesp = Maps.newHashMap();
        tworesp.put("appid",resp.get("appid"));
        tworesp.put("partnerid",resp.get("mch_id"));
        tworesp.put("prepayid",resp.get("prepay_id"));
        tworesp.put("noncestr",resp.get("nonce_str"));
        tworesp.put("timestamp",String.valueOf(System.currentTimeMillis() / 1000));
        tworesp.put("package", "Sign=WXPay");
        try {
            String sign = WXPayUtil.generateSignature(tworesp, key);
            tworesp.put("sign",sign);
        }catch (Exception e){
            logger.error("签名失败~~");
        }


        tworesp.put("result_code",resp.get("result_code"));

        return tworesp;
    }
}
