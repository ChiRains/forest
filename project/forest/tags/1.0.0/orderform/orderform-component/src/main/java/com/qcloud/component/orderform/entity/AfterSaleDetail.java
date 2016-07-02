package com.qcloud.component.orderform.entity;

public class AfterSaleDetail extends AfterSale {

    private OrderItemDetailEntity orderItemDetail;

    public OrderItemDetailEntity getOrderItemDetail() {

        return orderItemDetail;
    }

    public void setOrderItemDetail(OrderItemDetailEntity orderItemDetail) {

        this.orderItemDetail = orderItemDetail;
    }
}
