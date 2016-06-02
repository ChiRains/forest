package com.qcloud.component.my.model;

import java.util.Date;
import java.math.BigDecimal;

public class InvoiceMode {
	
	//ID
	private long id;		
	
	//用户
	private long userId;		
	
	//模式 1开发票 1不开发票
	private int mode;		
	
	//类型 1普通 1增值税
	private int type;		
	
	//抬头
	private String head;		
	
	//内容
	private String content;		

	public InvoiceMode(){
	
	}

	public InvoiceMode(long id,long userId,int mode,int type,String head,String content){
		this.id = id;		
		this.userId = userId;		
		this.mode = mode;		
		this.type = type;		
		this.head = head;		
		this.content = content;		
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
		
	public void setMode(int mode) {
		this.mode = mode;
	}

	public int getMode() {
		return mode;
	}	
		
	public void setType(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}	
		
	public void setHead(String head) {
		this.head = head;
	}

	public String getHead() {
		return head;
	}	
		
	public void setContent(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}	
		
}
