package com.qcloud.component.orderform.web.vo.personal;

public class OrderItemVO {

    // 统一商品ID
    private long    unifiedMerchandiseId;

    // 折扣价,成交价
    private double  discount;

    // 数量
    private double  price;

    // 数量
    private int     number;

    // 商品名称
    private String  name;

    // 规格说明
    private String  specifications;

    // 缩略图
    private String  image;

    // 商品编号
    private String  code;

    // 订单项
    private Long    orderItemId;

    //
    private String  unit;

    // 能否售后
    private boolean afterSale;

    // 能否评论
    private boolean evaluation;

    private long    orderItemDetailId;

    public long getUnifiedMerchandiseId() {

        return unifiedMerchandiseId;
    }

    public void setUnifiedMerchandiseId(long unifiedMerchandiseId) {

        this.unifiedMerchandiseId = unifiedMerchandiseId;
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

    public int getNumber() {

        return number;
    }

    public void setNumber(int number) {

        this.number = number;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getSpecifications() {

        return specifications;
    }

    public void setSpecifications(String specifications) {

        this.specifications = specifications;
    }

    public String getImage() {

        return image;
    }

    public void setImage(String image) {

        this.image = image;
    }

    public String getCode() {

        return code;
    }

    public void setCode(String code) {

        this.code = code;
    }

    public Long getOrderItemId() {

        return orderItemId;
    }

    public void setOrderItemId(Long orderItemId) {

        this.orderItemId = orderItemId;
    }

    public boolean isAfterSale() {

        return afterSale;
    }

    public void setAfterSale(boolean afterSale) {

        this.afterSale = afterSale;
    }

    public boolean isEvaluation() {

        return evaluation;
    }

    public void setEvaluation(boolean evaluation) {

        this.evaluation = evaluation;
    }

    public String getUnit() {

        return unit;
    }

    public void setUnit(String unit) {

        this.unit = unit;
    }

    public long getOrderItemDetailId() {

        return orderItemDetailId;
    }

    public void setOrderItemDetailId(long orderItemDetailId) {

        this.orderItemDetailId = orderItemDetailId;
    }
}
