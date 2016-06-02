package com.qcloud.component.personalcenter.model;

import java.util.Date;

// 不做过期
public class RecentlyLoginUser {

    private long userId;

    // 登录时间
    private Date loginTime;

    public long getUserId() {

        return userId;
    }

    public void setUserId(long userId) {

        this.userId = userId;
    }

    public Date getLoginTime() {

        return loginTime;
    }

    public void setLoginTime(Date loginTime) {

        this.loginTime = loginTime;
    }
}
