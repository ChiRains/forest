package com.qcloud.component.orderform.model;

import com.qcloud.component.orderform.OrderObserver;

public class OrderObserverDefination {

    private int           state;

    private int           type;

    private OrderObserver orderObserver;

    private String        desc;

    private String[]      variable;

    public int getState() {

        return state;
    }

    public void setState(int state) {

        this.state = state;
    }

    public int getType() {

        return type;
    }

    public void setType(int type) {

        this.type = type;
    }

    public String getDesc() {

        return desc;
    }

    public void setDesc(String desc) {

        this.desc = desc;
    }

    public String[] getVariable() {

        return variable;
    }

    public void setVariable(String[] variable) {

        this.variable = variable;
    }

    public OrderObserver getOrderObserver() {

        return orderObserver;
    }

    public void setOrderObserver(OrderObserver orderObserver) {

        this.orderObserver = orderObserver;
    }
}
