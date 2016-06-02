package com.qcloud.component.personalcenter.model;

import java.util.Date;

public class MySignInStatistics {

    // ID
    private long id;

    // 用户
    private long userId;

    // 签到总数
    private int  total;

    // 最大连签记录
    private int  maxSignIn;

    // 当前连签记录
    private int  currentSignIn;

    private Date lastSignInDay;

    private Date firstSignInDay;

    private int  firstIntegral;

    public MySignInStatistics() {

    }

    public void setId(long id) {

        this.id = id;
    }

    public long getId() {

        return id;
    }

    public void setUserId(long userId) {

        this.userId = userId;
    }

    public long getUserId() {

        return userId;
    }

    public void setTotal(int total) {

        this.total = total;
    }

    public int getTotal() {

        return total;
    }

    public void setMaxSignIn(int maxSignIn) {

        this.maxSignIn = maxSignIn;
    }

    public int getMaxSignIn() {

        return maxSignIn;
    }

    public void setCurrentSignIn(int currentSignIn) {

        this.currentSignIn = currentSignIn;
    }

    public int getCurrentSignIn() {

        return currentSignIn;
    }

    public Date getLastSignInDay() {

        return lastSignInDay;
    }

    public void setLastSignInDay(Date lastSignInDay) {

        this.lastSignInDay = lastSignInDay;
    }

    public Date getFirstSignInDay() {

        return firstSignInDay;
    }

    public void setFirstSignInDay(Date firstSignInDay) {

        this.firstSignInDay = firstSignInDay;
    }

    public int getFirstIntegral() {

        return firstIntegral;
    }

    public void setFirstIntegral(int firstIntegral) {

        this.firstIntegral = firstIntegral;
    }
}
