package com.qcloud.component.orderform.model;

public class ReturnOrderItemDetail {

    private long id;

    // 订单id
    private long orderId;

    // 子单id
    private long subOrderId;

    // 订单项id
    private long orderItemId;

    // 订单项详细
    private long orderItemDetailId;

    // 退货数量
    private int  number;

    // 退货单id
    private long returnId;

    // 退货单项id
    private long returnItemId;

    // 状态(1:已申请 2:通过 3:未通过 )
    private int  state;

    private int  returnType;

    public ReturnOrderItemDetail() {

    }

    public ReturnOrderItemDetail(long id, long orderId, long subOrderId, long orderItemId, long orderItemDetailId, int number, long returnId, long returnItemId, int state, int returnType) {

        this.id = id;
        this.orderId = orderId;
        this.subOrderId = subOrderId;
        this.orderItemId = orderItemId;
        this.orderItemDetailId = orderItemDetailId;
        this.number = number;
        this.returnId = returnId;
        this.returnItemId = returnItemId;
        this.state = state;
        this.returnType = returnType;
    }

    public void setId(long id) {

        this.id = id;
    }

    public long getId() {

        return id;
    }

    public void setOrderId(long orderId) {

        this.orderId = orderId;
    }

    public long getOrderId() {

        return orderId;
    }

    public void setSubOrderId(long subOrderId) {

        this.subOrderId = subOrderId;
    }

    public long getSubOrderId() {

        return subOrderId;
    }

    public void setOrderItemId(long orderItemId) {

        this.orderItemId = orderItemId;
    }

    public long getOrderItemId() {

        return orderItemId;
    }

    public void setOrderItemDetailId(long orderItemDetailId) {

        this.orderItemDetailId = orderItemDetailId;
    }

    public long getOrderItemDetailId() {

        return orderItemDetailId;
    }

    public void setNumber(int number) {

        this.number = number;
    }

    public int getNumber() {

        return number;
    }

    public void setReturnId(long returnId) {

        this.returnId = returnId;
    }

    public long getReturnId() {

        return returnId;
    }

    public void setReturnItemId(long returnItemId) {

        this.returnItemId = returnItemId;
    }

    public long getReturnItemId() {

        return returnItemId;
    }

    public void setState(int state) {

        this.state = state;
    }

    public int getState() {

        return state;
    }

    public void setReturnType(int returnType) {

        this.returnType = returnType;
    }

    public int getReturnType() {

        return returnType;
    }
}
