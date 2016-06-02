package com.qcloud.component.my.web.vo;

import java.util.Date;

public class MyCommissionWithdrawCashVO {

    // 当次提现值
    private double commissionCash;

    // 持卡人
    private String cardholder;

    // 银行
    private String bank;

    // 卡
    private String card;

    // 发生时间
    private Date   time;

    private String timeStr;

    // 完成时间
    private Date   completeTime;

    private String completeTimeStr;

    // 类型1. 申请 2.审核 3.成功
    private int    state;

    public MyCommissionWithdrawCashVO() {

    }

    public void setCommissionCash(double commissionCash) {

        this.commissionCash = commissionCash;
    }

    public double getCommissionCash() {

        return commissionCash;
    }

    public void setTime(Date time) {

        this.time = time;
    }

    public Date getTime() {

        return time;
    }

    public void setCompleteTime(Date completeTime) {

        this.completeTime = completeTime;
    }

    public Date getCompleteTime() {

        return completeTime;
    }

    public void setState(int state) {

        this.state = state;
    }

    public int getState() {

        return state;
    }

    public String getBank() {

        return bank;
    }

    public void setBank(String bank) {

        this.bank = bank;
    }

    public String getCard() {

        return card;
    }

    public void setCard(String card) {

        this.card = card;
    }

    public String getTimeStr() {

        return timeStr;
    }

    public void setTimeStr(String timeStr) {

        this.timeStr = timeStr;
    }

    public String getCompleteTimeStr() {

        return completeTimeStr;
    }

    public void setCompleteTimeStr(String completeTimeStr) {

        this.completeTimeStr = completeTimeStr;
    }

    public String getCardholder() {

        return cardholder;
    }

    public void setCardholder(String cardholder) {

        this.cardholder = cardholder;
    }
}
