package com.qcloud.component.personalcenter.dao;

import com.qcloud.component.personalcenter.model.OnlineUser;

public interface OnlineUserDao {

    boolean add(OnlineUser onlineUser);

    // 每次访问续租
    boolean relet(long userId);

    long count();
}
