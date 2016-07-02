package com.qcloud.component.my.web.vo;

import java.util.List;

public class CollectionMerchant {

    private Long                         merchantId;

    private String                       name;

    // 图片
    private String                       image;

    private Long                         classifyId;

    private List<MerchantHotMerchandise> hotMerchandises;

    public Long getMerchantId() {

        return merchantId;
    }

    public void setMerchantId(Long merchantId) {

        this.merchantId = merchantId;
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

    public Long getClassifyId() {

        return classifyId;
    }

    public void setClassifyId(Long classifyId) {

        this.classifyId = classifyId;
    }

    public List<MerchantHotMerchandise> getHotMerchandises() {

        return hotMerchandises;
    }

    public void setHotMerchandises(List<MerchantHotMerchandise> hotMerchandises) {

        this.hotMerchandises = hotMerchandises;
    }
}
