package com.qcloud.component.organization.web.vo.admin;

import java.util.Date;
import java.math.BigDecimal;

public class AdminDepartmentVO {

    // ID
    private long   id;

    private long   parentId;

    // 树编码
    private String bsid;

    // 名称
    private String name;

    // 经理
    private long   manager;

    // 显示级层名称
    private String displayName;

    private String managerName;
    
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

    // 类型(1平台，2单位，3子单位，4部门)
    private int    type;

    // 编码（唯一编码）
    private String code;

    private String image;
    
    private String imageUid;

    // 登记时间
    private Date   registTime;
    
    private String phone;

    public AdminDepartmentVO() {

    }

    public AdminDepartmentVO(long id, long parentId, String bsid, String name, long manager, String displayName) {

        this.id = id;
        this.parentId = parentId;
        this.bsid = bsid;
        this.name = name;
        this.manager = manager;
        this.displayName = displayName;
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

    public void setName(String name) {

        this.name = name;
    }

    public String getName() {

        return name;
    }

    public void setManager(long manager) {

        this.manager = manager;
    }

    public long getManager() {

        return manager;
    }

    public void setDisplayName(String displayName) {

        this.displayName = displayName;
    }

    public String getDisplayName() {

        return displayName;
    }

    public String getManagerName() {

        return managerName;
    }

    public void setManagerName(String managerName) {

        this.managerName = managerName;
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

    
    public String getAddress() {
    
        return address;
    }

    
    public void setAddress(String address) {
    
        this.address = address;
    }

    
    public double getLongitude() {
    
        return longitude;
    }

    
    public void setLongitude(double longitude) {
    
        this.longitude = longitude;
    }

    
    public double getLatitude() {
    
        return latitude;
    }

    
    public void setLatitude(double latitude) {
    
        this.latitude = latitude;
    }

    
    public int getType() {
    
        return type;
    }

    
    public void setType(int type) {
    
        this.type = type;
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

    
    public Date getRegistTime() {
    
        return registTime;
    }

    
    public void setRegistTime(Date registTime) {
    
        this.registTime = registTime;
    }

    
    public String getPhone() {
    
        return phone;
    }

    
    public void setPhone(String phone) {
    
        this.phone = phone;
    }

    
    public String getImageUid() {
    
        return imageUid;
    }

    
    public void setImageUid(String imageUid) {
    
        this.imageUid = imageUid;
    }
}
