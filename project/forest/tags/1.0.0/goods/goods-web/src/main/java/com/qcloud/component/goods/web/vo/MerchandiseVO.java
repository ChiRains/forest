package com.qcloud.component.goods.web.vo;

import com.qcloud.pirates.util.StringUtil;

public class MerchandiseVO {

    // ID
    private long   id;

    // 商家分类ID
    private long   merchantClassifyId;

    // 商城分类ID
    private long   mallClassifyId;

    // 商家ID
    private long   merchantId;

    // 名称
    private String name;

    // 商品编号
    private String code;

    // 图片,缩略图
    private String image;

    // 单位
    private String unit;

    // 折扣价
    private double discount;

    // 原价
    private double price;

    //
    private double vipDiscount;

    //
    private double minVipDiscount;

    //
    private double maxVipDiscount;

    // 销量
    private long   salesVolume;

    private int    goodEvaluationRate;

    private long   evaluationNumber;

    private String desc;

    private String specifications;

    private Long   unifiedMerchandiseId = -1L;

    // 购物车数量
    private int    number;

    private long   brandId;

    public MerchandiseVO() {

    }

    public void setId(long id) {

        this.id = id;
    }

    public long getId() {

        return id;
    }

    public int getGoodEvaluationRate() {

        return goodEvaluationRate;
    }

    public void setGoodEvaluationRate(int goodEvaluationRate) {

        this.goodEvaluationRate = goodEvaluationRate;
    }

    public void setMerchantClassifyId(long merchantClassifyId) {

        this.merchantClassifyId = merchantClassifyId;
    }

    public long getMerchantClassifyId() {

        return merchantClassifyId;
    }

    public void setMallClassifyId(long mallClassifyId) {

        this.mallClassifyId = mallClassifyId;
    }

    public long getMallClassifyId() {

        return mallClassifyId;
    }

    public void setMerchantId(long merchantId) {

        this.merchantId = merchantId;
    }

    public long getMerchantId() {

        return merchantId;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getName() {

        return name;
    }

    public void setCode(String code) {

        this.code = code;
    }

    public String getCode() {

        return code;
    }

    public void setImage(String image) {

        this.image = image;
    }

    public String getImage() {

        return image;
    }

    public void setUnit(String unit) {

        this.unit = unit;
    }

    public String getUnit() {

        return unit;
    }

    public void setDiscount(double discount) {

        this.discount = discount;
    }

    public double getDiscount() {

        return discount;
    }

    public void setPrice(double price) {

        this.price = price;
    }

    public double getPrice() {

        return price;
    }

    public long getSalesVolume() {

        return salesVolume;
    }

    public void setSalesVolume(long salesVolume) {

        this.salesVolume = salesVolume;
    }

    public String getDesc() {

        return StringUtil.nullToEmpty(desc);
    }

    public void setDesc(String desc) {

        this.desc = desc;
    }

    public Long getUnifiedMerchandiseId() {

        return unifiedMerchandiseId;
    }

    public void setUnifiedMerchandiseId(Long unifiedMerchandiseId) {

        this.unifiedMerchandiseId = unifiedMerchandiseId;
    }

    public String getSpecifications() {

        return specifications;
    }

    public void setSpecifications(String specifications) {

        this.specifications = specifications;
    }

    public long getEvaluationNumber() {

        return evaluationNumber;
    }

    public void setEvaluationNumber(long evaluationNumber) {

        this.evaluationNumber = evaluationNumber;
    }

    public double getMinVipDiscount() {

        return minVipDiscount;
    }

    public void setMinVipDiscount(double minVipDiscount) {

        this.minVipDiscount = minVipDiscount;
    }

    public double getMaxVipDiscount() {

        return maxVipDiscount;
    }

    public void setMaxVipDiscount(double maxVipDiscount) {

        this.maxVipDiscount = maxVipDiscount;
    }

    public double getVipDiscount() {

        return vipDiscount;
    }

    public void setVipDiscount(double vipDiscount) {

        this.vipDiscount = vipDiscount;
    }

    public int getNumber() {

        return number;
    }

    public void setNumber(int number) {

        this.number = number;
    }

    public long getBrandId() {

        return brandId;
    }

    public void setBrandId(long brandId) {

        this.brandId = brandId;
    }
}
