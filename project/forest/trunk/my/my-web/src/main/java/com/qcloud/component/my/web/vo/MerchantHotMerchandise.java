package com.qcloud.component.my.web.vo;

public class MerchantHotMerchandise {

    private Long   unifiedMerchandiseId;

    private Long   merchandiseId;

    private String image;

    private double discount;

    private double price;

    public Long getUnifiedMerchandiseId() {

        return unifiedMerchandiseId;
    }

    public void setUnifiedMerchandiseId(Long unifiedMerchandiseId) {

        this.unifiedMerchandiseId = unifiedMerchandiseId;
    }

    public Long getMerchandiseId() {

        return merchandiseId;
    }

    public void setMerchandiseId(Long merchandiseId) {

        this.merchandiseId = merchandiseId;
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

    public double getPrice() {

        return price;
    }

    public void setPrice(double price) {

        this.price = price;
    }
}
