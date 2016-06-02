package com.qcloud.component.orderform.web.observer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.orderform.OrderObserver;
import com.qcloud.component.orderform.QOrder;
import com.qcloud.component.personalcenter.PersonalcenterClient;
import com.qcloud.component.personalcenter.WealthType;

@Component
public class OrderSuccessDispatchIntegralObserver implements OrderObserver<QOrder> {

    private Log                  logger = LogFactory.getLog(getClass());

    @Autowired
    private PersonalcenterClient personalcenterClient;

    @Override
    public void doNotify(QOrder t, int state, String[] variable) {

        logger.info("促成交易,赠送积分:" + t.getOrderNumber());
        personalcenterClient.calculateMyWealth(t.getUserId(), WealthType.INTEGRAL, t.getCash() - t.getPostage(), true, "订单：" + t.getOrderNumber() + ",现金消费：" + (t.getCash() - t.getPostage()));
    }
}
