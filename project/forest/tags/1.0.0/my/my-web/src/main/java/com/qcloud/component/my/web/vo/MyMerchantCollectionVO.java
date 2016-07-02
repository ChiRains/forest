package com.qcloud.component.my.web.vo;

public class MyMerchantCollectionVO {

    private long               id;

    // 统一商品ID
    private long               merchantId;

    // 收藏时间
    private String             timeStr;

    private CollectionMerchant collectionMerchant;

    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
    }

    public long getMerchantId() {

        return merchantId;
    }

    public void setMerchantId(long merchantId) {

        this.merchantId = merchantId;
    }

    public String getTimeStr() {

        return timeStr;
    }

    public void setTimeStr(String timeStr) {

        this.timeStr = timeStr;
    }

    public CollectionMerchant getCollectionMerchant() {

        return collectionMerchant;
    }

    public void setCollectionMerchant(CollectionMerchant collectionMerchant) {

        this.collectionMerchant = collectionMerchant;
    }
}
