package com.qcloud.component.my.model.query;

import java.util.Date;

public class MyEvaluationQuery {

    private long userId;

    private Date orderTime;

    private long orderItemDetailId;

    public long getUserId() {

        return userId;
    }

    public void setUserId(long userId) {

        this.userId = userId;
    }

    public Date getOrderTime() {

        return orderTime;
    }

    public void setOrderTime(Date orderTime) {

        this.orderTime = orderTime;
    }

    public long getOrderItemDetailId() {

        return orderItemDetailId;
    }

    public void setOrderItemDetailId(long orderItemDetailId) {

        this.orderItemDetailId = orderItemDetailId;
    }
}
