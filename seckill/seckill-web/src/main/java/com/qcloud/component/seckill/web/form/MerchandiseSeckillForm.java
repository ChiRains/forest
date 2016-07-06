package com.qcloud.component.seckill.web.form;

import java.util.List;

public class MerchandiseSeckillForm {

    private List<Long>    unifiedMerchandiseIds;

    private List<Integer> orderNums;

    private List<Double>  purchase;

    private List<Double>  discount;

    private List<Double>  price;

    private List<Long>    merchandiseItemsIds;

    // 库存
    private List<Integer> stock;

    private List<Long>    merchantIds;

    private List<Double>  seckillPrice;

    // //秒杀场次
    // private Long screeningsId;
    public List<Long> getUnifiedMerchandiseIds() {

        return unifiedMerchandiseIds;
    }

    public void setUnifiedMerchandiseIds(List<Long> unifiedMerchandiseIds) {

        this.unifiedMerchandiseIds = unifiedMerchandiseIds;
    }

    public List<Integer> getOrderNums() {

        return orderNums;
    }

    public void setOrderNums(List<Integer> orderNums) {

        this.orderNums = orderNums;
    }

    public List<Double> getPurchase() {

        return purchase;
    }

    public void setPurchase(List<Double> purchase) {

        this.purchase = purchase;
    }

    public List<Double> getDiscount() {

        return discount;
    }

    public void setDiscount(List<Double> discount) {

        this.discount = discount;
    }

    public List<Double> getPrice() {

        return price;
    }

    public void setPrice(List<Double> price) {

        this.price = price;
    }

    public List<Integer> getStock() {

        return stock;
    }

    public void setStock(List<Integer> stock) {

        this.stock = stock;
    }

    public List<Long> getMerchantIds() {

        return merchantIds;
    }

    public void setMerchantIds(List<Long> merchantIds) {

        this.merchantIds = merchantIds;
    }

    public List<Long> getMerchandiseItemsIds() {

        return merchandiseItemsIds;
    }

    public void setMerchandiseItemsIds(List<Long> merchandiseItemsIds) {

        this.merchandiseItemsIds = merchandiseItemsIds;
    }

    public List<Double> getSeckillPrice() {

        return seckillPrice;
    }

    public void setSeckillPrice(List<Double> seckillPrice) {

        this.seckillPrice = seckillPrice;
    }
    // public Long getScreeningsId() {
    // return screeningsId;
    // }
    //
    // public void setScreeningsId(Long screeningsId) {
    // this.screeningsId = screeningsId;
    // }
}
