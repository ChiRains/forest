package com.qcloud.component.orderform.model;

public class OrderState {

    private int     orderState;

    private int     merchantOrderState;

    private int     personalOrderState;

    private String  orderStateDesc;

    private String  merchantOrderStateDesc;

    private String  personalOrderStateDesc;

    private String  desc;

    private boolean init;

    private boolean finish;

    private boolean canRefund;

    private boolean canReturn;

    private boolean canExchange;

    public int getMerchantOrderState() {

        return merchantOrderState;
    }

    public void setMerchantOrderState(int merchantOrderState) {

        this.merchantOrderState = merchantOrderState;
    }

    public int getPersonalOrderState() {

        return personalOrderState;
    }

    public void setPersonalOrderState(int personalOrderState) {

        this.personalOrderState = personalOrderState;
    }

    public String getDesc() {

        return desc;
    }

    public void setDesc(String desc) {

        this.desc = desc;
    }

    public String getMerchantOrderStateDesc() {

        return merchantOrderStateDesc;
    }

    public void setMerchantOrderStateDesc(String merchantOrderStateDesc) {

        this.merchantOrderStateDesc = merchantOrderStateDesc;
    }

    public int getOrderState() {

        return orderState;
    }

    public void setOrderState(int orderState) {

        this.orderState = orderState;
    }

    public String getOrderStateDesc() {

        return orderStateDesc;
    }

    public void setOrderStateDesc(String orderStateDesc) {

        this.orderStateDesc = orderStateDesc;
    }

    public String getPersonalOrderStateDesc() {

        return personalOrderStateDesc;
    }

    public void setPersonalOrderStateDesc(String personalOrderStateDesc) {

        this.personalOrderStateDesc = personalOrderStateDesc;
    }

    public boolean isInit() {

        return init;
    }

    public void setInit(boolean init) {

        this.init = init;
    }

    public boolean isFinish() {

        return finish;
    }

    public void setFinish(boolean finish) {

        this.finish = finish;
    }

    public boolean isCanRefund() {

        return canRefund;
    }

    public void setCanRefund(boolean canRefund) {

        this.canRefund = canRefund;
    }

    public boolean isCanReturn() {

        return canReturn;
    }

    public void setCanReturn(boolean canReturn) {

        this.canReturn = canReturn;
    }

    public boolean isCanExchange() {

        return canExchange;
    }

    public void setCanExchange(boolean canExchange) {

        this.canExchange = canExchange;
    }
}
