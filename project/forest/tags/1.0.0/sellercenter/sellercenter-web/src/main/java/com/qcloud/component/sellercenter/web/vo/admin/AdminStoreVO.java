package com.qcloud.component.sellercenter.web.vo.admin;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

public class AdminStoreVO {

    // ID
    private long   id;

    private long   parentId;

    // 树编码
    private String bsid;

    // 商家ID
    private long   merchantId;

    // 名称
    private String name;

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

    // 电话
    private String phone;
    
    private List<AdminMerchantOrderFormVO> merchantOrderFormVOs;
    
    private double sum;
    
    private String mobile;
	//是否启用 (0：禁用  1：启用)
	private int enable;		

    public AdminStoreVO() {

    }

    public String getLogoUid() {

        return logoUid;
    }

    public void setLogoUid(String logoUid) {

        this.logoUid = logoUid;
    }

    public void setId(long id) {

        this.id = id;
    }

    public long getId() {

        return id;
    }

    public void setParentId(long parentId) {

        this.parentId = parentId;
    }

    public long getParentId() {

        return parentId;
    }

    public void setBsid(String bsid) {

        this.bsid = bsid;
    }

    public String getBsid() {

        return bsid;
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

    public void setPhone(String phone) {

        this.phone = phone;
    }

    public String getPhone() {

        return phone;
    }

    
    public List<AdminMerchantOrderFormVO> getMerchantOrderFormVOs() {
    
        return merchantOrderFormVOs;
    }

    
    public void setMerchantOrderFormVOs(List<AdminMerchantOrderFormVO> merchantOrderFormVOs) {
    
        this.merchantOrderFormVOs = merchantOrderFormVOs;
    }

    
    public double getSum() {
    
        return sum;
    }

    
    public void setSum(double sum) {
    
        this.sum = sum;
    }

    
    public String getMobile() {
    
        return mobile;
    }

    
    public void setMobile(String mobile) {
    
        this.mobile = mobile;
    }

    
    public int getEnable() {
    
        return enable;
    }

    
    public void setEnable(int enable) {
    
        this.enable = enable;
    }
}
