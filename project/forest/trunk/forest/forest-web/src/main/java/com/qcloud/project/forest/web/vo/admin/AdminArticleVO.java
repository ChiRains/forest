package com.qcloud.project.forest.web.vo.admin;

import java.util.Date;

public class AdminArticleVO {
	
	//ID
	private long id;		
	
	//标题
	private String name;		
	
	//图标
	private String icon;		
	
	//关键词
	private String keywords;		
	
	//标签
	private String label;		
	
	//简介
	private String brief;		
	
	//详情
	private String detail;		
	
	//日期
	private Date date;		
	
	//排序号
	private int sort;		
	
	//是否启用
	private int enable;		
	
	//分类类型
	private long classifyId;	
	
	//分类名称
	private String classifyName;
	
	//查看次数
	private int viewCount;		
	
	//点赞次数
	private int likeCount;		

	public AdminArticleVO(){
	
	}

	
	public AdminArticleVO(long id, String name, String icon, String keywords,
			String label, String brief, String detail, Date date, int sort,
			int enable, long classifyId, String classifyName, int viewCount,
			int likeCount) {
		this.id = id;
		this.name = name;
		this.icon = icon;
		this.keywords = keywords;
		this.label = label;
		this.brief = brief;
		this.detail = detail;
		this.date = date;
		this.sort = sort;
		this.enable = enable;
		this.classifyId = classifyId;
		this.classifyName = classifyName;
		this.viewCount = viewCount;
		this.likeCount = likeCount;
	}


	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}	
		
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}	
		
	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getIcon() {
		return icon;
	}	
		
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getKeywords() {
		return keywords;
	}	
		
	public void setLabel(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}	
		
	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String getBrief() {
		return brief;
	}	
		
	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getDetail() {
		return detail;
	}	
		
	public void setDate(Date date) {
		this.date = date;
	}

	public Date getDate() {
		return date;
	}	
		
	public void setSort(int sort) {
		this.sort = sort;
	}

	public int getSort() {
		return sort;
	}	
		
	public void setEnable(int enable) {
		this.enable = enable;
	}

	public int getEnable() {
		return enable;
	}	
		
	public void setClassifyId(long classifyId) {
		this.classifyId = classifyId;
	}

	public long getClassifyId() {
		return classifyId;
	}	
		
	public String getClassifyName() {
		return classifyName;
	}

	public void setClassifyName(String classifyName) {
		this.classifyName = classifyName;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	public int getViewCount() {
		return viewCount;
	}	
		
	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public int getLikeCount() {
		return likeCount;
	}	
		
}
