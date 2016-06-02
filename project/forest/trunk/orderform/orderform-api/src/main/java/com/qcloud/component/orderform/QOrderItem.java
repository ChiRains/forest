package com.qcloud.component.orderform;

import java.util.List;

public interface QOrderItem {

    // List<QAfterSaleOrderItem> getAfterSaleOrderItemList();
    QOrder getOrder();

    QMerchantOrder getMerchantOrder();

    List<QOrderItemDetail> getOrderItemDetailList();

    long getUnifiedMerchandiseId();

    double getPrice();

    double getTotalPrice();

    double getDiscount();

    double getSum();

    double getPurchase();

    double getTotalPurchase();

    double getCash();

    int getNumber();

    double getPreferential();

    int getState();

    int getSence();

    long getId();

    String getName();

    String getImage();

    boolean isEvaluation();

    boolean isAfterSale();

    String getSpecifications();
}
