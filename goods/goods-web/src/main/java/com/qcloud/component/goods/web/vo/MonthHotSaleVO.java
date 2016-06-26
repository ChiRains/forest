package com.qcloud.component.goods.web.vo;

public class MonthHotSaleVO {

    // 统一商品ID
    private long   unifiedMerchandiseId;

    private String name;

    private String image;

    // 折扣价
    private double discount;

    //
    private double minVipDiscount;

    //
    private double maxVipDiscount;

    public MonthHotSaleVO() {

    }

    public void setUnifiedMerchandiseId(long unifiedMerchandiseId) {

        this.unifiedMerchandiseId = unifiedMerchandiseId;
    }

    public long getUnifiedMerchandiseId() {

        return unifiedMerchandiseId;
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

    public double getMinVipDiscount() {

        return minVipDiscount;
    }

    public void setMinVipDiscount(double minVipDiscount) {

        this.minVipDiscount = minVipDiscount;
    }

    public double getMaxVipDiscount() {

        return maxVipDiscount;
    }

    public void setMaxVipDiscount(double maxVipDiscount) {

        this.maxVipDiscount = maxVipDiscount;
    }
}
