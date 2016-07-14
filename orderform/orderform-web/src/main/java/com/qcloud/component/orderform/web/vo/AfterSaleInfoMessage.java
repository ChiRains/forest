package com.qcloud.component.orderform.web.vo;


public class AfterSaleInfoMessage {

    private int    state;

    private String stateDesc;

    private String message;

    public int getState() {

        return state;
    }

    public void setState(int state) {

        this.state = state;
    }

    public String getStateDesc() {

        return stateDesc;
    }

    public void setStateDesc(String stateDesc) {

        this.stateDesc = stateDesc;
    }

    public String getMessage() {

        return message;
    }

    public void setMessage(String message) {

        this.message = message;
    }
}
