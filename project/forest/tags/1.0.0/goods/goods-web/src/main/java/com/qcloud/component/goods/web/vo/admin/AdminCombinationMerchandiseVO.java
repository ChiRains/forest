package com.qcloud.component.goods.web.vo.admin;

import java.util.Date;
import java.math.BigDecimal;

public class AdminCombinationMerchandiseVO {
	
	//ID
	private long id;		
	
	//商家ID
	private long merchantId;		
	
	//ID
	private long unifiedMerchandiseId;		
	
	//进货价
	private double purchase;		
	
	//折扣价
	private double discount;		
	
	//原价
	private double price;		
	
	//库存
	private int stock;

    //名称
    private String name;
    
    //图片，缩略图
    private String image;
    
    private String imageUID;

	public AdminCombinationMerchandiseVO(){
	
	}

    public AdminCombinationMerchandiseVO(long id, long merchantId, long unifiedMerchandiseId, double purchase, double discount, double price, int stock, String name,String image,String imageUID) {
        this.id = id;
        this.merchantId = merchantId;
        this.unifiedMerchandiseId = unifiedMerchandiseId;
        this.purchase = purchase;
        this.discount = discount;
        this.price = price;
        this.stock = stock;
        this.name = name;
        this.image = image;
        this.imageUID=imageUID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(long merchantId) {
        this.merchantId = merchantId;
    }

    public long getUnifiedMerchandiseId() {
        return unifiedMerchandiseId;
    }

    public void setUnifiedMerchandiseId(long unifiedMerchandiseId) {
        this.unifiedMerchandiseId = unifiedMerchandiseId;
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

    
    public String getImageUID() {
    
        return imageUID;
    }

    
    public void setImageUID(String imageUID) {
    
        this.imageUID = imageUID;
    }
}
