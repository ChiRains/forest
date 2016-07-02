package com.qcloud.component.orderform.web.observer;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.goods.CommoditycenterClient;
import com.qcloud.component.orderform.OrderObserver;
import com.qcloud.component.orderform.QAfterSaleItem;
import com.qcloud.component.orderform.QAfterSaleOrder;
import com.qcloud.component.orderform.QOrderItem;

@Component
public class ReleaseRefundOnLineStockObserver implements OrderObserver<QAfterSaleOrder> {

    private Log                   logger = LogFactory.getLog(getClass());

    @Autowired
    private CommoditycenterClient commoditycenterClient;

    @Override
    public void doNotify(QAfterSaleOrder t, int state, String[] variable) {

        List<QAfterSaleItem> list = t.listItem();
        for (QAfterSaleItem qAfterSaleOrderItem : list) {
            QOrderItem orderItem = qAfterSaleOrderItem.getOrderItem();
            //
            logger.info("退款 释放库存" + orderItem.getName() + " " + orderItem.getNumber());
            //
            commoditycenterClient.lockOnlineStock(orderItem.getUnifiedMerchandiseId(), orderItem.getNumber());
        }
    }
}
