package play.wxpay;

import com.github.wxpay.sdk.WXPayConfig;
import org.apache.commons.codec.binary.Hex;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * 微信配置类
 * @author liuxiaoxue
 * @data 2018年12月21日17:41:20
 */
public class OurWxPayConfig implements WXPayConfig {
    private byte[] certData;

    public OurWxPayConfig() throws Exception {
//        String certPath = "E:/wxzs/apiclient_cert.p12";
        String certPath = "/usr/local/wxzs/apiclient_cert.p12";
        File file = new File(certPath);
        InputStream certStream = new FileInputStream(file);
        this.certData = new byte[(int) file.length()];
        certStream.read(this.certData);
        certStream.close();
    }

    /**
     * 获取 App ID
     *
     * @return App ID
     */
    @Override
    public String getAppID() {
        return "wx97b27c67bdb9fb3d";
    }

    /**
     * 获取 Mch ID
     *
     * @return Mch ID
     */
    @Override
    public String getMchID() {
        return "1521349591";
    }

    /**
     * 获取 API 密钥
     *
     * @return API密钥
     */
    @Override
    public String getKey() {
        return "W8uBtsYtm7xH6aduTGLyaAwOWIhbTTGk";
    }


    /**
     * 获取商户证书内容
     *
     * @return 商户证书内容
     */
    @Override
    public InputStream getCertStream() {
        ByteArrayInputStream certBis = new ByteArrayInputStream(this.certData);
        return certBis;
    }

    /**
     * HTTP(S) 连接超时时间，单位毫秒
     *
     * @return
     */
    @Override
    public int getHttpConnectTimeoutMs() {
        return 8000;
    }

    /**
     * HTTP(S) 读数据超时时间，单位毫秒
     *
     * @return
     */
    @Override
    public int getHttpReadTimeoutMs() {
        return 10000;
    }

}
