//package com.qcloud.component.sellercenter.model;
//
//import java.util.Date;
//import java.math.BigDecimal;
//
//public class Merchant {
//
//    private long   id;
//
//    // 名称
//    private String name;
//
//    // 编码（商家唯一编码）
//    private String code;
//
//    private Long   userId;
//
//    // 省份
//    private String province;
//
//    // 市
//    private String city;
//
//    // 区
//    private String district;
//
//    // 地址
//    private String address;
//
//    // 经度
//    private double longitude;
//
//    // 纬度
//    private double latitude;
//
//    // LOGO
//    private String logo;
//
//    // 商家分类
//    private long   classifyId;
//
//    // 主打产品
//    private String flagship;
//
//    // 简介
//    private String introduction;
//
//    // 详细介绍
//    private String detailIntroduction;
//
//    // 联系人
//    private String linkman;
//
//    // 商家电话
//    private String phone;
//
//    // 积分模式
//    private long   integralModeId;
//
//    // 上传商品是否需要审核
//    private int    commodityAuditing;
//
//    // 卖出商品是否支持分销
//    private int    distribution;
//
//    // 登记时间
//    private Date   registTime;
//
//    // 商家是否禁用(1否，2是)
//    private int    state;
//
//    // 1普通商家2房产商家
//    private int    merchantType;
//
//    // 消费币
//    private double consumptionCurrency;
//
//    // 版本
//    private long   version;
//
//    private int    notify;
//
//    private String image;
//
//    private String receiveMobile;
//
//    private Date   validDate;
//
//    private int    buckle;
//
//    private int    isCertified;
//
//    private int    isSpecialService;
//
//    private int    isNoReason;
//
//    private int    isExternalUrl;
//
//    private String admin;
//
//    private int    isIncludePost;
//
//    public Merchant() {
//
//    }
//
//    public void setId(long id) {
//
//        this.id = id;
//    }
//
//    public long getId() {
//
//        return id;
//    }
//
//    public void setName(String name) {
//
//        this.name = name;
//    }
//
//    public String getName() {
//
//        return name;
//    }
//
//    public void setCode(String code) {
//
//        this.code = code;
//    }
//
//    public String getCode() {
//
//        return code;
//    }
//
//    public void setUserId(Long userId) {
//
//        this.userId = userId;
//    }
//
//    public Long getUserId() {
//
//        return userId;
//    }
//
//    public void setProvince(String province) {
//
//        this.province = province;
//    }
//
//    public String getProvince() {
//
//        return province;
//    }
//
//    public void setCity(String city) {
//
//        this.city = city;
//    }
//
//    public String getCity() {
//
//        return city;
//    }
//
//    public void setDistrict(String district) {
//
//        this.district = district;
//    }
//
//    public String getDistrict() {
//
//        return district;
//    }
//
//    public void setAddress(String address) {
//
//        this.address = address;
//    }
//
//    public String getAddress() {
//
//        return address;
//    }
//
//    public void setLongitude(double longitude) {
//
//        this.longitude = longitude;
//    }
//
//    public double getLongitude() {
//
//        return longitude;
//    }
//
//    public void setLatitude(double latitude) {
//
//        this.latitude = latitude;
//    }
//
//    public double getLatitude() {
//
//        return latitude;
//    }
//
//    public void setLogo(String logo) {
//
//        this.logo = logo;
//    }
//
//    public String getLogo() {
//
//        return logo;
//    }
//
//    public void setClassifyId(long classifyId) {
//
//        this.classifyId = classifyId;
//    }
//
//    public long getClassifyId() {
//
//        return classifyId;
//    }
//
//    public void setFlagship(String flagship) {
//
//        this.flagship = flagship;
//    }
//
//    public String getFlagship() {
//
//        return flagship;
//    }
//
//    public void setIntroduction(String introduction) {
//
//        this.introduction = introduction;
//    }
//
//    public String getIntroduction() {
//
//        return introduction;
//    }
//
//    public void setDetailIntroduction(String detailIntroduction) {
//
//        this.detailIntroduction = detailIntroduction;
//    }
//
//    public String getDetailIntroduction() {
//
//        return detailIntroduction;
//    }
//
//    public void setLinkman(String linkman) {
//
//        this.linkman = linkman;
//    }
//
//    public String getLinkman() {
//
//        return linkman;
//    }
//
//    public void setPhone(String phone) {
//
//        this.phone = phone;
//    }
//
//    public String getPhone() {
//
//        return phone;
//    }
//
//    public void setIntegralModeId(long integralModeId) {
//
//        this.integralModeId = integralModeId;
//    }
//
//    public long getIntegralModeId() {
//
//        return integralModeId;
//    }
//
//    public void setCommodityAuditing(int commodityAuditing) {
//
//        this.commodityAuditing = commodityAuditing;
//    }
//
//    public int getCommodityAuditing() {
//
//        return commodityAuditing;
//    }
//
//    public void setDistribution(int distribution) {
//
//        this.distribution = distribution;
//    }
//
//    public int getDistribution() {
//
//        return distribution;
//    }
//
//    public void setRegistTime(Date registTime) {
//
//        this.registTime = registTime;
//    }
//
//    public Date getRegistTime() {
//
//        return registTime;
//    }
//
//    public void setState(int state) {
//
//        this.state = state;
//    }
//
//    public int getState() {
//
//        return state;
//    }
//
//    public void setMerchantType(int merchantType) {
//
//        this.merchantType = merchantType;
//    }
//
//    public int getMerchantType() {
//
//        return merchantType;
//    }
//
//    public void setConsumptionCurrency(double consumptionCurrency) {
//
//        this.consumptionCurrency = consumptionCurrency;
//    }
//
//    public double getConsumptionCurrency() {
//
//        return consumptionCurrency;
//    }
//
//    public void setVersion(long version) {
//
//        this.version = version;
//    }
//
//    public long getVersion() {
//
//        return version;
//    }
//
//    public int getNotify() {
//
//        return notify;
//    }
//
//    public void setNotify(int notify) {
//
//        this.notify = notify;
//    }
//
//    public String getImage() {
//
//        return image;
//    }
//
//    public void setImage(String image) {
//
//        this.image = image;
//    }
//
//    public String getReceiveMobile() {
//
//        return receiveMobile;
//    }
//
//    public void setReceiveMobile(String receiveMobile) {
//
//        this.receiveMobile = receiveMobile;
//    }
//
//    public Date getValidDate() {
//
//        return validDate;
//    }
//
//    public void setValidDate(Date validDate) {
//
//        this.validDate = validDate;
//    }
//
//    public int getBuckle() {
//
//        return buckle;
//    }
//
//    public void setBuckle(int buckle) {
//
//        this.buckle = buckle;
//    }
//
//    public int getIsCertified() {
//
//        return isCertified;
//    }
//
//    public void setIsCertified(int isCertified) {
//
//        this.isCertified = isCertified;
//    }
//
//    public int getIsSpecialService() {
//
//        return isSpecialService;
//    }
//
//    public void setIsSpecialService(int isSpecialService) {
//
//        this.isSpecialService = isSpecialService;
//    }
//
//    public int getIsNoReason() {
//
//        return isNoReason;
//    }
//
//    public void setIsNoReason(int isNoReason) {
//
//        this.isNoReason = isNoReason;
//    }
//
//    public int getIsExternalUrl() {
//
//        return isExternalUrl;
//    }
//
//    public void setIsExternalUrl(int isExternalUrl) {
//
//        this.isExternalUrl = isExternalUrl;
//    }
//
//    public String getAdmin() {
//
//        return admin;
//    }
//
//    public void setAdmin(String admin) {
//
//        this.admin = admin;
//    }
//
//    public int getIsIncludePost() {
//
//        return isIncludePost;
//    }
//
//    public void setIsIncludePost(int isIncludePost) {
//
//        this.isIncludePost = isIncludePost;
//    }
//}
