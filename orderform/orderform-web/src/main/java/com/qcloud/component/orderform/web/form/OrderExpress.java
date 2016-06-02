package com.qcloud.component.orderform.web.form;

public class OrderExpress {

    private Long   merchantId;

    private String expressCode;

    public Long getMerchantId() {

        return merchantId;
    }

    public void setMerchantId(Long merchantId) {

        this.merchantId = merchantId;
    }

    public String getExpressCode() {

        return expressCode;
    }

    public void setExpressCode(String expressCode) {

        this.expressCode = expressCode;
    }
}
