package com.qcloud.project.forest.model;

import java.util.Date;
import java.math.BigDecimal;

public class Activity {
	
	private long id;		
	
	//活动名称
	private String name;		
	
	//商家ID
	private long departmentId;		
	
	//插入时间
	private Date time;		

	public Activity(){
	
	}

	public Activity(long id,String name,long departmentId,Date time){
		this.id = id;		
		this.name = name;		
		this.departmentId = departmentId;		
		this.time = time;		
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
		
	public void setDepartmentId(long departmentId) {
		this.departmentId = departmentId;
	}

	public long getDepartmentId() {
		return departmentId;
	}	
		
	public void setTime(Date time) {
		this.time = time;
	}

	public Date getTime() {
		return time;
	}	
		
}
