package com.qcloud.component.orderform.engine;

import com.qcloud.component.orderform.entity.ExchangeAfterSaleOrder;
import com.qcloud.component.orderform.entity.ExchangeAfterSaleOrderItem;
import com.qcloud.component.orderform.entity.RefundAfterSaleOrder;
import com.qcloud.component.orderform.entity.RefundAfterSaleOrderItem;
import com.qcloud.component.orderform.entity.ReturnAfterSaleOrder;
import com.qcloud.component.orderform.entity.ReturnAfterSaleOrderItem;

public interface AfterSaleStateService {

    // 变更换货单状态
    boolean exchangeExchangeOrderState(ExchangeAfterSaleOrder exchangeAfterSaleOrder, int state);

    // 变更换货明细单状态
    boolean exchangeExchangeOrderItemState(ExchangeAfterSaleOrderItem exchangeAfterSaleOrderItem, int state);

    // 变更换货单状态
    boolean exchangeReturnOrderState(ReturnAfterSaleOrder returnAfterSaleOrder, int state);

    // 变更换货明细单状态
    boolean exchangeReturnOrderItemState(ReturnAfterSaleOrderItem returnAfterSaleOrderItem, int state);

    // 变更换货单状态
    boolean exchangeRefundOrderState(RefundAfterSaleOrder refundAfterSaleOrder, int state);

    // 变更换货明细单状态
    boolean exchangeRefundOrderItemState(RefundAfterSaleOrderItem refundAfterSaleOrderItem, int state);
}
