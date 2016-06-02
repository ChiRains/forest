package com.qcloud.component.personalcenter;

import java.util.Date;

public interface QUser {

    long getId();

    String getHeadImage();

    String getName();

    String getNickname();

    String getMobile();

    String getEmail();

    Date getEntryDate();

    int getSex();

    QGrade getGrade();

    String getProvince();

    String getCity();

    String getDistrict();

    String getAdress();
}
