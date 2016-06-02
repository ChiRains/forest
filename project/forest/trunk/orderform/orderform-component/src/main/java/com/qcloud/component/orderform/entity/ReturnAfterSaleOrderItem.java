package com.qcloud.component.orderform.entity;

import java.util.Date;
import com.qcloud.component.orderform.QAfterSaleItem;
import com.qcloud.component.orderform.model.ReturnOrderItem;

public class ReturnAfterSaleOrderItem extends AfterSaleOrderItem implements QAfterSaleItem {

    private OrderItemEntity orderItemEntity;

    private ReturnOrderItem returnOrderItem;

    public ReturnAfterSaleOrderItem(AfterSaleOrder afterSaleOrder, OrderItemEntity orderItemEntity, ReturnOrderItem returnOrderItem) {

        super(afterSaleOrder);
        this.orderItemEntity = orderItemEntity;
        this.returnOrderItem = returnOrderItem;
    }

    public OrderItemEntity getOrderItem() {

        return orderItemEntity;
    }

    public ReturnOrderItem getReturnOrderItem() {

        return returnOrderItem;
    }

    @Override
    public long getAfterSaleOrderItemId() {

        return returnOrderItem.getId();
    }

    @Override
    public int getState() {

        return returnOrderItem.getState();
    }

    @Override
    public Date getTime() {

        return returnOrderItem.getTime();
    }

    @Override
    public int getNumber() {

        return returnOrderItem.getNumber();
    }

    @Override
    public double getSum() {

        return returnOrderItem.getSum();
    }

    @Override
    public String getExplain() {

        return returnOrderItem.getExplain();
    }

    @Override
    public String getReason() {

        return returnOrderItem.getReason();
    }

    @Override
    public void setState(int state) {

        returnOrderItem.setState(state);
    }
}
