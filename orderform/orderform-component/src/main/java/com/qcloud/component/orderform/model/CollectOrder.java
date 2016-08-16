package com.qcloud.component.orderform.model;

import java.util.Date;
import java.math.BigDecimal;

public class CollectOrder {

    // ID
    private long   id;

    // 订单号
    private String orderNumber;

    // 用户ID
    private long   userId;

    // 下单时间
    private Date   time;

    // 最后更新时间
    private Date   lastUpdateTime;

    // 订单金额
    private double sum;

    // 现金
    private double cash;

    // 折扣
    private int    discount;

    // 优惠劵
    private double coupon;

    // 邮费
    private double postage;

    // 积分
    private int    integral;

    // 消费币
    private double consumption;

    // 优惠面额
    private double preferential;

    // 收货人
    private String consignee;

    // 收货地址
    private String address;

    // 收货电话
    private String mobile;

    // 收货人邮箱
    private String email;

    // 现金支付方式 101支付宝 102微信支付 103银联 104货到付款 105钱包
    private int    paymentMode;

    // 状态,待付款,已付款,待发货,发货中,已签收
    private int    state;

    private int    prestate;

    // 是否开发票
    private int    needInvoice;

    // 发票类型
    private int    invoiceType;

    // 发票抬头
    private String invoiceHead;

    // 发票内容
    private String invoiceContent;

    // 能否评论,1可以 2不行
    private int    evaluation;

    // 能否售后,1可以 2不行
    private int    afterSale;

    private Date   stateValidTime;

    // 省
    private String province;

    // 市
    private String city;

    // 区
    private String district;

    public CollectOrder() {

    }

    public CollectOrder(long id, String orderNumber, long userId, Date time, Date lastUpdateTime, double sum, double cash, int discount, double coupon, double postage, int integral, double consumption, double preferential, String consignee, String address, String mobile, String email, int paymentMode, int state, int prestate, int needInvoice, int invoiceType, String invoiceHead, String invoiceContent, int evaluation, int afterSale, Date stateValidTime, String province, String city, String district) {

        this.id = id;
        this.orderNumber = orderNumber;
        this.userId = userId;
        this.time = time;
        this.lastUpdateTime = lastUpdateTime;
        this.sum = sum;
        this.cash = cash;
        this.discount = discount;
        this.coupon = coupon;
        this.postage = postage;
        this.integral = integral;
        this.consumption = consumption;
        this.preferential = preferential;
        this.consignee = consignee;
        this.address = address;
        this.mobile = mobile;
        this.email = email;
        this.paymentMode = paymentMode;
        this.state = state;
        this.prestate = prestate;
        this.needInvoice = needInvoice;
        this.invoiceType = invoiceType;
        this.invoiceHead = invoiceHead;
        this.invoiceContent = invoiceContent;
        this.evaluation = evaluation;
        this.afterSale = afterSale;
        this.stateValidTime = stateValidTime;
        this.province = province;
        this.city = city;
        this.district = district;
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

    public void setUserId(long userId) {

        this.userId = userId;
    }

    public long getUserId() {

        return userId;
    }

    public void setTime(Date time) {

        this.time = time;
    }

    public Date getTime() {

        return time;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {

        this.lastUpdateTime = lastUpdateTime;
    }

    public Date getLastUpdateTime() {

        return lastUpdateTime;
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

    public void setDiscount(int discount) {

        this.discount = discount;
    }

    public int getDiscount() {

        return discount;
    }

    public void setCoupon(double coupon) {

        this.coupon = coupon;
    }

    public double getCoupon() {

        return coupon;
    }

    public void setPostage(double postage) {

        this.postage = postage;
    }

    public double getPostage() {

        return postage;
    }

    public void setIntegral(int integral) {

        this.integral = integral;
    }

    public int getIntegral() {

        return integral;
    }

    public void setConsumption(double consumption) {

        this.consumption = consumption;
    }

    public double getConsumption() {

        return consumption;
    }

    public void setPreferential(double preferential) {

        this.preferential = preferential;
    }

    public double getPreferential() {

        return preferential;
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

    public void setMobile(String mobile) {

        this.mobile = mobile;
    }

    public String getMobile() {

        return mobile;
    }

    public void setEmail(String email) {

        this.email = email;
    }

    public String getEmail() {

        return email;
    }

    public void setPaymentMode(int paymentMode) {

        this.paymentMode = paymentMode;
    }

    public int getPaymentMode() {

        return paymentMode;
    }

    public void setState(int state) {

        this.state = state;
    }

    public int getState() {

        return state;
    }

    public void setPrestate(int prestate) {

        this.prestate = prestate;
    }

    public int getPrestate() {

        return prestate;
    }

    public void setNeedInvoice(int needInvoice) {

        this.needInvoice = needInvoice;
    }

    public int getNeedInvoice() {

        return needInvoice;
    }

    public void setInvoiceType(int invoiceType) {

        this.invoiceType = invoiceType;
    }

    public int getInvoiceType() {

        return invoiceType;
    }

    public void setInvoiceHead(String invoiceHead) {

        this.invoiceHead = invoiceHead;
    }

    public String getInvoiceHead() {

        return invoiceHead;
    }

    public void setInvoiceContent(String invoiceContent) {

        this.invoiceContent = invoiceContent;
    }

    public String getInvoiceContent() {

        return invoiceContent;
    }

    public void setEvaluation(int evaluation) {

        this.evaluation = evaluation;
    }

    public int getEvaluation() {

        return evaluation;
    }

    public void setAfterSale(int afterSale) {

        this.afterSale = afterSale;
    }

    public int getAfterSale() {

        return afterSale;
    }

    public void setStateValidTime(Date stateValidTime) {

        this.stateValidTime = stateValidTime;
    }

    public Date getStateValidTime() {

        return stateValidTime;
    }

    public String getProvince() {

        return province;
    }

    public void setProvince(String province) {

        this.province = province;
    }

    public String getCity() {

        return city;
    }

    public void setCity(String city) {

        this.city = city;
    }

    public String getDistrict() {

        return district;
    }

    public void setDistrict(String district) {

        this.district = district;
    }
}
