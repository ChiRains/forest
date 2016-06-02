package com.qcloud.component.orderform.web.vo.merchant;

public class OrderItemVO {

    // 折扣价,成交价
    private double discount;

    // 数量
    private int    number;

    // 规格说明
    private String specifications;

    // 缩略图
    private String image;

    // 商品名称
    private String name;

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

    public String getSpecifications() {

        return specifications;
    }

    public void setSpecifications(String specifications) {

        this.specifications = specifications;
    }

    public String getImage() {

        return image;
    }

    public void setImage(String image) {

        this.image = image;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }
}
