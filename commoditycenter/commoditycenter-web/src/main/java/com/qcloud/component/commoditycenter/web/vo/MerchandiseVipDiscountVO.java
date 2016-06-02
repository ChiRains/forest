package com.qcloud.component.commoditycenter.web.vo;

import java.util.Date;
import java.math.BigDecimal;

public class MerchandiseVipDiscountVO {

    private String name;

    private String unit;

    private double discount;

    private double vipDiscount;

    private double discountRate;

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getUnit() {

        return unit;
    }

    public void setUnit(String unit) {

        this.unit = unit;
    }

    public double getDiscount() {

        return discount;
    }

    public void setDiscount(double discount) {

        this.discount = discount;
    }

    public double getVipDiscount() {

        return vipDiscount;
    }

    public void setVipDiscount(double vipDiscount) {

        this.vipDiscount = vipDiscount;
    }

    public double getDiscountRate() {

        return discountRate;
    }

    public void setDiscountRate(double discountRate) {

        this.discountRate = discountRate;
    }
}
