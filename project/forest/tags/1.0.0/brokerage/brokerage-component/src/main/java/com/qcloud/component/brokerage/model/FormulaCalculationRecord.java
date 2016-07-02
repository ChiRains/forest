package com.qcloud.component.brokerage.model;

import java.util.Date;
import java.math.BigDecimal;

public class FormulaCalculationRecord {
	
	//ID
	private long id;		
	
	//状态 1开始计算 2完成
	private int state;		
	
	//公式ID
	private long formulaId;		
	
	//计算时间
	private Date calculateTime;		
	
	//周期:开始时间
	private Date beginTime;		
	
	//周期:结束时间
	private Date endTime;		

	public FormulaCalculationRecord(){
	
	}

	public FormulaCalculationRecord(long id,int state,long formulaId,Date calculateTime,Date beginTime,Date endTime){
		this.id = id;		
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
		
}
