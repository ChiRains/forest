package com.qcloud.component.mall.web.vo;

public class EvaluateOrderItemVO {

    private long   orderItemId;

    private String name;

    private String image;

    private double discount;

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

    public long getOrderItemId() {

        return orderItemId;
    }

    public void setOrderItemId(long orderItemId) {

        this.orderItemId = orderItemId;
    }
}
