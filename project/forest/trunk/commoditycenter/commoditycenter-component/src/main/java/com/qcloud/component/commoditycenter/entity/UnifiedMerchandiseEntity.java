package com.qcloud.component.commoditycenter.entity;

import java.util.ArrayList;
import java.util.List;
import com.qcloud.component.commoditycenter.QMerchandiseItem;
import com.qcloud.component.commoditycenter.QUnifiedMerchandise;
import com.qcloud.component.sellercenter.QMerchant;

public class UnifiedMerchandiseEntity implements QUnifiedMerchandise {

    // 统一商品标识
    private Long                   id;

    private String                 name;

    private String                 image;

    private long                   merchantId;

    // 商品类型
    private MerchandiseType        type;

    private int                    sence;

    // 进货价
    private double                 purchase;

    // 折扣价
    private double                 discount;

    // 原价
    private double                 price;

    // 库存
    private int                    stock;

    private double                 weight;

    private boolean                isIncludePost;

    // 商品明细:组合套餐,则可能是多个;单一商品则是一个,促销,热销,秒杀等等都是一个,必须是指定的某个具体型号规格的商品
    private List<QMerchandiseItem> list = new ArrayList<QMerchandiseItem>();

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public MerchandiseType getType() {

        return type;
    }

    public void setType(MerchandiseType type) {

        this.type = type;
    }

    public double getPurchase() {

        return purchase;
    }

    public void setPurchase(double purchase) {

        this.purchase = purchase;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getImage() {

        return image;
    }

    public void setImage(String image) {

        this.image = image;
    }

    public double getDiscount() {

        return discount;
    }

    public void setDiscount(double discount) {

        this.discount = discount;
    }

    public double getPrice() {

        return price;
    }

    public void setPrice(double price) {

        this.price = price;
    }

    public int getStock() {

        return stock;
    }

    public void setStock(int stock) {

        this.stock = stock;
    }

    public List<QMerchandiseItem> getList() {

        return list;
    }

    public void setList(List<QMerchandiseItem> list) {

        this.list = list;
    }

    public long getMerchantId() {

        return merchantId;
    }

    public void setMerchantId(long merchantId) {

        this.merchantId = merchantId;
    }

    public int getSence() {

        return sence;
    }

    public void setSence(int sence) {

        this.sence = sence;
    }

    @Override
    public String getSpecifications() {

        MerchandiseType type = getType();
        if (MerchandiseType.COMBINATION.equals(type)) {
            return "组合套餐";
        } else {
            return getList().get(0).getSpecifications();
        }
    }

    @Override
    public int hashCode() {

        return new Long(getId()).hashCode();
    }

    @Override
    public boolean equals(Object obj) {

        if (obj instanceof QMerchant) {
            return ((QMerchant) obj).getId() == getId();
        }
        return false;
    }

    public double getWeight() {

        return weight;
    }

    public void setWeight(double weight) {

        this.weight = weight;
    }

    @Override
    public String getUnit() {

        MerchandiseType type = getType();
        if (MerchandiseType.COMBINATION.equals(type)) {
            return "组合套餐";
        } else {
            return getList().get(0).getUnit();
        }
    }

    public boolean getIsIncludePost() {

        return isIncludePost;
    }

    public void setIncludePost(boolean isIncludePost) {

        this.isIncludePost = isIncludePost;
    }
}
