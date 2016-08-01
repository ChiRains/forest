package com.qcloud.project.forest.model.oms;

/**
 * 产品行信息
 */
public class Item {

    // 商品编码
    private String product_id;

    // 商家商品编码
    private String shop_product_id;

    // 商品名称
    private String title;

    // 规格编码
    private String sku_id;

    // 商家规格编码
    private String shop_sku_id;

    // 规格名称
    private String sku_name;

    // 商品数量
    private int    qty_ordered;

    // 商品单价
    private int    price;

    // 子订单号（非必要）
    private int    oid;

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
