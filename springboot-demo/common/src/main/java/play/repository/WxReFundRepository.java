package play.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import play.entity.WxReFund;

/**
 * 微信退款记录
 */
public interface WxReFundRepository extends JpaRepository<WxReFund,Integer>{
    WxReFund findByOutTradeNo(String outTradeNo);
}
