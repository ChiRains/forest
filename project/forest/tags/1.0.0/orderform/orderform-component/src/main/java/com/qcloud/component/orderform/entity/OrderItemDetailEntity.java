package com.qcloud.component.orderform.entity;

import java.util.ArrayList;
import java.util.List;
import com.qcloud.component.orderform.QAfterSaleOrderItem;
import com.qcloud.component.orderform.QOrderItemDetail;
import com.qcloud.component.orderform.model.OrderItemDetail;

public class OrderItemDetailEntity implements QOrderItemDetail {

    private OrderEntity         order;

    private MerchantOrderEntity merchantOrder;

    private OrderItemEntity     orderItem;

    private OrderItemDetail     orderItemDetail;

    // private List<AfterSaleOrderItem> afterSaleItemList = new ArrayList<AfterSaleOrderItem>();
    public OrderItemDetailEntity(OrderEntity order, MerchantOrderEntity merchantOrder, OrderItemEntity orderItem, OrderItemDetail orderItemDetail) {

        super();
        this.order = order;
        this.merchantOrder = merchantOrder;
        this.orderItem = orderItem;
        this.orderItemDetail = orderItemDetail;
    }

    @Override
    public OrderEntity getOrder() {

        return order;
    }

    @Override
    public MerchantOrderEntity getMerchantOrder() {

        return merchantOrder;
    }

    @Override
    public OrderItemEntity getOrderItem() {

        return orderItem;
    }

    // public List<QAfterSaleOrderItem> getAfterSaleOrderItemList() {
    //
    // List<AfterSaleOrderItem> list = getAfterSaleItemList();
    // List<QAfterSaleOrderItem> afterSaleOrderItemList = new ArrayList<QAfterSaleOrderItem>();
    // for (AfterSaleOrderItem afterSaleOrderItem : list) {
    // afterSaleOrderItemList.add(afterSaleOrderItem);
    // }
    // return afterSaleOrderItemList;
    // }
    //
    // public List<AfterSaleOrderItem> getAfterSaleItemList() {
    //
    // return afterSaleItemList;
    // }
    //
    // public void setAfterSaleItemList(List<AfterSaleOrderItem> afterSaleItemList) {
    //
    // this.afterSaleItemList = afterSaleItemList;
    // }

    @Override
    public String getName() {

        return orderItemDetail.getName();
    }

    @Override
    public String getImage() {

        return orderItemDetail.getImage();
    }

    @Override
    public String getCode() {

        return orderItemDetail.getCode();
    }

    @Override
    public String getSpecifications() {

        return orderItemDetail.getSpecifications();
    }

    @Override
    public int getState() {

        return orderItemDetail.getState();
    }

    public void setState(int state) {

        orderItemDetail.setState(state);
    }

    @Override
    public long getUnifiedMerchandiseId() {

        return orderItemDetail.getUnifiedMerchandiseId();
    }

    @Override
    public long getMerchandiseItemId() {

        return orderItemDetail.getMerchandiseItemId();
    }

    @Override
    public long getId() {

        return orderItemDetail.getId();
    }

    public OrderItemDetail getOrderItemDetail() {

        return orderItemDetail;
    }

    @Override
    public int getNumber() {

        return orderItemDetail.getNumber();
    }
}
