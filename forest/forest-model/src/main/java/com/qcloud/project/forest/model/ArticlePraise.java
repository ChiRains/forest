package com.qcloud.project.forest.model;

import java.util.Date;
import java.math.BigDecimal;

public class ArticlePraise {
	
	private long id;		
	
	//文章Id
	private long articleId;		
	
	//用户Id
	private long userId;		
	
	//评论时间
	private Date time;		

	public ArticlePraise(){
	
	}

	public ArticlePraise(long id,long articleId,long userId,Date time){
		this.id = id;		
		this.articleId = articleId;		
		this.userId = userId;		
		this.time = time;		
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}	
		
	public void setArticleId(long articleId) {
		this.articleId = articleId;
	}

	public long getArticleId() {
		return articleId;
	}	
		
	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getUserId() {
		return userId;
	}	
		
	public void setTime(Date time) {
		this.time = time;
	}

	public Date getTime() {
		return time;
	}	
		
}
