package com.qcloud.component.brokerage.model.query;

public class UserDistributionGradeQuery {

    private long gradeId;

    private long userId;

    public UserDistributionGradeQuery() {

    }

    public long getGradeId() {

        return gradeId;
    }

    public void setGradeId(long gradeId) {

        this.gradeId = gradeId;
    }

    public long getUserId() {

        return userId;
    }

    public void setUserId(long userId) {

        this.userId = userId;
    }
}
