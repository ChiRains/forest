package com.qcloud.component.sellercenter.model.query;


public class MerchantSortQuery {
    
    //名称
    private String name;
    
    //商家ID
    private long merchantId;

    
    public MerchantSortQuery() {

    }


    public String getName() {
    
        return name;
    }

    
    public void setName(String name) {
    
        this.name = name;
    }

    
    public long getMerchantId() {
    
        return merchantId;
    }

    
    public void setMerchantId(long merchantId) {
    
        this.merchantId = merchantId;
    }
    
}

