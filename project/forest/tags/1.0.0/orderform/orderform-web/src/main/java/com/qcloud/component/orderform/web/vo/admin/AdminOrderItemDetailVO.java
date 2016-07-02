package com.qcloud.component.orderform.web.vo.admin;

public class AdminOrderItemDetailVO {

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

    // 商品名称
    private String name;

    // 状态,待付款,已付款,待发货,已发货,已签收
    private int    state;

    // 物流公司
    private String logisticsCompany;

    // 物流查询号
    private String logisticsNumber;

    // 规格说明
    private String specifications;

    // 缩略图
    private String image;

    private int    stock;

    private int    realStock;

    public AdminOrderItemDetailVO() {

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

    public void setLogisticsCompany(String logisticsCompany) {

        this.logisticsCompany = logisticsCompany;
    }

    public String getLogisticsCompany() {

        return logisticsCompany;
    }

    public void setLogisticsNumber(String logisticsNumber) {

        this.logisticsNumber = logisticsNumber;
    }

    public String getLogisticsNumber() {

        return logisticsNumber;
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

    public int getStock() {

        return stock;
    }

    public void setStock(int stock) {

        this.stock = stock;
    }

    public int getRealStock() {

        return realStock;
    }

    public void setRealStock(int realStock) {

        this.realStock = realStock;
    }
}
