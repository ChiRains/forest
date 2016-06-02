package com.qcloud.component.personalcenter.model;

import java.util.Date;

/**
 * 在线用户  15D过期
 * 
 * @author Zoro
 */
public class OnlineUser {

    private long userId;

    // 最后访问时间
    private Date lastVisitTime;

    public long getUserId() {

        return userId;
    }

    public void setUserId(long userId) {

        this.userId = userId;
    }

    public Date getLastVisitTime() {

        return lastVisitTime;
    }

    public void setLastVisitTime(Date lastVisitTime) {

        this.lastVisitTime = lastVisitTime;
    }
}
