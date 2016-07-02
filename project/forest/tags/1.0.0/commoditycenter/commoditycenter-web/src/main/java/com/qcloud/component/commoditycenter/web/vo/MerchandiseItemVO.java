package com.qcloud.component.commoditycenter.web.vo;

import java.util.Date;
import java.math.BigDecimal;

public class MerchandiseItemVO {

    // ID
    private long   id;

    // ID
    private long   unifiedMerchandiseId;

    // 商品ID
    private long   merchandiseId;

    // 商城分类ID
    private long   mallClassifyId;

    // 价格ID
    private long   priceId;

    // 商品规格，库存
    private long   merchandiseSpecificationsId;

    // 商家
    private long   merchantId;

    // 商品名称
    private String name;

    // 关键词
    private String keywords;

    // 状态
    private int    state;

    public MerchandiseItemVO(long id, long unifiedMerchandiseId, long merchandiseId, long merchantId, long priceId, long merchandiseSpecificationsId, String name, String keywords, int state) {

        this.id = id;
        this.unifiedMerchandiseId = unifiedMerchandiseId;
        this.merchandiseId = merchandiseId;
        this.merchantId = merchantId;
        this.priceId = priceId;
        this.merchandiseSpecificationsId = merchandiseSpecificationsId;
        this.name = name;
        this.keywords = keywords;
        this.state = state;
    }

    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
    }

    public long getUnifiedMerchandiseId() {

        return unifiedMerchandiseId;
    }

    public void setUnifiedMerchandiseId(long unifiedMerchandiseId) {

        this.unifiedMerchandiseId = unifiedMerchandiseId;
    }

    public long getMerchandiseId() {

        return merchandiseId;
    }

    public void setMerchandiseId(long merchandiseId) {

        this.merchandiseId = merchandiseId;
    }

    public long getMerchantId() {

        return merchantId;
    }

    public void setMerchantId(long merchantId) {

        this.merchantId = merchantId;
    }

    public long getPriceId() {

        return priceId;
    }

    public void setPriceId(long priceId) {

        this.priceId = priceId;
    }

    public long getMerchandiseSpecificationsId() {

        return merchandiseSpecificationsId;
    }

    public void setMerchandiseSpecificationsId(long merchandiseSpecificationsId) {

        this.merchandiseSpecificationsId = merchandiseSpecificationsId;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getKeywords() {

        return keywords;
    }

    public void setKeywords(String keywords) {

        this.keywords = keywords;
    }

    public int getState() {

        return state;
    }

    public void setState(int state) {

        this.state = state;
    }

    
    public long getMallClassifyId() {
    
        return mallClassifyId;
    }

    
    public void setMallClassifyId(long mallClassifyId) {
    
        this.mallClassifyId = mallClassifyId;
    }
}
