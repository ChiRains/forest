package com.qcloud.component.goods;

import java.util.List;

public interface QUnifiedMerchandise {

    Long getId();

    String getName();

    String getImage();

    double getPrice();

    double getDiscount();

    double getPurchase();

    int getStock();

    long getMerchantId();

    int getSence();

    double getWeight();

    String getSpecifications();

    String getUnit();

    List<QMerchandiseItem> getList();

    MerchandiseType getType();
    
    boolean getIsIncludePost();
    //
    public static enum MerchandiseType {
        SINGLE, COMBINATION, MARKETING;
    }
}
