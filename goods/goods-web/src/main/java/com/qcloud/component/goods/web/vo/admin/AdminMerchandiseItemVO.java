package com.qcloud.component.goods.web.vo.admin;

import java.util.Date;
import java.math.BigDecimal;

public class AdminMerchandiseItemVO {

    //ID
    private long id;

    //ID
    private long unifiedMerchandiseId;

    
    //商城分类ID
    private long mallClassifyId;        
    
    //商品分类ID
    private long merchantClassifyId;        
    
    //商品ID
    private long merchandiseId;

    //价格ID
    private double price;

    //商品规格，库存
    private long merchandiseSpecificationsId;
    
    //进货价
    private double purchase;        
    
    //折扣价
    private double discount;

    //商家
    private long merchantId;

    //商品名称
    private String name;

    //关键词
    private String keywords;

    //状态
    private int state;
    
    private int isCheck;
    
    private int stock;
    
    private String image;
    //型号
    private String Specifications;
    
    

    public AdminMerchandiseItemVO() {
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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


    public long getMerchandiseSpecificationsId() {
        return merchandiseSpecificationsId;
    }

    public void setMerchandiseSpecificationsId(long merchandiseSpecificationsId) {
        this.merchandiseSpecificationsId = merchandiseSpecificationsId;
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

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    
    public int getIsCheck() {
    
        return isCheck;
    }

    
    public void setIsCheck(int isCheck) {
    
        this.isCheck = isCheck;
    }

    
    public int getStock() {
    
        return stock;
    }

    
    public void setStock(int stock) {
    
        this.stock = stock;
    }

    
    public long getMallClassifyId() {
    
        return mallClassifyId;
    }

    
    public void setMallClassifyId(long mallClassifyId) {
    
        this.mallClassifyId = mallClassifyId;
    }

    
    public long getMerchantClassifyId() {
    
        return merchantClassifyId;
    }

    
    public void setMerchantClassifyId(long merchantClassifyId) {
    
        this.merchantClassifyId = merchantClassifyId;
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getSpecifications() {
		return Specifications;
	}

	public void setSpecifications(String specifications) {
		Specifications = specifications;
	}


    
    public double getPrice() {
    
        return price;
    }


    
    public void setPrice(double price) {
    
        this.price = price;
    }
    
}
