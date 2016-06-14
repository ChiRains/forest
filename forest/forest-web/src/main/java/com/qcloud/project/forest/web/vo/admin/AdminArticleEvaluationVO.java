package com.qcloud.project.forest.web.vo.admin;

import java.util.Date;

public class AdminArticleEvaluationVO {

    private long   id;

    // 文章Id
    private long   articleId;

    // 评论内容
    private String content;

    // 状态
    private int    state;

    // 用户Id
    private long   userId;

    // 评论时间
    private Date   time;

    private String articleName;

    private String userName;

    public AdminArticleEvaluationVO() {

    }

    public AdminArticleEvaluationVO(long id, long articleId, String content, int state, long userId, Date time) {

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
