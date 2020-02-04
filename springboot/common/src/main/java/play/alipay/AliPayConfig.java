package play.alipay;


import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;

/**
 * 配置公共参数
 * @author 胡海洋
 * @date 2016/10/29
 */
public final class AliPayConfig {
    /**
     * 应用号
     */
    public static String APP_ID = "2018121262524444";
    /**
     * 商户的私钥
     */
    public static String APP_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDfas5twe9Gna0P\n" +
            "Ml/tMK0FBqtUr64WjFHPhYYuDt2cHKM3zw3wfJwF70DxbWK1O5QKqlhEzqypkVjr\n" +
            "gWmgsarqbVm8GQVO1dKyFNuTf3vNlUmnDYXXOSI0fEpEpzZyrGLBd7xFElwtlxu2\n" +
            "/M5r6p3cON6X8Vu4kpRQhMjZuiM8or3g63rXURJPZPIvPiTzDP66v68wOfzqbUkM\n" +
            "lpAVDw70xp+JSRey+IwakuwyPw8ppayJyAXQrml3KQY0AVrL5Kg+wCa5gOXaQ3Dp\n" +
            "ZDu+QJ+rfxFU8YcTYb8iGc2FKbgzzWmP02tttiJ1G7OjfF22T1l7Et0szdHDsVLL\n" +
            "1WuwtQntAgMBAAECggEBAKB8k56KMfxS5W7GK9wxHAhBOy+RJwexck8UxkctjIlI\n" +
            "lQJQ7N10VYt5yvfiMIapLnXe2ZtLrwH7k5lQk/THy1Osi0ov2v0/YaNkkjpI2EDc\n" +
            "XVTvxNXOh8CWC693QghrxmDdRtODFc31/wWaYhlpJk58V4Fj8hQKzxlr2O914zv4\n" +
            "/2QsityBN8jl6tfvIa8uqXeiNl6ApjcwUCQHZfvHDPagWEQBKnC2SG2zqYKHhUB2\n" +
            "7m7QRAAjyfujrNzr+qW5mzmOL9C08mtEJD7/Io2KVWezqgDLaggJ6espOYcr6ZAM\n" +
            "KHW9XiNFjsrXPVkRbIfI6Q77i2ztj/S4ukVR9+YBjQECgYEA+lottwxKJm6kafLH\n" +
            "aXst6LjuVDitLGldxBooItlAFdnFiUZ62En5QikZTsNJswB11abKMlhbYjJxWqwx\n" +
            "xcPk6aAukn6Cvo/4csvIiSlCyTOmmjg3q3jvR1OEDqBXCJ6t2E+6XgMJei5M9MrM\n" +
            "8uwcHHFwYWsSdY+O9WCB+9p0Ui0CgYEA5HUS6qx+KBofoEnwZYEU0z/1Wq4qXkbJ\n" +
            "A+HMoJrBCB2oAP3OpzLU8XJsaR1GjI2Gp/FT09OjgNHIq+9jLK33vugf+U4leAPB\n" +
            "QNfnMyhPcG5jgMtr9JaG1q59W792VHyudNDv7+bYWFZZVE+UqNA6d1wUsufGhlns\n" +
            "hjmYVBObLsECgYBu/ZHs6E7rcPwFWLQdyJWW6n4G6sWjvebrLOmEYaquxUh1TsDh\n" +
            "qRIPyebFa4D+EgMrwHE+FrwU8eYdW+AJHIsdJH3hRqMyVuPACSaT4K4SQ4rmLmxH\n" +
            "4iq7BYSzc1SCps7N6rLmQRRvjZ1inZJ1VEY2Ofinx+98HTQ1Bx85iCUv1QKBgDtJ\n" +
            "5s7gVR0zKmH+zlSJW043mwUdjgFJfYF0aBaGEvJ4P9GCDM93cav3dgsRfe2cDQYo\n" +
            "qwieNCCKUPnN9n7x5wm0VBfvH+o1j0G0xluQao1TGJ+kKzAeoH+2aPIWji7+7IeE\n" +
            "ccBguGS0hBpNTNdCsumnC/giDnNvguKSr+Wh8PQBAoGAE4iVXZoh6Z10vvn8tc8z\n" +
            "+GW6qLbqhnYL920I3N5TcBZmJtD2WbypE7TabebcEoYfM3VaQ9ktqa7H7HGsk9kz\n" +
            "0QPkDAoAHEGEv33KW4UWTvErAVVju/2jGI5ti3iYXtHQOJIPDve7oQyQznGq34Rs\n" +
            "QD8MaXo5bPLXMG/o5fLI68A=";

    /**
     * 支付宝公钥
     */
    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA1Vkp85VN7EaQBC4sWLvL9BcrRnFEGiLERLYBT3RZlMh9JalV/ENcmkf12y1dC/FhD5f4vs7JxOiQaA4SnZMDdf6Qf2bpW2OG32qgLLPum0qgCXf5dqIDhW+teLHSRZNviWgZT5+ZeNrW1ly9QYWC+xiMmClzLcFnHGyd9JoTrmSb39DS2eJGxDvAhvqd3eevIeeZBWif7Hbz7VNLtmk8JPnB7EPjFnroJVbbIhfiWxq6EzzhlKhfc5uq3HIGRPT2WaDZDgTcF2+aZA2U8HKKCZpjwAkhC+vKWST/tOKARDupJxrw0Iyj8tFEMIJ3dboe/1/1StFl1Ij7gWE1hqyX0wIDAQAB";
    /**
     * 支付宝网关地址
     */
    private static String GATEWAY = "https://openapi.alipay.com/gateway.do";
    /**
     * 成功付款回调
     */
    public static String PAY_NOTIFY = "http://101.254.183.242:11102/hotel-admin/a/hotelApp/pay/notify";
    /**
     * 支付成功回调
     */
    public static String REFUND_NOTIFY = "http://101.254.183.242:11102/hotel-admin/a/hotelApp/pay/notify";
    /**
     * 前台通知地址
     */
    public static String RETURN_URL = "http://101.254.183.242:11102/hotel-admin/a/hotelApp/pay/notify";
    /**
     * 参数类型
     */
    public static String PARAM_TYPE = "json";
    /**
     * 编码
     */
    public static String CHARSET = "UTF-8";
    /**
     * 成功标识
     */
    public static final String SUCCESS_REQUEST = "TRADE_SUCCESS";
    /**
     * 交易关闭回调(当该笔订单全部退款完毕,则交易关闭)
     */
    public static final String TRADE_CLOSED = "TRADE_CLOSED";
    /**
     * 收款方账号
     */
    public static final String SELLER_ID = "2167124320@qq.com";
    /**
     * 支付宝请求客户端入口
     */
    private volatile static AlipayClient alipayClient = null;

    /**
     * 加密类型
     */
    public static String SIGNTYPE = "RSA2";

    /**
     * 不可实例化
     */
    private AliPayConfig(){};

    /**
     * 双重锁单例
     * @return 支付宝请求客户端实例
     */
    public static AlipayClient getInstance(){
        if (alipayClient == null){
            synchronized (AliPayConfig.class){
                if (alipayClient == null){
                    alipayClient = new DefaultAlipayClient(GATEWAY,APP_ID,APP_PRIVATE_KEY,PARAM_TYPE,CHARSET,ALIPAY_PUBLIC_KEY,SIGNTYPE);
                }
            }
        }
        return alipayClient;
    }

}
