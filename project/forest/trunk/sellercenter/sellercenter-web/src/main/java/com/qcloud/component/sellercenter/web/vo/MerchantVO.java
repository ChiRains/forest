package com.qcloud.component.sellercenter.web.vo;

import java.util.Date;
import java.math.BigDecimal;

public class MerchantVO {

    private long   id;

    // 名称
    private String name;

    private long   userId;

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

    // 主打产品
    private String flagship;

    // 商家电话
    private String phone;

    // 简介
    private String introduction;

    private int    state;

    private int    merchantType;      // 1普通商家2房产商家

    // 商家列表展示图
    private String image;

    private String detailIntroduction;

    private String shareUrl;

    public MerchantVO() {

    }

    public void setId(long id) {

        this.id = id;
    }

    public long getId() {

        return id;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getName() {

        return name;
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

    public void setIntroduction(String introduction) {

        this.introduction = introduction;
    }

    public String getIntroduction() {

        return introduction;
    }

    public long getUserId() {

        return userId;
    }

    public void setUserId(long userId) {

        this.userId = userId;
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

    public String getFlagship() {

        return flagship;
    }

    public void setFlagship(String flagship) {

        this.flagship = flagship;
    }

    public String getPhone() {

        return phone;
    }

    public void setPhone(String phone) {

        this.phone = phone;
    }

    public String getProvince() {

        return province;
    }

    public void setProvince(String province) {

        this.province = province;
    }

    public String getCity() {

        return city;
    }

    public void setCity(String city) {

        this.city = city;
    }

    public String getDistrict() {

        return district;
    }

    public void setDistrict(String district) {

        this.district = district;
    }

    public String getImage() {

        return image;
    }

    public void setImage(String image) {

        this.image = image;
    }

    public String getDetailIntroduction() {

        return detailIntroduction;
    }

    public void setDetailIntroduction(String detailIntroduction) {

        this.detailIntroduction = detailIntroduction;
    }

    public String getShareUrl() {

        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {

        this.shareUrl = shareUrl;
    }
}
