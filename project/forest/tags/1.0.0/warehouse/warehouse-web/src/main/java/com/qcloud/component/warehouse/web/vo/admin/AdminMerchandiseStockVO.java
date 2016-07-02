package com.qcloud.component.warehouse.web.vo.admin;

import java.util.Date;
import java.math.BigDecimal;

public class AdminMerchandiseStockVO {

    // ID
    private long   id;

    // 商家ID
    private long   merchantId;

    // 门店ID
    private long   storeId;

    // 统一商品ID
    private long   unifiedMerchandiseId;

    // 名称
    private String merchandiseName;

    // 进货价
    private double purchase;

    // 折扣价
    private double discount;

    // 原价
    private double price;

    // 库存
    private int    stock;

    // 规格
    private String specifications;

    public AdminMerchandiseStockVO() {

    }

    public AdminMerchandiseStockVO(long id, long merchantId, long storeId, long unifiedMerchandiseId, String merchandiseName, double purchase, double discount, double price, int stock) {

        this.id = id;
        this.merchantId = merchantId;
        this.storeId = storeId;
        this.unifiedMerchandiseId = unifiedMerchandiseId;
        this.merchandiseName = merchandiseName;
        this.purchase = purchase;
        this.discount = discount;
        this.price = price;
        this.stock = stock;
    }

    public void setId(long id) {

        this.id = id;
    }

    public long getId() {

        return id;
    }

    public void setMerchantId(long merchantId) {

        this.merchantId = merchantId;
    }

    public long getMerchantId() {

        return merchantId;
    }

    public void setStoreId(long storeId) {

        this.storeId = storeId;
    }

    public long getStoreId() {

        return storeId;
    }

    public void setUnifiedMerchandiseId(long unifiedMerchandiseId) {

        this.unifiedMerchandiseId = unifiedMerchandiseId;
    }

    public long getUnifiedMerchandiseId() {

        return unifiedMerchandiseId;
    }

    public void setMerchandiseName(String merchandiseName) {

        this.merchandiseName = merchandiseName;
    }

    public String getMerchandiseName() {

        return merchandiseName;
    }

    public void setPurchase(double purchase) {

        this.purchase = purchase;
    }

    public double getPurchase() {

        return purchase;
    }

    public void setDiscount(double discount) {

        this.discount = discount;
    }

    public double getDiscount() {

        return discount;
    }

    public void setPrice(double price) {

        this.price = price;
    }

    public double getPrice() {

        return price;
    }

    public void setStock(int stock) {

        this.stock = stock;
    }

    public int getStock() {

        return stock;
    }

    public String getSpecifications() {

        return specifications;
    }

    public void setSpecifications(String specifications) {

        this.specifications = specifications;
    }
}
