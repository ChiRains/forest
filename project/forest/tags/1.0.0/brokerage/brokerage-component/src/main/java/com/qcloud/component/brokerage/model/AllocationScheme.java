package com.qcloud.component.brokerage.model;

import java.util.Date;
import java.math.BigDecimal;

public class AllocationScheme {
	
	private long id;		
	
	//分佣公式ID
	private long formulaId;		
	
	//分配对象,等级类别
	private long allocationGradeId;		
	
	//分配比例,如50%,则存50
	private int proportion;		

	public AllocationScheme(){
	
	}

	public AllocationScheme(long id,long formulaId,long allocationGradeId,int proportion){
		this.id = id;		
		this.formulaId = formulaId;		
		this.allocationGradeId = allocationGradeId;		
		this.proportion = proportion;		
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}	
		
	public void setFormulaId(long formulaId) {
		this.formulaId = formulaId;
	}

	public long getFormulaId() {
		return formulaId;
	}	
		
	public void setAllocationGradeId(long allocationGradeId) {
		this.allocationGradeId = allocationGradeId;
	}

	public long getAllocationGradeId() {
		return allocationGradeId;
	}	
		
	public void setProportion(int proportion) {
		this.proportion = proportion;
	}

	public int getProportion() {
		return proportion;
	}	
		
}
