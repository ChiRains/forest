package com.qcloud.component.my.web.vo;

public class MyStoreCollectionVO {

    private long            id;

    // 统一商品ID
    private long            storeId;

    // 收藏时间
    private String          timeStr;

    private CollectionStore collectionStore;

    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
    }

    public long getStoreId() {

        return storeId;
    }

    public void setStoreId(long storeId) {

        this.storeId = storeId;
    }

    public String getTimeStr() {

        return timeStr;
    }

    public void setTimeStr(String timeStr) {

        this.timeStr = timeStr;
    }

    public CollectionStore getCollectionStore() {

        return collectionStore;
    }

    public void setCollectionStore(CollectionStore collectionStore) {

        this.collectionStore = collectionStore;
    }
}
