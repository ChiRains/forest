package com.qcloud.component.orderform;

import java.util.Date;
import java.util.List;
import com.qcloud.component.my.AfterSaleType;

public interface QAfterSaleOrder {

    public <T extends QAfterSaleOrderItem> List<T> listItem();

    public QMerchantOrder getMerchantOrder();

    public long getMerchantId();

    public long getStoreId();

    public long getAfterSaleId();

    public int getState();

    public Date getTime();

    public long getUserId();

    public String getExplain();

    public String getReason();

    public String getAfterSaleOrderNumber();

    public double getSum();

    public AfterSaleType getAfterSaleType();

    public boolean isFinish();

    public int getUserState();

    public String getUserStateStr();

    public double getAfterSaleSum();
}
