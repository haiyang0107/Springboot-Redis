package play.service;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.domain.AlipayTradeRefundModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.google.common.collect.Maps;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import play.alipay.AliPayConfig;
import constants.PayConstants;
import play.dto.PayDto;
import play.dto.RefundDto;
import play.entity.AliPayRefund;
import play.entity.AlipayNotify;
import play.repository.AliPayRefundRepository;
import play.repository.AliPayRepository;
import utils.DateUtil;
import utils.JSONUtils;
import utils.JsonUtil;
import utils.WebUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 支付服务类
 * @author liuxiaoxue
 * @data 2018年12月18日17:09:46
 *
 */
@Service
public class PayAppService {

    //定义一个全局的记录器，通过LoggerFactory获取
    private final static Logger logger = LoggerFactory.getLogger(PayAppService.class);
    /**
     * 支付宝支付
     */
    @Autowired
    private AliPayRepository alirepository;
    /**
     * 支付宝退款
     */
    @Autowired
    private AliPayRefundRepository payRefundRepository;




    /**
     * 支付宝异步请求逻辑处理
     * @param conversionParams
     * @return
     * @throws
     */
    public String notify(Map<String, String> conversionParams){

        logger.info("==================支付宝异步请求逻辑处理:{}",JSON.toJSONString(conversionParams));

        //签名验证(对支付宝返回的数据验证，确定是支付宝返回的)
        boolean signVerified = false;

        try {
            //调用SDK验证签名
            signVerified = AlipaySignature.rsaCheckV1(conversionParams, AliPayConfig.ALIPAY_PUBLIC_KEY, AliPayConfig.CHARSET, AliPayConfig.SIGNTYPE);

        } catch (AlipayApiException e) {
            logger.info("==================验签失败 ！");
            e.printStackTrace();
        }

        //对验签进行处理
        if (signVerified) {
            //验签通过
            //获取需要保存的数据
            String appId=conversionParams.get("app_id");//支付宝分配给开发者的应用Id
            String notifyTime=conversionParams.get("notify_time");//通知时间:yyyy-MM-dd HH:mm:ss
            String gmtCreate=conversionParams.get("gmt_create");//交易创建时间:yyyy-MM-dd HH:mm:ss
            String gmtPayment=conversionParams.get("gmt_payment");//交易付款时间
            String gmtRefund=conversionParams.get("gmt_refund");//交易退款时间
            String gmtClose=conversionParams.get("gmt_close");//交易结束时间
            String tradeNo=conversionParams.get("trade_no");//支付宝的交易号
            String outTradeNo = conversionParams.get("out_trade_no");//获取商户之前传给支付宝的订单号（商户系统的唯一订单号）
            String outBizNo=conversionParams.get("out_biz_no");//商户业务号(商户业务ID，主要是退款通知中返回退款申请的流水号)
            String buyerLogonId=conversionParams.get("buyer_logon_id");//买家支付宝账号
            String sellerId=conversionParams.get("seller_id");//卖家支付宝用户号
            String sellerEmail=conversionParams.get("seller_email");//卖家支付宝账号
            String totalAmount=conversionParams.get("total_amount");//订单金额:本次交易支付的订单金额，单位为人民币（元）
            String receiptAmount=conversionParams.get("receipt_amount");//实收金额:商家在交易中实际收到的款项，单位为元
            String invoiceAmount=conversionParams.get("invoice_amount");//开票金额:用户在交易中支付的可开发票的金额
            String buyerPayAmount=conversionParams.get("buyer_pay_amount");//付款金额:用户在交易中支付的金额
            String tradeStatus = conversionParams.get("trade_status");// 获取交易状态

            //支付宝官方建议校验的值（out_trade_no、total_amount、sellerId、app_id）
            AlipayNotify alipaymentOrder= new AlipayNotify();
            alipaymentOrder.setOutTradeNo(outTradeNo);
//            alipaymentOrder = repository.fin

            alipaymentOrder = alirepository.findByOutTradeNo(outTradeNo);

            if(alipaymentOrder!=null&&totalAmount.equals(alipaymentOrder.getTotalAmount().toString())&&AliPayConfig.APP_ID.equals(appId)){
                //修改数据库支付宝订单表(因为要保存每次支付宝返回的信息到数据库里，以便以后查证)
                alipaymentOrder.setNotifyTime(DateUtil.toDateTimeWithDash(notifyTime));
                alipaymentOrder.setGmtCreate(DateUtil.toDateTimeWithDash(gmtCreate));
                alipaymentOrder.setGmtPayment(DateUtil.toDateTimeWithDash(gmtPayment));
                alipaymentOrder.setGmtClose(DateUtil.now());
                alipaymentOrder.setTradeNo(tradeNo);
                alipaymentOrder.setOutBizNo(outBizNo);
                alipaymentOrder.setBuyerLogonId(buyerLogonId);
                alipaymentOrder.setSellerId(sellerId);
                alipaymentOrder.setSellerEmail(sellerEmail);
                alipaymentOrder.setTotalAmount(totalAmount);
                alipaymentOrder.setReceiptAmount(receiptAmount);
                alipaymentOrder.setInvoiceAmount(invoiceAmount);
                alipaymentOrder.setBuyerPayAmount(buyerPayAmount);
                alipaymentOrder.setUpdate_by("System");
                alipaymentOrder.setUpdate_date(DateUtil.now());
                switch (tradeStatus) // 判断交易结果
                {
                    case "TRADE_FINISHED": // 交易结束并不可退款
                        alipaymentOrder.setTradeStatus(PayConstants.TradeStatus.THREE);
                        break;
                    case "TRADE_SUCCESS": // 交易支付成功
                        alipaymentOrder.setTradeStatus(PayConstants.TradeStatus.TWO);
                        break;
                    case "TRADE_CLOSED": // 未付款交易超时关闭或支付完成后全额退款
                        alipaymentOrder.setTradeStatus(PayConstants.TradeStatus.ONE);
                        break;
                    case "WAIT_BUYER_PAY": // 交易创建并等待买家付款
                        alipaymentOrder.setTradeStatus(PayConstants.TradeStatus.ZERO);
                        break;
                    default:
                        break;
                }

//                super.save(alipaymentOrder);    //更新交易表中状态

                alirepository.save(alipaymentOrder);

                if(tradeStatus.equals("TRADE_SUCCESS")) {    //只处理支付成功的订单: 修改交易表状态,支付成功
                    Map<String,Object> param = Maps.newHashMap();
                    param.put("id",outTradeNo);
                    param.put("type",PayConstants.OrderType.ALITYPE);
                    String post = WebUtils.post(PayConstants.UPDATEORDERURL, param);
                    logger.info("订单接口返回结果:{}",post);
                    return PayConstants.SUCCESS;
                }else{
                    return PayConstants.FAIL;
                }

            }else{
                logger.info("==================支付宝官方建议校验的值（out_trade_no、total_amount、sellerId、app_id）,不一致！返回fail");
                return PayConstants.FAIL;
            }

        } else {  //验签不通过
            logger.info("==================验签不通过 ！");
            return PayConstants.FAIL;
        }

    }


    /**
     * 获取支付宝加签后台的订单信息字符串
     *
     * @param payDto
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public String getAliPayOrderStr(PayDto payDto) {
        //最终返回加签之后的，app需要传给支付宝app的订单信息字符串
        String orderString = "";
        logger.info("==================支付宝下单,商户订单号为:{}",payDto.getOutTradeNo());

        logger.info("支付宝下单数据:{}", JSON.toJSON(payDto));
        //创建商户支付宝订单(因为需要记录每次支付宝支付的记录信息，单独存一个表跟商户订单表关联，以便以后查证)
        AlipayNotify alipaymentOrder= alirepository.findByOutTradeNo(payDto.getOutTradeNo());
        if (alipaymentOrder!=null){
            if (alipaymentOrder.getTradeStatus().equals(PayConstants.TradeStatus.TWO)){
                return PayConstants.FAIL;
            }
        }


        //保存支付宝信息
        saveAliPayMentOrder(alipaymentOrder,payDto);

        try {

            //实例化客户端（参数：网关地址、商户appid、商户私钥、格式、编码、支付宝公钥、加密类型），为了取得预付订单信息
            AlipayClient alipayClient = AliPayConfig.getInstance();

            //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
            AlipayTradeAppPayRequest ali_request = new AlipayTradeAppPayRequest();

            //SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式
            AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();

            //业务参数传入,可以传很多，参考API
            //model.setPassbackParams(URLEncoder.encode(request.getBody().toString())); //公用参数（附加数据）
            model.setBody(payDto.getBody());   //对一笔交易的具体描述信息。如果是多种商品，请将商品描述字符串累加传给body。
            model.setSubject(payDto.getSubjecy());                 //商品名称
            model.setOutTradeNo(payDto.getOutTradeNo());           //商户订单号(自动生成)
            // model.setTimeoutExpress("30m");     			  //交易超时时间
            model.setTotalAmount(payDto.getTotalAmount());         //支付金额
            model.setProductCode("QUICK_MSECURITY_PAY");        	  //销售产品码（固定值）
            ali_request.setBizModel(model);
            logger.info("====================异步通知的地址为:{}",AliPayConfig.PAY_NOTIFY);
            ali_request.setNotifyUrl(AliPayConfig.PAY_NOTIFY);        //异步回调地址（后台）
            ali_request.setReturnUrl(AliPayConfig.RETURN_URL);	    //同步回调地址（APP）

            // 这里和普通的接口调用不同，使用的是sdkExecute
            AlipayTradeAppPayResponse alipayTradeAppPayResponse = alipayClient.sdkExecute(ali_request); //返回支付宝订单信息(预处理)
            orderString=alipayTradeAppPayResponse.getBody();//就是orderString 可以直接给APP请求，无需再做处理。

        }catch (Exception e){
            logger.info("与支付宝交互出错，未能生成订单，请检查代码！");
            return PayConstants.FAIL;
        }

        return orderString;
    }




    /**
     * 支付宝退款
     * @param refundDto
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public String retreatPayOrderStr(RefundDto refundDto){
        logger.info("==================支付宝退订,商户订单号为:{}",refundDto.getOutTradeNo());
        //实例化客户端（参数：网关地址、商户appid、商户私钥、格式、编码、支付宝公钥、加密类型），为了取得预付订单信息
        AlipayNotify byOutTradeNo = alirepository.findByOutTradeNo(refundDto.getOutTradeNo());
        if (byOutTradeNo==null){
            return PayConstants.ERROR;
        }
        if(byOutTradeNo.getTradeStatus().equals(PayConstants.TradeStatus.ONE)){
            return PayConstants.FAIL;
        }

        AlipayClient alipayClient = AliPayConfig.getInstance();

        //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();

        //SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式
        AlipayTradeRefundModel model = new AlipayTradeRefundModel();

        model.setOutTradeNo(byOutTradeNo.getOutTradeNo());
        model.setRefundAmount(byOutTradeNo.getTotalAmount());
        //部分退款参数
//        model.setOutRequestNo(PayConstants.OUTREQUESTNO);
        model.setRefundReason("商品退款");

        request.setBizModel(model);

        try{
            AlipayTradeRefundResponse response = alipayClient.execute(request);
//            net.sf.json.JSONObject.toBean(response.getBody(),AlipayNotify.class);
            AliPayRefund aliPayRefund = JsonUtil.jsonStrToObject(response.getBody(), AliPayRefund.class);
            aliPayRefund.setOrder_id(refundDto.getOutTradeNo());
            payRefundRepository.save(aliPayRefund);
            byOutTradeNo.setTradeStatus(PayConstants.TradeStatus.ONE);
            alirepository.save(byOutTradeNo);
            logger.info("退款结果:{}",response.getMsg());
            logger.info("退款返回参数:{}",response.getBody());
            return response.getBody();
        }catch(Exception e){
            logger.error("支付宝退款错误！",e.getMessage());
            return  PayConstants.FAIL;
        }


    }


    /**
     * 保存支付宝记录
     * @param alipaymentOrder
     * @param payDto
     */
    private void saveAliPayMentOrder(AlipayNotify alipaymentOrder,PayDto payDto){
        if (alipaymentOrder==null){
            alipaymentOrder = new AlipayNotify();
        }
        alipaymentOrder.setOrderId(payDto.getOrderId());//商家订单主键
        alipaymentOrder.setOrderId(payDto.getOrderId());//商家订单主键
        alipaymentOrder.setOutTradeNo(payDto.getOutTradeNo());//商户订单号
        alipaymentOrder.setTradeStatus("0");//交易状态
        alipaymentOrder.setTotalAmount(payDto.getTotalAmount());//订单金额
        alipaymentOrder.setReceiptAmount("0.00");//实收金额
        alipaymentOrder.setInvoiceAmount("0.00");//开票金额
        alipaymentOrder.setBuyerPayAmount("0.00");//付款金额
        alipaymentOrder.setCreate_by("System");
        alipaymentOrder.setCreate_date(DateUtil.now());
        alirepository.save(alipaymentOrder);
    }

    /**
     * 订单金额校验
     * @param payDto
     * @return
     */

    public boolean checkPaymentNumberByOrderId(PayDto payDto){

        Map<String,Object> param = Maps.newHashMap();
        param.put("id",payDto.getOutTradeNo());
        param.put("totalPrice",payDto.getTotalAmount());
        param.put("type",payDto.getPayType());
        try {
            String post = WebUtils.post(PayConstants.CHECKPAYMENTNUMBERBYORDERID, param);
            logger.info("订单金额校验返回数据:{}",post);
            HashMap hashMap = JSONUtils.toHashMap(post);
            if (hashMap.get("status").equals(true)){
                return true;
            }
        }catch (Exception e){
            logger.info("调用订单金额接口失败！");
            return false;
        }


        return false;
    }


    /**
     * 获取支付宝支付时间
     * @param payDto
     * @return
     */
    public Map<String,Object> getAliPayDate(PayDto payDto){
        logger.info("获取支付宝支付时间:{}",JSON.toJSONString(payDto));
        Map<String,Object> result = Maps.newHashMap();
        AlipayNotify byOutTradeNo = alirepository.findByOutTradeNo(payDto.getOutTradeNo());
        if(byOutTradeNo!=null){
            if (byOutTradeNo.getTradeStatus().equals(PayConstants.TradeStatus.TWO)){
                result.put("date",byOutTradeNo.getGmtPayment());
                result.put(PayConstants.Result.STATUS,true);
            }else {
                result.put(PayConstants.Result.STATUS,false);
            }
        }else {
            result.put(PayConstants.Result.MESSAGE,"支付订单不存在！");
            result.put(PayConstants.Result.STATUS,false);
        }

        return result;
    }


    /***
     * 将对象转换为HashMap
     * @param object
     * @return
     */
    public static HashMap toHashMap(Object object)
    {
        HashMap<String, Object> data = new HashMap<String, Object>();
        JSONObject jsonObject = JSONObject.fromObject(object);
        Iterator it = jsonObject.keys();
        while (it.hasNext())
        {
            String key = String.valueOf(it.next());
            Object value = jsonObject.get(key);
            data.put(key, value);
        }

        return data;
    }
}
