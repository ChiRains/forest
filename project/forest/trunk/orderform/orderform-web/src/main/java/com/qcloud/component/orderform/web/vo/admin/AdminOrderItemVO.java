package com.qcloud.component.orderform.web.vo.admin;

import com.qcloud.component.goods.model.UnifiedMerchandise;
import com.qcloud.component.sellercenter.model.Merchant;

public class AdminOrderItemVO {

    // ID
    private long               id;

    // 总单ID
    private long               orderId;

    // 子单ID
    private long               subOrderId;

    // 商家ID
    private long               merchantId;

    // 统一商品ID
    private long               unifiedMerchandiseId;

    // 单价
    private double             price;

    // 数量
    private int                number;

    // 小计金额
    private double             sum;

    // 成交价
    private double             discount;

    // 状态,待付款,已付款,待发货,已发货,已签收
    private int                state;

    private UnifiedMerchandise item;

    // 商家
    private Merchant           merchant;

    public AdminOrderItemVO() {

    }

    public void setId(long id) {

        this.id = id;
    }

    public long getId() {

        return id;
    }

    public void setOrderId(long orderId) {

        this.orderId = orderId;
    }

    public long getOrderId() {

        return orderId;
    }

    public void setSubOrderId(long subOrderId) {

        this.subOrderId = subOrderId;
    }

    public long getSubOrderId() {

        return subOrderId;
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

    public void setPrice(double price) {

        this.price = price;
    }

    public double getPrice() {

        return price;
    }

    public void setNumber(int number) {

        this.number = number;
    }

    public int getNumber() {

        return number;
    }

    public void setSum(double sum) {

        this.sum = sum;
    }

    public double getSum() {

        return sum;
    }

    public void setState(int state) {

        this.state = state;
    }

    public int getState() {

        return state;
    }

    public UnifiedMerchandise getItem() {

        return item;
    }

    public void setItem(UnifiedMerchandise item) {

        this.item = item;
    }

    public double getDiscount() {

        return discount;
    }

    public void setDiscount(double discount) {

        this.discount = discount;
    }

    public Merchant getMerchant() {

        return merchant;
    }

    public void setMerchant(Merchant merchant) {

        this.merchant = merchant;
    }
}
