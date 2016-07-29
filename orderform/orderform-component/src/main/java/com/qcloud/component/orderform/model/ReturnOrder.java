package com.qcloud.component.orderform.model;

import java.util.Date;

public class ReturnOrder {

    // ID
    private long   id;

    // 总单ID
    private long   orderId;

    // 下单时间
    private Date   orderDate;

    // 订单号
    private String orderNumber;

    // 子单ID
    private long   subOrderId;

    // 商家ID
    private long   merchantId;

    // 门店ID
    private long   storeId;

    // 退款金额
    private double sum;

    // 状态(1:已申请 2:通过 3:未通过 )
    private int    state;

    // 用户id
    private long   userId;

    private Date   time;

    // 退货单号
    private String returnNumber;

    // 说明
    private String explain;

    // 原因
    private String reason;

    // 物流公司
    private String logisticsCompany;

    // 物流查询号
    private String logisticsNumber;

    private Date   lastUpdateTime;

    private Date   stateValidTime;

    private double afterSaleSum;

    private String afterSaleImage;

    private int    returnType;

    public ReturnOrder() {

    }

    public ReturnOrder(long id, long orderId, Date orderDate, String orderNumber, long subOrderId, long merchantId, long storeId, double sum, int state, long userId, Date time, String returnNumber, String explain, String reason, String logisticsCompany, String logisticsNumber, Date lastUpdateTime, Date stateValidTime) {

        this.id = id;
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.orderNumber = orderNumber;
        this.subOrderId = subOrderId;
        this.merchantId = merchantId;
        this.storeId = storeId;
        this.sum = sum;
        this.state = state;
        this.userId = userId;
        this.time = time;
        this.returnNumber = returnNumber;
        this.explain = explain;
        this.reason = reason;
        this.logisticsCompany = logisticsCompany;
        this.logisticsNumber = logisticsNumber;
        this.lastUpdateTime = lastUpdateTime;
        this.stateValidTime = stateValidTime;
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

    public void setSubOrderId(long subOrderId) {

        this.subOrderId = subOrderId;
    }

    public long getSubOrderId() {

        return subOrderId;
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

    public void setSum(double sum) {

        this.sum = sum;
    }

    public double getSum() {

        return sum;
    }

    public void setState(int state) {

        this.state = state;
    }

    public int getState() {

        return state;
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

    public void setReturnNumber(String returnNumber) {

        this.returnNumber = returnNumber;
    }

    public String getReturnNumber() {

        return returnNumber;
    }

    public void setExplain(String explain) {

        this.explain = explain;
    }

    public String getExplain() {

        return explain;
    }

    public void setReason(String reason) {

        this.reason = reason;
    }

    public String getReason() {

        return reason;
    }

    public void setLogisticsCompany(String logisticsCompany) {

        this.logisticsCompany = logisticsCompany;
    }

    public String getLogisticsCompany() {

        return logisticsCompany;
    }

    public void setLogisticsNumber(String logisticsNumber) {

        this.logisticsNumber = logisticsNumber;
    }

    public String getLogisticsNumber() {

        return logisticsNumber;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {

        this.lastUpdateTime = lastUpdateTime;
    }

    public Date getLastUpdateTime() {

        return lastUpdateTime;
    }

    public void setStateValidTime(Date stateValidTime) {

        this.stateValidTime = stateValidTime;
    }

    public Date getStateValidTime() {

        return stateValidTime;
    }

    public double getAfterSaleSum() {

        return afterSaleSum;
    }

    public void setAfterSaleSum(double afterSaleSum) {

        this.afterSaleSum = afterSaleSum;
    }

    public String getAfterSaleImage() {

        return afterSaleImage;
    }

    public void setAfterSaleImage(String afterSaleImage) {

        this.afterSaleImage = afterSaleImage;
    }

    public int getReturnType() {

        return returnType;
    }

    public void setReturnType(int returnType) {

        this.returnType = returnType;
    }
}
