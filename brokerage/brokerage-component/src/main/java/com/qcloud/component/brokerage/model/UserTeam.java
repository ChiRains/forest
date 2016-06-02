package com.qcloud.component.brokerage.model;

import java.util.Date;
import java.math.BigDecimal;

public class UserTeam {
	
	private long id;		
	
	//用户ID
	private long userId;		
	
	//上级
	private long leader;		

	public UserTeam(){
	
	}

	public UserTeam(long id,long userId,long leader){
		this.id = id;		
		this.userId = userId;		
		this.leader = leader;		
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
		
	public void setLeader(long leader) {
		this.leader = leader;
	}

	public long getLeader() {
		return leader;
	}	
		
}
