package com.qcloud.component.my.web.vo;

public class MyMerchandiseCollectionVO {

    private long   id;

    // 统一商品ID
    private long   unifiedMerchandiseId;

    // 收藏时间
    private String timeStr;

    // 名称
    private String name;

    // 图片
    private String image;

    // 价格
    private double discount;

    // 原价
    private double price;

    // 已购买数量
    private long   salesVolume;

    // 规格
    private String specifications;

    private Long   mallClassifyId;

    private double goodEvaluationRate;

    private int    stock;

    private String label;

    private String merchantName;

    public MyMerchandiseCollectionVO() {

    }

    public void setId(long id) {

        this.id = id;
    }

    public long getId() {

        return id;
    }

    public void setUnifiedMerchandiseId(long unifiedMerchandiseId) {

        this.unifiedMerchandiseId = unifiedMerchandiseId;
    }

    public long getUnifiedMerchandiseId() {

        return unifiedMerchandiseId;
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

    public long getSalesVolume() {

        return salesVolume;
    }

    public void setSalesVolume(long salesVolume) {

        this.salesVolume = salesVolume;
    }

    public String getSpecifications() {

        return specifications;
    }

    public void setSpecifications(String specifications) {

        this.specifications = specifications;
    }

    public Long getMallClassifyId() {

        return mallClassifyId;
    }

    public void setMallClassifyId(Long mallClassifyId) {

        this.mallClassifyId = mallClassifyId;
    }

    public double getGoodEvaluationRate() {

        return goodEvaluationRate;
    }

    public void setGoodEvaluationRate(double goodEvaluationRate) {

        this.goodEvaluationRate = goodEvaluationRate;
    }

    public int getStock() {

        return stock;
    }

    public void setStock(int stock) {

        this.stock = stock;
    }

    public String getLabel() {

        return label;
    }

    public void setLabel(String label) {

        this.label = label;
    }

    public String getMerchantName() {

        return merchantName;
    }

    public void setMerchantName(String merchantName) {

        this.merchantName = merchantName;
    }
}
