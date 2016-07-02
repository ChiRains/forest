package com.qcloud.component.marketing.model.query;

public class RecentDiscountQuery {

    private Long   merchantId;

    private String name;
    
    private String date;
    
    

    public RecentDiscountQuery() {

    }

    public Long getMerchantId() {

        return merchantId;
    }

    public void setMerchantId(Long merchantId) {

        this.merchantId = merchantId;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    
    public String getDate() {
    
        return date;
    }

    
    public void setDate(String date) {
    
        this.date = date;
    }

}
