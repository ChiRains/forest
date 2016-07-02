package com.qcloud.project.forest.web.vo;

import java.util.Date;

public class ArticleEvaluationVO {

    private long   id;

    // 文章Id
    private long   articleId;

    // 评论内容
    private String content;

    // 状态
    private int    state;

    // 用户Id
    private long   userId;

    private String num;

    private String time;

    // 评论时间
    private String userName;

    private String gradeIcon;

    private String headImage;

    public ArticleEvaluationVO() {

    }

    public ArticleEvaluationVO(long id, long articleId, String content, int state, long userId, Date time) {

        this.id = id;
        this.articleId = articleId;
        this.content = content;
        this.state = state;
        this.userId = userId;
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

    public String getNum() {

        return num;
    }

    public void setNum(String num) {

        this.num = num;
    }

    public String getTime() {

        return time;
    }

    public void setTime(String time) {

        this.time = time;
    }

    public String getGradeIcon() {

        return gradeIcon;
    }

    public void setGradeIcon(String gradeIcon) {

        this.gradeIcon = gradeIcon;
    }

    public String getHeadImage() {

        return headImage;
    }

    public void setHeadImage(String headImage) {

        this.headImage = headImage;
    }

    public String getUserName() {

        return userName;
    }

    public void setUserName(String userName) {

        this.userName = userName;
    }
}
