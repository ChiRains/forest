package com.qcloud.project.forest.model;

import java.util.Date;
import java.math.BigDecimal;

public class ArticleEvaluation {
	
	//ID
	private long id;		
	
	//资讯id
	private long articleId;		
	
	//内容
	private String content;		
	
	//审核状态
	private int state;		
	
	//用户id
	private long userId;		
	
	//评价时间
	private Date time;		

	public ArticleEvaluation(){
	
	}

	public ArticleEvaluation(long id,long articleId,String content,int state,long userId,Date time){
		this.id = id;		
		this.articleId = articleId;		
		this.content = content;		
		this.state = state;		
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
		
	public void setContent(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}	
		
	public void setState(int state) {
		this.state = state;
	}

	public int getState() {
		return state;
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
