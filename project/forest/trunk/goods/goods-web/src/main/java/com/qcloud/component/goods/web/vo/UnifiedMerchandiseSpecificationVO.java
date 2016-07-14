package com.qcloud.component.goods.web.vo;

import java.util.ArrayList;
import java.util.List;

public class UnifiedMerchandiseSpecificationVO {

    private long         unifiedMerchandiseId;

    private String       name;

    private int          stock;

    private double       discount;

    private double       price;

    private String       image;

    private List<String> specificationsList = new ArrayList<String>();

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

    public int getStock() {

        return stock;
    }

    public void setStock(int stock) {

        this.stock = stock;
    }

    public double getDiscount() {

        return discount;
    }

    public void setDiscount(double discount) {

        this.discount = discount;
    }

    public double getPrice() {

        return price;
    }

    public void setPrice(double price) {

        this.price = price;
    }

    public String getImage() {

        return image;
    }

    public void setImage(String image) {

        this.image = image;
    }

    public List<String> getSpecificationsList() {

        return specificationsList;
    }

    public void setSpecificationsList(List<String> specificationsList) {

        this.specificationsList = specificationsList;
    }
}
