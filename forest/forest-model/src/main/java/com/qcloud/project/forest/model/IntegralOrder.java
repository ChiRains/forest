package com.qcloud.project.forest.model;

import java.util.Date;

public class IntegralOrder {

    private long   id;

    private String orderNumber;

    private Date   time;

    private long   userId;

    private double sum;

    private double cash;

    private int    integral;

    private long   unifiedMerchandiseId;

    private String name;

    private String image;

    private String specifications;

    // 10待发货20待收货30已完成
    private int    state;

    private int    paymentMode;

    private String consignee;

    private String address;

    private String email;

    private String mobile;

    // 0没催促1已催促
    private int    remind;

    private String expressName;

    private String expressCode;

    private String expressNumber;

    public IntegralOrder() {

    }

    public IntegralOrder(long id, String orderNumber, Date time, long userId, double sum, double cash, int integral, long unifiedMerchandiseId, String name, String image, String specifications, int state, int paymentMode, String consignee, String address, String email, String mobile, int remind) {

        this.id = id;
        this.orderNumber = orderNumber;
        this.time = time;
        this.userId = userId;
        this.sum = sum;
        this.cash = cash;
        this.integral = integral;
        this.unifiedMerchandiseId = unifiedMerchandiseId;
        this.name = name;
        this.image = image;
        this.specifications = specifications;
        this.state = state;
        this.paymentMode = paymentMode;
        this.consignee = consignee;
        this.address = address;
        this.email = email;
        this.mobile = mobile;
        this.remind = remind;
    }

    public void setId(long id) {

        this.id = id;
    }

    public long getId() {

        return id;
    }

    public void setOrderNumber(String orderNumber) {

        this.orderNumber = orderNumber;
    }

    public String getOrderNumber() {

        return orderNumber;
    }

    public void setTime(Date time) {

        this.time = time;
    }

    public Date getTime() {

        return time;
    }

    public void setUserId(long userId) {

        this.userId = userId;
    }

    public long getUserId() {

        return userId;
    }

    public void setSum(double sum) {

        this.sum = sum;
    }

    public double getSum() {

        return sum;
    }

    public void setCash(double cash) {

        this.cash = cash;
    }

    public double getCash() {

        return cash;
    }

    public void setIntegral(int integral) {

        this.integral = integral;
    }

    public int getIntegral() {

        return integral;
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

    public void setSpecifications(String specifications) {

        this.specifications = specifications;
    }

    public String getSpecifications() {

        return specifications;
    }

    public void setState(int state) {

        this.state = state;
    }

    public int getState() {

        return state;
    }

    public void setPaymentMode(int paymentMode) {

        this.paymentMode = paymentMode;
    }

    public int getPaymentMode() {

        return paymentMode;
    }

    public void setConsignee(String consignee) {

        this.consignee = consignee;
    }

    public String getConsignee() {

        return consignee;
    }

    public void setAddress(String address) {

        this.address = address;
    }

    public String getAddress() {

        return address;
    }

    public void setEmail(String email) {

        this.email = email;
    }

    public String getEmail() {

        return email;
    }

    public void setMobile(String mobile) {

        this.mobile = mobile;
    }

    public String getMobile() {

        return mobile;
    }

    public void setRemind(int remind) {

        this.remind = remind;
    }

    public int getRemind() {

        return remind;
    }

    public long getUnifiedMerchandiseId() {

        return unifiedMerchandiseId;
    }

    public void setUnifiedMerchandiseId(long unifiedMerchandiseId) {

        this.unifiedMerchandiseId = unifiedMerchandiseId;
    }

    public String getExpressName() {

        return expressName;
    }

    public void setExpressName(String expressName) {

        this.expressName = expressName;
    }

    public String getExpressCode() {

        return expressCode;
    }

    public void setExpressCode(String expressCode) {

        this.expressCode = expressCode;
    }

    public String getExpressNumber() {

        return expressNumber;
    }

    public void setExpressNumber(String expressNumber) {

        this.expressNumber = expressNumber;
    }
}
