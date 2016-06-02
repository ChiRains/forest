package com.qcloud.component.my.web.vo;

import java.util.Date;
import java.math.BigDecimal;

public class MyCouponVO {

    // ID
    private long    id;

    // 用户
    private long    userId;

    // 优惠劵活动
    private long    couponId;

    // 优惠劵项
    private long    couponItemId;

    // 有效时间
    private Date    validDate;

    // 优惠券名称
    private String  name;

    // 面额
    private double  price;

    // 状态 1未使用 2已使用
    private int     state;

    // 使用订单
    private long    orderId;

    // 下订单时间
    private Date    orderDate;

    private String  validDateStr;

    private String  description;

    private double  limitPrice;

    // private Long merchantId;
    private String  code;

    private boolean merchant;

    private boolean expire;

    private String  orderDateStr;

    public MyCouponVO() {

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

    public void setCouponId(long couponId) {

        this.couponId = couponId;
    }

    public long getCouponId() {

        return couponId;
    }

    public void setCouponItemId(long couponItemId) {

        this.couponItemId = couponItemId;
    }

    public long getCouponItemId() {

        return couponItemId;
    }

    public void setValidDate(Date validDate) {

        this.validDate = validDate;
    }

    public Date getValidDate() {

        return validDate;
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

    public void setState(int state) {

        this.state = state;
    }

    public int getState() {

        return state;
    }

    public void setOrderId(long orderId) {

        this.orderId = orderId;
    }

    public long getOrderId() {

        return orderId;
    }

    public void setOrderDate(Date orderDate) {

        this.orderDate = orderDate;
    }

    public Date getOrderDate() {

        return orderDate;
    }

    public String getValidDateStr() {

        return validDateStr;
    }

    public void setValidDateStr(String validDateStr) {

        this.validDateStr = validDateStr;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {

        this.description = description;
    }

    public double getLimitPrice() {

        return limitPrice;
    }

    public void setLimitPrice(double limitPrice) {

        this.limitPrice = limitPrice;
    }

    public String getCode() {

        return code;
    }

    public void setCode(String code) {

        this.code = code;
    }

    public boolean isMerchant() {

        return merchant;
    }

    public void setMerchant(boolean merchant) {

        this.merchant = merchant;
    }

    public boolean isExpire() {

        return expire;
    }

    public void setExpire(boolean expire) {

        this.expire = expire;
    }

    public String getOrderDateStr() {

        return orderDateStr;
    }

    public void setOrderDateStr(String orderDateStr) {

        this.orderDateStr = orderDateStr;
    }
}
