package com.qcloud.component.orderform.web.observer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.my.MyClient;
import com.qcloud.component.orderform.OrderObserver;
import com.qcloud.component.orderform.QMerchantOrder;
import com.qcloud.component.orderform.QOrder;
import com.qcloud.component.orderform.service.OrderConfigService;

@Component
public class AddUserOrderFormObserver implements OrderObserver<Object> {

    private Log                logger = LogFactory.getLog(getClass());

    @Autowired
    private MyClient           myClient;

    @Autowired
    private OrderConfigService orderConfigService;

    @Override
    public void doNotify(Object t, int state, String[] variable) {

        if (t instanceof QOrder) {
            QOrder o = (QOrder) t;
            doEvent(o, state);
        } else if (t instanceof QMerchantOrder) {
            QMerchantOrder m = (QMerchantOrder) t;
            doEvent(m, state);
        }
    }

    private void doEvent(QOrder t, int state) {

        logger.info("添加用户订单:" + t.getOrderNumber());
        myClient.addMyOrderForm(t.getUserId(), orderConfigService.getNormalPersonalOrderState(t.getState()), t.getOrderDate(), t.getId());
    }

    private void doEvent(QMerchantOrder m, int state) {

        logger.info("添加用户订单:" + m.getOrderNumber());
        myClient.addMyOrderForm(m.getOrder().getUserId(), orderConfigService.getNormalPersonalOrderState(state), m.getOrder().getOrderDate(), m.getOrder().getId(), m.getId());
    }
}
