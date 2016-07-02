package com.qcloud.component.orderform.entity;

import java.util.ArrayList;
import java.util.List;
import com.qcloud.component.orderform.QOrderItem;
import com.qcloud.component.orderform.QOrderItemDetail;
import com.qcloud.component.orderform.model.OrderItem;
import com.qcloud.component.publicdata.EnableType;

public class OrderItemEntity implements QOrderItem {

    private OrderEntity                 order;

    private MerchantOrderEntity         merchantOrder;

    private List<OrderItemDetailEntity> orderItemDetailList;

    // private List<AfterSaleOrderItem> afterSaleItemList = new ArrayList<AfterSaleOrderItem>();
    private OrderItem                   orderItem;

    public OrderItemEntity(OrderEntity order, MerchantOrderEntity merchantOrder, OrderItem orderItem) {

        super();
        this.order = order;
        this.merchantOrder = merchantOrder;
        this.orderItem = orderItem;
    }

    @Override
    public OrderEntity getOrder() {

        return order;
    }

    @Override
    public MerchantOrderEntity getMerchantOrder() {

        return merchantOrder;
    }

    public List<QOrderItemDetail> getOrderItemDetailList() {

        List<QOrderItemDetail> list = new ArrayList<QOrderItemDetail>();
        for (QOrderItemDetail orderItemDetail : orderItemDetailList) {
            list.add(orderItemDetail);
        }
        return list;
    }

    public List<OrderItemDetailEntity> getEntityList() {

        return orderItemDetailList;
    }

    public OrderItemDetailEntity getOrderItemDetail(Long orderItemDetailId) {

        for (OrderItemDetailEntity orderItemDetail : orderItemDetailList) {
            if (orderItemDetail.getId() == orderItemDetailId) {
                return orderItemDetail;
            }
        }
        return null;
    }

    public void setOrderItemDetailList(List<OrderItemDetailEntity> orderItemDetailList) {

        this.orderItemDetailList = orderItemDetailList;
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
    public long getUnifiedMerchandiseId() {

        return orderItem.getUnifiedMerchandiseId();
    }

    @Override
    public double getPrice() {

        return orderItem.getPrice();
    }

    @Override
    public double getDiscount() {

        return orderItem.getDiscount();
    }

    @Override
    public int getNumber() {

        return orderItem.getNumber();
    }

    @Override
    public double getSum() {

        return orderItem.getSum();
    }

    @Override
    public int getState() {

        return orderItem.getState();
    }

    public void setState(int state) {

        orderItem.setState(state);
    }

    @Override
    public int getSence() {

        return orderItem.getSence();
    }

    @Override
    public long getId() {

        return orderItem.getId();
    }

    @Override
    public String getName() {

        return orderItem.getName();
    }

    @Override
    public String getImage() {

        return orderItem.getImage();
    }

    public OrderItem getOrderItem() {

        return orderItem;
    }

    @Override
    public double getTotalPrice() {

        return orderItem.getPrice() * orderItem.getNumber();
    }

    @Override
    public double getPurchase() {

        return orderItem.getPurchase();
    }

    @Override
    public double getTotalPurchase() {

        return orderItem.getPurchase() * orderItem.getNumber();
    }

    @Override
    public double getCash() {

        return orderItem.getCash();
    }

    @Override
    public double getPreferential() {

        return orderItem.getPreferential();
    }

    @Override
    public boolean isEvaluation() {

        return orderItem.getEvaluation() == EnableType.ENABLE.getKey();
    }

    @Override
    public boolean isAfterSale() {

        return orderItem.getAfterSale() == EnableType.ENABLE.getKey();
    }

    @Override
    public String getSpecifications() {

        if (orderItemDetailList.size() > 1) {
            return "组合套餐";
        } else {
            return orderItemDetailList.get(0).getSpecifications();
        }
    }

    public void setOrder(OrderEntity order) {

        this.order = order;
    }

    public void setMerchantOrder(MerchantOrderEntity merchantOrder) {

        this.merchantOrder = merchantOrder;
    }
}
