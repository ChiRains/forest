package com.qcloud.component.goods.entity;

import com.qcloud.component.goods.QMerchandiseItem;

public class MerchandiseItemEntity implements QMerchandiseItem {

    //
    private Long   id;

    private Long   unifiedMerchandiseId;

    private long   mallClassifyId;

    // 分类ID
    private long   merchantClassifyId;

    // 商家ID
    private long   merchantId;

    private long   merchandiseId;

    // 名称
    private String name;

    // 商品编号
    private String code;

    // 图片,缩略图
    private String image;

    // 规格描述
    private String specifications;

    private long   salesVolume;

    private int    number;

    private String unit;

    // 差评数量
    private long   lowEvaluation;

    // 中评数量
    private long   middleEvaluation;

    // 好评数量
    private long   goodEvaluation;

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public long getMerchantClassifyId() {

        return merchantClassifyId;
    }

    public void setMerchantClassifyId(long merchantClassifyId) {

        this.merchantClassifyId = merchantClassifyId;
    }

    public long getMerchantId() {

        return merchantId;
    }

    public void setMerchantId(long merchantId) {

        this.merchantId = merchantId;
    }

    public long getMerchandiseId() {

        return merchandiseId;
    }

    public void setMerchandiseId(long merchandiseId) {

        this.merchandiseId = merchandiseId;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getCode() {

        return code;
    }

    public void setCode(String code) {

        this.code = code;
    }

    public String getImage() {

        return image;
    }

    public void setImage(String image) {

        this.image = image;
    }

    public String getSpecifications() {

        return specifications;
    }

    public void setSpecifications(String specifications) {

        this.specifications = specifications;
    }

    public long getSalesVolume() {

        return salesVolume;
    }

    public void setSalesVolume(long salesVolume) {

        this.salesVolume = salesVolume;
    }

    public long getMallClassifyId() {

        return mallClassifyId;
    }

    public void setMallClassifyId(long mallClassifyId) {

        this.mallClassifyId = mallClassifyId;
    }

    public int getNumber() {

        return number;
    }

    public void setNumber(int number) {

        this.number = number;
    }

    public Long getUnifiedMerchandiseId() {

        return unifiedMerchandiseId;
    }

    public void setUnifiedMerchandiseId(Long unifiedMerchandiseId) {

        this.unifiedMerchandiseId = unifiedMerchandiseId;
    }

    public String getUnit() {

        return unit;
    }

    public void setUnit(String unit) {

        this.unit = unit;
    }

    public long getLowEvaluation() {

        return lowEvaluation;
    }

    public void setLowEvaluation(long lowEvaluation) {

        this.lowEvaluation = lowEvaluation;
    }

    public long getMiddleEvaluation() {

        return middleEvaluation;
    }

    public void setMiddleEvaluation(long middleEvaluation) {

        this.middleEvaluation = middleEvaluation;
    }

    public long getGoodEvaluation() {

        return goodEvaluation;
    }

    public void setGoodEvaluation(long goodEvaluation) {

        this.goodEvaluation = goodEvaluation;
    }
}
