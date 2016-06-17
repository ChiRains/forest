package com.qcloud.component.sellercenter.entity;

import com.qcloud.component.sellercenter.QStore;

public class StoreEntity implements QStore {

    private Long    id;

    private String  name;

    private String  address;

    private String  phone;

    private boolean isRoot;

    private Long    merchantId;

    private String  smsMobile;

    private Double  latitude;

    private Double  longitude;

    public void setLatitude(Double latitude) {

        this.latitude = latitude;
    }

    public void setLongitude(Double longitude) {

        this.longitude = longitude;
    }

    public void setSmsMobile(String smsMobile) {

        this.smsMobile = smsMobile;
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
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

    public String getPhone() {

        return phone;
    }

    public void setPhone(String phone) {

        this.phone = phone;
    }

    public boolean isRoot() {

        return isRoot;
    }

    public void setRoot(boolean isRoot) {

        this.isRoot = isRoot;
    }

    public Long getMerchantId() {

        return merchantId;
    }

    public void setMerchantId(Long merchantId) {

        this.merchantId = merchantId;
    }

    @Override
    public String getSmsMobile() {

        return smsMobile;
    }

    @Override
    public Double getLatitude() {

        return latitude;
    }

    @Override
    public Double getLongitude() {

        return longitude;
    }
}
