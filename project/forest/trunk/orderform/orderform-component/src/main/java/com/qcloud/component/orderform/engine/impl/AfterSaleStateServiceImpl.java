package com.qcloud.component.orderform.engine.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.qcloud.component.orderform.QAfterSaleOrder;
import com.qcloud.component.orderform.QAfterSaleOrderItem;
import com.qcloud.component.orderform.engine.AfterSaleStateService;
import com.qcloud.component.orderform.entity.AfterSaleOrder;
import com.qcloud.component.orderform.entity.AfterSaleOrderItem;
import com.qcloud.component.orderform.entity.ExchangeAfterSaleOrder;
import com.qcloud.component.orderform.entity.ExchangeAfterSaleOrderItem;
import com.qcloud.component.orderform.entity.RefundAfterSaleOrder;
import com.qcloud.component.orderform.entity.RefundAfterSaleOrderItem;
import com.qcloud.component.orderform.entity.ReturnAfterSaleOrder;
import com.qcloud.component.orderform.entity.ReturnAfterSaleOrderItem;
import com.qcloud.component.orderform.service.ExchangeOrderItemDetailService;
import com.qcloud.component.orderform.service.ExchangeOrderService;
import com.qcloud.component.orderform.service.OrderConfigService;
import com.qcloud.component.orderform.service.OrderObserverService;
import com.qcloud.component.orderform.service.RefundOrderItemService;
import com.qcloud.component.orderform.service.RefundOrderService;
import com.qcloud.component.orderform.service.ReturnOrderItemService;
import com.qcloud.component.orderform.service.ReturnOrderService;
import com.qcloud.pirates.util.AssertUtil;

@Service
public class AfterSaleStateServiceImpl implements AfterSaleStateService {

    @Autowired
    private ExchangeOrderService           exchangeOrderService;

    @Autowired
    private ExchangeOrderItemDetailService exchangeOrderItemDetailService;

    @Autowired
    private ReturnOrderService             returnOrderService;

    @Autowired
    private ReturnOrderItemService         returnOrderItemService;

    @Autowired
    private RefundOrderService             refundOrderService;

    @Autowired
    private RefundOrderItemService         refundOrderItemService;

    // @Autowired
    // private OrderStateService orderStateService;
    @Autowired
    private OrderConfigService             orderConfigService;

    @Autowired
    private OrderObserverService           orderObserverService;

    private Set<String>                    onSet = new HashSet<String>();

    @Transactional
    public boolean exchangeExchangeOrderState(ExchangeAfterSaleOrder exchangeAfterSaleOrder, int state) {

        try {
            if (onSet.add(exchangeAfterSaleOrder.getAfterSaleOrderNumber())) {
                boolean ok = check(orderConfigService.exchangeChain(), exchangeAfterSaleOrder.getState(), state);
                AssertUtil.assertTrue(ok, "换货单状态跳转有误." + exchangeAfterSaleOrder.getState() + "==》" + state);
                updateState(exchangeAfterSaleOrder, state, new ExchangeStateUpdater());
                List<ExchangeAfterSaleOrderItem> list = exchangeAfterSaleOrder.listExchangeItem();
                for (ExchangeAfterSaleOrderItem exchangeAfterSaleOrderItem : list) {
                    updateState(exchangeAfterSaleOrderItem, state, new ExchangeStateUpdater());
                }
                return true;
            } else {
                return false;
            }
        } finally {
            onSet.remove(exchangeAfterSaleOrder.getAfterSaleOrderNumber());
        }
    }

    @Transactional
    public boolean exchangeExchangeOrderItemState(ExchangeAfterSaleOrderItem exchangeAfterSaleOrderItem, int state) {

        boolean ok = check(orderConfigService.exchangeChain(), exchangeAfterSaleOrderItem.getState(), state);
        AssertUtil.assertTrue(ok, "换货单项状态跳转有误." + exchangeAfterSaleOrderItem.getState() + "==》" + state);
        updateState(exchangeAfterSaleOrderItem, state, new ExchangeStateUpdater());
        updateParent(exchangeAfterSaleOrderItem.getAfterSaleOrder(), state, new ExchangeStateUpdater());
        return true;
    }

    @Transactional
    public boolean exchangeReturnOrderState(ReturnAfterSaleOrder returnAfterSaleOrder, int state) {

        try {
            if (onSet.add(returnAfterSaleOrder.getAfterSaleOrderNumber())) {
                boolean ok = check(orderConfigService.returnChain(), returnAfterSaleOrder.getState(), state);
                AssertUtil.assertTrue(ok, "退货单状态跳转有误." + returnAfterSaleOrder.getState() + "==》" + state);
                updateState(returnAfterSaleOrder, state, new ReturnStateUpdater());
                List<ReturnAfterSaleOrderItem> list = returnAfterSaleOrder.listReturnItem();
                for (ReturnAfterSaleOrderItem returnAfterSaleOrderItem : list) {
                    updateState(returnAfterSaleOrderItem, state, new ReturnStateUpdater());
                }
                return true;
            } else {
                return false;
            }
        } finally {
            onSet.remove(returnAfterSaleOrder.getAfterSaleOrderNumber());
        }
    }

    @Transactional
    public boolean exchangeReturnOrderItemState(ReturnAfterSaleOrderItem returnAfterSaleOrderItem, int state) {

        boolean ok = check(orderConfigService.returnChain(), returnAfterSaleOrderItem.getState(), state);
        AssertUtil.assertTrue(ok, "退货单项状态跳转有误." + returnAfterSaleOrderItem.getState() + "==》" + state);
        updateState(returnAfterSaleOrderItem, state, new RefundStateUpdater());
        updateParent(returnAfterSaleOrderItem.getAfterSaleOrder(), state, new RefundStateUpdater());
        return true;
    }

    @Transactional
    public boolean exchangeRefundOrderState(RefundAfterSaleOrder refundAfterSaleOrder, int state) {

        try {
            if (onSet.add(refundAfterSaleOrder.getAfterSaleOrderNumber())) {
                boolean ok = check(orderConfigService.refundChain(), refundAfterSaleOrder.getState(), state);
                AssertUtil.assertTrue(ok, "退款单状态跳转有误." + refundAfterSaleOrder.getState() + "==》" + state);
                updateState(refundAfterSaleOrder, state, new RefundStateUpdater());
                List<RefundAfterSaleOrderItem> list = refundAfterSaleOrder.listRefundItem();
                for (RefundAfterSaleOrderItem refundAfterSaleOrderItem : list) {
                    updateState(refundAfterSaleOrderItem, state, new RefundStateUpdater());
                }
                return true;
            } else {
                return false;
            }
        } finally {
            onSet.remove(refundAfterSaleOrder.getAfterSaleOrderNumber());
        }
    }

    @Transactional
    public boolean exchangeRefundOrderItemState(RefundAfterSaleOrderItem refundAfterSaleOrderItem, int state) {

        boolean ok = check(orderConfigService.refundChain(), refundAfterSaleOrderItem.getState(), state);
        AssertUtil.assertTrue(ok, "退款单项状态跳转有误." + refundAfterSaleOrderItem.getState() + "==》" + state);
        updateState(refundAfterSaleOrderItem, state, new ReturnStateUpdater());
        updateParent(refundAfterSaleOrderItem.getAfterSaleOrder(), state, new ReturnStateUpdater());
        return true;
    }

    private boolean updateState(AfterSaleOrder afterSaleOrder, int state, AfterSaleStateUpdater updater) {

        updater.update(afterSaleOrder, afterSaleOrder.getAfterSaleId(), state);
        afterSaleOrder.setState(state);
        return true;
    }

    private boolean updateState(AfterSaleOrderItem afterSaleOrderItem, int state, AfterSaleStateUpdater updater) {

        updater.updateItem(afterSaleOrderItem.getAfterSaleOrderItemId(), state);
        afterSaleOrderItem.setState(state);
        return true;
    }

    private boolean updateParent(AfterSaleOrder afterSaleOrder, int state, AfterSaleStateUpdater updater) {

        List<QAfterSaleOrderItem> afterSaleOrderItemList = afterSaleOrder.listItem();
        boolean update = true;
        for (QAfterSaleOrderItem afterSaleOrderItem : afterSaleOrderItemList) {
            if (afterSaleOrderItem.getState() < state) {
                update = false;
                break;
            }
        }
        if (update) {
            updateState(afterSaleOrder, state, updater);
        }
        return true;
    }

    private boolean check(List<int[]> stateChainList, int currentState, int newState) {

        for (int[] chain : stateChainList) {
            for (int index = 0; index < chain.length; index++) {
                if (currentState == chain[index] && index + 1 < chain.length && chain[index + 1] == newState) {
                    return true;
                }
            }
        }
        return false;
    }
    // private static final List<int[]> returnStateChainList = new ArrayList<int[]>();
    //
    // private static final List<int[]> refundStateChainList = new ArrayList<int[]>();
    //
    // private static final List<int[]> exchangeStateChainList = new ArrayList<int[]>();
    // static {
    // // 退货流程
    // int[] invalidReturnChain = new int[] { OrderStateType.RETURN.getKey(), OrderStateType.RETURN_REFUSE.getKey(), OrderStateType.RETURN_FAIL.getKey()};
    // int[] normalReturnChain = new int[] { OrderStateType.RETURN.getKey(), OrderStateType.RETURN_CONFIRM.getKey(), OrderStateType.RETURN_SHIPPED.getKey(), OrderStateType.RETURN_SIGN.getKey(), OrderStateType.RETURN_PAID.getKey(), OrderStateType.RETURN_CONFIRM_PAID.getKey(), OrderStateType.RETURN_SUCCESS.getKey()};
    // returnStateChainList.add(invalidReturnChain);
    // returnStateChainList.add(normalReturnChain);
    // // 退款流程
    // int[] invalidRefundChain = new int[] { OrderStateType.REFUND.getKey(), OrderStateType.REFUND_REFUSE.getKey(), OrderStateType.REFUND_FAIL.getKey()};
    // int[] normalRefundChain = new int[] { OrderStateType.REFUND.getKey(), OrderStateType.REFUND_CONFIRM.getKey(), OrderStateType.REFUND_PAID.getKey(), OrderStateType.REFUND_CONFIRM_PAID.getKey(), OrderStateType.REFUND_SUCCESS.getKey()};
    // refundStateChainList.add(invalidRefundChain);
    // refundStateChainList.add(normalRefundChain);
    // // 换货流程
    // int[] invalidExchangeChain = new int[] { OrderStateType.EXCHANGE.getKey(), OrderStateType.EXCHANGE_REFUSE.getKey(), OrderStateType.EXCHANGE_FAIL.getKey()};
    // int[] normalExchangeChain = new int[] { OrderStateType.EXCHANGE.getKey(), OrderStateType.EXCHANGE_CONFIRM.getKey(), OrderStateType.EXCHANGE_SHIPPED.getKey(), OrderStateType.EXCHANGE_SIGN.getKey(), OrderStateType.EXCHANGE_SHIPPED_AGAIN.getKey(), OrderStateType.EXCHANGE_SIGN_AGAIN.getKey(), OrderStateType.EXCHANGE_SUCCESS.getKey()};
    // exchangeStateChainList.add(invalidExchangeChain);
    // exchangeStateChainList.add(normalExchangeChain);
    // }
    //
    private static interface AfterSaleStateUpdater<T extends QAfterSaleOrder> {

        public boolean update(T t, Long afterSaleId, int state);

        public boolean updateItem(Long afterSaleItemId, int state);
    }
    //
    private class ExchangeStateUpdater implements AfterSaleStateUpdater<ExchangeAfterSaleOrder> {

        @Override
        public boolean update(ExchangeAfterSaleOrder exchangeAfterSaleOrder, Long afterSaleId, int state) {

            orderObserverService.doNotify(exchangeAfterSaleOrder, state);
            return exchangeOrderService.update(afterSaleId, state);
        }

        @Override
        public boolean updateItem(Long afterSaleItemId, int state) {

            return exchangeOrderItemDetailService.update(afterSaleItemId, state);
        }
    }
    //
    private class ReturnStateUpdater implements AfterSaleStateUpdater<RefundAfterSaleOrder> {

        @Override
        public boolean update(RefundAfterSaleOrder refundAfterSaleOrder, Long afterSaleId, int state) {

            orderObserverService.doNotify(refundAfterSaleOrder, state);
            return returnOrderService.update(afterSaleId, state);
        }

        @Override
        public boolean updateItem(Long afterSaleItemId, int state) {

            return returnOrderItemService.update(afterSaleItemId, state);
        }
    }
    //
    private class RefundStateUpdater implements AfterSaleStateUpdater<ReturnAfterSaleOrder> {

        @Override
        public boolean update(ReturnAfterSaleOrder returnAfterSaleOrder, Long afterSaleId, int state) {

            orderObserverService.doNotify(returnAfterSaleOrder, state);
            return refundOrderService.update(afterSaleId, state);
        }

        @Override
        public boolean updateItem(Long afterSaleItemId, int state) {

            return refundOrderItemService.update(afterSaleItemId, state);
        }
    }
}
