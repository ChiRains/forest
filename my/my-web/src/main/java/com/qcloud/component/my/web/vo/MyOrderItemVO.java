package com.qcloud.component.my.web.vo;

public class MyOrderItemVO {

    private long   orderItemId;

    private long   unifiedMerchandiseId;

    private double discount;

    private int    number;

    private String image;

    private String name;

    private String specifications;

    private long   merchantId;

    private String merchantName;

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

    public int getNumber() {

        return number;
    }

    public void setNumber(int number) {

        this.number = number;
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

    public String getSpecifications() {

        return specifications;
    }

    public void setSpecifications(String specifications) {

        this.specifications = specifications;
    }

    public long getMerchantId() {

        return merchantId;
    }

    public void setMerchantId(long merchantId) {

        this.merchantId = merchantId;
    }

    public String getMerchantName() {

        return merchantName;
    }

    public void setMerchantName(String merchantName) {

        this.merchantName = merchantName;
    }

    public long getOrderItemId() {

        return orderItemId;
    }

    public void setOrderItemId(long orderItemId) {

        this.orderItemId = orderItemId;
    }
}
