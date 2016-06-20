package com.qcloud.project.forest.web.observer;

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
import com.qcloud.pirates.util.AssertUtil;

@Component
public class ForestAlterAfterSaleReturnOrderStateObserver extends AbstractNotifyAllStateOrderObserver {

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

    // AssertUtil.assertTrue(variable != null, "发起售后订单跳转状态未定义.");
    // QMerchantOrder merchantOrder = t.getMerchantOrder();
    // if (!hasAfterSale(merchantOrder, calculateState(variable))) {
    // orderStateService.returnSubOrderState(t.getMerchantOrder().getId(), t.getMerchantOrder().getOrder().getOrderDate(), -999L);
    // }
    @Override
    protected void doDetailNotify(QOrderItemDetail t, int state, String[] variable) {

        throw new NotImplementedException();
    }

    @Override
    protected void doRefundNotify(QAfterSaleOrder t, int state, String[] variable) {

        AssertUtil.assertTrue(variable != null, "发起售后订单跳转状态未定义.");
        QMerchantOrder merchantOrder = t.getMerchantOrder();
        if (merchantOrder.getOrder().getState() == 60) {
            orderStateService.returnOrderState(merchantOrder.getOrder().getId(), merchantOrder.getOrder().getOrderDate(), -999L);
        }
    }

    @Override
    protected void doReturnNotify(QAfterSaleOrder t, int state, String[] variable) {

        throw new NotImplementedException();
    }

    @Override
    protected void doExchangeNotify(QAfterSaleOrder t, int state, String[] variable) {

        throw new NotImplementedException();
    }
}
