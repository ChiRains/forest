package com.qcloud.component.brokerage.web.vo;

import java.util.Date;
import java.math.BigDecimal;

public class UserRelationshipVO {
	
	private long id;		
	
	//用户ID
	private long userId;		
	
	//推荐人ID
	private long recommedId;		

	public UserRelationshipVO(){
	
	}

	public UserRelationshipVO(long id,long userId,long recommedId){
		this.id = id;		
		this.userId = userId;		
		this.recommedId = recommedId;		
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}	
		
	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getUserId() {
		return userId;
	}	
		
	public void setRecommedId(long recommedId) {
		this.recommedId = recommedId;
	}

	public long getRecommedId() {
		return recommedId;
	}	
		
}
