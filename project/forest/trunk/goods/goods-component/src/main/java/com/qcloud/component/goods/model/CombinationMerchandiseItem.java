package com.qcloud.component.goods.model;

import java.util.Date;
import java.math.BigDecimal;

public class CombinationMerchandiseItem {

    // ID
    private long   id;

    // 组合统一商品ID
    private long   combinationUnifiedMerchandiseId;

    // 关联单品统一商品ID
    private long   relaUnifiedMerchandiseId;

    // 商家ID
    private long   merchantId;

    // 数量
    private int    number;

    // 折扣价
    private double discount;

    private int    type;

    public CombinationMerchandiseItem() {

    }

    public CombinationMerchandiseItem(long id, long combinationUnifiedMerchandiseId, long relaUnifiedMerchandiseId, long merchantId, int number, double discount, int type) {

        this.id = id;
        this.combinationUnifiedMerchandiseId = combinationUnifiedMerchandiseId;
        this.relaUnifiedMerchandiseId = relaUnifiedMerchandiseId;
        this.merchantId = merchantId;
        this.number = number;
        this.discount = discount;
        this.type = type;
    }

    public void setId(long id) {

        this.id = id;
    }

    public long getId() {

        return id;
    }

    public void setCombinationUnifiedMerchandiseId(long combinationUnifiedMerchandiseId) {

        this.combinationUnifiedMerchandiseId = combinationUnifiedMerchandiseId;
    }

    public long getCombinationUnifiedMerchandiseId() {

        return combinationUnifiedMerchandiseId;
    }

    public void setRelaUnifiedMerchandiseId(long relaUnifiedMerchandiseId) {

        this.relaUnifiedMerchandiseId = relaUnifiedMerchandiseId;
    }

    public long getRelaUnifiedMerchandiseId() {

        return relaUnifiedMerchandiseId;
    }

    public void setMerchantId(long merchantId) {

        this.merchantId = merchantId;
    }

    public long getMerchantId() {

        return merchantId;
    }

    public void setNumber(int number) {

        this.number = number;
    }

    public int getNumber() {

        return number;
    }

    public void setDiscount(double discount) {

        this.discount = discount;
    }

    public double getDiscount() {

        return discount;
    }

    public int getType() {

        return type;
    }

    public void setType(int type) {

        this.type = type;
    }
}
