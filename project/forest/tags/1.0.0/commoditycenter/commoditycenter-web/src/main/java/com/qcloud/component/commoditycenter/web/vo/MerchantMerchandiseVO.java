package com.qcloud.component.commoditycenter.web.vo;

public class MerchantMerchandiseVO {

    private int    merchandiseNumber;

    private int    collectNumber;

    private Long   merchantId;

    private String merchantName;

    private String deliveryPlace;

    private String merchantImage;

    public int getMerchandiseNumber() {

        return merchandiseNumber;
    }

    public void setMerchandiseNumber(int merchandiseNumber) {

        this.merchandiseNumber = merchandiseNumber;
    }

    public int getCollectNumber() {

        return collectNumber;
    }

    public void setCollectNumber(int collectNumber) {

        this.collectNumber = collectNumber;
    }

    public Long getMerchantId() {

        return merchantId;
    }

    public void setMerchantId(Long merchantId) {

        this.merchantId = merchantId;
    }

    public String getMerchantName() {

        return merchantName;
    }

    public void setMerchantName(String merchantName) {

        this.merchantName = merchantName;
    }

    public String getDeliveryPlace() {

        return deliveryPlace;
    }

    public void setDeliveryPlace(String deliveryPlace) {

        this.deliveryPlace = deliveryPlace;
    }

    public String getMerchantImage() {

        return merchantImage;
    }

    public void setMerchantImage(String merchantImage) {

        this.merchantImage = merchantImage;
    }
}
