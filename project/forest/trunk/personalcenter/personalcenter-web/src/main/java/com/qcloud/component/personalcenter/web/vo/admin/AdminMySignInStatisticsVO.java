package com.qcloud.component.personalcenter.web.vo.admin;

import java.util.Date;
import java.math.BigDecimal;

public class AdminMySignInStatisticsVO {
	
	//ID
	private long id;		
	
	//用户
	private long userId;		
	
	//签到总数
	private int total;		
	
	//最大连签记录
	private int maxSignIn;		
	
	//当前连签记录
	private int currentSignIn;		

	public AdminMySignInStatisticsVO(){
	
	}

	public AdminMySignInStatisticsVO(long id,long userId,int total,int maxSignIn,int currentSignIn){
		this.id = id;		
		this.userId = userId;		
		this.total = total;		
		this.maxSignIn = maxSignIn;		
		this.currentSignIn = currentSignIn;		
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
		
	public void setTotal(int total) {
		this.total = total;
	}

	public int getTotal() {
		return total;
	}	
		
	public void setMaxSignIn(int maxSignIn) {
		this.maxSignIn = maxSignIn;
	}

	public int getMaxSignIn() {
		return maxSignIn;
	}	
		
	public void setCurrentSignIn(int currentSignIn) {
		this.currentSignIn = currentSignIn;
	}

	public int getCurrentSignIn() {
		return currentSignIn;
	}	
		
}
