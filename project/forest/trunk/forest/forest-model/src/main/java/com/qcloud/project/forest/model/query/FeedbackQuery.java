package com.qcloud.project.forest.model.query;

public class FeedbackQuery {

    private int    state;

    private int    classify;

    private String keyword;

    public FeedbackQuery() {

        state = -1;
    }

    public int getState() {

        return state;
    }

    public void setState(int state) {

        this.state = state;
    }

    public int getClassify() {

        return classify;
    }

    public void setClassify(int classify) {

        this.classify = classify;
    }

    public String getKeyword() {

        return keyword;
    }

    public void setKeyword(String keyword) {

        this.keyword = keyword;
    }
}
