package com.qcloud.project.forest.model.query;

public class ArticleEvaluationQuery {

    private long   articleId;

    private String keyWord;

    public ArticleEvaluationQuery() {

    }

    public String getKeyWord() {

        return keyWord;
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
