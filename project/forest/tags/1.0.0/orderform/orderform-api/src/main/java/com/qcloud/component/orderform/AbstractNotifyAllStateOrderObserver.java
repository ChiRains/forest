package com.qcloud.component.orderform;

import com.qcloud.component.my.AfterSaleType;

public abstract class AbstractNotifyAllStateOrderObserver implements OrderObserver<Object> {

    @Override
    public final void doNotify(Object t, int state, String[] variable) {

        if (t instanceof QOrder) {
            QOrder o = (QOrder) t;
            doOrderNotify(o, state, variable);
        } else if (t instanceof QMerchantOrder) {
            QMerchantOrder m = (QMerchantOrder) t;
            doMerchantNotify(m, state, variable);
        } else if (t instanceof QOrderItem) {
            QOrderItem i = (QOrderItem) t;
            doItemNotify(i, state, variable);
        } else if (t instanceof QOrderItemDetail) {
            QOrderItemDetail d = (QOrderItemDetail) t;
            doDetailNotify(d, state, variable);
        } else if (t instanceof QAfterSaleOrder) {
            QAfterSaleOrder m = (QAfterSaleOrder) t;
            AfterSaleType type = m.getAfterSaleType();
            if (AfterSaleType.REFUND.equals(type)) {
                doRefundNotify(m, state, variable);
            } else if (AfterSaleType.RETURN.equals(type)) {
                doReturnNotify(m, state, variable);
            } else if (AfterSaleType.EXCHANGE.equals(type)) {
                doExchangeNotify(m, state, variable);
            }
        }
    }

    protected abstract void doOrderNotify(QOrder t, int state, String[] variable);

    protected abstract void doMerchantNotify(QMerchantOrder t, int state, String[] variable);

    protected abstract void doItemNotify(QOrderItem t, int state, String[] variable);

    protected abstract void doDetailNotify(QOrderItemDetail t, int state, String[] variable);

    protected abstract void doRefundNotify(QAfterSaleOrder t, int state, String[] variable);

    protected abstract void doReturnNotify(QAfterSaleOrder t, int state, String[] variable);

    protected abstract void doExchangeNotify(QAfterSaleOrder t, int state, String[] variable);
}
