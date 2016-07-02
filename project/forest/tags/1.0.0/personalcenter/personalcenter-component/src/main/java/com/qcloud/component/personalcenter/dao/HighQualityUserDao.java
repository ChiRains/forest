package com.qcloud.component.personalcenter.dao;

import com.qcloud.component.personalcenter.model.HighQualityUser;

public interface HighQualityUserDao {

    boolean add(HighQualityUser highQualityUser);

    boolean zincrby(HighQualityUser highQualityUser);

    HighQualityUser get(long userId);
}
