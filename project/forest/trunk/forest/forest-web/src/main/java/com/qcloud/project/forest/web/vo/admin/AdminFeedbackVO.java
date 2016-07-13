package com.qcloud.project.forest.web.vo.admin;

public class AdminFeedbackVO {

    private long   id;

    // 反馈内容
    private String content;

    // 反馈时间
    private String date;

    private String userName;

    private String mobile;

    private String classifyName;

    private String stateName;

    private String typeName;

    public AdminFeedbackVO() {

    }

    public AdminFeedbackVO(long id, String content, String date, String userName, String mobile, String classifyName, String stateName, String typeName) {

        super();
        this.id = id;
        this.content = content;
        this.date = date;
        this.userName = userName;
        this.mobile = mobile;
        this.classifyName = classifyName;
        this.stateName = stateName;
        this.typeName = typeName;
    }

    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
    }

    public String getContent() {

        return content;
    }

    public void setContent(String content) {

        this.content = content;
    }

    public String getDate() {

        return date;
    }

    public void setDate(String date) {

        this.date = date;
    }

    public String getUserName() {

        return userName;
    }

    public void setUserName(String userName) {

        this.userName = userName;
    }

    public String getMobile() {

        return mobile;
    }

    public void setMobile(String mobile) {

        this.mobile = mobile;
    }

    public String getClassifyName() {

        return classifyName;
    }

    public void setClassifyName(String classifyName) {

        this.classifyName = classifyName;
    }

    public String getStateName() {

        return stateName;
    }

    public void setStateName(String stateName) {

        this.stateName = stateName;
    }

    public String getTypeName() {

        return typeName;
    }

    public void setTypeName(String typeName) {

        this.typeName = typeName;
    }
}
