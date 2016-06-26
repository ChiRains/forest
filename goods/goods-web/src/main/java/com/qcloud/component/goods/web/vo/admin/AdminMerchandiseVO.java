package com.qcloud.component.goods.web.vo.admin;

public class AdminMerchandiseVO {

    // ID
    private long   id;

    // 商家分类ID
    private long   merchantClassifyId;

    private String merchantClassifyStr;

    // 商城分类ID
    private long   mallClassifyId;

    private String mallClassifyStr;

    private long   specClassifyId;

    private String specClassifyStr;

    // 商家ID
    private long   merchantId;

    // 名称
    private String name;

    // 商品编号
    private String code;

    // 图片,缩略图
    private String image;

    // 关键字
    private String keywords;

    // 状态
    private int    state;

    // 单位
    private String unit;

    // 详情
    private String details;

    // 进货价
    private double purchase;

    // 折扣价
    private double discount;

    // 原价
    private double price;

    // 库存
    private int    stock;

    private int    weight;

    private String imageUid;

    private String desc;

    private String sysCode;

    private int    isCertified;

    private int    isSpecialService;

    private int    isNoReason;

    private int    isExternalUrl;

    private String certified;

    private String specialService;

    private String noReason;

    private String externalUrl;

    private String merchantAdmin;

    private String merchantCode;

    private String merchantName;

    private int    isIncludePost;

    private long   brandId;

    public AdminMerchandiseVO() {

    }

    public String getMerchantClassifyStr() {

        return merchantClassifyStr;
    }

    public void setMerchantClassifyStr(String merchantClassifyStr) {

        this.merchantClassifyStr = merchantClassifyStr;
    }

    public String getMallClassifyStr() {

        return mallClassifyStr;
    }

    public void setMallClassifyStr(String mallClassifyStr) {

        this.mallClassifyStr = mallClassifyStr;
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

    public void setKeywords(String keywords) {

        this.keywords = keywords;
    }

    public String getKeywords() {

        return keywords;
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

    public void setPurchase(double purchase) {

        this.purchase = purchase;
    }

    public double getPurchase() {

        return purchase;
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

    public void setStock(int stock) {

        this.stock = stock;
    }

    public int getStock() {

        return stock;
    }

    public String getImageUid() {

        return imageUid;
    }

    public void setImageUid(String imageUid) {

        this.imageUid = imageUid;
    }

    public String getDesc() {

        return desc;
    }

    public void setDesc(String desc) {

        this.desc = desc;
    }

    public long getSpecClassifyId() {

        return specClassifyId;
    }

    public void setSpecClassifyId(long specClassifyId) {

        this.specClassifyId = specClassifyId;
    }

    public String getSpecClassifyStr() {

        return specClassifyStr;
    }

    public void setSpecClassifyStr(String specClassifyStr) {

        this.specClassifyStr = specClassifyStr;
    }

    public int getWeight() {

        return weight;
    }

    public void setWeight(int weight) {

        this.weight = weight;
    }

    public String getSysCode() {

        return sysCode;
    }

    public void setSysCode(String sysCode) {

        this.sysCode = sysCode;
    }

    public int getIsCertified() {

        return isCertified;
    }

    public void setIsCertified(int isCertified) {

        this.isCertified = isCertified;
    }

    public int getIsSpecialService() {

        return isSpecialService;
    }

    public void setIsSpecialService(int isSpecialService) {

        this.isSpecialService = isSpecialService;
    }

    public int getIsNoReason() {

        return isNoReason;
    }

    public void setIsNoReason(int isNoReason) {

        this.isNoReason = isNoReason;
    }

    public int getIsExternalUrl() {

        return isExternalUrl;
    }

    public void setIsExternalUrl(int isExternalUrl) {

        this.isExternalUrl = isExternalUrl;
    }

    public String getCertified() {

        return certified;
    }

    public void setCertified(String certified) {

        this.certified = certified;
    }

    public String getSpecialService() {

        return specialService;
    }

    public void setSpecialService(String specialService) {

        this.specialService = specialService;
    }

    public String getNoReason() {

        return noReason;
    }

    public void setNoReason(String noReason) {

        this.noReason = noReason;
    }

    public String getExternalUrl() {

        return externalUrl;
    }

    public void setExternalUrl(String externalUrl) {

        this.externalUrl = externalUrl;
    }

    public String getMerchantAdmin() {

        return merchantAdmin;
    }

    public void setMerchantAdmin(String merchantAdmin) {

        this.merchantAdmin = merchantAdmin;
    }

    public String getMerchantCode() {

        return merchantCode;
    }

    public void setMerchantCode(String merchantCode) {

        this.merchantCode = merchantCode;
    }

    public String getMerchantName() {

        return merchantName;
    }

    public void setMerchantName(String merchantName) {

        this.merchantName = merchantName;
    }

    public int getIsIncludePost() {

        return isIncludePost;
    }

    public void setIsIncludePost(int isIncludePost) {

        this.isIncludePost = isIncludePost;
    }

    public long getBrandId() {

        return brandId;
    }

    public void setBrandId(long brandId) {

        this.brandId = brandId;
    }
}
