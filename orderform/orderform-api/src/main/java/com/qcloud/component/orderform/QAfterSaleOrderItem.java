package com.qcloud.component.orderform;

import java.util.Date;

public interface QAfterSaleOrderItem {

    public QAfterSaleOrder getAfterSaleOrder();

    public long getAfterSaleOrderItemId();

    public int getState();

    public Date getTime();

    public int getNumber();

    public double getSum();

    public String getExplain();

    public String getReason();
}
