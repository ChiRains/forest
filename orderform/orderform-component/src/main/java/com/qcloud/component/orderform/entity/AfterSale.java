package com.qcloud.component.orderform.entity;

public abstract class AfterSale {

    private String explain;

    private String reason;

    private int    number;

    public String getExplain() {

        return explain;
    }

    public void setExplain(String explain) {

        this.explain = explain;
    }

    public String getReason() {

        return reason;
    }

    public void setReason(String reason) {

        this.reason = reason;
    }

    public int getNumber() {

        return number;
    }

    public void setNumber(int number) {

        this.number = number;
    }
}
