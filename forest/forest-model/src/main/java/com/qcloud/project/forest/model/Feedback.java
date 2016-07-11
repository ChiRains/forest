package com.qcloud.project.forest.model;

import java.util.Date;
import java.math.BigDecimal;

public class Feedback {
	
	private long id;		
	
	//用户ID
	private long userId;		
	
	//反馈内容
	private String content;		
	
	//反馈类型
	private long classify;		
	
	//类型（1，很好；2，一般；3，差劲）
	private int type;		
	
	//状态（0，未处理；1，已查看）
	private int state;		
	
	//反馈时间
	private Date date;		

	public Feedback(){
	
	}

	public Feedback(long id,long userId,String content,long classify,int type,int state,Date date){
		this.id = id;		
		this.userId = userId;		
		this.content = content;		
		this.classify = classify;		
		this.type = type;		
		this.state = state;		
		this.date = date;		
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
		
	public void setContent(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}	
		
	public void setClassify(long classify) {
		this.classify = classify;
	}

	public long getClassify() {
		return classify;
	}	
		
	public void setType(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}	
		
	public void setState(int state) {
		this.state = state;
	}

	public int getState() {
		return state;
	}	
		
	public void setDate(Date date) {
		this.date = date;
	}

	public Date getDate() {
		return date;
	}	
		
}
