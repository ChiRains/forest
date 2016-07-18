package com.qcloud.component.goods.web.form;

public class MerchandiseForm {

    private String  keywords;

    private Long    merchantId;

    private Long    merchantClassifyId;

    private Long    mallClassifyId;

    // 商品档案列表查询类别:1 销量 2时间 3价格 4热度
    private Integer queryType;

    // 1 升序 2降序 默认 销量 降序 时间降序 价格升序 热度降序
    private Integer orderType;

    private Long    brandId;

    private Double  maxDiscount;

    private Double  minDiscount;

    public String getKeywords() {

        return keywords;
    }

    public void setKeywords(String keywords) {

        this.keywords = keywords;
    }

    public Long getMerchantId() {

        return merchantId;
    }

    public void setMerchantId(Long merchantId) {

        this.merchantId = merchantId;
    }

    public Integer getQueryType() {

        return queryType == null ? 1 : queryType;
    }

    public void setQueryType(Integer queryType) {

        this.queryType = queryType;
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

    public Integer getOrderType() {

        if (orderType == null) {
            switch (getQueryType()) {
            case 1:
                orderType = 2;
                break;
            case 2:
                orderType = 2;
                break;
            case 3:
                orderType = 1;
                break;
            case 4:
                orderType = 2;
                break;
            default:
                break;
            }
        }
        return orderType;
    }

    public void setOrderType(Integer orderType) {

        this.orderType = orderType;
    }

    public Long getBrandId() {

        return brandId;
    }

    public void setBrandId(Long brandId) {

        this.brandId = brandId;
    }

    public Double getMaxDiscount() {

        return maxDiscount == null ? 0 : maxDiscount;
    }

    public void setMaxDiscount(Double maxDiscount) {

        this.maxDiscount = maxDiscount;
    }

    public Double getMinDiscount() {

        return minDiscount == null ? 0 : minDiscount;
    }

    public void setMinDiscount(Double minDiscount) {

        this.minDiscount = minDiscount;
    }
}
