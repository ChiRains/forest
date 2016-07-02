package com.qcloud.component.goods.model.query;

import com.qcloud.component.goods.model.key.TypeEnum.OrderType;
import com.qcloud.component.goods.model.key.TypeEnum.QueryType;

public class MerchandiseQuery {

    private Long      merchantId;

    private String    name;

    private Long      merchantClassifyId;

    private Long      mallClassifyId;

    private QueryType queryType;

    private OrderType orderType;

    private Long      specClassifyId;

    private String    code;

    public MerchandiseQuery() {

    }

    public Long getMerchantId() {

        return merchantId == null ? -1 : merchantId;
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

        return merchantClassifyId == null ? -1 : merchantClassifyId;
    }

    public void setMerchantClassifyId(Long merchantClassifyId) {

        this.merchantClassifyId = merchantClassifyId;
    }

    public Long getMallClassifyId() {

        return mallClassifyId == null ? -1 : mallClassifyId;
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

    public String getCode() {

        return code;
    }

    public void setCode(String code) {

        this.code = code;
    }

    public Long getSpecClassifyId() {

        return specClassifyId == null ? -1 : specClassifyId;
    }

    public void setSpecClassifyId(Long specClassifyId) {

        this.specClassifyId = specClassifyId;
    }
}
