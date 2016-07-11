package com.qcloud.project.forest.model.outside.entity;

/**
 * 商品详细
 */
public class SalesOrderLineEntity {

    // 商品代码
    public String productCode;

    // 商品名称
    public String productName;

    // 规格代码
    public String skuCode;

    // 规格名称
    public String skuName;

    // 数量
    public double quantity;

    // 单价
    public double priceSelling;

    public String getProductCode() {

        return productCode;
    }

    public void setProductCode(String productCode) {

        this.productCode = productCode;
    }

    public String getProductName() {

        return productName;
    }

    public void setProductName(String productName) {

        this.productName = productName;
    }

    public String getSkuCode() {

        return skuCode;
    }

    public void setSkuCode(String skuCode) {

        this.skuCode = skuCode;
    }

    public String getSkuName() {

        return skuName;
    }

    public void setSkuName(String skuName) {

        this.skuName = skuName;
    }

    public double getQuantity() {

        return quantity;
    }

    public void setQuantity(double quantity) {

        this.quantity = quantity;
    }

    public double getPriceSelling() {

        return priceSelling;
    }

    public void setPriceSelling(double priceSelling) {

        this.priceSelling = priceSelling;
    }
}
