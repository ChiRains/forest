package com.qcloud.component.orderform.web.observer;

import org.apache.commons.lang.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.my.AfterSaleType;
import com.qcloud.component.my.MyClient;
import com.qcloud.component.orderform.AbstractNotifyAllStateOrderObserver;
import com.qcloud.component.orderform.QAfterSaleOrder;
import com.qcloud.component.orderform.QMerchantOrder;
import com.qcloud.component.orderform.QOrder;
import com.qcloud.component.orderform.QOrderItem;
import com.qcloud.component.orderform.QOrderItemDetail;

@Component
public class AddMyAfterSaleAlterOrderObserver extends AbstractNotifyAllStateOrderObserver {

    @Autowired
    private MyClient myClient;

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

        myClient.addMyAfterSale(t.getUserId(), t.getAfterSaleId(), t.getMerchantOrder().getOrder().getId(), t.getMerchantOrder().getId(), AfterSaleType.REFUND);
    }

    @Override
    protected void doReturnNotify(QAfterSaleOrder t, int state, String[] variable) {

        myClient.addMyAfterSale(t.getUserId(), t.getAfterSaleId(), t.getMerchantOrder().getOrder().getId(), t.getMerchantOrder().getId(), AfterSaleType.RETURN);
    }

    @Override
    protected void doExchangeNotify(QAfterSaleOrder t, int state, String[] variable) {

        myClient.addMyAfterSale(t.getUserId(), t.getAfterSaleId(), t.getMerchantOrder().getOrder().getId(), t.getMerchantOrder().getId(), AfterSaleType.EXCHANGE);
    }
}
