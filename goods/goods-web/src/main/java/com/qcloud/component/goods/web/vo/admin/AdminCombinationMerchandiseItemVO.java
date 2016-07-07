package com.qcloud.component.goods.web.vo.admin;

import java.util.Date;
import java.math.BigDecimal;

public class AdminCombinationMerchandiseItemVO {

    // ID
    private long   id;

    // 组合商品ID
    private long   combinationMerchandiseId;

    // 单一商品ID
    private long   merchandiseItemId;

    // 商家ID
    private long   merchantId;

    private int    num;

    private long   unid;

    private String name;

    private String specifications;

    private int    stock;

    private double discount;

    public AdminCombinationMerchandiseItemVO() {

    }

    public void setId(long id) {

        this.id = id;
    }

    public long getId() {

        return id;
    }

    public void setCombinationMerchandiseId(long combinationMerchandiseId) {

        this.combinationMerchandiseId = combinationMerchandiseId;
    }

    public long getCombinationMerchandiseId() {

        return combinationMerchandiseId;
    }

    public void setMerchandiseItemId(long merchandiseItemId) {

        this.merchandiseItemId = merchandiseItemId;
    }

    public long getMerchandiseItemId() {

        return merchandiseItemId;
    }

    public void setMerchantId(long merchantId) {

        this.merchantId = merchantId;
    }

    public long getMerchantId() {

        return merchantId;
    }

    public int getNum() {

        return num;
    }

    public void setNum(int num) {

        this.num = num;
    }

    public long getUnid() {

        return unid;
    }

    public void setUnid(long unid) {

        this.unid = unid;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public int getStock() {

        return stock;
    }

    public void setStock(int stock) {

        this.stock = stock;
    }

    public String getSpecifications() {

        return specifications;
    }

    public void setSpecifications(String specifications) {

        this.specifications = specifications;
    }

    public double getDiscount() {

        return discount;
    }

    public void setDiscount(double discount) {

        this.discount = discount;
    }
}
