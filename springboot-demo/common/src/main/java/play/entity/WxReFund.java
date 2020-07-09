package play.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * 微信退款记录表
 */
@Entity(name = "t_wxrefund")
public class WxReFund {
    @Id
    @GeneratedValue
    private Integer id;//主键自增
    @Column
    private String appid;
    @Column
    private String mch_id;//商户号
    @Column
    private String nonce_str;//随机字符串
    @Column
    private String sign;//签名
    @Column
    private String result_code; //业务结果
    @Column
    private String err_code; //错误代码
    @Column
    private String err_code_des;//错误代码描述
    @Column
    private String fee_type; //标价金额
    @Column
    private Integer total_fee;//标价金额
    @Column
    private String transaction_id;//微信订单号
    @Column(name = "out_trade_no")
    private String outTradeNo;// 商户订单号 out_trade_no 是
    @Column
    private String out_refund_no;//商户退款单号
    @Column
    private String refund_id;//微信退款单号
    @Column
    private Integer refund_fee;//退款金额
    @Column
    private Integer cash_fee;//现金支付金额
    @Column
    private Date refund_data;//退款时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getResult_code() {
        return result_code;
    }

    public void setResult_code(String result_code) {
        this.result_code = result_code;
    }

    public String getErr_code() {
        return err_code;
    }

    public void setErr_code(String err_code) {
        this.err_code = err_code;
    }

    public String getErr_code_des() {
        return err_code_des;
    }

    public void setErr_code_des(String err_code_des) {
        this.err_code_des = err_code_des;
    }

    public String getFee_type() {
        return fee_type;
    }

    public void setFee_type(String fee_type) {
        this.fee_type = fee_type;
    }

    public Integer getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(Integer total_fee) {
        this.total_fee = total_fee;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getOut_refund_no() {
        return out_refund_no;
    }

    public void setOut_refund_no(String out_refund_no) {
        this.out_refund_no = out_refund_no;
    }

    public String getRefund_id() {
        return refund_id;
    }

    public void setRefund_id(String refund_id) {
        this.refund_id = refund_id;
    }

    public Integer getRefund_fee() {
        return refund_fee;
    }

    public void setRefund_fee(Integer refund_fee) {
        this.refund_fee = refund_fee;
    }

    public Integer getCash_fee() {
        return cash_fee;
    }

    public void setCash_fee(Integer cash_fee) {
        this.cash_fee = cash_fee;
    }

    public Date getRefund_data() {
        return refund_data;
    }

    public void setRefund_data(Date refund_data) {
        this.refund_data = refund_data;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    @Override
    public String toString() {
        return "WxReFund{" +
                "id=" + id +
                ", appid='" + appid + '\'' +
                ", mch_id='" + mch_id + '\'' +
                ", nonce_str='" + nonce_str + '\'' +
                ", sign='" + sign + '\'' +
                ", result_code='" + result_code + '\'' +
                ", err_code='" + err_code + '\'' +
                ", err_code_des='" + err_code_des + '\'' +
                ", fee_type='" + fee_type + '\'' +
                ", total_fee=" + total_fee +
                ", transaction_id='" + transaction_id + '\'' +
                ", outTradeNo='" + outTradeNo + '\'' +
                ", out_refund_no='" + out_refund_no + '\'' +
                ", refund_id='" + refund_id + '\'' +
                ", refund_fee=" + refund_fee +
                ", cash_fee=" + cash_fee +
                ", refund_data=" + refund_data +
                '}';
    }
}
