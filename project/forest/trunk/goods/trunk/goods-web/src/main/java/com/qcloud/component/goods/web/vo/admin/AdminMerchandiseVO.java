package com.qcloud.component.goods.web.vo.admin;

import java.util.Date;
import java.math.BigDecimal;

public class AdminMerchandiseVO {
	
	//ID
	private long id;		
	
	//商家分类ID
	private long merchantClassifyId;		
	
	//商城分类ID
	private long mallClassifyId;		
	
	//规格分类ID
	private long specClassifyId;		
	
	//商家ID
	private long merchantId;		
	
	//名称
	private String name;		
	
	//系统编号
	private String sysCode;		
	
	//商品编号
	private String code;		
	
	//图片,缩略图
	private String image;		
	
	//关键字
	private String keywords;		
	
	//重量
	private int weight;		
	
	//状态
	private int state;		
	
	//单位
	private String unit;		
	
	//详情
	private String details;		
	
	//描述
	private String desc;		
	
	private int isCertified;		
	
	private int isSpecialService;		
	
	private int isNoReason;		
	
	private int isExternalUrl;		
	
	private String certified;		
	
	private String specialService;		
	
	private String noReason;		
	
	private String externalUrl;		
	
	private int isIncludePost;		
	
	private long brandId;		

	public AdminMerchandiseVO(){
	
	}

	public AdminMerchandiseVO(long id,long merchantClassifyId,long mallClassifyId,long specClassifyId,long merchantId,String name,String sysCode,String code,String image,String keywords,int weight,int state,String unit,String details,String desc,int isCertified,int isSpecialService,int isNoReason,int isExternalUrl,String certified,String specialService,String noReason,String externalUrl,int isIncludePost,long brandId){
		this.id = id;		
		this.merchantClassifyId = merchantClassifyId;		
		this.mallClassifyId = mallClassifyId;		
		this.specClassifyId = specClassifyId;		
		this.merchantId = merchantId;		
		this.name = name;		
		this.sysCode = sysCode;		
		this.code = code;		
		this.image = image;		
		this.keywords = keywords;		
		this.weight = weight;		
		this.state = state;		
		this.unit = unit;		
		this.details = details;		
		this.desc = desc;		
		this.isCertified = isCertified;		
		this.isSpecialService = isSpecialService;		
		this.isNoReason = isNoReason;		
		this.isExternalUrl = isExternalUrl;		
		this.certified = certified;		
		this.specialService = specialService;		
		this.noReason = noReason;		
		this.externalUrl = externalUrl;		
		this.isIncludePost = isIncludePost;		
		this.brandId = brandId;		
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}	
		
	public void setMerchantClassifyId(long merchantClassifyId) {
		this.merchantClassifyId = merchantClassifyId;
	}

	public long getMerchantClassifyId() {
		return merchantClassifyId;
	}	
		
	public void setMallClassifyId(long mallClassifyId) {
		this.mallClassifyId = mallClassifyId;
	}

	public long getMallClassifyId() {
		return mallClassifyId;
	}	
		
	public void setSpecClassifyId(long specClassifyId) {
		this.specClassifyId = specClassifyId;
	}

	public long getSpecClassifyId() {
		return specClassifyId;
	}	
		
	public void setMerchantId(long merchantId) {
		this.merchantId = merchantId;
	}

	public long getMerchantId() {
		return merchantId;
	}	
		
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}	
		
	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}

	public String getSysCode() {
		return sysCode;
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
		
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getKeywords() {
		return keywords;
	}	
		
	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getWeight() {
		return weight;
	}	
		
	public void setState(int state) {
		this.state = state;
	}

	public int getState() {
		return state;
	}	
		
	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getUnit() {
		return unit;
	}	
		
	public void setDetails(String details) {
		this.details = details;
	}

	public String getDetails() {
		return details;
	}	
		
	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}	
		
	public void setIsCertified(int isCertified) {
		this.isCertified = isCertified;
	}

	public int getIsCertified() {
		return isCertified;
	}	
		
	public void setIsSpecialService(int isSpecialService) {
		this.isSpecialService = isSpecialService;
	}

	public int getIsSpecialService() {
		return isSpecialService;
	}	
		
	public void setIsNoReason(int isNoReason) {
		this.isNoReason = isNoReason;
	}

	public int getIsNoReason() {
		return isNoReason;
	}	
		
	public void setIsExternalUrl(int isExternalUrl) {
		this.isExternalUrl = isExternalUrl;
	}

	public int getIsExternalUrl() {
		return isExternalUrl;
	}	
		
	public void setCertified(String certified) {
		this.certified = certified;
	}

	public String getCertified() {
		return certified;
	}	
		
	public void setSpecialService(String specialService) {
		this.specialService = specialService;
	}

	public String getSpecialService() {
		return specialService;
	}	
		
	public void setNoReason(String noReason) {
		this.noReason = noReason;
	}

	public String getNoReason() {
		return noReason;
	}	
		
	public void setExternalUrl(String externalUrl) {
		this.externalUrl = externalUrl;
	}

	public String getExternalUrl() {
		return externalUrl;
	}	
		
	public void setIsIncludePost(int isIncludePost) {
		this.isIncludePost = isIncludePost;
	}

	public int getIsIncludePost() {
		return isIncludePost;
	}	
		
	public void setBrandId(long brandId) {
		this.brandId = brandId;
	}

	public long getBrandId() {
		return brandId;
	}	
		
}
