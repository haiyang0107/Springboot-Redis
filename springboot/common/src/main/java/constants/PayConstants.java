package constants;

/**
 * 支付常量类
 * @author liuxiaoxue
 * @date 2018年12月11日15:47:43
 *
 */
public interface PayConstants {

    /**
     * 成功返回
     */
    public static final String SUCCESS = "success";

    /**
     * 错误返回
     */
    public static final String FAIL = "fail";

    /**
     * 数据不存在
     */
    public static final String ERROR = "error";

    /**
     * 支付宝
     */
    public static final String ALIPAY = "alipay";

    /**
     * 微信
     */
    public static final String WXPAY = "wxpay";

    /**
     * 部分退款可选
     */
    public static final String OUTREQUESTNO = "HZ01RF001";
    // JSONP
    public static final String JSONP_HEAD = "onBack";

    interface ResultType{

        String PAYTYPE = "payType";

        String INFO = "info";

        String DATA = "data";
    }

    interface Result{
       String CODE = "code";

       String  STATUS = "status";

       String STATUSCODE = "statusCode";

       String MESSAGE = "message";
    }


    /**
     * 修改订单状态接口
     */
    public static final String UPDATEORDERURL = "http://192.168.1.120:8985/hotel-admin/a/hotelApp/orders/updatePaymentStatus";

    /**
     * 订单金额校验
     */
    public static final String CHECKPAYMENTNUMBERBYORDERID = "http://192.168.1.120:8985/hotel-admin/a/hotelApp/orders/checkPaymentNumberByOrderId";

    interface OrderType{
        //支付宝
        String ALITYPE = "20";
        //微信
        String WXTYPE = "30";
    }

    interface TradeStatus{
        // 交易创建并等待买家付款
        String ZERO = "0";
        // 未付款交易超时关闭或支付完成后全额退款
        String ONE = "1";
        // 交易支付成功
        String TWO = "2";
        // 交易结束并不可退款
        String THREE = "3";

    }


    /**
     * 微信支付回调 地址
     */
    public static final String WXNOTIFYURL = "http://101.254.183.242:11102/hotel-admin/a/hotelApp/pay/wxnotify";
}
