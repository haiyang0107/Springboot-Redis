package play.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import play.entity.AlipayNotify;

/**
 * 支付宝交易记录
 */
public interface AliPayRepository extends JpaRepository<AlipayNotify,Integer> {
    AlipayNotify findByOutTradeNo(String outTradeNo);
}
