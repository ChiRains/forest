package com.qcloud.project.forest.web.vo;

public class BrandSalesVO {

    private long   id;

    // 商品图片
    private String image;

    // 商品名称
    private String name;

    // 商品价格
    private double price;

    public BrandSalesVO() {

    }

    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
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

    public double getPrice() {

        return price;
    }

    public void setPrice(double price) {

        this.price = price;
    }

    public BrandSalesVO(String image, String name, double price) {

        super();
        this.image = image;
        this.name = name;
        this.price = price;
    }
}
