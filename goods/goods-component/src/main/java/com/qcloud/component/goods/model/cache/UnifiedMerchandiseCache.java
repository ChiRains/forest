package com.qcloud.component.goods.model.cache;

public class UnifiedMerchandiseCache {

    // ID
    private long    id;

    private String  name;

    private String  image;

    // 商家ID
    private long    merchantId;

    // 类型
    private int     type;

    // 进货价
    private double  purchase;

    // 折扣价
    private double  discount;

    // 原价
    private double  price;

    // 库存
    private int     stock;

    private int     sence = -1;

    private boolean isIncludePost;

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

    public double getPurchase() {

        return purchase;
    }

    public void setPurchase(double purchase) {

        this.purchase = purchase;
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

    public int getStock() {

        return stock;
    }

    public void setStock(int stock) {

        this.stock = stock;
    }

    public long getMerchantId() {

        return merchantId;
    }

    public void setMerchantId(long merchantId) {

        this.merchantId = merchantId;
    }

    public int getType() {

        return type;
    }

    public void setType(int type) {

        this.type = type;
    }

    public void setId(long id) {

        this.id = id;
    }

    public long getId() {

        return id;
    }

    public int getSence() {

        return sence;
    }

    public void setSence(int sence) {

        this.sence = sence;
    }

    public boolean getIsIncludePost() {

        return isIncludePost;
    }

    public void setIncludePost(boolean isIncludePost) {

        this.isIncludePost = isIncludePost;
    }
}
