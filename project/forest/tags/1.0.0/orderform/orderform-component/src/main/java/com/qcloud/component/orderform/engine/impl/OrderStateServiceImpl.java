package com.qcloud.component.orderform.engine.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.qcloud.component.orderform.engine.OrderSelecterService;
import com.qcloud.component.orderform.engine.OrderStateService;
import com.qcloud.component.orderform.entity.MerchantOrderEntity;
import com.qcloud.component.orderform.entity.OrderEntity;
import com.qcloud.component.orderform.entity.OrderItemDetailEntity;
import com.qcloud.component.orderform.entity.OrderItemEntity;
import com.qcloud.component.orderform.model.CollectOrder;
import com.qcloud.component.orderform.model.OrderItem;
import com.qcloud.component.orderform.model.OrderItemDetail;
import com.qcloud.component.orderform.model.SubOrder;
import com.qcloud.component.orderform.service.CollectOrderService;
import com.qcloud.component.orderform.service.OrderConfigService;
import com.qcloud.component.orderform.service.OrderItemDetailService;
import com.qcloud.component.orderform.service.OrderItemService;
import com.qcloud.component.orderform.service.SubOrderService;
import com.qcloud.pirates.util.AssertUtil;

@Service
public class OrderStateServiceImpl implements OrderStateService {

    @Autowired
    private CollectOrderService    collectOrderService;

    @Autowired
    private SubOrderService        subOrderService;

    @Autowired
    private OrderItemService       orderItemService;

    @Autowired
    private OrderItemDetailService orderItemDetailService;

    @Autowired
    private OrderSelecterService   orderSelecterService;

    @Autowired
    private OrderConfigService     orderConfigService;

    private Set<String>            onSet = new HashSet<String>();

    @Override
    public boolean returnOrderState(Long orderId, Date orderDate, Long authentication) {

        CollectOrder collectOrder = collectOrderService.get(orderId, orderDate);
        AssertUtil.assertNotNull(collectOrder, "订单不存在." + orderId);
        boolean ok = check(collectOrder.getState(), collectOrder.getPrestate());
        AssertUtil.assertTrue(ok, "订单状态跳转有误." + collectOrder.getState() + "==》" + collectOrder.getPrestate());
        return exchangeOrderState(orderId, orderDate, collectOrder.getPrestate(), authentication);
    }

    @Override
    public boolean returnSubOrderState(Long subOrderId, Date orderDate, Long authentication) {

        SubOrder subOrder = subOrderService.get(subOrderId, orderDate);
        AssertUtil.assertNotNull(subOrder, "订单不存在." + subOrderId);
        boolean ok = check(subOrder.getState(), subOrder.getPrestate());
        AssertUtil.assertTrue(ok, "订单状态跳转有误." + subOrder.getState() + "==》" + subOrder.getPrestate());
        return exchangeSubOrderState(subOrderId, orderDate, subOrder.getPrestate(), authentication);
    }

    @Transactional
    public boolean exchangeOrderState(Long orderId, Date orderDate, int state, Long authentication) {

        OrderEntity orderEntity = orderSelecterService.getOrder(orderId, orderDate);
        try {
            if (onSet.add(orderEntity.getOrderNumber())) {
                AssertUtil.assertNotNull(orderEntity, "订单不存在." + orderId);
                final int currentState = orderEntity.getState();
                boolean ok = check(currentState, state);
                AssertUtil.assertTrue(ok, "订单状态跳转有误." + currentState + "==》" + state);
                updateState(orderEntity, state);
                return true;
            } else {
                return false;
            }
        } finally {
            onSet.remove(orderEntity.getOrderNumber());
        }
    }

    private boolean updateState(OrderEntity orderEntity, int state) {

        List<MerchantOrderEntity> list = orderEntity.getEntityList();
        for (MerchantOrderEntity merchantOrder : list) {
            updateState(merchantOrder, state);
        }
        updateOrderState(orderEntity, state);
        orderEntity.setState(state);
        return true;
    }

    private boolean updateOrderState(OrderEntity orderEntity, int state) {

        return collectOrderService.updateState(orderEntity, state);
    }

    @Transactional
    @Override
    public boolean exchangeSubOrderState(Long subOrderId, Date orderDate, int state, Long authentication) {

        SubOrder subOrder = subOrderService.get(subOrderId, orderDate);
        try {
            if (onSet.add(subOrder.getOrderNumber())) {
                AssertUtil.assertNotNull(subOrder, "子订单不存在." + subOrderId);
                OrderEntity orderEntity = orderSelecterService.getOrder(subOrder.getOrderId(), orderDate);
                MerchantOrderEntity merchantOrderEntity = orderEntity.getMerchantOrder(subOrderId);
                final int currentState = merchantOrderEntity.getState();
                boolean ok = check(currentState, state);
                AssertUtil.assertTrue(ok, "子订单状态跳转有误." + currentState + "==》" + state);
                updateState(merchantOrderEntity, state);
                updateParent(merchantOrderEntity);
                return true;
            } else {
                return false;
            }
        } finally {
            onSet.remove(subOrder.getOrderNumber());
        }
    }

    private boolean updateParent(MerchantOrderEntity merchantOrderEntity) {

        int state = merchantOrderEntity.getState();
        OrderEntity orderEntity = merchantOrderEntity.getOrder();
        List<MerchantOrderEntity> subOrderList = orderEntity.getEntityList();
        boolean update = true;
        for (MerchantOrderEntity merchantOrder : subOrderList) {
            if (merchantOrder.getState() < state) {
                update = false;
                break;
            }
        }
        if (update) {
            updateOrderState(orderEntity, state);
            orderEntity.setState(state);
        }
        return true;
    }

    private boolean updateState(MerchantOrderEntity merchantOrderEntity, int state) {

        List<OrderItemEntity> orderItemList = merchantOrderEntity.getEntityList();
        for (OrderItemEntity orderItem : orderItemList) {
            updateState(orderItem, state);
        }
        updateSubOrderState(merchantOrderEntity, state);
        merchantOrderEntity.setState(state);
        return true;
    }

    private boolean updateSubOrderState(MerchantOrderEntity merchantOrderEntity, int state) {

        return subOrderService.updateState(merchantOrderEntity, state);
    }

    @Transactional
    @Override
    public boolean exchangeOrderItemState(Long orderItemId, Date orderDate, int state, Long authentication) {

        OrderItem orderItem = orderItemService.get(orderItemId, orderDate);
        AssertUtil.assertNotNull(orderItem, "订单项不存在." + orderItemId);
        OrderEntity orderEntity = orderSelecterService.getOrder(orderItem.getOrderId(), orderDate);
        OrderItemEntity orderItemEntity = orderEntity.getMerchantOrder(orderItem.getSubOrderId()).getOrderItem(orderItemId);
        final int currentState = orderItemEntity.getState();
        boolean ok = check(currentState, state);
        AssertUtil.assertTrue(ok, "订单项状态跳转有误." + currentState + "==》" + state);
        updateState(orderItemEntity, state);
        updateParent(orderItemEntity);
        return true;
    }

    private boolean updateParent(OrderItemEntity orderItemEntity) {

        int state = orderItemEntity.getState();
        MerchantOrderEntity merchantOrderEntity = orderItemEntity.getMerchantOrder();
        List<OrderItemEntity> orderItemList = merchantOrderEntity.getEntityList();
        boolean update = true;
        for (OrderItemEntity orderItem : orderItemList) {
            if (orderItem.getState() < state) {
                update = false;
                break;
            }
        }
        if (update) {
            updateSubOrderState(merchantOrderEntity, state);
            merchantOrderEntity.setState(state);
            updateParent(merchantOrderEntity);
        }
        return true;
    }

    private boolean updateState(OrderItemEntity orderItemEntity, int state) {

        List<OrderItemDetailEntity> itemDetailList = orderItemEntity.getEntityList();
        for (OrderItemDetailEntity orderItemDetail : itemDetailList) {
            updateState(orderItemDetail, state);
        }
        updateOrderItemState(orderItemEntity, state);
        orderItemEntity.setState(state);
        return true;
    }

    private boolean updateOrderItemState(OrderItemEntity orderItemEntity, int state) {

        return orderItemService.updateState(orderItemEntity, state);
    }

    @Transactional
    @Override
    public boolean exchangeOrderItemDetailState(Long orderItemDetailId, Date orderDate, int state, Long authentication) {

        OrderItemDetail orderItemDetail = orderItemDetailService.get(orderItemDetailId, orderDate);
        AssertUtil.assertNotNull(orderItemDetail, "订单明细不存在." + orderItemDetailId);
        OrderEntity orderEntity = orderSelecterService.getOrder(orderItemDetail.getOrderId(), orderDate);
        OrderItemDetailEntity orderItemDetailEntity = orderEntity.getMerchantOrder(orderItemDetail.getSubOrderId()).getOrderItem(orderItemDetail.getOrderItemId()).getOrderItemDetail(orderItemDetail.getId());
        final int currentState = orderItemDetailEntity.getState();
        boolean ok = check(currentState, state);
        AssertUtil.assertTrue(ok, "订单明细状态跳转有误." + currentState + "==》" + state);
        updateState(orderItemDetailEntity, state);
        updateParent(orderItemDetailEntity);
        return true;
    }

    private boolean updateParent(OrderItemDetailEntity detail) {

        int state = detail.getState();
        OrderItemEntity orderItemEntity = detail.getOrderItem();
        List<OrderItemDetailEntity> detailList = orderItemEntity.getEntityList();
        boolean update = true;
        for (OrderItemDetailEntity orderItemDetail : detailList) {
            if (orderItemDetail.getState() < state) {
                update = false;
                break;
            }
        }
        if (update) {
            updateOrderItemState(orderItemEntity, state);
            orderItemEntity.setState(state);
            updateParent(orderItemEntity);
        }
        return true;
    }

    private boolean updateState(OrderItemDetailEntity orderItemDetailEntity, int state) {

        orderItemDetailService.updateState(orderItemDetailEntity, state);
        orderItemDetailEntity.setState(state);
        return true;
    }

    private boolean check(int currentState, int newState) {

        List<int[]> normalStateChainList = orderConfigService.normalChain();
        for (int[] chain : normalStateChainList) {
            for (int index = 0; index < chain.length; index++) {
                if (currentState == chain[index] && index + 1 < chain.length && chain[index + 1] == newState) {
                    return true;
                }
            }
        }
        return false;
    }
    // private static final List<int[]> normalStateChainList = new ArrayList<int[]>();
    // static {
    // int[] invalidOrderChain = new int[] { OrderStateType.NORMAL_TOPAY.getKey(), OrderStateType.NORMAL_INVALID.getKey()};
    // int[] cancelOrderChain = new int[] { OrderStateType.NORMAL_TOPAY.getKey(), OrderStateType.NORMAL_CANCEL_ORDER.getKey()};
    // int[] cancelPaidChain = new int[] { OrderStateType.NORMAL_TOPAY.getKey(), OrderStateType.NORMAL_PAID.getKey(), OrderStateType.NORMAL_CANCEL_PAID.getKey()};
    // int[] cancelConfirmChain = new int[] { OrderStateType.NORMAL_TOPAY.getKey(), OrderStateType.NORMAL_PAID.getKey(), OrderStateType.NORMAL_CONFIRM_ORDER.getKey(), OrderStateType.NORMAL_CANCEL_PAID.getKey()};
    // int[] failChain = new int[] { OrderStateType.NORMAL_TOPAY.getKey(), OrderStateType.NORMAL_PAID.getKey(), OrderStateType.NORMAL_CONFIRM_ORDER.getKey(), OrderStateType.NORMAL_SHIPPED.getKey(), OrderStateType.NORMAL_SIGN.getKey(), OrderStateType.NORMAL_TRADE_FAIL.getKey()};
    // int[] normalChain = new int[] { OrderStateType.NORMAL_TOPAY.getKey(), OrderStateType.NORMAL_PAID.getKey(), OrderStateType.NORMAL_CONFIRM_ORDER.getKey(), OrderStateType.NORMAL_SHIPPED.getKey(), OrderStateType.NORMAL_SIGN.getKey(), OrderStateType.NORMAL_TRADE_SUCCESS.getKey()};
    // normalStateChainList.add(normalChain);
    // normalStateChainList.add(failChain);
    // normalStateChainList.add(invalidOrderChain);
    // normalStateChainList.add(cancelOrderChain);
    // normalStateChainList.add(cancelConfirmChain);
    // normalStateChainList.add(cancelPaidChain);
    // }
    // @Override
    // public boolean invalid() {
    //
    // AutoChanger invalid = new AutoChanger() {
    //
    // @Override
    // public int before(Long orderId, Date orderDate) {
    //
    // return orderConfigService.getInvalidOrderState();
    // }
    //
    // @Override
    // public boolean done(Long orderId, Date orderDate, int target) {
    //
    // return true;
    // }
    // };
    // int limitOrderTimeMinutes = payClient.getPayMinutes();
    // Date[] dates = collectOrderService.getDatesByLatelyMinutes(limitOrderTimeMinutes);
    // if (dates.length == 1) {
    // autoChange(dates[0], dates[0], orderConfigService.getInitOrderState(), invalid);
    // } else if (dates.length == 2) {
    // autoChange(dates[0], dates[1], orderConfigService.getInitOrderState(), invalid);
    // autoChange(dates[0], dates[0], orderConfigService.getInitOrderState(), invalid);
    // }
    // return true;
    // }
    //
    // @Override
    // public boolean sign() {
    //
    // // TODO 自动签收时间
    // int day = 1;
    // int latelyMinutes = day * 24 * 60;
    // //
    // Date[] dates = collectOrderService.getDatesByLatelyMinutes(latelyMinutes);
    // AutoChanger sign = new AutoChanger() {
    //
    // @Override
    // public int before(Long orderId, Date orderDate) {
    //
    // return orderConfigService.getSignOrderState();
    // }
    //
    // @Override
    // public boolean done(Long orderId, Date orderDate, int target) {
    //
    // return true;
    // }
    // };
    // if (dates.length == 1) {
    // autoChange(dates[0], dates[0], orderConfigService.getShippedOrderState(), sign);
    // } else if (dates.length == 2) {
    // autoChange(dates[0], dates[1], orderConfigService.getShippedOrderState(), sign);
    // autoChange(dates[0], dates[0], orderConfigService.getShippedOrderState(), sign);
    // }
    // return true;
    // }
    //
    // @Override
    // public boolean trade() {
    //
    // // TODO
    // // TODO 交易成功时间
    // // int day = 1;
    // // int latelyMinutes = day * 24 * 60;
    // // //
    // // Date[] dates = collectOrderService.getDatesByLatelyMinutes(latelyMinutes);
    // // AutoChanger trade = new AutoChanger() {
    // //
    // // @Override
    // // public int before(Long orderId, Date orderDate) {
    // //
    // // OrderEntity orderEntity = orderSelecterService.getOrder(orderId, orderDate);
    // // AssertUtil.assertNotNull(orderEntity, "订单不存在." + orderId);
    // // List<AfterSaleOrder> list = afterSaleSelecterService.listAfterSaleOrder(orderEntity);
    // // for (AfterSaleOrder afterSaleOrder : list) {
    // // if (OrderStateType.EXCHANGE.getKey() == afterSaleOrder.getState() || OrderStateType.RETURN.getKey() == afterSaleOrder.getState()) {
    // // // 待处理的售后
    // // return null;
    // // } else if (OrderStateType.EXCHANGE_CONFIRM.getKey() <= afterSaleOrder.getState() || OrderStateType.RETURN_CONFIRM.getKey() <= afterSaleOrder.getState()) {
    // // // 存在问题的
    // // return OrderStateType.NORMAL_TRADE_FAIL;
    // // }
    // // }
    // // // 售后
    // // return OrderStateType.NORMAL_TRADE_SUCCESS;
    // // }
    // //
    // // @Override
    // // public boolean done(Long orderId, Date orderDate, OrderStateType target) {
    // //
    // // if (OrderStateType.NORMAL_TRADE_SUCCESS.equals(target)) {
    // // OrderEntity orderEntity = orderSelecterService.getOrder(orderId, orderDate);
    // // // 订单 有优惠的不参加分佣
    // // if (orderEntity.getCash() != orderEntity.getSum()) {
    // // return true;
    // // }
    // // List<MerchantOrderEntity> merchantOrderList = orderEntity.getEntityList();
    // // for (MerchantOrderEntity merchantOrder : merchantOrderList) {
    // // double purchaseSum = 0.0;
    // // double discountSum = 0.0;
    // // double priceSum = 0.0;
    // // List<OrderItemEntity> list = merchantOrder.getEntityList();
    // // for (OrderItemEntity item : list) {
    // // // 正常购买单品
    // // if (item.getSence() == -1) {
    // // distributionClient.addMerchandiseDealRecords(orderEntity.getUserId(), item.getUnifiedMerchandiseId(), item.getPurchase(), item.getDiscount(), item.getPrice(), item.getNumber(), orderEntity.getId(), item.getId(), orderEntity.getOrderDate());
    // // purchaseSum += item.getPurchase() * item.getNumber();
    // // discountSum += item.getSum();
    // // priceSum += item.getPrice() * item.getNumber();
    // // }
    // // }
    // // if (discountSum > 0) {
    // // distributionClient.addMerchantDealRecords(orderEntity.getUserId(), merchantOrder.getMerchantId(), purchaseSum, discountSum, priceSum, merchantOrder.getId(), orderEntity.getOrderDate());
    // // }
    // // }
    // // }
    // // return true;
    // // }
    // // };
    // // if (dates.length == 1) {
    // // autoChange(dates[0], dates[0], OrderStateType.NORMAL_SIGN, trade);
    // // } else if (dates.length == 2) {
    // // autoChange(dates[0], dates[1], OrderStateType.NORMAL_SIGN, trade);
    // // autoChange(dates[0], dates[0], OrderStateType.NORMAL_SIGN, trade);
    // // }
    // return true;
    // }
    //
    //
    // private static interface AutoChanger {
    //
    // int before(Long orderId, Date orderDate);
    //
    // boolean done(Long orderId, Date orderDate, int target);
    // }
}
