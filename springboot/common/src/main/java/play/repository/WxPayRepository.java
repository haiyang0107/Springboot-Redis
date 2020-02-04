package play.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import play.entity.WxPayNotfy;

/**
 * 微信交易记录
 */
public interface WxPayRepository extends JpaRepository<WxPayNotfy,Integer> {
//    WxPayNotfy findByOut_trade_no(String outTradeNo);
    WxPayNotfy findByOutTradeNo(String outTradeNo);
}
