package com.qcloud.component.sellercenter.web.vo.admin;

import java.util.Date;

public class AdminMerchantOrderFormVO {

    // ID
    private long   id;

    // 卖家
    private long   merchantId;

    private long   userId;

    private String userName;

    private String nickname;

    // 总单
    private long   orderId;

    private String orderNumber;

    // 子单ID
    private long   subOrderId;

    private String subOrderNumber;

    // 状态
    private int    state;

    private String stateStr;

    private double sum;

    // 下单时间
    private Date   time;

    private String address;

    private String mobile;

    private String deliveryTimeStr;

    private String explain;

    private String consignee;

    private long   storeId;

    private String pickupAddressStr;

    private String storeName;

    // 是否开发票
    private int    needInvoiceType;

    // 发票类型
    private int    invoiceType;

    private double cash;

    public AdminMerchantOrderFormVO() {

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

    public void setOrderId(long orderId) {

        this.orderId = orderId;
    }

    public long getOrderId() {

        return orderId;
    }

    public void setSubOrderId(long subOrderId) {

        this.subOrderId = subOrderId;
    }

    public long getSubOrderId() {

        return subOrderId;
    }

    public void setState(int state) {

        this.state = state;
    }

    public int getState() {

        return state;
    }

    public void setTime(Date time) {

        this.time = time;
    }

    public String getOrderNumber() {

        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {

        this.orderNumber = orderNumber;
    }

    public String getSubOrderNumber() {

        return subOrderNumber;
    }

    public void setSubOrderNumber(String subOrderNumber) {

        this.subOrderNumber = subOrderNumber;
    }

    public String getStateStr() {

        return stateStr;
    }

    public void setStateStr(String stateStr) {

        this.stateStr = stateStr;
    }

    public Date getTime() {

        return time;
    }

    public long getUserId() {

        return userId;
    }

    public void setUserId(long userId) {

        this.userId = userId;
    }

    public String getUserName() {

        return userName;
    }

    public void setUserName(String userName) {

        this.userName = userName;
    }

    public double getSum() {

        return sum;
    }

    public void setSum(double sum) {

        this.sum = sum;
    }

    public String getAddress() {

        return address;
    }

    public void setAddress(String address) {

        this.address = address;
    }

    public String getMobile() {

        return mobile;
    }

    public void setMobile(String mobile) {

        this.mobile = mobile;
    }

    public String getDeliveryTimeStr() {

        return deliveryTimeStr;
    }

    public void setDeliveryTimeStr(String deliveryTimeStr) {

        this.deliveryTimeStr = deliveryTimeStr;
    }

    public String getExplain() {

        return explain;
    }

    public void setExplain(String explain) {

        this.explain = explain;
    }

    public String getConsignee() {

        return consignee;
    }

    public void setConsignee(String consignee) {

        this.consignee = consignee;
    }

    public long getStoreId() {

        return storeId;
    }

    public void setStoreId(long storeId) {

        this.storeId = storeId;
    }

    public String getNickname() {

        return nickname;
    }

    public void setNickname(String nickname) {

        this.nickname = nickname;
    }

    public String getPickupAddressStr() {

        return pickupAddressStr;
    }

    public void setPickupAddressStr(String pickupAddressStr) {

        this.pickupAddressStr = pickupAddressStr;
    }

    public String getStoreName() {

        return storeName;
    }

    public void setStoreName(String storeName) {

        this.storeName = storeName;
    }

    public int getNeedInvoiceType() {

        return needInvoiceType;
    }

    public void setNeedInvoiceType(int needInvoiceType) {

        this.needInvoiceType = needInvoiceType;
    }

    public int getInvoiceType() {

        return invoiceType;
    }

    public void setInvoiceType(int invoiceType) {

        this.invoiceType = invoiceType;
    }

    public double getCash() {

        return cash;
    }

    public void setCash(double cash) {

        this.cash = cash;
    }
}
