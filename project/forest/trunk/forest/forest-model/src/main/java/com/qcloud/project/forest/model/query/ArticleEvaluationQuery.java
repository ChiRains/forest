package com.qcloud.project.forest.model.query;

public class ArticleEvaluationQuery {

    private long   articleId;

    private int    state;

    private String keyWord;

    public ArticleEvaluationQuery() {

        state = -1;
    }

    public String getKeyWord() {

        return keyWord;
    }

    public int getState() {

        return state;
    }

    public void setState(int state) {

        this.state = state;
    }

    public void setKeyWord(String keyWord) {

        this.keyWord = keyWord;
    }

    public long getArticleId() {

        return articleId;
    }

    public void setArticleId(long articleId) {

        this.articleId = articleId;
    }
}
