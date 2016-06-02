package com.qcloud.component.orderform.model.query;

import com.qcloud.component.orderform.model.key.TypeEnum;

public class RefundOrderQuery {

    private Long   merchantId;

    private Long   storeId;

    private int    state;

    private String refundNumber;

    public RefundOrderQuery() {

    }

    public Long getStoreId() {

        return storeId;
    }

    public void setStoreId(Long storeId) {

        this.storeId = storeId;
    }

    public Long getMerchantId() {

        return merchantId;
    }

    public void setMerchantId(Long merchantId) {

        this.merchantId = merchantId;
    }

    public int getState() {

        return state;
    }

    public void setState(int state) {

        this.state = state;
    }

    public String getRefundNumber() {

        return refundNumber;
    }

    public void setRefundNumber(String refundNumber) {

        this.refundNumber = refundNumber;
    }
}
