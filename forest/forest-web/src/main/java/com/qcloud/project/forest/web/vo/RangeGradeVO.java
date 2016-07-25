package com.qcloud.project.forest.web.vo;

import java.util.Date;
import java.math.BigDecimal;

public class RangeGradeVO {
	
	private long id;		
	
	private long rangeId;		
	
	private long gradeId;		

	public RangeGradeVO(){
	
	}

	public RangeGradeVO(long id,long rangeId,long gradeId){
		this.id = id;		
		this.rangeId = rangeId;		
		this.gradeId = gradeId;		
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}	
		
	public void setRangeId(long rangeId) {
		this.rangeId = rangeId;
	}

	public long getRangeId() {
		return rangeId;
	}	
		
	public void setGradeId(long gradeId) {
		this.gradeId = gradeId;
	}

	public long getGradeId() {
		return gradeId;
	}	
		
}
