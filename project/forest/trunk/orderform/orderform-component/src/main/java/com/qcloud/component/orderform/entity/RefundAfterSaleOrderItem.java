package com.qcloud.component.orderform.entity;

import java.util.Date;
import com.qcloud.component.orderform.QAfterSaleItem;
import com.qcloud.component.orderform.model.RefundOrderItem;

public class RefundAfterSaleOrderItem extends AfterSaleOrderItem implements QAfterSaleItem {

    private OrderItemEntity orderItemEntity;

    private RefundOrderItem refundOrderItem;

    public RefundAfterSaleOrderItem(AfterSaleOrder afterSaleOrder, OrderItemEntity orderItemEntity, RefundOrderItem refundOrderItem) {

        super(afterSaleOrder);
        this.orderItemEntity = orderItemEntity;
        this.refundOrderItem = refundOrderItem;
    }

    public RefundOrderItem getRefundOrderItem() {

        return refundOrderItem;
    }

    @Override
    public OrderItemEntity getOrderItem() {

        return orderItemEntity;
    }

    @Override
    public long getAfterSaleOrderItemId() {

        return refundOrderItem.getId();
    }

    @Override
    public int getState() {

        return refundOrderItem.getState();
    }

    @Override
    public Date getTime() {

        return refundOrderItem.getTime();
    }

    @Override
    public int getNumber() {

        return refundOrderItem.getNumber();
    }

    @Override
    public double getSum() {

        return refundOrderItem.getSum();
    }

    @Override
    public String getExplain() {

        return refundOrderItem.getExplain();
    }

    @Override
    public String getReason() {

        return refundOrderItem.getReason();
    }

    @Override
    public void setState(int state) {

        refundOrderItem.setState(state);
    }
}
