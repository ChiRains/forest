package com.qcloud.component.orderform.model;
public class PromotionToken {
    // 促销token
    private String promotionDetailTokenId;
    // 类别.不同的类别不同处理
    private int    type;
    // 促销标识
    private long   promotionId;

    public String getPromotionDetailTokenId() {
        return promotionDetailTokenId;
    }

    public void setPromotionDetailTokenId(String promotionDetailTokenId) {
        this.promotionDetailTokenId = promotionDetailTokenId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(long promotionId) {
        this.promotionId = promotionId;
    }
}
