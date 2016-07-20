package com.qcloud.component.goods.web.vo;

public class SimpleMerchandiseVO {

    private Long   unifiedMerchandiseId;

    private String name;

    private String image;

    private double discount;

    private String merchantName;

    public Long getUnifiedMerchandiseId() {

        return unifiedMerchandiseId;
    }

    public void setUnifiedMerchandiseId(Long unifiedMerchandiseId) {

        this.unifiedMerchandiseId = unifiedMerchandiseId;
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

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getMerchantName() {

        return merchantName;
    }

    public void setMerchantName(String merchantName) {

        this.merchantName = merchantName;
    }
}
