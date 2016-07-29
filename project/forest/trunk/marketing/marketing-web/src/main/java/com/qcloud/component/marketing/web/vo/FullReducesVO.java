package com.qcloud.component.marketing.web.vo;

import java.util.Date;
import java.math.BigDecimal;

public class FullReducesVO {

    private long   id;

    // 商家id,平台为-1
    private long   merchantId;

    private String name;

    // 优惠金额
    private double benefit;

    // 使用限额
    private double limitPrice;

    // 活动开始时间
    private Date   beginDate;

    // 活动结束时间
    private Date   endDate;

    // 是否可以和优惠券一起使用1可以2不可以
    private int    canUseCoupon;

    // 是否可以与秒杀一起1可以2不可以
    private int    canUseSeckill;

    // 是否启用1启用2禁用3删除
    private int    state;

    public FullReducesVO() {

    }

    public void setId(long id) {

        this.id = id;
    }

    public long getId() {

        return id;
    }

    public void setMerchantId(long merchantId) {

        this.merchantId = merchantId;
    }

    public long getMerchantId() {

        return merchantId;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getName() {

        return name;
    }

    public void setBenefit(double benefit) {

        this.benefit = benefit;
    }

    public double getBenefit() {

        return benefit;
    }

    public void setLimitPrice(double limitPrice) {

        this.limitPrice = limitPrice;
    }

    public double getLimitPrice() {

        return limitPrice;
    }

    public void setBeginDate(Date beginDate) {

        this.beginDate = beginDate;
    }

    public Date getBeginDate() {

        return beginDate;
    }

    public void setEndDate(Date endDate) {

        this.endDate = endDate;
    }

    public Date getEndDate() {

        return endDate;
    }

    public void setCanUseCoupon(int canUseCoupon) {

        this.canUseCoupon = canUseCoupon;
    }

    public int getCanUseCoupon() {

        return canUseCoupon;
    }

    public void setState(int state) {

        this.state = state;
    }

    public int getState() {

        return state;
    }

    public int getCanUseSeckill() {

        return canUseSeckill;
    }

    public void setCanUseSeckill(int canUseSeckill) {

        this.canUseSeckill = canUseSeckill;
    }
}
