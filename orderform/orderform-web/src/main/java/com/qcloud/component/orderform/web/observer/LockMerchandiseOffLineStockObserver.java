package com.qcloud.component.orderform.web.observer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.orderform.OrderObserver;
import com.qcloud.component.orderform.QOrderItemDetail;
import com.qcloud.component.warehouse.WarehouseClient;

@Component
public class LockMerchandiseOffLineStockObserver implements OrderObserver<QOrderItemDetail> {

    private Log             logger = LogFactory.getLog(getClass());

    @Autowired
    private WarehouseClient warehouseClient;

    @Override
    public void doNotify(QOrderItemDetail t, int state, String[] variable) {

        logger.info("发货,减线下库存:" + t.getOrder().getOrderNumber() + " " + t.getName());
        warehouseClient.reduce(t.getMerchantOrder().getMerchantId(), t.getMerchantOrder().getStoreId(), t.getUnifiedMerchandiseId(), t.getOrderItem().getNumber());
    }
}
