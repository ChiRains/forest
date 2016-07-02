package com.qcloud.component.orderform.entity;

public class AfterSaleItem extends AfterSale {

    private OrderItemEntity orderItem;

    public OrderItemEntity getOrderItem() {

        return orderItem;
    }

    public void setOrderItem(OrderItemEntity orderItem) {

        this.orderItem = orderItem;
    }
}
