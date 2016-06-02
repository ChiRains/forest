package com.qcloud.component.sellercenter.model.query;

public class DistributeMembershipCardStatQuery {

    private String merchantCode;

    private String merchantName;

    public DistributeMembershipCardStatQuery() {

    }

    public String getMerchantCode() {

        return merchantCode;
    }

    public void setMerchantCode(String merchantCode) {

        this.merchantCode = merchantCode;
    }

    public String getMerchantName() {

        return merchantName;
    }

    public void setMerchantName(String merchantName) {

        this.merchantName = merchantName;
    }
}
