package com.qcloud.component.orderform.web.observer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.goods.CommoditycenterClient;
import com.qcloud.component.orderform.OrderObserver;
import com.qcloud.component.orderform.QOrderItem;

@Component
public class ReleaseMerchandiseOnLineStockObserver implements OrderObserver<QOrderItem> {

    private Log                   logger = LogFactory.getLog(getClass());

    @Autowired
    private CommoditycenterClient commoditycenterClient;

    @Override
    public void doNotify(QOrderItem t, int state, String[] variable) {

        logger.info("订单 释放库存" + t.getName() + " " + t.getNumber() + " " + state);
        //
        commoditycenterClient.lockOnlineStock(t.getUnifiedMerchandiseId(), t.getNumber());
    }
}
