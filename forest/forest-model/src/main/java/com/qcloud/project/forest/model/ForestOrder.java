package com.qcloud.project.forest.model;

import java.util.Date;

public class ForestOrder {

    private long   id;

    private long   orderId;

    private Date   orderDate;

    private String orderNumber;

    private long   merchantId;

    private long   storeId;

    private long   userId;

    private long   giftCouponId;

    private int    state;

    private Date   deliveryDate;

    private Date   pickUpDate;

    public ForestOrder() {

    }

    public void setId(long id) {

        this.id = id;
    }

    public long getId() {

        return id;
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

    public void setOrderNumber(String orderNumber) {

        this.orderNumber = orderNumber;
    }

    public String getOrderNumber() {

        return orderNumber;
    }

    public void setMerchantId(long merchantId) {

        this.merchantId = merchantId;
    }

    public long getMerchantId() {

        return merchantId;
    }

    public void setStoreId(long storeId) {

        this.storeId = storeId;
    }

    public long getStoreId() {

        return storeId;
    }

    public void setUserId(long userId) {

        this.userId = userId;
    }

    public long getUserId() {

        return userId;
    }

    public void setState(int state) {

        this.state = state;
    }

    public int getState() {

        return state;
    }

    public long getGiftCouponId() {

        return giftCouponId;
    }

    public void setGiftCouponId(long giftCouponId) {

        this.giftCouponId = giftCouponId;
    }

    
    public Date getDeliveryDate() {
    
        return deliveryDate;
    }

    
    public void setDeliveryDate(Date deliveryDate) {
    
        this.deliveryDate = deliveryDate;
    }

    
    public Date getPickUpDate() {
    
        return pickUpDate;
    }

    
    public void setPickUpDate(Date pickUpDate) {
    
        this.pickUpDate = pickUpDate;
    }
}
