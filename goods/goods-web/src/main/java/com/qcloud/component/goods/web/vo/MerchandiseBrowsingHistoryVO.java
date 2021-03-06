package com.qcloud.component.goods.web.vo;

public class MerchandiseBrowsingHistoryVO {

    // 统一商品ID
    private long   unifiedMerchandiseId;

    // 名称
    private String name;

    // 图片
    private String image;

    // 价格
    private double discount;

    // 原价
    private double price;

    // 规格
    private String specifications;

    // 浏览时间
    private String browsingTimeStr;

    // 客户端类型:1微信 2安卓 3IOS 4PC
    private String clientType;

    private long   id;

    private String merchantName;

    private double stock;

    private long   goodEvaluationRate;

    public MerchandiseBrowsingHistoryVO() {

    }

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

    public double getPrice() {

        return price;
    }

    public void setPrice(double price) {

        this.price = price;
    }

    public String getSpecifications() {

        return specifications;
    }

    public void setSpecifications(String specifications) {

        this.specifications = specifications;
    }

    public String getBrowsingTimeStr() {

        return browsingTimeStr;
    }

    public void setBrowsingTimeStr(String browsingTimeStr) {

        this.browsingTimeStr = browsingTimeStr;
    }

    public String getClientType() {

        return clientType;
    }

    public void setClientType(String clientType) {

        this.clientType = clientType;
    }

    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
    }

    public String getMerchantName() {

        return merchantName;
    }

    public void setMerchantName(String merchantName) {

        this.merchantName = merchantName;
    }

    
    public double getStock() {
    
        return stock;
    }

    
    public void setStock(double stock) {
    
        this.stock = stock;
    }

    
    public long getGoodEvaluationRate() {
    
        return goodEvaluationRate;
    }

    
    public void setGoodEvaluationRate(long goodEvaluationRate) {
    
        this.goodEvaluationRate = goodEvaluationRate;
    }
}
