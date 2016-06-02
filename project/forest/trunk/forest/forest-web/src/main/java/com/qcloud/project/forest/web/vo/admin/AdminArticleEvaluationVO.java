package com.qcloud.project.forest.web.vo.admin;

import java.util.Date;

public class AdminArticleEvaluationVO {
	
	//ID
	private long id;		
	
	//资讯id
	private long articleId;
	
	//
	private String articleName;
	
	//内容
	private String content;		
	
	//审核状态
	private int state;		
	
	//用户id
	private long userId;
  //用户名称
	private String userName;
	
	//评价时间
	private Date time;		

	public AdminArticleEvaluationVO(){
	
	}

	public AdminArticleEvaluationVO(long id, long articleId,
			String articleName, String content, int state, long userId,
			String userName, Date time) {
		this.id = id;
		this.articleId = articleId;
		this.articleName = articleName;
		this.content = content;
		this.state = state;
		this.userId = userId;
		this.userName = userName;
		this.time = time;
	}



	public String getArticleName() {
		return articleName;
	}

	public void setArticleName(String articleName) {
		this.articleName = articleName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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
