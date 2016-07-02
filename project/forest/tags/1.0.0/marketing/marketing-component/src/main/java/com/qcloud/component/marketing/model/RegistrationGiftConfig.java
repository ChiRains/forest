package com.qcloud.component.marketing.model;

public class RegistrationGiftConfig {

    //积分
    private Integer point;
    //兑兑券
    private Integer currency;
    //佣金
    private Integer brokerage;

    public int getPoint() {

        return point == null ? 0 : point;
    }

    public void setPoint(Integer point) {

        this.point = point;
    }

    public int getCurrency() {

        return currency == null ? 0 : currency;
    }

    public void setCurrency(Integer currency) {

        this.currency = currency;
    }

    public int getBrokerage() {

        return brokerage == null ? 0 : brokerage;
    }

    public void setBrokerage(Integer brokerage) {

        this.brokerage = brokerage;
    }
}
