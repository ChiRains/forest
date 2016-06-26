package com.qcloud.component.goods.model.cache;

public class MerchandiseItemCache {

    // 单一商品ID
    private Long    id;

    // ID
    private long    unifiedMerchandiseId;

    // 商家分类ID
    private long    merchantClassifyId;

    // 商城分类ID
    private long    mallClassifyId;

    // 商家ID
    private long    merchantId;

    private long    merchandiseId;

    // 名称
    private String  name;

    // 商品编号
    private String  code;

    // 图片,缩略图
    private String  image;

    // 进货价
    private double  purchase;

    // 折扣价
    private double  discount;

    // 原价
    private double  price;

    // 库存
    private int     stock;

    // 商品规格，库存
    private long    merchandiseSpecificationsId;

    // 规格说明
    private String  specifications;

    private long    salesVolume;

    private int     number;

    private double  weight;

    private String  unit;

    private boolean isIncludePost;

    // 差评数量
    private long    lowEvaluation;

    // 中评数量
    private long    middleEvaluation;

    // 好评数量
    private long    goodEvaluation;

    public long getMerchandiseId() {

        return merchandiseId;
    }

    public void setMerchandiseId(long merchandiseId) {

        this.merchandiseId = merchandiseId;
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public long getUnifiedMerchandiseId() {

        return unifiedMerchandiseId;
    }

    public void setUnifiedMerchandiseId(long unifiedMerchandiseId) {

        this.unifiedMerchandiseId = unifiedMerchandiseId;
    }

    public long getMerchantClassifyId() {

        return merchantClassifyId;
    }

    public void setMerchantClassifyId(long merchantClassifyId) {

        this.merchantClassifyId = merchantClassifyId;
    }

    public long getMallClassifyId() {

        return mallClassifyId;
    }

    public void setMallClassifyId(long mallClassifyId) {

        this.mallClassifyId = mallClassifyId;
    }

    public long getMerchantId() {

        return merchantId;
    }

    public void setMerchantId(long merchantId) {

        this.merchantId = merchantId;
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

    public double getPurchase() {

        return purchase;
    }

    public void setPurchase(double purchase) {

        this.purchase = purchase;
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

    public int getStock() {

        return stock;
    }

    public void setStock(int stock) {

        this.stock = stock;
    }

    public long getMerchandiseSpecificationsId() {

        return merchandiseSpecificationsId;
    }

    public void setMerchandiseSpecificationsId(long merchandiseSpecificationsId) {

        this.merchandiseSpecificationsId = merchandiseSpecificationsId;
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

    public int getNumber() {

        return number;
    }

    public void setNumber(int number) {

        this.number = number;
    }

    public double getWeight() {

        return weight;
    }

    public void setWeight(double weight) {

        this.weight = weight;
    }

    public String getUnit() {

        return unit;
    }

    public void setUnit(String unit) {

        this.unit = unit;
    }

    public boolean getIsIncludePost() {

        return isIncludePost;
    }

    public void setIncludePost(boolean isIncludePost) {

        this.isIncludePost = isIncludePost;
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
