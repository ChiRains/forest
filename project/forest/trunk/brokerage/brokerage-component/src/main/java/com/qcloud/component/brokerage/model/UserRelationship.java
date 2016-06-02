package com.qcloud.component.brokerage.model;

import java.util.Date;
import java.math.BigDecimal;

public class UserRelationship {
	
	private long id;		
	
	//用户ID
	private long userId;		
	
	//推荐人ID
	private long recommedId;		
	
	//是否分配到部门
	private int allocation;		
	
	//推荐时间
	private Date time;		

	public UserRelationship(){
	
	}

	public UserRelationship(long id,long userId,long recommedId,int allocation,Date time){
		this.id = id;		
		this.userId = userId;		
		this.recommedId = recommedId;		
		this.allocation = allocation;		
		this.time = time;		
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
		
	public void setAllocation(int allocation) {
		this.allocation = allocation;
	}

	public int getAllocation() {
		return allocation;
	}	
		
	public void setTime(Date time) {
		this.time = time;
	}

	public Date getTime() {
		return time;
	}	
		
}
