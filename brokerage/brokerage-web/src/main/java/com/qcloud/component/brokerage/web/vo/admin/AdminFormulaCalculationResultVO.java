package com.qcloud.component.brokerage.web.vo.admin;

import java.util.Date;
import java.math.BigDecimal;

public class AdminFormulaCalculationResultVO {
	
	//ID
	private long id;		
	
	//ID
	private long dataPoolId;		
	
	//类别 data pool type...
	private int type;		
	
	//名称
	private String name;		
	
	//图
	private String image;		
	
	//佣金
	private double brokerage;		
	
	//下定时间
	private Date orderTime;		
	
	//用户ID
	private long userId;		
	
	//卖家ID
	private long merchantId;		
	
	//状态 1计算好 2正在分配 3已分配
	private int state;	
	
	private String resultStateStr;
	
	//公式ID
	private long formulaId;		
	
	//计算时间
	private Date calculateTime;		
	
	//周期:开始时间
	private Date beginTime;		
	
	//周期:结束时间
	private Date endTime;		

	public AdminFormulaCalculationResultVO(){
	
	}

	public AdminFormulaCalculationResultVO(long id,long dataPoolId,int type,String name,String image,double brokerage,Date orderTime,long userId,long merchantId,int state,long formulaId,Date calculateTime,Date beginTime,Date endTime){
		this.id = id;		
		this.dataPoolId = dataPoolId;		
		this.type = type;		
		this.name = name;		
		this.image = image;		
		this.brokerage = brokerage;		
		this.orderTime = orderTime;		
		this.userId = userId;		
		this.merchantId = merchantId;		
		this.state = state;		
		this.formulaId = formulaId;		
		this.calculateTime = calculateTime;		
		this.beginTime = beginTime;		
		this.endTime = endTime;		
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}	
		
	public void setDataPoolId(long dataPoolId) {
		this.dataPoolId = dataPoolId;
	}

	public long getDataPoolId() {
		return dataPoolId;
	}	
		
	public void setType(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}	
		
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}	
		
	public void setImage(String image) {
		this.image = image;
	}

	public String getImage() {
		return image;
	}	
		
	public void setBrokerage(double brokerage) {
		this.brokerage = brokerage;
	}

	public double getBrokerage() {
		return brokerage;
	}	
		
	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public Date getOrderTime() {
		return orderTime;
	}	
		
	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getUserId() {
		return userId;
	}	
		
	public void setMerchantId(long merchantId) {
		this.merchantId = merchantId;
	}

	public long getMerchantId() {
		return merchantId;
	}	
		
	public void setState(int state) {
		this.state = state;
	}

	public int getState() {
		return state;
	}	
		
	public void setFormulaId(long formulaId) {
		this.formulaId = formulaId;
	}

	public long getFormulaId() {
		return formulaId;
	}	
		
	public void setCalculateTime(Date calculateTime) {
		this.calculateTime = calculateTime;
	}

	public Date getCalculateTime() {
		return calculateTime;
	}	
		
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getBeginTime() {
		return beginTime;
	}	
		
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getEndTime() {
		return endTime;
	}

    
    public String getResultStateStr() {
    
        return resultStateStr;
    }

    
    public void setResultStateStr(String resultStateStr) {
    
        this.resultStateStr = resultStateStr;
    }	
		
}
