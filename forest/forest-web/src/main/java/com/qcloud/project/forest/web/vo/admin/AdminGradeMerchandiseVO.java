package com.qcloud.project.forest.web.vo.admin;

import java.util.Date;
import java.math.BigDecimal;

public class AdminGradeMerchandiseVO {
	
	private long id;		
	
	private long gradeId;		
	
	private long unifiedMerchandiseId;		

	public AdminGradeMerchandiseVO(){
	
	}

	public AdminGradeMerchandiseVO(long id,long gradeId,long unifiedMerchandiseId){
		this.id = id;		
		this.gradeId = gradeId;		
		this.unifiedMerchandiseId = unifiedMerchandiseId;		
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}	
		
	public void setGradeId(long gradeId) {
		this.gradeId = gradeId;
	}

	public long getGradeId() {
		return gradeId;
	}	
		
	public void setUnifiedMerchandiseId(long unifiedMerchandiseId) {
		this.unifiedMerchandiseId = unifiedMerchandiseId;
	}

	public long getUnifiedMerchandiseId() {
		return unifiedMerchandiseId;
	}	
		
}
