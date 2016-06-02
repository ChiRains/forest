package com.qcloud.component.marketing.web.vo;

public class MerchandiseCustomClassificationVO {

    private String image;

    private long   unifiedMerchandiseId;

    private long   merchantId;

    private long   merchandiseId;

    private String name;

    private double price;

    private double discount;
    
    
    private long   salesVolume;

    private int    goodEvaluationRate;

    private long   evaluationNumber;
    
    //系统编号
    private String sysCode;

    public MerchandiseCustomClassificationVO() {

    }

    public void setMerchantId(long merchantId) {

        this.merchantId = merchantId;
    }

    public long getMerchantId() {

        return merchantId;
    }

    public void setUnifiedMerchandiseId(long unifiedMerchandiseId) {

        this.unifiedMerchandiseId = unifiedMerchandiseId;
    }

    public long getUnifiedMerchandiseId() {

        return unifiedMerchandiseId;
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

    public long getMerchandiseId() {

        return merchandiseId;
    }

    public void setMerchandiseId(long merchandiseId) {

        this.merchandiseId = merchandiseId;
    }

    public double getPrice() {

        return price;
    }

    public void setPrice(double price) {

        this.price = price;
    }

    public double getDiscount() {

        return discount;
    }

    public void setDiscount(double discount) {

        this.discount = discount;
    }

    
    public String getSysCode() {
    
        return sysCode;
    }

    
    public void setSysCode(String sysCode) {
    
        this.sysCode = sysCode;
    }

    
    public long getSalesVolume() {
    
        return salesVolume;
    }

    
    public void setSalesVolume(long salesVolume) {
    
        this.salesVolume = salesVolume;
    }

    
    public int getGoodEvaluationRate() {
    
        return goodEvaluationRate;
    }

    
    public void setGoodEvaluationRate(int goodEvaluationRate) {
    
        this.goodEvaluationRate = goodEvaluationRate;
    }

    
    public long getEvaluationNumber() {
    
        return evaluationNumber;
    }

    
    public void setEvaluationNumber(long evaluationNumber) {
    
        this.evaluationNumber = evaluationNumber;
    }
}
