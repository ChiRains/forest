package com.qcloud.component.orderform.model.query;

import com.qcloud.component.orderform.model.key.TypeEnum;

public class ReturnOrderQuery {

    private Long   merchantId;

    private Long   storeId;

    private int    state;

    // 退货单号
    private String returnNumber;

    public ReturnOrderQuery() {

    }

    public Long getMerchantId() {

        return merchantId;
    }

    public void setMerchantId(Long merchantId) {

        this.merchantId = merchantId;
    }

    public Long getStoreId() {

        return storeId;
    }

    public void setStoreId(Long storeId) {

        this.storeId = storeId;
    }

    public int getState() {

        return state;
    }

    public void setState(int state) {

        this.state = state;
    }

    public String getReturnNumber() {

        return returnNumber;
    }

    public void setReturnNumber(String returnNumber) {

        this.returnNumber = returnNumber;
    }
}
