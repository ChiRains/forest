package com.qcloud.component.goods.web.vo;

import java.util.ArrayList;
import java.util.List;

public class ExchangeMerchandiseVO {

    private String       name;

    private String       image;

    private long         stock;

    private double       price;

    private double       currency;

    private long         unifiedMerchandiseId;

    // ID
    private long         merchandiseId;

    // 商家ID
    private long         merchantId;

    private String       desc;

    private long         evaluationNumber;

    private List<String> imageList = new ArrayList<String>();

    private String       shareUrl;

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

    public long getStock() {

        return stock;
    }

    public void setStock(long stock) {

        this.stock = stock;
    }

    public double getCurrency() {

        return currency;
    }

    public void setCurrency(double currency) {

        this.currency = currency;
    }

    public double getPrice() {

        return price;
    }

    public void setPrice(double price) {

        this.price = price;
    }

    public long getUnifiedMerchandiseId() {

        return unifiedMerchandiseId;
    }

    public void setUnifiedMerchandiseId(long unifiedMerchandiseId) {

        this.unifiedMerchandiseId = unifiedMerchandiseId;
    }

    public long getMerchandiseId() {

        return merchandiseId;
    }

    public void setMerchandiseId(long merchandiseId) {

        this.merchandiseId = merchandiseId;
    }

    public long getMerchantId() {

        return merchantId;
    }

    public void setMerchantId(long merchantId) {

        this.merchantId = merchantId;
    }

    public String getDesc() {

        return desc;
    }

    public void setDesc(String desc) {

        this.desc = desc;
    }

    public List<String> getImageList() {

        return imageList;
    }

    public void setImageList(List<String> imageList) {

        this.imageList = imageList;
    }

    public long getEvaluationNumber() {

        return evaluationNumber;
    }

    public void setEvaluationNumber(long evaluationNumber) {

        this.evaluationNumber = evaluationNumber;
    }

    public String getShareUrl() {

        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {

        this.shareUrl = shareUrl;
    }
}
