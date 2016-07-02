package com.qcloud.component.orderform.engine;

import java.util.Date;

public interface OrderStateService {

    boolean returnOrderState(Long orderId, Date orderDate, Long authentication);

    boolean returnSubOrderState(Long subOrderId, Date orderDate, Long authentication);

    boolean exchangeOrderState(Long orderId, Date orderDate, int state, Long authentication);

    boolean exchangeSubOrderState(Long subOrderId, Date orderDate, int state, Long authentication);

    boolean exchangeOrderItemState(Long orderItemId, Date orderDate, int state, Long authentication);

    boolean exchangeOrderItemDetailState(Long orderItemDetailId, Date orderDate, int state, Long authentication);
}
