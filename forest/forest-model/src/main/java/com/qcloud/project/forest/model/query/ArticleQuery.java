package com.qcloud.project.forest.model.query;

public class ArticleQuery {

    private long   classifyId;

    private String keyWord;

    private int    enable;

    private int    homePage;

    public int getEnable() {

        return enable;
    }

    public void setEnable(int enable) {

        this.enable = enable;
    }

    public String getKeyWord() {

        return keyWord;
    }

    public void setKeyWord(String keyWord) {

        this.keyWord = keyWord;
    }

    public ArticleQuery() {

    }

    public long getClassifyId() {

        return classifyId;
    }

    public void setClassifyId(long classifyId) {

        this.classifyId = classifyId;
    }

    public int getHomePage() {

        return homePage;
    }

    public void setHomePage(int homePage) {

        this.homePage = homePage;
    }
}
