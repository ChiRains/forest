package com.qcloud.component.pay.model;

import java.util.Date;
import java.math.BigDecimal;

public class PayRecord {
	
	//ID
	private long id;		
	
	//业务ID
	private long objectId;		
	
	//发生时间
	private Date occurTime;		
	
	//交易号
	private String tradeId;		
	
	//模块 业务决定
	private String module;		
	
	//类型 支付宝等
	private String type;		
	
	//客户端 web  app  weixin等
	private String client;		
	
	//支付附带参数
	private String attach;		
	
	//支付时间
	private Date time;		

	public PayRecord(){
	
	}

	public PayRecord(long id,long objectId,Date occurTime,String tradeId,String module,String type,String client,String attach,Date time){
		this.id = id;		
		this.objectId = objectId;		
		this.occurTime = occurTime;		
		this.tradeId = tradeId;		
		this.module = module;		
		this.type = type;		
		this.client = client;		
		this.attach = attach;		
		this.time = time;		
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}	
		
	public void setObjectId(long objectId) {
		this.objectId = objectId;
	}

	public long getObjectId() {
		return objectId;
	}	
		
	public void setOccurTime(Date occurTime) {
		this.occurTime = occurTime;
	}

	public Date getOccurTime() {
		return occurTime;
	}	
		
	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public String getTradeId() {
		return tradeId;
	}	
		
	public void setModule(String module) {
		this.module = module;
	}

	public String getModule() {
		return module;
	}	
		
	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}	
		
	public void setClient(String client) {
		this.client = client;
	}

	public String getClient() {
		return client;
	}	
		
	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getAttach() {
		return attach;
	}	
		
	public void setTime(Date time) {
		this.time = time;
	}

	public Date getTime() {
		return time;
	}	
		
}
