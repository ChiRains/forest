package com.qcloud.component.orderform.entity;

import java.util.Date;
import com.qcloud.component.orderform.QAfterSaleDetail;
import com.qcloud.component.orderform.model.ReturnOrderItemDetail;

public class ReturnAfterSaleOrderItem extends AfterSaleOrderItem implements QAfterSaleDetail {

    private OrderItemDetailEntity orderItemDetailEntity;

    private ReturnOrderItemDetail returnOrderItemDetail;

    public ReturnAfterSaleOrderItem(AfterSaleOrder afterSaleOrder, OrderItemDetailEntity orderItemDetailEntity, ReturnOrderItemDetail returnOrderItemDetail) {

        super(afterSaleOrder);
        this.orderItemDetailEntity = orderItemDetailEntity;
        this.returnOrderItemDetail = returnOrderItemDetail;
    }

    public OrderItemDetailEntity getOrderItemDetailEntity() {

        return orderItemDetailEntity;
    }

    public ReturnOrderItemDetail getReturnOrderItemDetail() {

        return returnOrderItemDetail;
    }

    @Override
    public long getAfterSaleOrderItemId() {

        return returnOrderItemDetail.getId();
    }

    @Override
    public int getState() {

        return returnOrderItemDetail.getState();
    }

    @Override
    public Date getTime() {

        return returnOrderItemDetail.getTime();
    }

    @Override
    public int getNumber() {

        return returnOrderItemDetail.getNumber();
    }

    @Override
    public double getSum() {

        return 0.0;
    }

    @Override
    public String getExplain() {

        return returnOrderItemDetail.getExplain();
    }

    @Override
    public String getReason() {

        return returnOrderItemDetail.getReason();
    }

    @Override
    public void setState(int state) {

        returnOrderItemDetail.setState(state);
    }

    @Override
    public OrderItemDetailEntity getOrderItemDetail() {

        return orderItemDetailEntity;
    }
}