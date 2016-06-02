package com.qcloud.component.orderform.service;

import com.qcloud.component.orderform.entity.ExchangeAfterSaleOrder;
import com.qcloud.component.orderform.entity.MerchantOrderEntity;
import com.qcloud.component.orderform.entity.OrderEntity;
import com.qcloud.component.orderform.entity.OrderItemDetailEntity;
import com.qcloud.component.orderform.entity.OrderItemEntity;
import com.qcloud.component.orderform.entity.RefundAfterSaleOrder;
import com.qcloud.component.orderform.entity.ReturnAfterSaleOrder;

public interface OrderObserverService {

    void doNotify(OrderEntity order, int state);

    void doNotify(MerchantOrderEntity merchantOrder, int state);

    void doNotify(OrderItemEntity orderItem, int state);

    void doNotify(OrderItemDetailEntity orderItemDetail, int state);

    //
    void doNotify(ExchangeAfterSaleOrder afterSaleOrder, int state);

    void doNotify(RefundAfterSaleOrder afterSaleOrder, int state);

    void doNotify(ReturnAfterSaleOrder afterSaleOrder, int state);
}
