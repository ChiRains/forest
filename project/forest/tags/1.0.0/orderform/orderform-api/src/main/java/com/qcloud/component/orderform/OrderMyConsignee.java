package com.qcloud.component.orderform;

import com.qcloud.component.my.QMyConsignee;

public class OrderMyConsignee implements QMyConsignee {

    private String                    name;

    private String                    mobile;

    private String                    email;

    private String                    province;

    private String                    city;

    private String                    district;

    private String                    address;

    private static final QMyConsignee EMPTY = new OrderMyConsignee();

    public static QMyConsignee get(final String name, final String mobile, final String address) {

        OrderMyConsignee orderMyConsignee = new OrderMyConsignee();
        orderMyConsignee.setName(name);
        orderMyConsignee.setMobile(mobile);
        orderMyConsignee.setAddress(address);
        return orderMyConsignee;
    }

    public static QMyConsignee get() {

        return EMPTY;
    }

    private OrderMyConsignee() {

        super();
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getMobile() {

        return mobile;
    }

    public void setMobile(String mobile) {

        this.mobile = mobile;
    }

    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {

        this.email = email;
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
}
