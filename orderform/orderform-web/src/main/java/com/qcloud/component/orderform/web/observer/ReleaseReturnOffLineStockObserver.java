package com.qcloud.component.orderform.web.observer;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.orderform.OrderObserver;
import com.qcloud.component.orderform.QAfterSaleItem;
import com.qcloud.component.orderform.QAfterSaleOrder;
import com.qcloud.component.orderform.QOrderItem;
import com.qcloud.component.warehouse.WarehouseClient;

@Component
public class ReleaseReturnOffLineStockObserver implements OrderObserver<QAfterSaleOrder> {

    private Log             logger = LogFactory.getLog(getClass());

    @Autowired
    private WarehouseClient warehouseClient;

    @Override
    public void doNotify(QAfterSaleOrder t, int state, String[] variable) {

        List<QAfterSaleItem> list = t.listItem();
        for (QAfterSaleItem afterSaleItem : list) {
            QOrderItem orderItem = afterSaleItem.getOrderItem();
            logger.info("退货,补上线下库存:" + orderItem.getOrder().getOrderNumber() + " " + orderItem.getName());
            warehouseClient.reduce(t.getMerchantOrder().getMerchantId(), t.getMerchantOrder().getStoreId(), orderItem.getUnifiedMerchandiseId(), orderItem.getNumber());
        }
    }
}
