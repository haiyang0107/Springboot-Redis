package play.cntroller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import play.dto.PayDownlad;
import play.service.PayWxService;

import java.util.Map;

/**
 * 对账接口
 */
@Controller
@RestController
@RequestMapping(value = "/hotelApp/pay/statement")
public class PayStatementController {

    //定义一个全局的记录器，通过LoggerFactory获取
    private final static Logger logger = LoggerFactory.getLogger(PayStatementController.class);


    /**
     * 微信服务类
     */
    @Autowired
    private PayWxService payWxService;

    /**
     * 下载对账单
     * @return
     */
    @RequestMapping(value="/downloadbil")
    @ResponseBody
    public String downloadbil(PayDownlad payDownlad){
        payWxService.downloadbill(payDownlad);

        return "";
    }


}
