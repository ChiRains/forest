package com.qcloud.component.goods.web.vo.admin;

import java.util.Date;

public class AdminUnifiedMerchandiseVO {

    // ID
    private long   id;

    // 类型
    private int    type;

    // 商家ID
    private long   merchantId;

    // 商品ID
    private long   merchandiseId;

    // 商城分类ID
    private long   mallClassifyId;

    private String mallClassifyStr;

    private String specifications;

    private String merchantClassifyStr;

    // 树编码
    private String mallClassifyBsid;

    // 商品分类ID
    private long   merchantClassifyId;

    // 树编码
    private String merchantClassifyBsid;

    // 名称
    private String name;

    // 商品编号
    private String code;

    // 图片,缩略图
    private String image;

    // 品牌
    private long   brandId;

    // 进货价
    private double purchase;

    // 折扣价
    private double discount;

    // 原价
    private double price;

    // 积分
    private long   integral;

    // 是否可用优惠卷
    private int    canUseCoupon;

    // 库存
    private int    stock;

    // 关键字
    private String keywords;

    // 状态
    private int    state;

    // 销量
    private long   salesVolume;

    // 虚销量
    private long   virtualSalesVolume;

    // 点击数量
    private long   clickRate;

    // 差评数量
    private long   lowEvaluation;

    // 中评数量
    private long   middleEvaluation;

    // 好评数量
    private long   goodEvaluation;

    // relaUnifiedMerchandiseId 关联的统一商品id
    private long   relaUnifiedMerchandiseId;

    // 排序
    private int    order;

    // 活动id
    private long   activityId;

    // 录入时间
    private Date   recordTime;

    // 更新时间
    private Date   updateTime;

    public AdminUnifiedMerchandiseVO() {

    }

    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
    }

    public int getType() {

        return type;
    }

    public void setType(int type) {

        this.type = type;
    }

    public long getMerchantId() {

        return merchantId;
    }

    public void setMerchantId(long merchantId) {

        this.merchantId = merchantId;
    }

    public long getMerchandiseId() {

        return merchandiseId;
    }

    public void setMerchandiseId(long merchandiseId) {

        this.merchandiseId = merchandiseId;
    }

    public long getMallClassifyId() {

        return mallClassifyId;
    }

    public void setMallClassifyId(long mallClassifyId) {

        this.mallClassifyId = mallClassifyId;
    }

    public String getMallClassifyBsid() {

        return mallClassifyBsid;
    }

    public void setMallClassifyBsid(String mallClassifyBsid) {

        this.mallClassifyBsid = mallClassifyBsid;
    }

    public long getMerchantClassifyId() {

        return merchantClassifyId;
    }

    public void setMerchantClassifyId(long merchantClassifyId) {

        this.merchantClassifyId = merchantClassifyId;
    }

    public String getMerchantClassifyBsid() {

        return merchantClassifyBsid;
    }

    public void setMerchantClassifyBsid(String merchantClassifyBsid) {

        this.merchantClassifyBsid = merchantClassifyBsid;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getCode() {

        return code;
    }

    public void setCode(String code) {

        this.code = code;
    }

    public String getImage() {

        return image;
    }

    public void setImage(String image) {

        this.image = image;
    }

    public long getBrandId() {

        return brandId;
    }

    public void setBrandId(long brandId) {

        this.brandId = brandId;
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

    public long getIntegral() {

        return integral;
    }

    public void setIntegral(long integral) {

        this.integral = integral;
    }

    public int getCanUseCoupon() {

        return canUseCoupon;
    }

    public void setCanUseCoupon(int canUseCoupon) {

        this.canUseCoupon = canUseCoupon;
    }

    public int getStock() {

        return stock;
    }

    public void setStock(int stock) {

        this.stock = stock;
    }

    public String getKeywords() {

        return keywords;
    }

    public void setKeywords(String keywords) {

        this.keywords = keywords;
    }

    public int getState() {

        return state;
    }

    public void setState(int state) {

        this.state = state;
    }

    public long getSalesVolume() {

        return salesVolume;
    }

    public void setSalesVolume(long salesVolume) {

        this.salesVolume = salesVolume;
    }

    public long getVirtualSalesVolume() {

        return virtualSalesVolume;
    }

    public void setVirtualSalesVolume(long virtualSalesVolume) {

        this.virtualSalesVolume = virtualSalesVolume;
    }

    public long getClickRate() {

        return clickRate;
    }

    public void setClickRate(long clickRate) {

        this.clickRate = clickRate;
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

    public long getRelaUnifiedMerchandiseId() {

        return relaUnifiedMerchandiseId;
    }

    public void setRelaUnifiedMerchandiseId(long relaUnifiedMerchandiseId) {

        this.relaUnifiedMerchandiseId = relaUnifiedMerchandiseId;
    }

    public int getOrder() {

        return order;
    }

    public void setOrder(int order) {

        this.order = order;
    }

    public long getActivityId() {

        return activityId;
    }

    public void setActivityId(long activityId) {

        this.activityId = activityId;
    }

    public Date getRecordTime() {

        return recordTime;
    }

    public void setRecordTime(Date recordTime) {

        this.recordTime = recordTime;
    }

    public Date getUpdateTime() {

        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {

        this.updateTime = updateTime;
    }

    public String getMallClassifyStr() {

        return mallClassifyStr;
    }

    public void setMallClassifyStr(String mallClassifyStr) {

        this.mallClassifyStr = mallClassifyStr;
    }

    public String getSpecifications() {

        return specifications;
    }

    public void setSpecifications(String specifications) {

        this.specifications = specifications;
    }

    
    public String getMerchantClassifyStr() {
    
        return merchantClassifyStr;
    }

    
    public void setMerchantClassifyStr(String merchantClassifyStr) {
    
        this.merchantClassifyStr = merchantClassifyStr;
    }
}
