package com.qcloud.component.organization;

public interface QDepartment {

    Long getId();

    String getName();

    QDepartment getParent();

    Long getManager();

    String getProvince();

    String getCity();

    String getDistrict();

    String getAddress();

    double getLongitude();

    double getLatitude();

    String getCode();

    String getImage();

    String getPhone();

    int getType();

    String getShopHour();
}
