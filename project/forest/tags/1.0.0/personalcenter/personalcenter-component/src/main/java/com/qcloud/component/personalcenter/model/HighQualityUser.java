package com.qcloud.component.personalcenter.model;

/**
 * 优质用户   不做过期
 * 
 * @author Zoro
 */
public class HighQualityUser {

    private long   userId;

    // 成交总量
    private double money;

    public long getUserId() {

        return userId;
    }

    public void setUserId(long userId) {

        this.userId = userId;
    }

    public double getMoney() {

        return money;
    }

    public void setMoney(double money) {

        this.money = money;
    }
}
