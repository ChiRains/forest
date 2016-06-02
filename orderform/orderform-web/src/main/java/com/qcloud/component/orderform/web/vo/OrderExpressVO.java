package com.qcloud.component.orderform.web.vo;

public class OrderExpressVO {

    private Long   merchantId;

    private String expressCode;

    private String name;

    private double postage;

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

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public double getPostage() {

        return postage;
    }

    public void setPostage(double postage) {

        this.postage = postage;
    }
}
