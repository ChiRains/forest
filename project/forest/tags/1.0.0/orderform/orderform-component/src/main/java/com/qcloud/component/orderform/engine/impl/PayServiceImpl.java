package com.qcloud.component.orderform.engine.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.qcloud.component.orderform.engine.OrderSelecterService;
import com.qcloud.component.orderform.engine.PayService;
import com.qcloud.component.orderform.entity.OrderEntity;
import com.qcloud.component.parameter.ParameterClient;
import com.qcloud.component.publicservice.PayClient;
import com.qcloud.component.publicservice.QWeiXinRequestPayResult;
import com.qcloud.pirates.core.env.ProjectInfo;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.Base64;
import com.qcloud.pirates.util.DateUtil;
import com.qcloud.pirates.util.EncryptUtil;

@Service
public class PayServiceImpl implements PayService {

    @Autowired
    PayClient                    payClient;

    @Autowired
    private OrderSelecterService orderSelecterService;

    @Autowired
    private ParameterClient      parameterClient;

    @Value("${pirates.pay.key}")
    private String               payKey     = "";

    private final String         DOMAIN_KEY = "domain-key";

    private Log                  logger     = LogFactory.getLog(getClass());

    @PostConstruct
    public void init() {

        logger.info("payKey encode " + payKey);
        payKey = Base64.decode(payKey);
        logger.info("payKey decode " + payKey);
        AssertUtil.assertNotEmpty(payKey, "支付加密字符串不能为空.");
    }

    @Override
    public Map<String, Object> requestWeiXinPay(String opneId, String ip, Long orderId, Date orderDate) {

        OrderEntity orderEntity = orderSelecterService.getOrder(orderId, orderDate);
        AssertUtil.assertNotNull(orderEntity, "订单不存在." + orderId);
        int wxCash = new Double(orderEntity.getCash() * 100).intValue();
        QWeiXinRequestPayResult result = null;
        String attach = String.valueOf(orderId) + "_" + DateUtil.date2String(orderDate, "yyyyMMddHHmmss");
        attach = encodeAttach(attach);
        if (ProjectInfo.isDev()) {
            result = payClient.getWeiXinPayClient().request(orderEntity.getOrderNumber(), attach, "支付订单" + orderEntity.getOrderNumber(), getNotifyUrl(orderEntity), ip, 1, orderEntity.getOrderDate(), 30, opneId);
        } else {
            result = payClient.getWeiXinPayClient().request(orderEntity.getOrderNumber(), attach, "支付订单" + orderEntity.getOrderNumber(), getNotifyUrl(orderEntity), ip, wxCash, orderEntity.getOrderDate(), 30, opneId);
        }
        return result.map();
    }

    private String encodeAttach(String attach) {

        return attach + "_" + EncryptUtil.md5(payKey + "@" + attach);
    }

    private String getNotifyUrl(OrderEntity orderEntity) {

        return "http://" + parameterClient.get(DOMAIN_KEY) + "/pay/paied.do";
    }

    @Override
    public boolean checkIsValidAttach(String attach) {

        if (attach == null) {
            return false;
        }
        String[] attachs = attach.split("_");
        if (attachs.length < 3) {
            return false;
        }
        String str = attachs[0] + "_" + attachs[1];
        return encodeAttach(str).equals(attach);
    }

    @Override
    public Map<String, Object> requestAlipayPay(Long orderId, Date orderDate) {

        OrderEntity orderEntity = orderSelecterService.getOrder(orderId, orderDate);
        AssertUtil.assertNotNull(orderEntity, "订单不存在." + orderId);
        int limitOrderTimeMinutes = payClient.getPayMinutes();
        Date endTime = DateUtil.addTime(orderEntity.getOrderDate(), limitOrderTimeMinutes);
        Date date = new Date();
        int less = new Long((endTime.getTime() - date.getTime()) / 1000).intValue();
        Map<String, Object> map = new HashMap<String, Object>();
        String attach = String.valueOf(orderId) + "_" + DateUtil.date2String(orderDate, "yyyyMMddHHmmss");
        map.put("out_trade_no", encodeAlipayAttach(attach));
        map.put("subject", orderEntity.getOrderNumber());
        map.put("body", "支付订单" + orderEntity.getOrderNumber());
        if (ProjectInfo.isDev()) {
            map.put("total_fee", 1);
        } else {
            map.put("total_fee", new Double(orderEntity.getCash() * 100).intValue());
        }
        map.put("notify_url", getAlipayNotifyUrl(orderEntity));
        map.put("service", "mobile.securitypay.pay");
        map.put("payment_type", "1");
        map.put("_input_charset", "utf-8");
        map.put("it_b_pay", less + "m");
        return map;
    }

    private String encodeAlipayAttach(String attach) {

        return attach + "_" + EncryptUtil.md5(payKey + "@" + attach);
    }

    private String getAlipayNotifyUrl(OrderEntity orderEntity) {

        return "http://" + parameterClient.get(DOMAIN_KEY) + "/pay/alipayPaied.do";
    }
}
