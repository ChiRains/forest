package com.qcloud.component.marketing.model.query;

public class CouponQuery {

    private long merchantId;

    private int  type;

    public CouponQuery() {

    }

    public long getMerchantId() {

        return merchantId;
    }

    public void setMerchantId(long merchantId) {

        this.merchantId = merchantId;
    }

    public int getType() {

        return type;
    }

    public void setType(int type) {

        this.type = type;
    }
}
