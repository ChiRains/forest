package com.qcloud.component.personalcenter.dao;

import com.qcloud.component.personalcenter.model.ActiveUser;

public interface ActiveUserDao {

    boolean add(ActiveUser activeUser);

    boolean zincrby(long userId);

    ActiveUser get(long userId);
}
