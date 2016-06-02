package com.qcloud.component.brokerage.web.vo.admin;

import java.util.Date;
import java.math.BigDecimal;

public class AdminUpgradeOrderVO {

    // ID
    private long   id;

    // 订单号
    private String orderNumber;

    private long   userId;

    private Date   time;

    private Date   deadlinePayTime;

    private long   originalGradeId;

    private long   upgradeGradeId;

    // 现金
    private double cash;

    // 现金支付方式 101支付宝 102微信支付 103银联 104货到付款 105钱包
    private int    paymentMode;

    private int    state;

    private String originalGrade;

    private String upgradeGrade;

    public AdminUpgradeOrderVO() {

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

    public void setDeadlinePayTime(Date deadlinePayTime) {

        this.deadlinePayTime = deadlinePayTime;
    }

    public Date getDeadlinePayTime() {

        return deadlinePayTime;
    }

    public void setOriginalGradeId(long originalGradeId) {

        this.originalGradeId = originalGradeId;
    }

    public long getOriginalGradeId() {

        return originalGradeId;
    }

    public void setUpgradeGradeId(long upgradeGradeId) {

        this.upgradeGradeId = upgradeGradeId;
    }

    public long getUpgradeGradeId() {

        return upgradeGradeId;
    }

    public void setCash(double cash) {

        this.cash = cash;
    }

    public double getCash() {

        return cash;
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

    public String getOriginalGrade() {

        return originalGrade;
    }

    public void setOriginalGrade(String originalGrade) {

        this.originalGrade = originalGrade;
    }

    public String getUpgradeGrade() {

        return upgradeGrade;
    }

    public void setUpgradeGrade(String upgradeGrade) {

        this.upgradeGrade = upgradeGrade;
    }
}
