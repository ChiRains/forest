package com.qcloud.component.goods.web.vo;

public class ExchangeMerchandiseListVO {

    private String name;

    private String image;

    private long   stock;

    private double price;

    private double currency;

    private long   unifiedMerchandiseId;

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

    public long getStock() {

        return stock;
    }

    public void setStock(long stock) {

        this.stock = stock;
    }

    public double getCurrency() {

        return currency;
    }

    public void setCurrency(double currency) {

        this.currency = currency;
    }

    public double getPrice() {

        return price;
    }

    public void setPrice(double price) {

        this.price = price;
    }

    public long getUnifiedMerchandiseId() {

        return unifiedMerchandiseId;
    }

    public void setUnifiedMerchandiseId(long unifiedMerchandiseId) {

        this.unifiedMerchandiseId = unifiedMerchandiseId;
    }
}
