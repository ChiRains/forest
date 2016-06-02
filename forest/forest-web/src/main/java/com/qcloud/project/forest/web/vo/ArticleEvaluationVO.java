package com.qcloud.project.forest.web.vo;


public class ArticleEvaluationVO {
	
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
	
	//用户名称
	private String userName;
	
	private String headImage;
	//评价时间
	private String time;		

	public ArticleEvaluationVO(){
	
	}

	public ArticleEvaluationVO(long id,long articleId,String content,int state,long userId,String userName,String headImage,String time){
		this.id = id;		
		this.articleId = articleId;		
		this.content = content;		
		this.state = state;		
		this.userId = userId;
		this.userName = userName;
		this.headImage=headImage;
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
		
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
    public String getHeadImage() {
    
        return headImage;
    }

    
    public void setHeadImage(String headImage) {
    
        this.headImage = headImage;
    }

    public void setTime(String time) {
		this.time = time;
	}

	public String getTime() {
		return time;
	}	
		
}
