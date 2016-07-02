package com.qcloud.component.my.web.form;

public class ConsigneeForm {

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

    // 邮编
    private String zipCode;

    public String getZipCode() {

        return zipCode;
    }

    public void setZipCode(String zipCode) {

        this.zipCode = zipCode;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getAddress() {

        return address;
    }

    public void setAddress(String address) {

        this.address = address;
    }

    public String getMobile() {

        return mobile;
    }

    public void setMobile(String mobile) {

        this.mobile = mobile;
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
}
