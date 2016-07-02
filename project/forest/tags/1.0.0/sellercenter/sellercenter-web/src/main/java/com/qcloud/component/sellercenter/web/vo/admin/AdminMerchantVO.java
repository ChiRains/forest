package com.qcloud.component.sellercenter.web.vo.admin;

import java.util.Date;

public class AdminMerchantVO {

    private long   id;

    // 编码（商家唯一编码）
    private String code;

    // 名称
    private String name;

    // 招商用户id
    private long   userId;

    // 招商用户名
    private String userMobile;

    // 省份
    private String province;

    // 市
    private String city;

    // 区
    private String district;

    // 地址
    private String address;

    // 经度
    private double longitude;

    // 纬度
    private double latitude;

    // LOGO
    private String logo;

    private String logoUid;

    // 商家分类
    private long   classifyId;

    // 商家分类
    private String classifyStr;

    // 简介
    private String introduction;

    // 登记时间
    private Date   registTime;

    // 上传商品是否需要审核
    private String commodityAuditingStr;

    // 卖出商品是否支持分销
    private String distributionStr;

    // 主打产品
    private String flagship;

    // 详细介绍
    private String detailIntroduction;

    // 联系人
    private String linkman;

    // 商家电话
    private String phone;

    // 是否禁用(0否，1是)
    private int    state;
    
    private String stateStr;

    // 1普通商家2房产商家
    private int    merchantType;

    // 是否要短信通知
    private int    notify;

    private String notifyStr;
    
    //接收短信的手机号
    private String receiveMobile;

    //图片
    private String image;

    private String imageUid;

    //有效期
    private Date   validDate;

    private String validDateStr;

    //成交扣点
    private int    buckle;

    //是否正品
    private int    isCertified;

    private String isCertifiedStr;
    
    //是否特色服务
    private int    isSpecialService;

    private String isSpecialServiceStr;
    
    //无理由退货
    private int    isNoReason;
    
    private String isNoReasonStr;

    //是否有外部链接
    private int    isExternalUrl;
    
    private String isExternalUrlStr;

    //是否包邮
    private int    isIncludePost;
    
    private String isIncludePostStr;

    private String admin;

    // 积分模式
    private long   integralModeId;

    // 消费币
    private double consumptionCurrency;

    public AdminMerchantVO() {

    }

    public void setId(long id) {

        this.id = id;
    }

    public long getId() {

        return id;
    }

    public String getCode() {

        return code;
    }

    public void setCode(String code) {

        this.code = code;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getName() {

        return name;
    }

    public void setProvince(String province) {

        this.province = province;
    }

    public String getProvince() {

        return province;
    }

    public void setCity(String city) {

        this.city = city;
    }

    public String getCity() {

        return city;
    }

    public void setDistrict(String district) {

        this.district = district;
    }

    public String getDistrict() {

        return district;
    }

    public void setAddress(String address) {

        this.address = address;
    }

    public String getAddress() {

        return address;
    }

    public void setLongitude(double longitude) {

        this.longitude = longitude;
    }

    public double getLongitude() {

        return longitude;
    }

    public void setLatitude(double latitude) {

        this.latitude = latitude;
    }

    public double getLatitude() {

        return latitude;
    }

    public void setLogo(String logo) {

        this.logo = logo;
    }

    public String getLogo() {

        return logo;
    }

    public String getLogoUid() {

        return logoUid;
    }

    public void setLogoUid(String logoUid) {

        this.logoUid = logoUid;
    }

    public void setClassifyId(long classifyId) {

        this.classifyId = classifyId;
    }

    public long getClassifyId() {

        return classifyId;
    }

    public void setIntroduction(String introduction) {

        this.introduction = introduction;
    }

    public String getIntroduction() {

        return introduction;
    }

    public void setRegistTime(Date registTime) {

        this.registTime = registTime;
    }

    public Date getRegistTime() {

        return registTime;
    }

    public String getCommodityAuditingStr() {

        return commodityAuditingStr;
    }

    public void setCommodityAuditingStr(String commodityAuditingStr) {

        this.commodityAuditingStr = commodityAuditingStr;
    }

    public String getDistributionStr() {

        return distributionStr;
    }

    public void setDistributionStr(String distributionStr) {

        this.distributionStr = distributionStr;
    }

    public String getClassifyStr() {

        return classifyStr;
    }

    public void setClassifyStr(String classifyStr) {

        this.classifyStr = classifyStr;
    }

    public String getFlagship() {

        return flagship;
    }

    public void setFlagship(String flagship) {

        this.flagship = flagship;
    }

    public String getDetailIntroduction() {

        return detailIntroduction;
    }

    public void setDetailIntroduction(String detailIntroduction) {

        this.detailIntroduction = detailIntroduction;
    }

    public String getLinkman() {

        return linkman;
    }

    public void setLinkman(String linkman) {

        this.linkman = linkman;
    }

    public String getPhone() {

        return phone;
    }

    public void setPhone(String phone) {

        this.phone = phone;
    }

    public long getUserId() {

        return userId;
    }

    public void setUserId(long userId) {

        this.userId = userId;
    }

    public String getUserMobile() {

        return userMobile;
    }

    public void setUserMobile(String userMobile) {

        this.userMobile = userMobile;
    }

    public int getState() {

        return state;
    }

    public void setState(int state) {

        this.state = state;
    }

    public int getMerchantType() {

        return merchantType;
    }

    public void setMerchantType(int merchantType) {

        this.merchantType = merchantType;
    }

    public int getNotify() {

        return notify;
    }

    public void setNotify(int notify) {

        this.notify = notify;
    }

    public String getImage() {

        return image;
    }

    public void setImage(String image) {

        this.image = image;
    }

    public String getImageUid() {

        return imageUid;
    }

    public void setImageUid(String imageUid) {

        this.imageUid = imageUid;
    }

    public String getReceiveMobile() {

        return receiveMobile;
    }

    public void setReceiveMobile(String receiveMobile) {

        this.receiveMobile = receiveMobile;
    }

    public Date getValidDate() {

        return validDate;
    }

    public void setValidDate(Date validDate) {

        this.validDate = validDate;
    }

    public int getBuckle() {

        return buckle;
    }

    public void setBuckle(int buckle) {

        this.buckle = buckle;
    }

    public String getValidDateStr() {

        return validDateStr;
    }

    public void setValidDateStr(String validDateStr) {

        this.validDateStr = validDateStr;
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

    public String getAdmin() {

        return admin;
    }

    public void setAdmin(String admin) {

        this.admin = admin;
    }

    public int getIsIncludePost() {

        return isIncludePost;
    }

    public void setIsIncludePost(int isIncludePost) {

        this.isIncludePost = isIncludePost;
    }

    public long getIntegralModeId() {

        return integralModeId;
    }

    public void setIntegralModeId(long integralModeId) {

        this.integralModeId = integralModeId;
    }

    public double getConsumptionCurrency() {

        return consumptionCurrency;
    }

    public void setConsumptionCurrency(double consumptionCurrency) {

        this.consumptionCurrency = consumptionCurrency;
    }

    public String getNotifyStr() {

        return notifyStr;
    }

    public void setNotifyStr(String notifyStr) {

        this.notifyStr = notifyStr;
    }

    
    public String getIsIncludePostStr() {
    
        return isIncludePostStr;
    }

    
    public void setIsIncludePostStr(String isIncludePostStr) {
    
        this.isIncludePostStr = isIncludePostStr;
    }

    
    public String getStateStr() {
    
        return stateStr;
    }

    
    public void setStateStr(String stateStr) {
    
        this.stateStr = stateStr;
    }

    
    public String getIsCertifiedStr() {
    
        return isCertifiedStr;
    }

    
    public void setIsCertifiedStr(String isCertifiedStr) {
    
        this.isCertifiedStr = isCertifiedStr;
    }

    
    public String getIsSpecialServiceStr() {
    
        return isSpecialServiceStr;
    }

    
    public void setIsSpecialServiceStr(String isSpecialServiceStr) {
    
        this.isSpecialServiceStr = isSpecialServiceStr;
    }

    
    public String getIsNoReasonStr() {
    
        return isNoReasonStr;
    }

    
    public void setIsNoReasonStr(String isNoReasonStr) {
    
        this.isNoReasonStr = isNoReasonStr;
    }

    
    public String getIsExternalUrlStr() {
    
        return isExternalUrlStr;
    }

    
    public void setIsExternalUrlStr(String isExternalUrlStr) {
    
        this.isExternalUrlStr = isExternalUrlStr;
    }
}
