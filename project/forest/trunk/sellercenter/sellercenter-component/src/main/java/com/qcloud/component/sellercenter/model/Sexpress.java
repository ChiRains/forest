package com.qcloud.component.sellercenter.model;

import java.util.Date;
import java.math.BigDecimal;

public class Sexpress {
	
	private long id;		
	
	//商家id
	private long merchandId;		
	
	//快递公司名称
	private String name;		
	
	//快递公司编码
	private String code;		
	
	//描述
	private String desc;		
	
	//快递公司图片
	private String logo;		
	
	//排序
	private long sort;		
	
	//首重重量
	private double firstWeight;		
	
	//首重费用
	private double firstPrice;		
	
	//续重重量
	private double continuedWeight;		
	
	//续重费用
	private double continuedPrice;		
	
	//是否启用 0否1是
	private long enable;		
	
	//1包邮2统一邮费3区域收费
	private int type;		
	
	private double fixedPrice;
	

	public Sexpress(){
	
	}

	
	private Sexpress(long id, long merchandId, String name, String code, String desc, String logo, long sort, double firstWeight, double firstPrice, double continuedWeight, double continuedPrice, long enable, int type, double fixedPrice) {

        super();
        this.id = id;
        this.merchandId = merchandId;
        this.name = name;
        this.code = code;
        this.desc = desc;
        this.logo = logo;
        this.sort = sort;
        this.firstWeight = firstWeight;
        this.firstPrice = firstPrice;
        this.continuedWeight = continuedWeight;
        this.continuedPrice = continuedPrice;
        this.enable = enable;
        this.type = type;
        this.fixedPrice = fixedPrice;
    }


    public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}	
		
	public void setMerchandId(long merchandId) {
		this.merchandId = merchandId;
	}

	public long getMerchandId() {
		return merchandId;
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
		
	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}	
		
	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getLogo() {
		return logo;
	}	
		
	public void setSort(long sort) {
		this.sort = sort;
	}

	public long getSort() {
		return sort;
	}	
		
	public void setFirstWeight(double firstWeight) {
		this.firstWeight = firstWeight;
	}

	public double getFirstWeight() {
		return firstWeight;
	}	
		
	public void setFirstPrice(double firstPrice) {
		this.firstPrice = firstPrice;
	}

	public double getFirstPrice() {
		return firstPrice;
	}	
		
	public void setContinuedWeight(double continuedWeight) {
		this.continuedWeight = continuedWeight;
	}

	public double getContinuedWeight() {
		return continuedWeight;
	}	
		
	public void setContinuedPrice(double continuedPrice) {
		this.continuedPrice = continuedPrice;
	}

	public double getContinuedPrice() {
		return continuedPrice;
	}	
		
	public void setEnable(long enable) {
		this.enable = enable;
	}

	public long getEnable() {
		return enable;
	}	
		
	public void setType(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}

    
    public double getFixedPrice() {
    
        return fixedPrice;
    }

    
    public void setFixedPrice(double fixedPrice) {
    
        this.fixedPrice = fixedPrice;
    }	
		
}
