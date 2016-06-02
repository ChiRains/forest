package com.qcloud.component.orderform.model.query;

import java.util.Date;

public class OrderItemQuery {

    private Long orderId;

    private Date time;

    public OrderItemQuery() {

    }

    public Long getOrderId() {

        return orderId;
    }

    public void setOrderId(Long orderId) {

        this.orderId = orderId;
    }

    public Date getTime() {

        return time;
    }

    public void setTime(Date time) {

        this.time = time;
    }
}
