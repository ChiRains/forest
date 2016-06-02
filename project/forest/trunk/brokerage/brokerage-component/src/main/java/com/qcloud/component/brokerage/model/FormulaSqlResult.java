package com.qcloud.component.brokerage.model;

import java.util.Date;

public class FormulaSqlResult {

    // ID
    private long   id;

    // 类别 data pool type...
    private int    type;

    // 名称
    private String name;

    // 图
    private String image;

    // 佣金
    private double brokerage;

    // 下定时间
    private Date   orderTime;

    // 用户ID
    private long   userId;

    // 卖家ID
    private long   merchantId;

    public void setId(long id) {

        this.id = id;
    }

    public long getId() {

        return id;
    }

    public void setType(int type) {

        this.type = type;
    }

    public int getType() {

        return type;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getName() {

        return name;
    }

    public void setImage(String image) {

        this.image = image;
    }

    public String getImage() {

        return image;
    }

    public void setBrokerage(double brokerage) {

        this.brokerage = brokerage;
    }

    public double getBrokerage() {

        return brokerage;
    }

    public void setOrderTime(Date orderTime) {

        this.orderTime = orderTime;
    }

    public Date getOrderTime() {

        return orderTime;
    }

    public void setUserId(long userId) {

        this.userId = userId;
    }

    public long getUserId() {

        return userId;
    }

    public void setMerchantId(long merchantId) {

        this.merchantId = merchantId;
    }

    public long getMerchantId() {

        return merchantId;
    }

    @Override
    public String toString() {

        return "id=" + id + ", type=" + type + ", name=" + name + ", image=" + image + ", brokerage=" + brokerage + ", orderTime=" + orderTime + ", userId=" + userId + ", merchantId=" + merchantId + "";
    }
}
