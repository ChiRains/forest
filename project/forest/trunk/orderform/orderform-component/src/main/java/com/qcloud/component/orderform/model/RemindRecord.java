package com.qcloud.component.orderform.model;

import java.util.Date;
import java.math.BigDecimal;

public class RemindRecord {

    // ID
    private long id;

    // 总单ID
    private long orderId;

    // 子单ID
    private long subOrderId;

    // 下单时间
    private Date orderDate;

    // 催促时间
    private Date time;

    public RemindRecord() {

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

    public void setSubOrderId(long subOrderId) {

        this.subOrderId = subOrderId;
    }

    public long getSubOrderId() {

        return subOrderId;
    }

    public void setOrderDate(Date orderDate) {

        this.orderDate = orderDate;
    }

    public Date getOrderDate() {

        return orderDate;
    }

    public void setTime(Date time) {

        this.time = time;
    }

    public Date getTime() {

        return time;
    }
}
