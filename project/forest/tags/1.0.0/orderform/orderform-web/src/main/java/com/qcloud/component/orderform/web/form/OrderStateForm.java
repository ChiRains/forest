package com.qcloud.component.orderform.web.form;

import java.util.Date;

public class OrderStateForm {

    public Long getOrderId() {

        return orderId;
    }

    public void setOrderId(Long orderId) {

        this.orderId = orderId;
    }

    public Long getSubOrderId() {

        return subOrderId;
    }

    public void setSubOrderId(Long subOrderId) {

        this.subOrderId = subOrderId;
    }

//    public Long getOrderItemId() {
//
//        return orderItemId;
//    }
//
//    public void setOrderItemId(Long orderItemId) {
//
//        this.orderItemId = orderItemId;
//    }
//
//    public Long getOrderItemDetailId() {
//
//        return orderItemDetailId;
//    }
//
//    public void setOrderItemDetailId(Long orderItemDetailId) {
//
//        this.orderItemDetailId = orderItemDetailId;
//    }

    public Date getOrderDate() {

        return orderDate;
    }

    public void setOrderDate(Date orderDate) {

        this.orderDate = orderDate;
    }

    private Long orderId;

    private Long subOrderId;

//    private Long orderItemId;
//
//    private Long orderItemDetailId;

    private Date orderDate;
}
