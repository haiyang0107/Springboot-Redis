package play.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * 微信支付记录表
 * @author liuxiaoxue
 * @data 2018年12月18日17:07:02
 */
@Entity(name = "t_wxpay_notify_links")
public class WxPayNotfy {

    @Id
    @GeneratedValue
    private Integer id;//主键自增
    @Column
    private String order_id;//系统订单号ID
    @Column
    private String appid;// 应用ID appid 是
    @Column
    private String mch_id;// 商户号 mch_id 是
    @Column
    private String device_info;// 设备号 device_info 否
    @Column
    private String nonce_str;// 随机字符串 nonce_str 是
    @Column
    private String sign;// 签名 sign 是
    @Column
    private String body;// 商品描述 body 是
    @Column
    private String detail;// 商品详情 detail 否
    @Column
    private String attach;// 附加数据 attach 否
    @Column(name = "out_trade_no")
    private String outTradeNo;// 商户订单号 out_trade_no 是
    @Column
    private String fee_type;// 货币类型 fee_type 否 默认人民币：CNY
    @Column
    private Integer total_fee;// 总金额 total_fee 是 单位分
    @Column
    private String spbill_create_ip;// 终端IP spbill_create_ip 是
    @Column
    private Date time_start;// 交易起始时间 time_start 否
    @Column
    private Date time_expire;// 交易结束时间 time_expire 否
    @Column
    private String goods_tag;// 商品标记 goods_tag 否
    @Column
    private String notify_url;// 通知地址 notify_url 是
    @Column
    private String trade_type;// 交易类型 trade_type 是
    @Column
    private String limit_pay;// 指定支付方式 limit_pay 否
    @Column
    private String result_code;//业务结果 result_code 是 SUCCESS/FAIL
    @Column
    private String return_msg;//返回消息
    @Column
    private String err_code;//错误代码 err_code 否
    @Column
    private String err_code_des;//错误代码描述 err_code_des 否
    @Column
    private String prepay_id;//预支付交易会话标识 prepay_id 是
    @Column
    private Date timestamp;//支付返回时间戳
    @Column
    private String open_id;// 用户标识 否
    @Column
    private String wx_status; //支付状态
    @Column
    private String notify_nonce_str;
    @Column
    private String notify_result_code;
    @Column
    private String notify_sign;
    @Column
    private String notify_transaction_id;

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

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getDevice_info() {
        return device_info;
    }

    public void setDevice_info(String device_info) {
        this.device_info = device_info;
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

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
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

    public String getSpbill_create_ip() {
        return spbill_create_ip;
    }

    public void setSpbill_create_ip(String spbill_create_ip) {
        this.spbill_create_ip = spbill_create_ip;
    }

    public Date getTime_start() {
        return time_start;
    }

    public void setTime_start(Date time_start) {
        this.time_start = time_start;
    }

    public Date getTime_expire() {
        return time_expire;
    }

    public void setTime_expire(Date time_expire) {
        this.time_expire = time_expire;
    }

    public String getGoods_tag() {
        return goods_tag;
    }

    public void setGoods_tag(String goods_tag) {
        this.goods_tag = goods_tag;
    }

    public String getNotify_url() {
        return notify_url;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public String getLimit_pay() {
        return limit_pay;
    }

    public void setLimit_pay(String limit_pay) {
        this.limit_pay = limit_pay;
    }

    public String getResult_code() {
        return result_code;
    }

    public void setResult_code(String result_code) {
        this.result_code = result_code;
    }

    public String getReturn_msg() {
        return return_msg;
    }

    public void setReturn_msg(String return_msg) {
        this.return_msg = return_msg;
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

    public String getPrepay_id() {
        return prepay_id;
    }

    public void setPrepay_id(String prepay_id) {
        this.prepay_id = prepay_id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getOpen_id() {
        return open_id;
    }

    public void setOpen_id(String open_id) {
        this.open_id = open_id;
    }

    public String getWx_status() {
        return wx_status;
    }

    public void setWx_status(String wx_status) {
        this.wx_status = wx_status;
    }

    public String getNotify_nonce_str() {
        return notify_nonce_str;
    }

    public void setNotify_nonce_str(String notify_nonce_str) {
        this.notify_nonce_str = notify_nonce_str;
    }

    public String getNotify_result_code() {
        return notify_result_code;
    }

    public void setNotify_result_code(String notify_result_code) {
        this.notify_result_code = notify_result_code;
    }

    public String getNotify_sign() {
        return notify_sign;
    }

    public void setNotify_sign(String notify_sign) {
        this.notify_sign = notify_sign;
    }

    public String getNotify_transaction_id() {
        return notify_transaction_id;
    }

    public void setNotify_transaction_id(String notify_transaction_id) {
        this.notify_transaction_id = notify_transaction_id;
    }

    @Override
    public String toString() {
        return "WxPayNotfy{" +
                "id=" + id +
                ", order_id='" + order_id + '\'' +
                ", appid='" + appid + '\'' +
                ", mch_id='" + mch_id + '\'' +
                ", device_info='" + device_info + '\'' +
                ", nonce_str='" + nonce_str + '\'' +
                ", sign='" + sign + '\'' +
                ", body='" + body + '\'' +
                ", detail='" + detail + '\'' +
                ", attach='" + attach + '\'' +
                ", outTradeNo='" + outTradeNo + '\'' +
                ", fee_type='" + fee_type + '\'' +
                ", total_fee=" + total_fee +
                ", spbill_create_ip='" + spbill_create_ip + '\'' +
                ", time_start=" + time_start +
                ", time_expire=" + time_expire +
                ", goods_tag='" + goods_tag + '\'' +
                ", notify_url='" + notify_url + '\'' +
                ", trade_type='" + trade_type + '\'' +
                ", limit_pay='" + limit_pay + '\'' +
                ", result_code='" + result_code + '\'' +
                ", return_msg='" + return_msg + '\'' +
                ", err_code='" + err_code + '\'' +
                ", err_code_des='" + err_code_des + '\'' +
                ", prepay_id='" + prepay_id + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", open_id='" + open_id + '\'' +
                ", wx_status='" + wx_status + '\'' +
                ", notify_nonce_str='" + notify_nonce_str + '\'' +
                ", notify_result_code='" + notify_result_code + '\'' +
                ", notify_sign='" + notify_sign + '\'' +
                ", notify_transaction_id='" + notify_transaction_id + '\'' +
                '}';
    }
}
