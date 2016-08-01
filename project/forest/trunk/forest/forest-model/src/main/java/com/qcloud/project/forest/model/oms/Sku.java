package com.qcloud.project.forest.model.oms;

/**
 * 商品规格
 */
public class Sku {

    // 编码
    private String sku_id;

    // 商户编码
    private String shop_sku_id;

    // 名称
    private String sku_name;

    // 库存
    private int    sku_storage;

    // 单价
    private int    sku_price;

    public String getSku_id() {

        return sku_id;
    }

    public void setSku_id(String sku_id) {

        this.sku_id = sku_id;
    }

    public String getShop_sku_id() {

        return shop_sku_id;
    }

    public void setShop_sku_id(String shop_sku_id) {

        this.shop_sku_id = shop_sku_id;
    }

    public String getSku_name() {

        return sku_name;
    }

    public void setSku_name(String sku_name) {

        this.sku_name = sku_name;
    }

    public int getSku_storage() {

        return sku_storage;
    }

    public void setSku_storage(int sku_storage) {

        this.sku_storage = sku_storage;
    }

    public int getSku_price() {

        return sku_price;
    }

    public void setSku_price(int sku_price) {

        this.sku_price = sku_price;
    }
}
