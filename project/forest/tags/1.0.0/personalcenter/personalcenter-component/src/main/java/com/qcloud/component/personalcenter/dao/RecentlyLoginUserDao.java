package com.qcloud.component.personalcenter.dao;

import com.qcloud.component.personalcenter.model.RecentlyLoginUser;

public interface RecentlyLoginUserDao {

    boolean add(RecentlyLoginUser recentlyLoginUser);

    // 登录或者续租
    boolean relet(long userId);
}
