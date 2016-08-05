package com.qcloud.component.goods.web.vo;

import java.util.ArrayList;
import java.util.List;

public class PointMerchandiseVO {

    private long         unifiedMerchandiseId;

    private String       name;

    private String       image;

    private double       price;

    private double       discount;

    private double       integral;

    private String       desc;

    private String       merchantName;

    private String       specifications;

    private List<String> imageList = new ArrayList<String>();

    public long getUnifiedMerchandiseId() {

        return unifiedMerchandiseId;
    }

    public void setUnifiedMerchandiseId(long unifiedMerchandiseId) {

        this.unifiedMerchandiseId = unifiedMerchandiseId;
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

    public double getDiscount() {

        return discount;
    }

    public void setDiscount(double discount) {

        this.discount = discount;
    }

    public double getIntegral() {

        return integral;
    }

    public void setIntegral(double integral) {

        this.integral = integral;
    }

    public String getDesc() {

        return desc;
    }

    public void setDesc(String desc) {

        this.desc = desc;
    }

    public double getPrice() {

        return price;
    }

    public void setPrice(double price) {

        this.price = price;
    }

    public String getMerchantName() {

        return merchantName;
    }

    public void setMerchantName(String merchantName) {

        this.merchantName = merchantName;
    }

    public String getSpecifications() {

        return specifications;
    }

    public void setSpecifications(String specifications) {

        this.specifications = specifications;
    }

    public List<String> getImageList() {

        return imageList;
    }

    public void setImageList(List<String> imageList) {

        this.imageList = imageList;
    }
}
