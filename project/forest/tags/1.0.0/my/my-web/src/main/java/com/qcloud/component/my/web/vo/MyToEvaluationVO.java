package com.qcloud.component.my.web.vo;

public class MyToEvaluationVO {

    //
    private long   id;

    // 商品名称
    private String name;

    // 缩略图
    private String image;

    // 折扣价,成交价
    private double discount;

    // 订单号
    private String orderNumber;

    public MyToEvaluationVO() {

    }

    public void setId(long id) {

        this.id = id;
    }

    public long getId() {

        return id;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getName() {

        return name;
    }

    public void setImage(String image) {

        this.image = image;
    }

    public String getImage() {

        return image;
    }

    public void setDiscount(double discount) {

        this.discount = discount;
    }

    public double getDiscount() {

        return discount;
    }

    public void setOrderNumber(String orderNumber) {

        this.orderNumber = orderNumber;
    }

    public String getOrderNumber() {

        return orderNumber;
    }
}
