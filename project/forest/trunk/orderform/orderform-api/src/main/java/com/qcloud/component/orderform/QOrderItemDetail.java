package com.qcloud.component.orderform;

public interface QOrderItemDetail {

    // List<QAfterSaleOrderItem> getAfterSaleOrderItemList();
    QOrder getOrder();

    QMerchantOrder getMerchantOrder();

    QOrderItem getOrderItem();

    String getName();

    String getImage();

    String getCode();

    String getSpecifications();

    int getState();

    long getUnifiedMerchandiseId();

    long getMerchandiseItemId();

    long getId();

    int getNumber();

    double getDiscount();
}
