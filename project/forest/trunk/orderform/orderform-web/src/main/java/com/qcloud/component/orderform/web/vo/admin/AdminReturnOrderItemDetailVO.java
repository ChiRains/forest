package com.qcloud.component.orderform.web.vo.admin;


public class AdminReturnOrderItemDetailVO {

    private long   id;

    // 订单id
    private long   orderId;

    // 子单id
    private long   subOrderId;

    // 订单项id
    private long   orderItemId;

    // 订单项详细
    private long   orderItemDetailId;

    // 退货数量
    private int    number;

    // 退货单id
    private long   returnId;

    // 退货单项id
    private long   returnItemId;

    // 状态(1:已申请 2:通过 3:未通过 )
    private int    state;

    private int    returnType;

    // 商品信息
    private String name;

    private String image;

    private String specifications;

    public AdminReturnOrderItemDetailVO() {

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

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getImage() {

        return image;
    }

    public void setImage(String image) {

        this.image = image;
    }

    public String getSpecifications() {

        return specifications;
    }

    public void setSpecifications(String specifications) {

        this.specifications = specifications;
    }
}
