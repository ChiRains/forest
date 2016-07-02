package com.qcloud.component.marketing.web.vo;

import java.util.Date;
import java.math.BigDecimal;

public class CouponItemsVO {

    private long   id;

    // 优惠券ID
    private long   couponID;

    // 优惠券名称
    private String name;

    // 面额
    private double price;

    // 总数量
    private int    totalNum;

    // 发放数量
    private int    sendNum;

    // 已使用数量
    private int    usedNum;

    private double limitPrice;

    private int    type;

    // 开始时间
    private String startDateStr;

    // 结束时间
    private String endDateStr;

    public CouponItemsVO() {

    }

    public void setId(long id) {

        this.id = id;
    }

    public long getId() {

        return id;
    }

    public void setCouponID(long couponID) {

        this.couponID = couponID;
    }

    public long getCouponID() {

        return couponID;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getName() {

        return name;
    }

    public void setPrice(double price) {

        this.price = price;
    }

    public double getPrice() {

        return price;
    }

    public void setTotalNum(int totalNum) {

        this.totalNum = totalNum;
    }

    public int getTotalNum() {

        return totalNum;
    }

    public void setSendNum(int sendNum) {

        this.sendNum = sendNum;
    }

    public int getSendNum() {

        return sendNum;
    }

    public void setUsedNum(int usedNum) {

        this.usedNum = usedNum;
    }

    public int getUsedNum() {

        return usedNum;
    }

    public double getLimitPrice() {

        return limitPrice;
    }

    public void setLimitPrice(double limitPrice) {

        this.limitPrice = limitPrice;
    }

    public int getType() {

        return type;
    }

    public void setType(int type) {

        this.type = type;
    }

    public String getStartDateStr() {

        return startDateStr;
    }

    public void setStartDateStr(String startDateStr) {

        this.startDateStr = startDateStr;
    }

    public String getEndDateStr() {

        return endDateStr;
    }

    public void setEndDateStr(String endDateStr) {

        this.endDateStr = endDateStr;
    }
}
