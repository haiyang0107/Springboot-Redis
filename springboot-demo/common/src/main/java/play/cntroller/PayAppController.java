package play.cntroller;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import constants.PayConstants;
import play.dto.PayDto;
import play.dto.RefundDto;
import play.service.PayAppService;
import play.service.PayWxService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Controller
@RestController
@RequestMapping(value = "/hotelApp/pay")
public class PayAppController {
    //定义一个全局的记录器，通过LoggerFactory获取
    private final static Logger logger = LoggerFactory.getLogger(PayAppController.class);
    /**
     * 支付宝服务
     */
    @Autowired
    private PayAppService appService;
    /**
     * 微信服务类
     */
    @Autowired
    private PayWxService payWxService;


    @RequestMapping(value = "/pay",method = RequestMethod.POST)
    @ResponseBody
    public String pay(PayDto payDto){
        logger.info("去支付接口入参:{}", JSON.toJSONString(payDto));
        Map<String,Object> result =  verifyParam(payDto);
        if(Boolean.valueOf(result.get("status").toString())){
            Map<String,Object> payresultmap = Maps.newHashMap();
            if(payDto.getPayType().equals(PayConstants.ALIPAY)){
                String payresult = appService.getAliPayOrderStr(payDto);
                if(payresult.equals(PayConstants.FAIL)){
                    payresultmap.put(PayConstants.ResultType.PAYTYPE,PayConstants.ALIPAY);
                    payresultmap.put(PayConstants.ResultType.INFO,"交易错误，重复下单，或下单失败！");
                    result.put(PayConstants.Result.CODE,PayConstants.FAIL);
                }else {
                    payresultmap.put(PayConstants.ResultType.PAYTYPE,PayConstants.ALIPAY);
                    payresultmap.put(PayConstants.ResultType.INFO,payresult);
                }

                logger.info("支付宝返回消息:{}",payresult);
            }else if (payDto.getPayType().equals(PayConstants.WXPAY)){
                Map map = payWxService.unifiedOrder(payDto);
                if(map.get("result_code").equals("FAIL")){
                    payresultmap.put(PayConstants.ResultType.PAYTYPE,PayConstants.ALIPAY);
                    payresultmap.put(PayConstants.ResultType.INFO,"交易错误，重复下单，或下单失败！");
                    result.put(PayConstants.Result.CODE,PayConstants.FAIL);
                }else {
                    payresultmap.put(PayConstants.ResultType.PAYTYPE,PayConstants.WXPAY);
                    payresultmap.put(PayConstants.ResultType.INFO,map);
                }

            }
            result.put(PayConstants.ResultType.DATA,payresultmap);

            result.put(PayConstants.Result.STATUS,true);
        }
        return addJsonpHead(result);
    }


    /**
     * 支付宝支付成功后.异步请求该接口
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(value="/notify")
    @ResponseBody
    public String notify(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.info("==================支付宝异步返回支付结果开始");
        //1.从支付宝回调的request域中取值
        //获取支付宝返回的参数集合
        Map<String, String[]> aliParams = request.getParameterMap();
        //用以存放转化后的参数集合
        Map<String, String> conversionParams = new HashMap<String, String>();
        for (Iterator<String> iter = aliParams.keySet().iterator(); iter.hasNext();) {
            String key = iter.next();
            String[] values = aliParams.get(key);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            // 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
            // valueStr = new String(valueStr.getBytes("ISO-8859-1"), "uft-8");
            conversionParams.put(key, valueStr);
        }
        logger.info("支付宝返回参数集合:",JSON.toJSONString(conversionParams));
        String status= appService.notify(conversionParams);
        return status;
    }

    /**
     * 微信异步通知
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/wxnotify")
    public String wxnotify(HttpServletRequest request, HttpServletResponse response) throws Exception{

        InputStream inStream = request.getInputStream();
        ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outSteam.write(buffer, 0, len);
        }
        String resultxml = new String(outSteam.toByteArray(), "utf-8");
        String wxnotify = payWxService.wxnotify(resultxml);
        logger.info("异步回调返回数据:{}",wxnotify);
        return wxnotify;
    }


    /**
     * 退款操作
     * @param refundDto
     * @return
     */
    @RequestMapping(value="/refundmoney")
    @ResponseBody
    public Map<String, Object> refundmoney(RefundDto refundDto){
        Map<String,Object> result = Maps.newHashMap();
        Map<String,Object> payresultmap = Maps.newHashMap();
        if (!refundDto.getOutTradeNo().equals("")){
            if(refundDto.getPayType().equals(PayConstants.ALIPAY)){
                String retratresult =  appService.retreatPayOrderStr(refundDto);
                if (retratresult.equals(PayConstants.FAIL)){
                    result.put(PayConstants.Result.STATUSCODE,"500");
                    result.put(PayConstants.Result.STATUS,false);
                    result.put(PayConstants.Result.MESSAGE,"不可重复提交退款操作!");
                }else if (retratresult.equals(PayConstants.ERROR)){
                    result.put(PayConstants.Result.STATUSCODE,"500");
                    result.put(PayConstants.Result.STATUS,false);
                    result.put(PayConstants.Result.MESSAGE,"数据不存在!");
                }else {
                    payresultmap.put(PayConstants.ResultType.PAYTYPE,PayConstants.ALIPAY);
                    payresultmap.put(PayConstants.ResultType.INFO,retratresult);

                    result.put(PayConstants.ResultType.DATA,payresultmap);
                    result.put(PayConstants.Result.STATUS,true);
                }

            }else if (refundDto.getPayType().equals(PayConstants.WXPAY)){
                String payOrderStr = payWxService.wxretreatPayOrderStr(refundDto);
                logger.info("退款返回结果:{}",payOrderStr);
                if (payOrderStr.equals(PayConstants.ERROR)){
                    result.put(PayConstants.Result.STATUSCODE,"500");
                    result.put(PayConstants.Result.STATUS,false);
                    result.put(PayConstants.Result.MESSAGE,"数据不存在!");
                }else if(payOrderStr.equals(PayConstants.FAIL)){
                    result.put(PayConstants.Result.STATUSCODE,"500");
                    result.put(PayConstants.Result.STATUS,false);
                    result.put(PayConstants.Result.MESSAGE,"订单支付未回调!");
                }else {
                    payresultmap.put(PayConstants.ResultType.PAYTYPE,PayConstants.WXPAY);
                    payresultmap.put(PayConstants.ResultType.INFO,payOrderStr);

                    result.put(PayConstants.ResultType.DATA,payresultmap);
                    result.put(PayConstants.Result.STATUS,true);
                }

            }

        }else{
            result.put(PayConstants.Result.STATUSCODE,"500");
            result.put(PayConstants.Result.STATUS,false);
            result.put(PayConstants.Result.MESSAGE,"请正确传入值!");
        }

        return result;
    }
    /**
     * 校验参数方法
     * @param payDto
     * @return
     */
    public Map<String,Object> verifyParam(PayDto payDto){
        //微信金额处理
        initWxtotalAmount(payDto);

        Map<String,Object> result = new HashMap<>();
        if(!appService.checkPaymentNumberByOrderId(payDto)){
            result.put(PayConstants.Result.STATUSCODE,"500");
            result.put(PayConstants.Result.STATUS,false);
            result.put(PayConstants.Result.MESSAGE,"支付金额校验失败!");
            return result;
        }

        if(payDto!=null){
            if(!payDto.getPayType().equals(PayConstants.ALIPAY)&&!payDto.getPayType().equals(PayConstants.WXPAY)){
                result.put(PayConstants.Result.STATUSCODE,"500");
                result.put(PayConstants.Result.STATUS,false);
                result.put(PayConstants.Result.MESSAGE,"支付类型不正确!");
                return result;
            }
            if(payDto.getOutTradeNo().equals("")){
                result.put(PayConstants.Result.STATUSCODE,"500");
                result.put(PayConstants.Result.STATUS,false);
                result.put(PayConstants.Result.MESSAGE,"商户订单号不正确!");
                return result;
            }
            if(payDto.getOrderId().equals("")){
                result.put(PayConstants.Result.STATUSCODE,"500");
                result.put(PayConstants.Result.STATUS,false);
                result.put(PayConstants.Result.MESSAGE,"商户订单不正确!");
                return result;
            }
            if(payDto.getTotalAmount().equals("")){
                result.put(PayConstants.Result.STATUSCODE,"500");
                result.put(PayConstants.Result.STATUS,false);
                result.put(PayConstants.Result.MESSAGE,"订单金额不正确!");
                return result;
            }
            if(payDto.getBody().equals("")){
                result.put(PayConstants.Result.STATUSCODE,"500");
                result.put(PayConstants.Result.STATUS,false);
                result.put(PayConstants.Result.MESSAGE,"交易的具体描述信息不正确!");
                return result;
            }
            if(payDto.getSubjecy().equals("")){
                result.put(PayConstants.Result.STATUSCODE,"500");
                result.put(PayConstants.Result.STATUS,false);
                result.put(PayConstants.Result.MESSAGE,"商品名称不正确!");
                return result;
            }
            if (payDto.getPayType().equals(PayConstants.WXPAY)){
                try {
                    Integer.parseInt(payDto.getTotalAmount());
                }catch (Exception e){
                    logger.error("微信支付支付金额必须是整数!");
                    result.put(PayConstants.Result.STATUSCODE,"500");
                    result.put(PayConstants.Result.STATUS,false);
                    result.put(PayConstants.Result.MESSAGE,"支付类型不正确!");
                    return result;
                }
            }
        }else{
            result.put(PayConstants.Result.STATUSCODE,"500");
            result.put(PayConstants.Result.STATUS,false);
            result.put(PayConstants.Result.MESSAGE,"支付类型不正确!");
            return result;
        }

        result.put(PayConstants.Result.STATUS,true);
        return result;
    }


    /**
     * 处理微信金额
     * @param payDto
     */
    public void initWxtotalAmount(PayDto payDto){

        if (payDto.getPayType().equals(PayConstants.WXPAY)){
            BigDecimal totalAmount = new BigDecimal(payDto.getTotalAmount());
            BigDecimal multiply = totalAmount.multiply(new BigDecimal(100));
            int intValue = multiply.intValue();

            payDto.setTotalAmount(String.valueOf(intValue));
        }
    }

    /**
     * 获取支付时间
     * @param payDto
     * @return
     */
    @RequestMapping(value="/getPayDate")
    @ResponseBody
    public Map<String,Object> getPayDate(PayDto payDto){
        Map<String,Object> resultmap = Maps.newHashMap();
        if (!payDto.getOutTradeNo().equals("")){
            if (payDto.getPayType().equals(PayConstants.ALIPAY)){
                resultmap = appService.getAliPayDate(payDto);
            }else if (payDto.getPayType().equals(PayConstants.WXPAY)){
                resultmap = payWxService.getPayDate(payDto);
            }

        }else {
            resultmap.put(PayConstants.Result.STATUS,false);
            resultmap.put(PayConstants.Result.MESSAGE,"订单号不能为空！");
        }
        logger.info("返回支付时间:{}",JSON.toJSONString(resultmap));
        return resultmap;
    }

    private String addJsonpHead(Map<String, Object> map) {
        net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject.fromObject(map);
        return PayConstants.JSONP_HEAD + "(" + jsonObject + ")";
    }
}
