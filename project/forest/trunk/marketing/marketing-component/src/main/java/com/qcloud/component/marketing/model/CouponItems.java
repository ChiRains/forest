package com.qcloud.component.marketing.model;


public class CouponItems {

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

    // 使用限额
    private double limitPrice;

    private int    type;

    public CouponItems() {

    }

    public CouponItems(long id, long couponID, String name, double price, int totalNum, int sendNum, int usedNum, double limitPrice) {

        super();
        this.id = id;
        this.couponID = couponID;
        this.name = name;
        this.price = price;
        this.totalNum = totalNum;
        this.sendNum = sendNum;
        this.usedNum = usedNum;
        this.limitPrice = limitPrice;
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
}
