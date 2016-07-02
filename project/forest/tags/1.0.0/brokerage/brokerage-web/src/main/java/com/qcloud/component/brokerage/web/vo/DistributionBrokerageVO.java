package com.qcloud.component.brokerage.web.vo;

import java.util.Date;
import java.math.BigDecimal;

public class DistributionBrokerageVO {
	
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
	
	//类别 data pool type...
	private int type;		
	
	//名称
	private String name;		
	
	//图
	private String image;		
	
	//佣金
	private double brokerage;		
	
	//公式ID
	private long formulaId;		
	
	//状态 1计算好 2审核通过 3审核不通过
	private int state;		
	
	//审核时间
	private Date auditTime;		
	
	// 手续费
    private double poundage;
    
    //购买时间
    private Date orderTime;

	public DistributionBrokerageVO(){
	
	}

	public DistributionBrokerageVO(long id,long owner,long resultId,long gradeId,long userId,String route,int proportion,int type,String name,String image,double brokerage,long formulaId,int state,Date auditTime){
		this.id = id;		
		this.owner = owner;		
		this.resultId = resultId;		
		this.gradeId = gradeId;		
		this.userId = userId;		
		this.route = route;		
		this.proportion = proportion;		
		this.type = type;		
		this.name = name;		
		this.image = image;		
		this.brokerage = brokerage;		
		this.formulaId = formulaId;		
		this.state = state;		
		this.auditTime = auditTime;		
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

    
    public double getPoundage() {
    
        return poundage;
    }

    
    public void setPoundage(double poundage) {
    
        this.poundage = poundage;
    }

    
    public Date getOrderTime() {
    
        return orderTime;
    }

    
    public void setOrderTime(Date orderTime) {
    
        this.orderTime = orderTime;
    }	
		
}
