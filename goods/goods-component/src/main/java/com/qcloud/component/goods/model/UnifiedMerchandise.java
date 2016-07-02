package com.qcloud.component.goods.model;

import java.util.Date;
import java.math.BigDecimal;

public class UnifiedMerchandise {
	
	//ID
	private long id;		
	
	//类型
	private int type;		
	
	//商家ID
	private long merchantId;		
	
	//商品ID
	private long merchandiseId;		
	
	//商城分类ID
	private long mallClassifyId;		
	
	//树编码
	private String mallClassifyBsid;		
	
	//商品分类ID
	private long merchantClassifyId;		
	
	//树编码
	private String merchantClassifyBsid;		
	
	//名称
	private String name;		
	
	//商品编号
	private String code;		
	
	//图片,缩略图
	private String image;		
	
	//品牌
	private long brandId;		
	
	//进货价
	private double purchase;		
	
	//折扣价
	private double discount;		
	
	//原价
	private double price;		
	
	//积分
	private long integral;		
	
	//是否可用优惠卷
	private int canUseCoupon;		
	
	//库存
	private int stock;		
	
	//关键字
	private String keywords;		
	
	//状态
	private int state;		
	
	//销量
	private long salesVolume;		
	
	//虚销量
	private long virtualSalesVolume;		
	
	//点击数量
	private long clickRate;		
	
	//差评数量
	private long lowEvaluation;		
	
	//中评数量
	private long middleEvaluation;		
	
	//好评数量
	private long goodEvaluation;		
	
	//relaUnifiedMerchandiseId
	private long relaUnifiedMerchandiseId;		
	
	//排序
	private int order;		
	
	//活动id
	private long activityId;		
	
	//录入时间
	private Date recordTime;		
	
	//更新时间
	private Date updateTime;		

	public UnifiedMerchandise(){
	
	}

	public UnifiedMerchandise(long id,int type,long merchantId,long merchandiseId,long mallClassifyId,String mallClassifyBsid,long merchantClassifyId,String merchantClassifyBsid,String name,String code,String image,long brandId,double purchase,double discount,double price,long integral,int canUseCoupon,int stock,String keywords,int state,long salesVolume,long virtualSalesVolume,long clickRate,long lowEvaluation,long middleEvaluation,long goodEvaluation,long relaUnifiedMerchandiseId,int order,long activityId,Date recordTime,Date updateTime){
		this.id = id;		
		this.type = type;		
		this.merchantId = merchantId;		
		this.merchandiseId = merchandiseId;		
		this.mallClassifyId = mallClassifyId;		
		this.mallClassifyBsid = mallClassifyBsid;		
		this.merchantClassifyId = merchantClassifyId;		
		this.merchantClassifyBsid = merchantClassifyBsid;		
		this.name = name;		
		this.code = code;		
		this.image = image;		
		this.brandId = brandId;		
		this.purchase = purchase;		
		this.discount = discount;		
		this.price = price;		
		this.integral = integral;		
		this.canUseCoupon = canUseCoupon;		
		this.stock = stock;		
		this.keywords = keywords;		
		this.state = state;		
		this.salesVolume = salesVolume;		
		this.virtualSalesVolume = virtualSalesVolume;		
		this.clickRate = clickRate;		
		this.lowEvaluation = lowEvaluation;		
		this.middleEvaluation = middleEvaluation;		
		this.goodEvaluation = goodEvaluation;		
		this.relaUnifiedMerchandiseId = relaUnifiedMerchandiseId;		
		this.order = order;		
		this.activityId = activityId;		
		this.recordTime = recordTime;		
		this.updateTime = updateTime;		
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}	
		
	public void setType(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}	
		
	public void setMerchantId(long merchantId) {
		this.merchantId = merchantId;
	}

	public long getMerchantId() {
		return merchantId;
	}	
		
	public void setMerchandiseId(long merchandiseId) {
		this.merchandiseId = merchandiseId;
	}

	public long getMerchandiseId() {
		return merchandiseId;
	}	
		
	public void setMallClassifyId(long mallClassifyId) {
		this.mallClassifyId = mallClassifyId;
	}

	public long getMallClassifyId() {
		return mallClassifyId;
	}	
		
	public void setMallClassifyBsid(String mallClassifyBsid) {
		this.mallClassifyBsid = mallClassifyBsid;
	}

	public String getMallClassifyBsid() {
		return mallClassifyBsid;
	}	
		
	public void setMerchantClassifyId(long merchantClassifyId) {
		this.merchantClassifyId = merchantClassifyId;
	}

	public long getMerchantClassifyId() {
		return merchantClassifyId;
	}	
		
	public void setMerchantClassifyBsid(String merchantClassifyBsid) {
		this.merchantClassifyBsid = merchantClassifyBsid;
	}

	public String getMerchantClassifyBsid() {
		return merchantClassifyBsid;
	}	
		
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}	
		
	public void setCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}	
		
	public void setImage(String image) {
		this.image = image;
	}

	public String getImage() {
		return image;
	}	
		
	public void setBrandId(long brandId) {
		this.brandId = brandId;
	}

	public long getBrandId() {
		return brandId;
	}	
		
	public void setPurchase(double purchase) {
		this.purchase = purchase;
	}

	public double getPurchase() {
		return purchase;
	}	
		
	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public double getDiscount() {
		return discount;
	}	
		
	public void setPrice(double price) {
		this.price = price;
	}

	public double getPrice() {
		return price;
	}	
		
	public void setIntegral(long integral) {
		this.integral = integral;
	}

	public long getIntegral() {
		return integral;
	}	
		
	public void setCanUseCoupon(int canUseCoupon) {
		this.canUseCoupon = canUseCoupon;
	}

	public int getCanUseCoupon() {
		return canUseCoupon;
	}	
		
	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getStock() {
		return stock;
	}	
		
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getKeywords() {
		return keywords;
	}	
		
	public void setState(int state) {
		this.state = state;
	}

	public int getState() {
		return state;
	}	
		
	public void setSalesVolume(long salesVolume) {
		this.salesVolume = salesVolume;
	}

	public long getSalesVolume() {
		return salesVolume;
	}	
		
	public void setVirtualSalesVolume(long virtualSalesVolume) {
		this.virtualSalesVolume = virtualSalesVolume;
	}

	public long getVirtualSalesVolume() {
		return virtualSalesVolume;
	}	
		
	public void setClickRate(long clickRate) {
		this.clickRate = clickRate;
	}

	public long getClickRate() {
		return clickRate;
	}	
		
	public void setLowEvaluation(long lowEvaluation) {
		this.lowEvaluation = lowEvaluation;
	}

	public long getLowEvaluation() {
		return lowEvaluation;
	}	
		
	public void setMiddleEvaluation(long middleEvaluation) {
		this.middleEvaluation = middleEvaluation;
	}

	public long getMiddleEvaluation() {
		return middleEvaluation;
	}	
		
	public void setGoodEvaluation(long goodEvaluation) {
		this.goodEvaluation = goodEvaluation;
	}

	public long getGoodEvaluation() {
		return goodEvaluation;
	}	
		
	public void setRelaUnifiedMerchandiseId(long relaUnifiedMerchandiseId) {
		this.relaUnifiedMerchandiseId = relaUnifiedMerchandiseId;
	}

	public long getRelaUnifiedMerchandiseId() {
		return relaUnifiedMerchandiseId;
	}	
		
	public void setOrder(int order) {
		this.order = order;
	}

	public int getOrder() {
		return order;
	}	
		
	public void setActivityId(long activityId) {
		this.activityId = activityId;
	}

	public long getActivityId() {
		return activityId;
	}	
		
	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}

	public Date getRecordTime() {
		return recordTime;
	}	
		
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}	
		
}
