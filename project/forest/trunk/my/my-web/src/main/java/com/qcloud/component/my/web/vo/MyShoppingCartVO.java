package com.qcloud.component.my.web.vo;

public class MyShoppingCartVO {

    // 商家ID
    private long    merchantId;

    // 商家分类ID
    private long    merchantClassifyId;

    // 统一商品ID
    private long    unifiedMerchandiseId;

    private String  timeStr;

    // 数量
    private int     number;

    private String  name;

    private String  image;

    private String  specifications;

    private double  discount;

    private double  price;

    // 库存
    private int     stock;

    private boolean collect = false;

    // 1单品 2组合 3.......
    private int     merchandiseType;

    private String  unit;

    private double  sum;

    public MyShoppingCartVO() {

    }

    public void setMerchantId(long merchantId) {

        this.merchantId = merchantId;
    }

    public long getMerchantId() {

        return merchantId;
    }

    public void setMerchantClassifyId(long merchantClassifyId) {

        this.merchantClassifyId = merchantClassifyId;
    }

    public long getMerchantClassifyId() {

        return merchantClassifyId;
    }

    public void setUnifiedMerchandiseId(long unifiedMerchandiseId) {

        this.unifiedMerchandiseId = unifiedMerchandiseId;
    }

    public long getUnifiedMerchandiseId() {

        return unifiedMerchandiseId;
    }

    public void setNumber(int number) {

        this.number = number;
    }

    public int getNumber() {

        return number;
    }

    public String getTimeStr() {

        return timeStr;
    }

    public void setTimeStr(String timeStr) {

        this.timeStr = timeStr;
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

    public String getSpecifications() {

        return specifications;
    }

    public void setSpecifications(String specifications) {

        this.specifications = specifications;
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

    public boolean isCollect() {

        return collect;
    }

    public void setCollect(boolean collect) {

        this.collect = collect;
    }

    public int getMerchandiseType() {

        return merchandiseType;
    }

    public void setMerchandiseType(int merchandiseType) {

        this.merchandiseType = merchandiseType;
    }

    public String getUnit() {

        return unit;
    }

    public void setUnit(String unit) {

        this.unit = unit;
    }

    public double getSum() {

        return sum;
    }

    public void setSum(double sum) {

        this.sum = sum;
    }
}
