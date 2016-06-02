package com.qcloud.component.orderform.entity;

import java.util.Date;
import com.qcloud.component.orderform.QAfterSaleDetail;
import com.qcloud.component.orderform.model.ExchangeOrderItemDetail;

public class ExchangeAfterSaleOrderItem extends AfterSaleOrderItem implements QAfterSaleDetail {

    private OrderItemDetailEntity   orderItemDetailEntity;

    private ExchangeOrderItemDetail exchangeOrderItemDetail;

    public ExchangeAfterSaleOrderItem(OrderItemDetailEntity orderItemDetailEntity, AfterSaleOrder afterSaleOrder, ExchangeOrderItemDetail exchangeOrderItemDetail) {

        super(afterSaleOrder);
        this.exchangeOrderItemDetail = exchangeOrderItemDetail;
        this.orderItemDetailEntity = orderItemDetailEntity;
    }

    public ExchangeOrderItemDetail getExchangeOrderItemDetail() {

        return exchangeOrderItemDetail;
    }

    public OrderItemDetailEntity getOrderItemDetailEntity() {

        return orderItemDetailEntity;
    }

    @Override
    public long getAfterSaleOrderItemId() {

        return exchangeOrderItemDetail.getId();
    }

    @Override
    public OrderItemDetailEntity getOrderItemDetail() {

        return orderItemDetailEntity;
    }

    @Override
    public int getState() {

        return exchangeOrderItemDetail.getState();
    }

    @Override
    public Date getTime() {

        return exchangeOrderItemDetail.getTime();
    }

    @Override
    public int getNumber() {

        return exchangeOrderItemDetail.getNumber();
    }

    @Override
    public double getSum() {

        return 0.0;
    }

    @Override
    public String getExplain() {

        return exchangeOrderItemDetail.getExplain();
    }

    @Override
    public String getReason() {

        return exchangeOrderItemDetail.getReason();
    }

    @Override
    public void setState(int state) {

        exchangeOrderItemDetail.setState(state);
    }
}
