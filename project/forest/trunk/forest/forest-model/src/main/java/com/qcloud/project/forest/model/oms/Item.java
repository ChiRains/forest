package com.qcloud.project.forest.model.oms;

public class Item {

    public String product_id;

    public String shop_product_id;

    public String title;

    public String sku_id;

    public String shop_sku_id;

    public String sku_name;

    public int    qty_ordered;

    public int    price;

    public int    oid;

    public String getProduct_id() {

        return product_id;
    }

    public void setProduct_id(String product_id) {

        this.product_id = product_id;
    }

    public String getShop_product_id() {

        return shop_product_id;
    }

    public void setShop_product_id(String shop_product_id) {

        this.shop_product_id = shop_product_id;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {

        this.title = title;
    }

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

    public int getQty_ordered() {

        return qty_ordered;
    }

    public void setQty_ordered(int qty_ordered) {

        this.qty_ordered = qty_ordered;
    }

    public int getPrice() {

        return price;
    }

    public void setPrice(int price) {

        this.price = price;
    }

    public int getOid() {

        return oid;
    }

    public void setOid(int oid) {

        this.oid = oid;
    }
}
