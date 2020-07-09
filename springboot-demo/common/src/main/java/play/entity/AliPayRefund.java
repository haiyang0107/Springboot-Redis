package play.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * 支付宝退款记录
 */
@Entity(name = "t_alipay_trade_refund")
public class AliPayRefund {
    @Id
    @GeneratedValue
    private Integer id;
    @Column
    private String order_id;
    @Column
    private String code;
    @Column
    private String msg;
    @Column
    private String buyer_logon_id;
    @Column
    private String buyer_user_id;
    @Column
    private String fund_change;
    @Column
    private Date gmt_refund_pay;
    @Column(name = "out_trade_no")
    private String outTradeNo;
    @Column
    private String refund_fee;
    @Column
    private String send_back_fee;
    @Column
    private String trade_no;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getBuyer_logon_id() {
        return buyer_logon_id;
    }

    public void setBuyer_logon_id(String buyer_logon_id) {
        this.buyer_logon_id = buyer_logon_id;
    }

    public String getBuyer_user_id() {
        return buyer_user_id;
    }

    public void setBuyer_user_id(String buyer_user_id) {
        this.buyer_user_id = buyer_user_id;
    }

    public String getFund_change() {
        return fund_change;
    }

    public void setFund_change(String fund_change) {
        this.fund_change = fund_change;
    }

    public Date getGmt_refund_pay() {
        return gmt_refund_pay;
    }

    public void setGmt_refund_pay(Date gmt_refund_pay) {
        this.gmt_refund_pay = gmt_refund_pay;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getRefund_fee() {
        return refund_fee;
    }

    public void setRefund_fee(String refund_fee) {
        this.refund_fee = refund_fee;
    }

    public String getSend_back_fee() {
        return send_back_fee;
    }

    public void setSend_back_fee(String send_back_fee) {
        this.send_back_fee = send_back_fee;
    }

    public String getTrade_no() {
        return trade_no;
    }

    public void setTrade_no(String trade_no) {
        this.trade_no = trade_no;
    }

    @Override
    public String toString() {
        return "AliPayRefund{" +
                "id=" + id +
                ", order_id='" + order_id + '\'' +
                ", code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", buyer_logon_id='" + buyer_logon_id + '\'' +
                ", buyer_user_id='" + buyer_user_id + '\'' +
                ", fund_change='" + fund_change + '\'' +
                ", gmt_refund_pay=" + gmt_refund_pay +
                ", outTradeNo='" + outTradeNo + '\'' +
                ", refund_fee='" + refund_fee + '\'' +
                ", send_back_fee='" + send_back_fee + '\'' +
                ", trade_no='" + trade_no + '\'' +
                '}';
    }
}
