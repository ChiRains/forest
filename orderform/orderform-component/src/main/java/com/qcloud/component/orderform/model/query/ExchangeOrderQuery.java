package com.qcloud.component.orderform.model.query;

import com.qcloud.component.orderform.model.key.TypeEnum;

public class ExchangeOrderQuery {

    private Long   storeId;

    private Long   merchantId;

    private Long   subOrderId;

    private int    state;

    private String exchangeNumber;

    public ExchangeOrderQuery() {

    }

    public Long getMerchantId() {

        return merchantId;
    }

    public void setMerchantId(Long merchantId) {

        this.merchantId = merchantId;
    }

    public Long getSubOrderId() {

        return subOrderId;
    }

    public void setSubOrderId(Long subOrderId) {

        this.subOrderId = subOrderId;
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
   
    public String getExchangeNumber() {

        return exchangeNumber;
    }

    public void setExchangeNumber(String exchangeNumber) {

        this.exchangeNumber = exchangeNumber;
    }
}
