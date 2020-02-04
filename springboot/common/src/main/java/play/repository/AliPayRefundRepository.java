package play.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import play.entity.AliPayRefund;

/**
 * 支付宝交易记录
 */
public interface AliPayRefundRepository extends JpaRepository<AliPayRefund,Integer> {
    AliPayRefund findByOutTradeNo(String outTradeNo);
}
