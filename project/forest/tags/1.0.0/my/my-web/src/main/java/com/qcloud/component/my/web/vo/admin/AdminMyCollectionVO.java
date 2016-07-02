package com.qcloud.component.my.web.vo.admin;

import java.util.Date;
import java.math.BigDecimal;

public class AdminMyCollectionVO {
	
	private long id;		
	
	//用户ID
	private long userId;		
	
	//统一商品ID
	private long unifiedMerchandiseId;		
	
	//收藏时间
	private Date time;		

	public AdminMyCollectionVO(){
	
	}

	public AdminMyCollectionVO(long id,long userId,long unifiedMerchandiseId,Date time){
		this.id = id;		
		this.userId = userId;		
		this.unifiedMerchandiseId = unifiedMerchandiseId;		
		this.time = time;		
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
		
	public void setUnifiedMerchandiseId(long unifiedMerchandiseId) {
		this.unifiedMerchandiseId = unifiedMerchandiseId;
	}

	public long getUnifiedMerchandiseId() {
		return unifiedMerchandiseId;
	}	
		
	public void setTime(Date time) {
		this.time = time;
	}

	public Date getTime() {
		return time;
	}	
		
}
