package com.qcloud.component.sellercenter;

public interface QStore {

    Long getId();

    String getName();

    String getAddress();

    String getPhone();

    boolean isRoot();

    Long getMerchantId();

    String getSmsMobile();

    Double getLatitude();

    Double getLongitude();
}
