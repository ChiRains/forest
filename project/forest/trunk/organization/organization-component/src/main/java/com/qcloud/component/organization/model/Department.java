package com.qcloud.component.organization.model;

import java.util.Date;
import com.qcloud.component.publicdata.IntKeyValue;

public class Department implements IntKeyValue {

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

    // 登记时间
    private Date   registTime;
    
    private String phone;

    public Department() {

    }

    public Department(long id, long parentId, String bsid, String name, long manager, String displayName, String province, String city, String district, String address, double longitude, double latitude, int type, String code, String image, Date registTime) {

        this.id = id;
        this.parentId = parentId;
        this.bsid = bsid;
        this.name = name;
        this.manager = manager;
        this.displayName = displayName;
        this.province = province;
        this.city = city;
        this.district = district;
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
        this.type = type;
        this.code = code;
        this.image = image;
        this.registTime = registTime;
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

    public void setType(int type) {

        this.type = type;
    }

    public int getType() {

        return type;
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

    public void setRegistTime(Date registTime) {

        this.registTime = registTime;
    }

    public Date getRegistTime() {

        return registTime;
    }

    @Override
    public long getKey() {

        return id;
    }

    @Override
    public String getValue() {

        return name;
    }

    
    public String getPhone() {
    
        return phone;
    }

    
    public void setPhone(String phone) {
    
        this.phone = phone;
    }
}
