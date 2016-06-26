package com.qcloud.component.goods.web.vo.admin;

import java.util.Date;
import java.math.BigDecimal;

public class AdminMerchandiseEvaluationVO {
	
	private long id;		
	
	//商品id
	private long merchandiseId;		
	
	//评价内容
	private String content;		
	
	//星级(1-5星，星值=(1-5)*10)
	private int star;		
	
	//评价时间
	private Date time;		
	
	//审核状态(0:未处理,1:未通过,2:已通过)
	private int status;		
	
	//规格
	private String specifications;		
	
	//评价人
	private long userId;		
	
	private int anonymous;		

	public AdminMerchandiseEvaluationVO(){
	
	}

	public AdminMerchandiseEvaluationVO(long id,long merchandiseId,String content,int star,Date time,int status,String specifications,long userId,int anonymous){
		this.id = id;		
		this.merchandiseId = merchandiseId;		
		this.content = content;		
		this.star = star;		
		this.time = time;		
		this.status = status;		
		this.specifications = specifications;		
		this.userId = userId;		
		this.anonymous = anonymous;		
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}	
		
	public void setMerchandiseId(long merchandiseId) {
		this.merchandiseId = merchandiseId;
	}

	public long getMerchandiseId() {
		return merchandiseId;
	}	
		
	public void setContent(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}	
		
	public void setStar(int star) {
		this.star = star;
	}

	public int getStar() {
		return star;
	}	
		
	public void setTime(Date time) {
		this.time = time;
	}

	public Date getTime() {
		return time;
	}	
		
	public void setStatus(int status) {
		this.status = status;
	}

	public int getStatus() {
		return status;
	}	
		
	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}

	public String getSpecifications() {
		return specifications;
	}	
		
	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getUserId() {
		return userId;
	}	
		
	public void setAnonymous(int anonymous) {
		this.anonymous = anonymous;
	}

	public int getAnonymous() {
		return anonymous;
	}	
		
}
