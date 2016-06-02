package com.qcloud.component.orderform.web.observer;

import java.util.List;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.orderform.AbstractNotifyAllStateOrderObserver;
import com.qcloud.component.orderform.QAfterSaleOrder;
import com.qcloud.component.orderform.QMerchantOrder;
import com.qcloud.component.orderform.QOrder;
import com.qcloud.component.orderform.QOrderItem;
import com.qcloud.component.orderform.QOrderItemDetail;
import com.qcloud.component.orderform.engine.AfterSaleSelecterService;
import com.qcloud.component.orderform.engine.OrderStateService;
import com.qcloud.component.orderform.entity.AfterSaleOrder;
import com.qcloud.component.orderform.entity.MerchantOrderEntity;
import com.qcloud.pirates.util.AssertUtil;

@Component
public class AlterAfterSaleReturnOrderStateObserver extends AbstractNotifyAllStateOrderObserver {

    @Autowired
    private AfterSaleSelecterService afterSaleSelecterService;

    @Autowired
    private OrderStateService        orderStateService;

    @Override
    protected void doOrderNotify(QOrder t, int state, String[] variable) {

        throw new NotImplementedException();
    }

    @Override
    protected void doMerchantNotify(QMerchantOrder t, int state, String[] variable) {

        throw new NotImplementedException();
    }

    @Override
    protected void doItemNotify(QOrderItem t, int state, String[] variable) {

        throw new NotImplementedException();
    }

    @Override
    protected void doDetailNotify(QOrderItemDetail t, int state, String[] variable) {

        throw new NotImplementedException();
    }

    @Override
    protected void doRefundNotify(QAfterSaleOrder t, int state, String[] variable) {

        AssertUtil.assertTrue(variable != null, "发起售后订单跳转状态未定义.");
        QMerchantOrder merchantOrder = t.getMerchantOrder();
        if (!hasAfterSale(merchantOrder, calculateState(variable))) {
            orderStateService.returnSubOrderState(t.getMerchantOrder().getId(), t.getMerchantOrder().getOrder().getOrderDate(), -999L);
        }
    }

    @Override
    protected void doReturnNotify(QAfterSaleOrder t, int state, String[] variable) {

        AssertUtil.assertTrue(variable != null, "发起售后订单跳转状态未定义.");
        QMerchantOrder merchantOrder = t.getMerchantOrder();
        if (!hasAfterSale(merchantOrder, calculateState(variable))) {
            orderStateService.returnSubOrderState(t.getMerchantOrder().getId(), t.getMerchantOrder().getOrder().getOrderDate(), -999L);
        }
    }

    @Override
    protected void doExchangeNotify(QAfterSaleOrder t, int state, String[] variable) {

        AssertUtil.assertTrue(variable != null, "发起售后订单跳转状态未定义.");
        QMerchantOrder merchantOrder = t.getMerchantOrder();
        if (!hasAfterSale(merchantOrder, calculateState(variable))) {
            orderStateService.returnSubOrderState(t.getMerchantOrder().getId(), t.getMerchantOrder().getOrder().getOrderDate(), -999L);
        }
    }

    private int[] calculateState(String[] variable) {

        int[] states = new int[variable.length];
        for (int index = 0; index < states.length; index++) {
            states[index] = Integer.parseInt(variable[index]);
        }
        return states;
    }

    private boolean hasAfterSale(QMerchantOrder merchantOrder, int[] states) {

        List<AfterSaleOrder> list = afterSaleSelecterService.listAfterSaleOrder((MerchantOrderEntity) merchantOrder);
        for (AfterSaleOrder afterSaleOrder : list) {
            for (int state : states) {
                if (state == afterSaleOrder.getState()) {
                    return true;
                }
            }
        }
        return false;
    }
}
