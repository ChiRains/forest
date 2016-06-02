package com.qcloud.component.orderform.engine;

import java.util.List;
import com.qcloud.component.orderform.entity.AfterSaleOrder;
import com.qcloud.component.orderform.entity.ExchangeAfterSaleOrder;
import com.qcloud.component.orderform.entity.MerchantOrderEntity;
import com.qcloud.component.orderform.entity.OrderEntity;
import com.qcloud.component.orderform.entity.RefundAfterSaleOrder;
import com.qcloud.component.orderform.entity.ReturnAfterSaleOrder;

public interface AfterSaleSelecterService {

    List<AfterSaleOrder> listAfterSaleOrder(OrderEntity orderEntity);

    List<AfterSaleOrder> listAfterSaleOrder(MerchantOrderEntity merchantOrderEntity);

    ExchangeAfterSaleOrder getExchangeOrder(MerchantOrderEntity merchantOrderEntity, Long exchangeId);

    List<AfterSaleOrder> listExchangeOrder(MerchantOrderEntity merchantOrderEntity);

    ReturnAfterSaleOrder getReturnOrder(MerchantOrderEntity merchantOrderEntity, Long returnId);

    List<AfterSaleOrder> listReturnOrder(MerchantOrderEntity merchantOrderEntity);

    RefundAfterSaleOrder getRefundOrder(MerchantOrderEntity merchantOrderEntity, Long refundId);

    List<AfterSaleOrder> listRefundOrder(MerchantOrderEntity merchantOrderEntity);
}
