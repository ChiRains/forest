package com.qcloud.component.publicservice.model;

import java.util.Date;
import java.math.BigDecimal;

public class MessageSource {
	
	private long id;		
	
	//消息标题
	private String title;		
	
	//消息内容
	private String content;		
	
	//1活动
	private long classifyId;		
	
	//平台-1
	private long merchantId;		
	
	private Date time;		
	
	//对应了message_type的code
	private int type;		

	public MessageSource(){
	
	}

	public MessageSource(long id,String title,String content,long classifyId,long merchantId,Date time,int type){
		this.id = id;		
		this.title = title;		
		this.content = content;		
		this.classifyId = classifyId;		
		this.merchantId = merchantId;		
		this.time = time;		
		this.type = type;		
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}	
		
	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}	
		
	public void setContent(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}	
		
	public void setClassifyId(long classifyId) {
		this.classifyId = classifyId;
	}

	public long getClassifyId() {
		return classifyId;
	}	
		
	public void setMerchantId(long merchantId) {
		this.merchantId = merchantId;
	}

	public long getMerchantId() {
		return merchantId;
	}	
		
	public void setTime(Date time) {
		this.time = time;
	}

	public Date getTime() {
		return time;
	}	
		
	public void setType(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}	
		
}
