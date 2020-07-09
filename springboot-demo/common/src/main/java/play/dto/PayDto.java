package play.dto;

/**
 * 支付参数类
 * @author liuxiaoxue
 * @date 2018年12月12日09:59:00
 */
public class PayDto {
    /**
     * 支付类型
     */
    private String payType;

    /**
     * 商户订单号
     */
    private String outTradeNo;
    /**
     * 商家订单主键
     */
    private String orderId;
    /**
     * 订单金额
     */
    private String totalAmount;
    /**
     * 对一笔交易的具体描述信息
     */
    private String body;
    /**
     * 商品名称
     */
    private String subjecy;
    /**
     * 商户订单号(自动生成)
     */
    private String outtradeno;






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


    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSubjecy() {
        return subjecy;
    }

    public void setSubjecy(String subjecy) {
        this.subjecy = subjecy;
    }

    public String getOuttradeno() {
        return outtradeno;
    }

    public void setOuttradeno(String outtradeno) {
        this.outtradeno = outtradeno;
    }
}
