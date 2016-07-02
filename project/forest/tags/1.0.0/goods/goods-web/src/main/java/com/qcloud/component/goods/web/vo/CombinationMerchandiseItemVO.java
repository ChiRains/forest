package com.qcloud.component.goods.web.vo;

public class CombinationMerchandiseItemVO {

    // 商品名称
    private String name;

    // 规格
    private String specifications;

    // 缩略图
    private String image;

    // 折扣价
    private double discount;

    // 数量
    private int    number;

    // 商家ID
    private long   merchantId;

    public CombinationMerchandiseItemVO() {

    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
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

    public void setMerchantId(long merchantId) {

        this.merchantId = merchantId;
    }

    public long getMerchantId() {

        return merchantId;
    }
}
