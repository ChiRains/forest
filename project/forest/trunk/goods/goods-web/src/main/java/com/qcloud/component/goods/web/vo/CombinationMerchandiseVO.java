package com.qcloud.component.goods.web.vo;

import java.util.ArrayList;
import java.util.List;

public class CombinationMerchandiseVO {

    // 名称
    private String                             name;

    // 商家ID
    private long                               merchantId;

    // ID
    private long                               unifiedMerchandiseId;

    // 进货价
    private double                             price;

    // 折扣价
    private double                             discount;

    // 折扣价
    private double                             surplus;

    // 套餐缩略图
    private String                             image;

    // 库存
    private int                                stock;

    private List<CombinationMerchandiseItemVO> itemList = new ArrayList<CombinationMerchandiseItemVO>();

    private int                                type;

    public CombinationMerchandiseVO() {

    }

    public double getPrice() {

        return price;
    }

    public void setPrice(double price) {

        this.price = price;
    }

    public double getSurplus() {

        return surplus;
    }

    public void setSurplus(double surplus) {

        this.surplus = surplus;
    }

    public String getImage() {

        return image;
    }

    public void setImage(String image) {

        this.image = image;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public void setMerchantId(long merchantId) {

        this.merchantId = merchantId;
    }

    public long getMerchantId() {

        return merchantId;
    }

    public void setUnifiedMerchandiseId(long unifiedMerchandiseId) {

        this.unifiedMerchandiseId = unifiedMerchandiseId;
    }

    public long getUnifiedMerchandiseId() {

        return unifiedMerchandiseId;
    }

    public void setDiscount(double discount) {

        this.discount = discount;
    }

    public double getDiscount() {

        return discount;
    }

    public void setStock(int stock) {

        this.stock = stock;
    }

    public int getStock() {

        return stock;
    }

    public List<CombinationMerchandiseItemVO> getItemList() {

        return itemList;
    }

    public void setItemList(List<CombinationMerchandiseItemVO> itemList) {

        this.itemList = itemList;
    }

    public int getType() {

        return type;
    }

    public void setType(int type) {

        this.type = type;
    }
}
