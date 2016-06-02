package com.qcloud.component.brokerage.web.vo;

import java.util.Date;
import java.math.BigDecimal;

public class UserTeamVO {
	
	private long id;		
	
	//用户ID
	private long userId;		
	
	//上级
	private long leader;		
	
	//层级
	private long hierarchy;		
	
	//叶子1是 2不是
	private int leaf;		

	public UserTeamVO(){
	
	}

	public UserTeamVO(long id,long userId,long leader,long hierarchy,int leaf){
		this.id = id;		
		this.userId = userId;		
		this.leader = leader;		
		this.hierarchy = hierarchy;		
		this.leaf = leaf;		
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
		
	public void setHierarchy(long hierarchy) {
		this.hierarchy = hierarchy;
	}

	public long getHierarchy() {
		return hierarchy;
	}	
		
	public void setLeaf(int leaf) {
		this.leaf = leaf;
	}

	public int getLeaf() {
		return leaf;
	}	
		
}
