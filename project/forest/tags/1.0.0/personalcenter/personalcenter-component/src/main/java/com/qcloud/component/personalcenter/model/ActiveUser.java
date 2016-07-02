package com.qcloud.component.personalcenter.model;

/**
 * 活跃用户  不过过期
 * 
 * @author Zoro
 */
public class ActiveUser {

    private long userId;

    // 登录总次数
    private int  times;

    public long getUserId() {

        return userId;
    }

    public void setUserId(long userId) {

        this.userId = userId;
    }

    public int getTimes() {

        return times;
    }

    public void setTimes(int times) {

        this.times = times;
    }
}
