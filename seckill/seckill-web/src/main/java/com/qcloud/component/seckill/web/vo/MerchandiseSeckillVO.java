package com.qcloud.component.seckill.web.vo;

public class MerchandiseSeckillVO {

    private long   id;

    // 库存
    private int    stock;

    // 销量
    private int    salesVolume;

    private String name;

    private String image;

    //
    private double price;

    private double seckillPrice;

    private int    rate;

    private String killName;

    public MerchandiseSeckillVO() {

    }

    public void setId(long id) {

        this.id = id;
    }

    public long getId() {

        return id;
    }

    public int getStock() {

        return stock;
    }

    public void setStock(int stock) {

        this.stock = stock;
    }

    public int getSalesVolume() {

        return salesVolume;
    }

    public void setSalesVolume(int salesVolume) {

        this.salesVolume = salesVolume;
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

    public double getSeckillPrice() {

        return seckillPrice;
    }

    public void setSeckillPrice(double seckillPrice) {

        this.seckillPrice = seckillPrice;
    }

    public double getPrice() {

        return price;
    }

    public void setPrice(double price) {

        this.price = price;
    }

    public int getRate() {

        return rate;
    }

    public void setRate(int rate) {

        this.rate = rate;
    }

    public String getKillName() {

        return killName;
    }

    public void setKillName(String killName) {

        this.killName = killName;
    }
}
