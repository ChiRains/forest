package com.qcloud.component.my.web.vo;

import com.qcloud.pirates.util.StringUtil;

public class ConsigneeVO {

    // ID
    private long   id;

    // 姓名
    private String name;

    // 省份
    private String province;

    // 市
    private String city;

    // 区
    private String district;

    // 地址
    private String address;

    // 电话
    private String mobile;

    // 默认
    private int    acquiesce;

    private String zipCode;

    public ConsigneeVO() {

    }

    public String getZipCode() {

        return zipCode;
    }

    public void setZipCode(String zipCode) {

        this.zipCode = zipCode;
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

        return StringUtil.nullToEmpty(name);
    }

    public String getProvince() {

        return StringUtil.nullToEmpty(province);
    }

    public void setProvince(String province) {

        this.province = province;
    }

    public String getCity() {

        return StringUtil.nullToEmpty(city);
    }

    public void setCity(String city) {

        this.city = city;
    }

    public String getDistrict() {

        return StringUtil.nullToEmpty(district);
    }

    public void setDistrict(String district) {

        this.district = district;
    }

    public void setAddress(String address) {

        this.address = address;
    }

    public String getAddress() {

        return StringUtil.nullToEmpty(address);
    }

    public void setMobile(String mobile) {

        this.mobile = mobile;
    }

    public String getMobile() {

        return StringUtil.nullToEmpty(mobile);
    }

    public int getAcquiesce() {

        return acquiesce;
    }

    public void setAcquiesce(int acquiesce) {

        this.acquiesce = acquiesce;
    }
}
