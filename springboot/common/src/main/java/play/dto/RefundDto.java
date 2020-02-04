package play.dto;

/**
 * 支付退款类
 * @author liuxiaoxue
 * @date 2018年12月12日09:59:00
 */
public class RefundDto {
    /**
     * 退款类型
     */
    private String payType ;
    /**
     * 商户订单号
     */
    private String outTradeNo;


    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }
}
