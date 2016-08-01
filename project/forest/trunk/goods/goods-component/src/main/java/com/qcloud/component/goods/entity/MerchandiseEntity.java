package com.qcloud.component.goods.entity;

import com.qcloud.component.goods.QMerchandise;

public class MerchandiseEntity implements QMerchandise {

    // ID
    private long   id;

    // 商家分类ID
    private long   merchantClassifyId;

    // 商城分类ID
    private long   mallClassifyId;

    // 规格分类ID
    private long   specClassifyId;

    // 商家ID
    private long   merchantId;

    // 名称
    private String name;

    // 系统编号
    private String sysCode;

    // 图片,缩略图
    private String image;

    // 关键字
    private String keywords;

    // 重量
    private int    weight;

    // 状态(1, "待初始化" 2,"新增" 3,"待审核" 4, "上线" 5"下线")
    private int    state;

    // 单位
    private String unit;

    // 详情
    private String details;

    // 描述
    private String desc;

    private int    isCertified;

    private int    isSpecialService;

    private int    isNoReason;

    private int    isExternalUrl;

    private String certified;

    private String specialService;

    private String noReason;

    private String externalUrl;

    private int    isIncludePost;

    private long   brandId;

    private String label;

    // 最低折扣价（按规格）
    private double lowDiscount;

    // 最低原价（按规格）
    private double lowPrice;

    // 差评
    private long   lowEvaluation;

    // 中评
    private long   middleEvaluation;

    // 好评
    private long   goodEvaluation;

    // 销量
    private long   totalSalesVolume;

    // 好评率
    private String hpRate;

    // 状态(1, "待初始化" 2,"新增" 3,"待审核" 4, "上线" 5"下线")
    private String stateStr;

    private long   unifiedMerchandiseId;

    public double getLowPrice() {

        return lowPrice;
    }

    public void setLowPrice(double lowPrice) {

        this.lowPrice = lowPrice;
    }

    public long getLowEvaluation() {

        return lowEvaluation;
    }

    public void setLowEvaluation(long lowEvaluation) {

        this.lowEvaluation = lowEvaluation;
    }

    public long getMiddleEvaluation() {

        return middleEvaluation;
    }

    public void setMiddleEvaluation(long middleEvaluation) {

        this.middleEvaluation = middleEvaluation;
    }

    public long getGoodEvaluation() {

        return goodEvaluation;
    }

    public void setGoodEvaluation(long goodEvaluation) {

        this.goodEvaluation = goodEvaluation;
    }

    public void setId(long id) {

        this.id = id;
    }

    public long getId() {

        return id;
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

    public void setSpecClassifyId(long specClassifyId) {

        this.specClassifyId = specClassifyId;
    }

    public long getSpecClassifyId() {

        return specClassifyId;
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

    public void setSysCode(String sysCode) {

        this.sysCode = sysCode;
    }

    public String getSysCode() {

        return sysCode;
    }

    public void setImage(String image) {

        this.image = image;
    }

    public String getImage() {

        return image;
    }

    public void setKeywords(String keywords) {

        this.keywords = keywords;
    }

    public String getKeywords() {

        return keywords;
    }

    public void setWeight(int weight) {

        this.weight = weight;
    }

    public int getWeight() {

        return weight;
    }

    public void setState(int state) {

        this.state = state;
    }

    public int getState() {

        return state;
    }

    public void setUnit(String unit) {

        this.unit = unit;
    }

    public String getUnit() {

        return unit;
    }

    public void setDetails(String details) {

        this.details = details;
    }

    public String getDetails() {

        return details;
    }

    public void setDesc(String desc) {

        this.desc = desc;
    }

    public String getDesc() {

        return desc;
    }

    public void setIsCertified(int isCertified) {

        this.isCertified = isCertified;
    }

    public int getIsCertified() {

        return isCertified;
    }

    public void setIsSpecialService(int isSpecialService) {

        this.isSpecialService = isSpecialService;
    }

    public int getIsSpecialService() {

        return isSpecialService;
    }

    public void setIsNoReason(int isNoReason) {

        this.isNoReason = isNoReason;
    }

    public int getIsNoReason() {

        return isNoReason;
    }

    public void setIsExternalUrl(int isExternalUrl) {

        this.isExternalUrl = isExternalUrl;
    }

    public int getIsExternalUrl() {

        return isExternalUrl;
    }

    public void setCertified(String certified) {

        this.certified = certified;
    }

    public String getCertified() {

        return certified;
    }

    public void setSpecialService(String specialService) {

        this.specialService = specialService;
    }

    public String getSpecialService() {

        return specialService;
    }

    public void setNoReason(String noReason) {

        this.noReason = noReason;
    }

    public String getNoReason() {

        return noReason;
    }

    public void setExternalUrl(String externalUrl) {

        this.externalUrl = externalUrl;
    }

    public String getExternalUrl() {

        return externalUrl;
    }

    public void setIsIncludePost(int isIncludePost) {

        this.isIncludePost = isIncludePost;
    }

    public int getIsIncludePost() {

        return isIncludePost;
    }

    public void setBrandId(long brandId) {

        this.brandId = brandId;
    }

    public long getBrandId() {

        return brandId;
    }

    public String getLabel() {

        return label;
    }

    public void setLabel(String label) {

        this.label = label;
    }

    public double getLowDiscount() {

        return lowDiscount;
    }

    public void setLowDiscount(double lowDiscount) {

        this.lowDiscount = lowDiscount;
    }

    public long getTotalSalesVolume() {

        return totalSalesVolume;
    }

    public void setTotalSalesVolume(long totalSalesVolume) {

        this.totalSalesVolume = totalSalesVolume;
    }

    public String getHpRate() {

        return hpRate;
    }

    public void setHpRate(String hpRate) {

        this.hpRate = hpRate;
    }

    public String getStateStr() {

        return stateStr;
    }

    public void setStateStr(String stateStr) {

        this.stateStr = stateStr;
    }

    public long getUnifiedMerchandiseId() {

        return unifiedMerchandiseId;
    }

    public void setUnifiedMerchandiseId(long unifiedMerchandiseId) {

        this.unifiedMerchandiseId = unifiedMerchandiseId;
    }
}
