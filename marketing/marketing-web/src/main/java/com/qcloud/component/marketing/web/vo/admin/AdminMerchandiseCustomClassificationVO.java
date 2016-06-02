package com.qcloud.component.marketing.web.vo.admin;

import com.qcloud.component.commoditycenter.QUnifiedMerchandise;

public class AdminMerchandiseCustomClassificationVO {

    // ID
    private long                id;

    // 商家ID,商城为1
    private long                merchantId;

    // 商品ID
    private long                unifiedMerchandiseId;

    // 排序
    private int                 orderNum;

    // 分类定义
    private long                customClassifyId;

    private String              name;//商品名称

    private String              image;

    private int                 stock;

    private String              specifications;

    //系统编号
    private String sysCode;

    private QUnifiedMerchandise qUnifiedMerchandise;

    public AdminMerchandiseCustomClassificationVO() {

    }

    public AdminMerchandiseCustomClassificationVO(long id, long merchantId, long unifiedMerchandiseId, int orderNum, long customClassifyId, String name, String image, int stock, QUnifiedMerchandise qUnifiedMerchandise) {

        this.id = id;
        this.merchantId = merchantId;
        this.unifiedMerchandiseId = unifiedMerchandiseId;
        this.orderNum = orderNum;
        this.customClassifyId = customClassifyId;
        this.name = name;
        this.image = image;
        this.stock = stock;
        this.qUnifiedMerchandise = qUnifiedMerchandise;
    }

    public void setId(long id) {

        this.id = id;
    }

    public long getId() {

        return id;
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

    public void setOrderNum(int orderNum) {

        this.orderNum = orderNum;
    }

    public int getOrderNum() {

        return orderNum;
    }

    public void setCustomClassifyId(long customClassifyId) {

        this.customClassifyId = customClassifyId;
    }

    public long getCustomClassifyId() {

        return customClassifyId;
    }

    public QUnifiedMerchandise getqUnifiedMerchandise() {

        return qUnifiedMerchandise;
    }

    public void setqUnifiedMerchandise(QUnifiedMerchandise qUnifiedMerchandise) {

        this.qUnifiedMerchandise = qUnifiedMerchandise;
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

    public int getStock() {

        return stock;
    }

    public void setStock(int stock) {

        this.stock = stock;
    }

    public String getSpecifications() {

        return specifications;
    }

    public void setSpecifications(String specifications) {

        this.specifications = specifications;
    }

    
    public String getSysCode() {
    
        return sysCode;
    }

    
    public void setSysCode(String sysCode) {
    
        this.sysCode = sysCode;
    }
}
