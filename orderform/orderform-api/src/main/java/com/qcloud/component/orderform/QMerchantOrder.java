package com.qcloud.component.orderform;

import java.util.List;

public interface QMerchantOrder {

    QOrder getOrder();

    List<QOrderItem> getOrderItemList();

    // List<QAfterSaleOrder> getAfterSaleOrderList();

    String getOrderNumber();

    String getExplain();

    long getId();

    int getState();

    long getMerchantId();

    long getStoreId();

    int getDeliveryMode();

    String getPickupAddressStr();

    String getDeliveryTimeStr();

    double getTotalPrice();

    double getSum();

    double getTotalPurchase();

    double getCash();

    double getPostage();

    int getNumber();

    int getIntegral();

    double getConsumption();

    double getPreferential();

    double getCoupon();

    String getExpressCode();

    String getExpressName();

    String getExpressNumber();

    int getUserState();

    String getUserStateStr();
    
    boolean canRemind();
}
