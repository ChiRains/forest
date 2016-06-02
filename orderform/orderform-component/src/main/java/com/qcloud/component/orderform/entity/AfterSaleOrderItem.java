package com.qcloud.component.orderform.entity;

import com.qcloud.component.orderform.QAfterSaleOrderItem;

public abstract class AfterSaleOrderItem implements QAfterSaleOrderItem {

    private AfterSaleOrder afterSaleOrder;

    public abstract void setState(int state);

    public AfterSaleOrderItem(AfterSaleOrder afterSaleOrder) {

        super();
        this.afterSaleOrder = afterSaleOrder;
    }

    public AfterSaleOrder getAfterSaleOrder() {

        return afterSaleOrder;
    }
}
