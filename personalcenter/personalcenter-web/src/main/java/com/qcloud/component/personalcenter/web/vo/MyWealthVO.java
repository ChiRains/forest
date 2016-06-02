package com.qcloud.component.personalcenter.web.vo;

public class MyWealthVO {

    private String name;

    // 积分
    private long   integral;

    // 佣金
    private double commission;

    // 消费币
    private double consumptionCurrency;

    private String headImage;

    public MyWealthVO() {

    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
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

    public String getHeadImage() {

        return headImage;
    }

    public void setHeadImage(String headImage) {

        this.headImage = headImage;
    }
}
