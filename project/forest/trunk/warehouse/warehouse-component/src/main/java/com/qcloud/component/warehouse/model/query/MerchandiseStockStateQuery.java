package com.qcloud.component.warehouse.model.query;

public class MerchandiseStockStateQuery {

    private int  state;

    private Long storeId;

    public int getState() {

        return state;
    }

    public void setState(int state) {

        this.state = state;
    }

    public Long getStoreId() {

        return storeId;
    }

    public void setStoreId(Long storeId) {

        this.storeId = storeId;
    }
}
