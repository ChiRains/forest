package com.qcloud.component.personalcenter.web.vo;

import java.util.Date;
import java.math.BigDecimal;

public class GradeVO {
	
	//ID
	private long id;		
	
	//名称
	private String name;		
	
	//等级描述
	private String desc;	
	
	// 积分
    private int    point;

    // 折扣
    private int    discount;
    
    //价格类型
    private int priceType;
    
  //图标
    private String image;
	 
    public int getPoint() {
    
        return point;
    }

    
    public void setPoint(int point) {
    
        this.point = point;
    }

    
    public int getDiscount() {
    
        return discount;
    }

    
    public void setDiscount(int discount) {
    
        this.discount = discount;
    }
	
	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}	
		
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}	
		
	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}


    public GradeVO(long id, String name, String desc, int point, int discount,int priceType,String image) {

        this.id = id;
        this.name = name;
        this.desc = desc;
        this.point = point;
        this.discount = discount;
        this.priceType=priceType;
        this.image = image;
    }


    public GradeVO() {

    }


    
    public int getPriceType() {
    
        return priceType;
    }


    
    public void setPriceType(int priceType) {
    
        this.priceType = priceType;
    }


    
    public String getImage() {
    
        return image;
    }


    
    public void setImage(String image) {
    
        this.image = image;
    }	
		
}
