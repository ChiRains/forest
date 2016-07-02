package com.qcloud.project.forest.web.vo.admin;

import java.util.Date;
import java.math.BigDecimal;

public class AdminModularUserVO {
	
	private long id;		
	
	//模块Id
	private long modularId;		
	
	//用户Id
	private long userId;		

	public AdminModularUserVO(){
	
	}

	public AdminModularUserVO(long id,long modularId,long userId){
		this.id = id;		
		this.modularId = modularId;		
		this.userId = userId;		
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}	
		
	public void setModularId(long modularId) {
		this.modularId = modularId;
	}

	public long getModularId() {
		return modularId;
	}	
		
	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getUserId() {
		return userId;
	}	
		
}
