package com.qcloud.component.orderform.web.observer;

import org.apache.commons.lang.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.orderform.AbstractNotifyAllStateOrderObserver;
import com.qcloud.component.orderform.QAfterSaleOrder;
import com.qcloud.component.orderform.QMerchantOrder;
import com.qcloud.component.orderform.QOrder;
import com.qcloud.component.orderform.QOrderItem;
import com.qcloud.component.orderform.QOrderItemDetail;
import com.qcloud.component.orderform.engine.OrderStateService;
import com.qcloud.pirates.util.AssertUtil;

@Component
public class AlterAfterSaleChangeOrderStateObserver extends AbstractNotifyAllStateOrderObserver {

    @Autowired
    private OrderStateService orderStateService;

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

        AssertUtil.assertTrue(variable != null && variable.length == 1, "发起售后订单跳转状态未定义.");
        AssertUtil.assertNotEmpty(variable[0], "订单欲跳转状态不能为空");
        orderStateService.exchangeSubOrderState(t.getMerchantOrder().getId(), t.getMerchantOrder().getOrder().getOrderDate(), Integer.parseInt(variable[0]), -999L);
    }

    @Override
    protected void doReturnNotify(QAfterSaleOrder t, int state, String[] variable) {

        AssertUtil.assertTrue(variable != null && variable.length == 1, "发起售后订单跳转状态未定义.");
        AssertUtil.assertNotEmpty(variable[0], "订单欲跳转状态不能为空");
        orderStateService.exchangeSubOrderState(t.getMerchantOrder().getId(), t.getMerchantOrder().getOrder().getOrderDate(), Integer.parseInt(variable[0]), -999L);
    }

    @Override
    protected void doExchangeNotify(QAfterSaleOrder t, int state, String[] variable) {

        AssertUtil.assertTrue(variable != null && variable.length == 1, "发起售后订单跳转状态未定义.");
        AssertUtil.assertNotEmpty(variable[0], "订单欲跳转状态不能为空");
        orderStateService.exchangeSubOrderState(t.getMerchantOrder().getId(), t.getMerchantOrder().getOrder().getOrderDate(), Integer.parseInt(variable[0]), -999L);
    }
}
