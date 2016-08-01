package com.qcloud.project.forest.model.oms;

import java.util.List;

/**
 * 商品属性
 */
public class Product {

    // 商品编码
    private String    product_id;

    // 商品名称
    private String    product_name;

    // 商品状态
    private String    product_status;

    // 商户商品编码
    private String    shop_product_id;

    // 商品价格
    private int       product_price;

    // 商品库存
    private int       product_storage;

    // 规格
    private List<Sku> sku_list;

    public String getProduct_id() {

        return product_id;
    }

    public void setProduct_id(String product_id) {

        this.product_id = product_id;
    }

    public String getProduct_name() {

        return product_name;
    }

    public void setProduct_name(String product_name) {

        this.product_name = product_name;
    }

    public String getProduct_status() {

        return product_status;
    }

    public void setProduct_status(String product_status) {

        this.product_status = product_status;
    }

    public String getShop_product_id() {

        return shop_product_id;
    }

    public void setShop_product_id(String shop_product_id) {

        this.shop_product_id = shop_product_id;
    }

    public List<Sku> getSku_list() {

        return sku_list;
    }

    public void setSku_list(List<Sku> sku_list) {

        this.sku_list = sku_list;
    }

    public int getProduct_price() {

        return product_price;
    }

    public void setProduct_price(int product_price) {

        this.product_price = product_price;
    }

    public int getProduct_storage() {

        return product_storage;
    }

    public void setProduct_storage(int product_storage) {

        this.product_storage = product_storage;
    }
}
