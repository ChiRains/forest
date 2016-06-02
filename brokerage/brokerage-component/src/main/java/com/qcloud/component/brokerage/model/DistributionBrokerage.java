package com.qcloud.component.brokerage.model;

import java.util.Date;
import java.math.BigDecimal;

public class DistributionBrokerage {
	
	//ID
	private long id;		
	
	//佣金拥有者
	private long owner;		
	
	//结果ID
	private long resultId;		
	
	//级别ID
	private long gradeId;		
	
	//源用户ID
	private long userId;		
	
	//计算结果级别路线
	private String route;		
	
	//分配比例,如50%,则存50
	private int proportion;		
	
	private int poundageRate;		
	
	//类别 data pool type...
	private int type;		
	
	//名称
	private String name;		
	
	//图
	private String image;		
	
	//佣金
	private double brokerage;		
	
	//手续费
	private double poundage;		
	
	//公式ID
	private long formulaId;		
	
	//状态 1计算好 2审核通过 3审核不通过
	private int state;		
	
	//审核时间
	private Date auditTime;		
	
	private Date orderTime;		

	public DistributionBrokerage(){
	
	}

	public DistributionBrokerage(long id,long owner,long resultId,long gradeId,long userId,String route,int proportion,int poundageRate,int type,String name,String image,double brokerage,double poundage,long formulaId,int state,Date auditTime,Date orderTime){
		this.id = id;		
		this.owner = owner;		
		this.resultId = resultId;		
		this.gradeId = gradeId;		
		this.userId = userId;		
		this.route = route;		
		this.proportion = proportion;		
		this.poundageRate = poundageRate;		
		this.type = type;		
		this.name = name;		
		this.image = image;		
		this.brokerage = brokerage;		
		this.poundage = poundage;		
		this.formulaId = formulaId;		
		this.state = state;		
		this.auditTime = auditTime;		
		this.orderTime = orderTime;		
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}	
		
	public void setOwner(long owner) {
		this.owner = owner;
	}

	public long getOwner() {
		return owner;
	}	
		
	public void setResultId(long resultId) {
		this.resultId = resultId;
	}

	public long getResultId() {
		return resultId;
	}	
		
	public void setGradeId(long gradeId) {
		this.gradeId = gradeId;
	}

	public long getGradeId() {
		return gradeId;
	}	
		
	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getUserId() {
		return userId;
	}	
		
	public void setRoute(String route) {
		this.route = route;
	}

	public String getRoute() {
		return route;
	}	
		
	public void setProportion(int proportion) {
		this.proportion = proportion;
	}

	public int getProportion() {
		return proportion;
	}	
		
	public void setPoundageRate(int poundageRate) {
		this.poundageRate = poundageRate;
	}

	public int getPoundageRate() {
		return poundageRate;
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
		
	public void setPoundage(double poundage) {
		this.poundage = poundage;
	}

	public double getPoundage() {
		return poundage;
	}	
		
	public void setFormulaId(long formulaId) {
		this.formulaId = formulaId;
	}

	public long getFormulaId() {
		return formulaId;
	}	
		
	public void setState(int state) {
		this.state = state;
	}

	public int getState() {
		return state;
	}	
		
	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

	public Date getAuditTime() {
		return auditTime;
	}	
		
	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public Date getOrderTime() {
		return orderTime;
	}	
		
}
