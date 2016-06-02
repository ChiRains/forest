package com.qcloud.component.my.web.vo.admin;

import java.util.Date;
import java.math.BigDecimal;

public class AdminQuestionnaireAnswersVO {
	
	//ID
	private long id;		
	
	//用户
	private long userId;		
	
	private String userName;
	
	//回答时间
	private Date time;		
	
	//问卷ID
	private long questionnaireId;		
	
	//问卷
	private String questionnaireName;		
	
	//问题ID
	private long questionId;		
	
	//问题名称
	private String questionName;		
	
	//答案
	private String answer;		
	
	//状态 1已处理 2新增
	private int state;		
	
	private String stateStr;

	public AdminQuestionnaireAnswersVO(){
	
	}

	public AdminQuestionnaireAnswersVO(long id,long userId,Date time,long questionnaireId,String questionnaireName,long questionId,String questionName,String answer,int state){
		this.id = id;		
		this.userId = userId;		
		this.time = time;		
		this.questionnaireId = questionnaireId;		
		this.questionnaireName = questionnaireName;		
		this.questionId = questionId;		
		this.questionName = questionName;		
		this.answer = answer;		
		this.state = state;		
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
		
	public void setTime(Date time) {
		this.time = time;
	}

	public Date getTime() {
		return time;
	}	
		
	public void setQuestionnaireId(long questionnaireId) {
		this.questionnaireId = questionnaireId;
	}

	public long getQuestionnaireId() {
		return questionnaireId;
	}	
		
	public void setQuestionnaireName(String questionnaireName) {
		this.questionnaireName = questionnaireName;
	}

	public String getQuestionnaireName() {
		return questionnaireName;
	}	
		
	public void setQuestionId(long questionId) {
		this.questionId = questionId;
	}

	public long getQuestionId() {
		return questionId;
	}	
		
	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}

	public String getQuestionName() {
		return questionName;
	}	
		
	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getAnswer() {
		return answer;
	}	
		
	public void setState(int state) {
		this.state = state;
	}

	public int getState() {
		return state;
	}

    
    public String getUserName() {
    
        return userName;
    }

    
    public void setUserName(String userName) {
    
        this.userName = userName;
    }

    
    public String getStateStr() {
    
        return stateStr;
    }

    
    public void setStateStr(String stateStr) {
    
        this.stateStr = stateStr;
    }	
		
}
