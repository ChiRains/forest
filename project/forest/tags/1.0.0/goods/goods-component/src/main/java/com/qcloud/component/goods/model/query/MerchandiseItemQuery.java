package com.qcloud.component.goods.model.query;

import com.qcloud.component.goods.model.key.TypeEnum.OrderType;
import com.qcloud.component.goods.model.key.TypeEnum.QueryItemType;
import com.qcloud.component.goods.model.key.TypeEnum.QueryType;

public class MerchandiseItemQuery {

    private Long          unifiedMerchandiseId;

    private Long          merchantId;

    private String        name;

    private Long          merchantClassifyId;

    private Long          mallClassifyId;

    private QueryType     queryType;

    private OrderType     orderType;

    private QueryItemType queryItemType;

    private String        notInIds;

    private String        microShop;

    private String        mallClassifyBsid;

    private String        merchantClassifyBsid;

    private Long          brandId;

    private long          classifyType;

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

    public Long getMerchantClassifyId() {

        return merchantClassifyId;
    }

    public void setMerchantClassifyId(Long merchantClassifyId) {

        this.merchantClassifyId = merchantClassifyId;
    }

    public Long getMallClassifyId() {

        return mallClassifyId;
    }

    public void setMallClassifyId(Long mallClassifyId) {

        this.mallClassifyId = mallClassifyId;
    }

    public QueryType getQueryType() {

        return queryType;
    }

    public void setQueryType(QueryType queryType) {

        this.queryType = queryType;
    }

    public OrderType getOrderType() {

        return orderType;
    }

    public void setOrderType(OrderType orderType) {

        this.orderType = orderType;
    }

    public QueryItemType getQueryItemType() {

        return queryItemType;
    }

    public void setQueryItemType(QueryItemType queryItemType) {

        this.queryItemType = queryItemType;
    }

    public String getNotInIds() {

        return notInIds;
    }

    public void setNotInIds(String notInIds) {

        this.notInIds = notInIds;
    }

    public String getMicroShop() {

        return microShop;
    }

    public void setMicroShop(String microShop) {

        this.microShop = microShop;
    }

    public Long getUnifiedMerchandiseId() {

        return unifiedMerchandiseId;
    }

    public void setUnifiedMerchandiseId(Long unifiedMerchandiseId) {

        this.unifiedMerchandiseId = unifiedMerchandiseId;
    }

    public String getMallClassifyBsid() {

        return mallClassifyBsid;
    }

    public void setMallClassifyBsid(String mallClassifyBsid) {

        this.mallClassifyBsid = mallClassifyBsid;
    }

    public String getMerchantClassifyBsid() {

        return merchantClassifyBsid;
    }

    public void setMerchantClassifyBsid(String merchantClassifyBsid) {

        this.merchantClassifyBsid = merchantClassifyBsid;
    }

    public Long getBrandId() {

        return brandId;
    }

    public void setBrandId(Long brandId) {

        this.brandId = brandId;
    }

    public long getClassifyType() {

        return classifyType;
    }

    public void setClassifyType(long classifyType) {

        this.classifyType = classifyType;
    }
}
