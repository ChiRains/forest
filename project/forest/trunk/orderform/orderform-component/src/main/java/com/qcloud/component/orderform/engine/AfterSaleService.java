package com.qcloud.component.orderform.engine;

import java.util.List;
import com.qcloud.component.orderform.entity.AfterSaleDetail;
import com.qcloud.component.orderform.entity.AfterSaleItem;
import com.qcloud.component.orderform.entity.ExchangeAfterSaleOrder;
import com.qcloud.component.orderform.entity.OrderEntity;
import com.qcloud.component.orderform.entity.OrderItemDetailEntity;
import com.qcloud.component.orderform.entity.OrderItemEntity;
import com.qcloud.component.orderform.entity.RefundAfterSaleOrder;
import com.qcloud.component.orderform.entity.ReturnAfterSaleOrder;

// 退换货单,多个商家的商品一起退,则做拆单处理
public interface AfterSaleService {

    // 重新申请
    boolean reApplyReturnOrder(ReturnAfterSaleOrder returnAfterSaleOrder);

    // 重新申请
    boolean reApplyRefundOrder(RefundAfterSaleOrder refundAfterSaleOrder);

    // 重新申请
    boolean reApplyExchangeOrder(ExchangeAfterSaleOrder exchangeAfterSaleOrder);

    // 申请换货
    boolean applyExchange(OrderEntity orderEntity, List<OrderItemDetailEntity> list, String explain, String reason);

    // 申请退货
    boolean applyReturn(OrderEntity orderEntity, List<OrderItemEntity> list, String explain, String reason);

    // 申请退款
    boolean applyRefund(OrderEntity orderEntity, List<OrderItemEntity> list, String explain, String reason);

    // 申请换货
    boolean applyExchange(OrderEntity orderEntity, List<AfterSaleDetail> list);

    // 申请退货
    boolean applyReturn(OrderEntity orderEntity, List<AfterSaleItem> list);

    // 申请退货
    boolean applyRefund(OrderEntity orderEntity, List<AfterSaleItem> list);
}
