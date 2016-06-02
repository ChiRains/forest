package com.qcloud.component.orderform.web.observer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.my.MyClient;
import com.qcloud.component.orderform.OrderObserver;
import com.qcloud.component.orderform.QOrderItem;

@Component
public class MerchandiseToEvaluationObserver implements OrderObserver<QOrderItem> {

    private Log      logger = LogFactory.getLog(getClass());

    @Autowired
    private MyClient myClient;

    @Override
    public void doNotify(QOrderItem t, int state, String[] variable) {

        logger.info("处理待评价记录：" + t.getOrder().getOrderNumber() + " " + t.getName());
        myClient.addMyToEvaluation(t.getOrder().getUserId(), t.getUnifiedMerchandiseId(), t.getDiscount(), t.getMerchantOrder().getId(), t.getId(), t.getOrder().getOrderDate(), t.getOrder().getOrderNumber());
    }
}
