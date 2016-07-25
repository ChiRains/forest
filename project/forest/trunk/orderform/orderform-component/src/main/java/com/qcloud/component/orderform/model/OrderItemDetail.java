package com.qcloud.component.orderform.model;

import java.util.Date;
import java.math.BigDecimal;

public class OrderItemDetail {

    // ID
    private long   id;

    // 总单ID
    private long   orderId;

    // 子单ID
    private long   subOrderId;

    // 订单项ID
    private long   orderItemId;

    // 商家ID
    private long   merchantId;

    // 统一商品ID
    private long   unifiedMerchandiseId;

    // 单一商品ID
    private long   merchandiseItemId;

    // 数量
    private int    number;

    // 商品名称
    private String name;

    // 状态,待付款,已付款,待发货,已发货,已签收
    private int    state;

    // 规格说明
    private String specifications;

    // 缩略图
    private String image;

    // 商品编码
    private String code;

    private double weight;

    private double discount;

    public OrderItemDetail() {

    }

    public OrderItemDetail(long id, long orderId, long subOrderId, long orderItemId, long merchantId, long unifiedMerchandiseId, long merchandiseItemId, int number, String name, int state, String specifications, String image, String code, double weight, double discount) {

        super();
        this.id = id;
        this.orderId = orderId;
        this.subOrderId = subOrderId;
        this.orderItemId = orderItemId;
        this.merchantId = merchantId;
        this.unifiedMerchandiseId = unifiedMerchandiseId;
        this.merchandiseItemId = merchandiseItemId;
        this.number = number;
        this.name = name;
        this.state = state;
        this.specifications = specifications;
        this.image = image;
        this.code = code;
        this.weight = weight;
        this.discount = discount;
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

    public void setOrderItemId(long orderItemId) {

        this.orderItemId = orderItemId;
    }

    public long getOrderItemId() {

        return orderItemId;
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

    public void setMerchandiseItemId(long merchandiseItemId) {

        this.merchandiseItemId = merchandiseItemId;
    }

    public long getMerchandiseItemId() {

        return merchandiseItemId;
    }

    public void setNumber(int number) {

        this.number = number;
    }

    public int getNumber() {

        return number;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getName() {

        return name;
    }

    public void setState(int state) {

        this.state = state;
    }

    public int getState() {

        return state;
    }

    public void setSpecifications(String specifications) {

        this.specifications = specifications;
    }

    public String getSpecifications() {

        return specifications;
    }

    public void setImage(String image) {

        this.image = image;
    }

    public String getImage() {

        return image;
    }

    public void setCode(String code) {

        this.code = code;
    }

    public String getCode() {

        return code;
    }

    public void setWeight(double weight) {

        this.weight = weight;
    }

    public double getWeight() {

        return weight;
    }

    public double getDiscount() {

        return discount;
    }

    public void setDiscount(double discount) {

        this.discount = discount;
    }
}
