package com.qcloud.component.personalcenter.model;

import java.util.Date;

public class MyWealth {

    // ID
    private long   id;

    // 用户
    private long   userId;

    // 积分
    private long   integral;

    // 佣金
    private double commission;

    // 消费币
    private double consumptionCurrency;

    // 积分总计
    private long   integralSummation;

    // 佣金总计
    private double commissionSummation;

    // 消费币总计
    private double consumptionCurrencySummation;

    // 最后更新时间
    private Date   time;

    // 版本
    private long   version;

    public MyWealth() {

    }

    public MyWealth(long id, long userId, long integral, double commission, double consumptionCurrency, long integralSummation, double commissionSummation, double consumptionCurrencySummation, Date time, long version) {

        this.id = id;
        this.userId = userId;
        this.integral = integral;
        this.commission = commission;
        this.consumptionCurrency = consumptionCurrency;
        this.integralSummation = integralSummation;
        this.commissionSummation = commissionSummation;
        this.consumptionCurrencySummation = consumptionCurrencySummation;
        this.time = time;
        this.version = version;
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

    public void setIntegral(long integral) {

        this.integral = integral;
    }

    public long getIntegral() {

        return integral;
    }

    public void setCommission(double commission) {

        this.commission = commission;
    }

    public double getCommission() {

        return commission;
    }

    public void setConsumptionCurrency(double consumptionCurrency) {

        this.consumptionCurrency = consumptionCurrency;
    }

    public double getConsumptionCurrency() {

        return consumptionCurrency;
    }

    public void setIntegralSummation(long integralSummation) {

        this.integralSummation = integralSummation;
    }

    public long getIntegralSummation() {

        return integralSummation;
    }

    public void setCommissionSummation(double commissionSummation) {

        this.commissionSummation = commissionSummation;
    }

    public double getCommissionSummation() {

        return commissionSummation;
    }

    public void setConsumptionCurrencySummation(double consumptionCurrencySummation) {

        this.consumptionCurrencySummation = consumptionCurrencySummation;
    }

    public double getConsumptionCurrencySummation() {

        return consumptionCurrencySummation;
    }

    public void setTime(Date time) {

        this.time = time;
    }

    public Date getTime() {

        return time;
    }

    public void setVersion(long version) {

        this.version = version;
    }

    public long getVersion() {

        return version;
    }
}
