package com.qcloud.component.orderform.engine;

import java.util.Date;
import java.util.Map;

public interface PayService {

    Map<String, Object> requestWeiXinPay(String opneId, String ip, Long orderId, Date orderDate);

    Map<String, Object> requestAlipayPay(Long orderId, Date orderDate);

    public boolean checkIsValidAttach(String attach);
}
