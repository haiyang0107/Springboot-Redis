package play.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * 支付宝交易记录表
 * @author liuxiaoxue
 * @data 2018年12月18日17:07:19
 */
@Entity(name = "t_alipay_notify_links")
public class AlipayNotify {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private Integer id;         //主键自增
    @Column(name = "order_id")
    private String orderId;		// 系统订单号
    @Column(name = "app_id")
    private String appId;		// 支付宝分配给开发者的应用Id
    @Column(name = "notify_time")
    private Date notifyTime;		// 通知时间:yyyy-MM-dd HH:mm:ss
    @Column(name = "gmt_create")
    private Date gmtCreate;		// 交易创建时间:yyyy-MM-dd HH:mm:ss
    @Column(name = "gmt_payment")
    private Date gmtPayment;		// 交易付款时间
    @Column(name = "gmt_refund")
    private Date gmtRefund;		// 交易退款时间
    @Column(name = "gmt_close")
    private Date gmtClose;		// 交易结束时间
    @Column(name = "trade_no")
    private String tradeNo;		// 支付宝的交易号
    @Column(name = "out_trade_no")
    private String outTradeNo;		// 获取商户之前传给支付宝的订单号（商户系统的唯一订单号）
    @Column(name = "out_biz_no")
    private String outBizNo;		// 商户业务号(商户业务ID，主要是退款通知中返回退款申请的流水号)
    @Column(name = "buyer_logon_id")
    private String buyerLogonId;		// 买家支付宝账号
    @Column(name = "seller_id")
    private String sellerId;		// 卖家支付宝用户号
    @Column(name = "seller_email")
    private String sellerEmail;		// 卖家支付宝账号
    @Column(name = "total_amount")
    private String totalAmount;		// 订单金额:本次交易支付的订单金额，单位为人民币（元）
    @Column(name = "receipt_amount")
    private String receiptAmount;		// 实收金额:商家在交易中实际收到的款项，单位为元
    @Column(name = "invoice_amount")
    private String invoiceAmount;		// 开票金额:用户在交易中支付的可开发票的金额
    @Column(name = "buyer_pay_amount")
    private String buyerPayAmount;		// 付款金额:用户在交易中支付的金额
    @Column(name = "trade_status")
    private String tradeStatus;		// 获取交易状态
    @Column
    private String create_by;
    @Column
    private Date create_date;
    @Column
    private String update_by;
    @Column
    private Date update_date;
    @Column
    private String extend1;		// 扩展字段1
    @Column
    private String extend2;		// 扩展字段2
    @Column
    private String extend3;		// 扩展字段3


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public Date getNotifyTime() {
        return notifyTime;
    }

    public void setNotifyTime(Date notifyTime) {
        this.notifyTime = notifyTime;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtPayment() {
        return gmtPayment;
    }

    public void setGmtPayment(Date gmtPayment) {
        this.gmtPayment = gmtPayment;
    }

    public Date getGmtRefund() {
        return gmtRefund;
    }

    public void setGmtRefund(Date gmtRefund) {
        this.gmtRefund = gmtRefund;
    }

    public Date getGmtClose() {
        return gmtClose;
    }

    public void setGmtClose(Date gmtClose) {
        this.gmtClose = gmtClose;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getOutBizNo() {
        return outBizNo;
    }

    public void setOutBizNo(String outBizNo) {
        this.outBizNo = outBizNo;
    }

    public String getBuyerLogonId() {
        return buyerLogonId;
    }

    public void setBuyerLogonId(String buyerLogonId) {
        this.buyerLogonId = buyerLogonId;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getSellerEmail() {
        return sellerEmail;
    }

    public void setSellerEmail(String sellerEmail) {
        this.sellerEmail = sellerEmail;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getReceiptAmount() {
        return receiptAmount;
    }

    public void setReceiptAmount(String receiptAmount) {
        this.receiptAmount = receiptAmount;
    }

    public String getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(String invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public String getBuyerPayAmount() {
        return buyerPayAmount;
    }

    public void setBuyerPayAmount(String buyerPayAmount) {
        this.buyerPayAmount = buyerPayAmount;
    }

    public String getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    public String getCreate_by() {
        return create_by;
    }

    public void setCreate_by(String create_by) {
        this.create_by = create_by;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public String getUpdate_by() {
        return update_by;
    }

    public void setUpdate_by(String update_by) {
        this.update_by = update_by;
    }

    public Date getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(Date update_date) {
        this.update_date = update_date;
    }

    public String getExtend1() {
        return extend1;
    }

    public void setExtend1(String extend1) {
        this.extend1 = extend1;
    }

    public String getExtend2() {
        return extend2;
    }

    public void setExtend2(String extend2) {
        this.extend2 = extend2;
    }

    public String getExtend3() {
        return extend3;
    }

    public void setExtend3(String extend3) {
        this.extend3 = extend3;
    }

    @Override
    public String toString() {
        return "AlipayNotify{" +
                "id=" + id +
                ", orderId='" + orderId + '\'' +
                ", appId='" + appId + '\'' +
                ", notifyTime=" + notifyTime +
                ", gmtCreate=" + gmtCreate +
                ", gmtPayment=" + gmtPayment +
                ", gmtRefund=" + gmtRefund +
                ", gmtClose=" + gmtClose +
                ", tradeNo='" + tradeNo + '\'' +
                ", outTradeNo='" + outTradeNo + '\'' +
                ", outBizNo='" + outBizNo + '\'' +
                ", buyerLogonId='" + buyerLogonId + '\'' +
                ", sellerId='" + sellerId + '\'' +
                ", sellerEmail='" + sellerEmail + '\'' +
                ", totalAmount='" + totalAmount + '\'' +
                ", receiptAmount='" + receiptAmount + '\'' +
                ", invoiceAmount='" + invoiceAmount + '\'' +
                ", buyerPayAmount='" + buyerPayAmount + '\'' +
                ", tradeStatus='" + tradeStatus + '\'' +
                ", create_by='" + create_by + '\'' +
                ", create_date=" + create_date +
                ", update_by='" + update_by + '\'' +
                ", update_date=" + update_date +
                ", extend1='" + extend1 + '\'' +
                ", extend2='" + extend2 + '\'' +
                ", extend3='" + extend3 + '\'' +
                '}';
    }
}
