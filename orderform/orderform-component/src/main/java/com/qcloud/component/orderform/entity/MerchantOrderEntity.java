package com.qcloud.component.orderform.entity;

import java.util.ArrayList;
import java.util.List;
import com.qcloud.component.orderform.QMerchantOrder;
import com.qcloud.component.orderform.QOrderItem;
import com.qcloud.component.orderform.model.OrderDiscount;
import com.qcloud.component.orderform.model.SubOrder;

public class MerchantOrderEntity implements QMerchantOrder {

    private OrderEntity           order;

    private List<OrderItemEntity> orderItemList;

    // private List<AfterSaleOrder> afterSaleList = new ArrayList<AfterSaleOrder>();
    private SubOrder              subOrder;

    private List<OrderDiscount>   discountList = new ArrayList<OrderDiscount>();

    private int                   userState;

    private String                userStateStr;

    private boolean               canRefund;

    private boolean               canExchange;

    private boolean               canReturn;

    private boolean               canRemind;

    public MerchantOrderEntity(OrderEntity order, SubOrder subOrder) {

        super();
        this.order = order;
        this.subOrder = subOrder;
    }

    @Override
    public OrderEntity getOrder() {

        return order;
    }

    @Override
    public List<QOrderItem> getOrderItemList() {

        List<QOrderItem> list = new ArrayList<QOrderItem>();
        for (OrderItemEntity orderItem : orderItemList) {
            list.add(orderItem);
        }
        return list;
    }

    public List<OrderItemEntity> getEntityList() {

        return orderItemList;
    }

    public OrderItemEntity getOrderItem(Long orderItemId) {

        for (OrderItemEntity orderItem : orderItemList) {
            if (orderItem.getId() == orderItemId) {
                return orderItem;
            }
        }
        return null;
    }

    public void setOrderItemList(List<OrderItemEntity> orderItemList) {

        this.orderItemList = orderItemList;
    }

    // public List<AfterSaleOrder> getAfterSaleList() {
    //
    // return afterSaleList;
    // }
    //
    // public void setAfterSaleList(List<AfterSaleOrder> afterSaleList) {
    //
    // this.afterSaleList = afterSaleList;
    // }
    //
    // @Override
    // public List<QAfterSaleOrder> getAfterSaleOrderList() {
    //
    // List<AfterSaleOrder> list = getAfterSaleList();
    // List<QAfterSaleOrder> afterSaleOrderList = new ArrayList<QAfterSaleOrder>();
    // for (AfterSaleOrder afterSaleOrder : list) {
    // afterSaleOrderList.add(afterSaleOrder);
    // }
    // return afterSaleOrderList;
    // }
    @Override
    public String getOrderNumber() {

        return subOrder.getOrderNumber();
    }

    @Override
    public double getSum() {

        return subOrder.getSum();
    }

    @Override
    public long getId() {

        return subOrder.getId();
    }

    @Override
    public int getState() {

        return subOrder.getState();
    }

    public void setState(int state) {

        subOrder.setState(state);
    }

    @Override
    public long getMerchantId() {

        return subOrder.getMerchantId();
    }

    @Override
    public long getStoreId() {

        return subOrder.getStoreId();
    }

    @Override
    public int getDeliveryMode() {

        return subOrder.getDeliveryMode();
    }

    @Override
    public String getPickupAddressStr() {

        return subOrder.getPickupAddressStr();
    }

    @Override
    public String getDeliveryTimeStr() {

        return subOrder.getDeliveryTimeStr();
    }

    public SubOrder getSubOrder() {

        return subOrder;
    }

    @Override
    public String getExplain() {

        return subOrder.getExplain();
    }

    @Override
    public double getTotalPrice() {

        double totalPrice = 0.0;
        for (OrderItemEntity orderItem : orderItemList) {
            totalPrice += orderItem.getPrice();
        }
        return totalPrice;
    }

    @Override
    public double getTotalPurchase() {

        double totalPurchase = 0.0;
        for (OrderItemEntity orderItem : orderItemList) {
            totalPurchase += orderItem.getPurchase();
        }
        return totalPurchase;
    }

    @Override
    public double getCash() {

        return subOrder.getCash();
    }

    @Override
    public int getNumber() {

        int totalNumber = 0;
        for (OrderItemEntity orderItem : orderItemList) {
            totalNumber += orderItem.getNumber();
        }
        return totalNumber;
    }

    @Override
    public double getPreferential() {

        return subOrder.getPreferential();
    }

    @Override
    public double getCoupon() {

        return subOrder.getCoupon();
    }

    public List<OrderDiscount> getDiscountList() {

        return discountList;
    }

    public void setDiscountList(List<OrderDiscount> discountList) {

        this.discountList = discountList;
    }

    @Override
    public double getPostage() {

        return subOrder.getPostage();
    }

    @Override
    public String getExpressCode() {

        return subOrder.getExpressCode();
    }

    @Override
    public String getExpressName() {

        return subOrder.getExpressName();
    }

    @Override
    public String getExpressNumber() {

        return subOrder.getExpressNumber();
    }

    @Override
    public int getIntegral() {

        return subOrder.getIntegral();
    }

    @Override
    public double getConsumption() {

        return subOrder.getConsumption();
    }

    public int getUserState() {

        return userState;
    }

    public void setUserState(int userState) {

        this.userState = userState;
    }

    public String getUserStateStr() {

        return userStateStr;
    }

    public void setUserStateStr(String userStateStr) {

        this.userStateStr = userStateStr;
    }

    public boolean canRefund() {

        // 付款,确认可以申请退款
        return order.canApplyAfterSale() && canRefund;
    }

    public boolean canExchange() {

        // 签收才可以退换货
        return order.canApplyAfterSale() && canExchange;
    }

    public boolean canReturn() {

        // 签收才可以退换货
        return order.canApplyAfterSale() && canReturn;
    }

    public void setCanRefund(boolean canRefund) {

        this.canRefund = canRefund;
    }

    public void setCanExchange(boolean canExchange) {

        this.canExchange = canExchange;
    }

    public void setCanReturn(boolean canReturn) {

        this.canReturn = canReturn;
    }

    public void setCanRemind(boolean canRemind) {

        this.canRemind = canRemind;
    }

    @Override
    public boolean canRemind() {

        return canRemind;
    }
}
