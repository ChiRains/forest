package com.qcloud.component.sellercenter;

public interface QStore {

    Long getId();

    String getName();

    String getAddress();

    String getSmsMobile();

    Double getLatitude();

    Double getLongitude();

    QStore getParent();

    QMerchant getMerchant();

    boolean isRoot();
}
