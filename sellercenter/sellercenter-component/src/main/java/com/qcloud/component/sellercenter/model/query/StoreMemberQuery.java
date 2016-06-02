package com.qcloud.component.sellercenter.model.query;

public class StoreMemberQuery {

    private Long merchantId;

    private Long storeId;

    private Long memberId;

    public StoreMemberQuery() {

    }

    public Long getMerchantId() {

        return merchantId;
    }

    public void setMerchantId(Long merchantId) {

        this.merchantId = merchantId;
    }

    public Long getStoreId() {

        return storeId == null ? -1 : storeId;
    }

    public void setStoreId(Long storeId) {

        this.storeId = storeId;
    }

    public Long getMemberId() {

        return memberId;
    }

    public void setMemberId(Long memberId) {

        this.memberId = memberId;
    }
}
