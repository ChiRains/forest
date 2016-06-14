package com.qcloud.project.forest.model.query;

public class ArticleQuery {

    private long   classifyId;

    private String keyWord;

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
}
