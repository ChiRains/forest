package com.qcloud.component.orderform.web.vo;

public class AfterSaleOrderItemVO {

    //
    private long   orderItemId;

    // 商品名称
    private String name;

    // 缩略图
    private String image;

    // 折扣价,成交价
    private double discount;

    private int    number;

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

    public double getDiscount() {

        return discount;
    }

    public void setDiscount(double discount) {

        this.discount = discount;
    }

    public int getNumber() {

        return number;
    }

    public void setNumber(int number) {

        this.number = number;
    }

    public long getOrderItemId() {

        return orderItemId;
    }

    public void setOrderItemId(long orderItemId) {

        this.orderItemId = orderItemId;
    }
}
