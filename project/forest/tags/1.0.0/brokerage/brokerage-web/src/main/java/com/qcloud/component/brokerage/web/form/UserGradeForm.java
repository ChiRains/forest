package com.qcloud.component.brokerage.web.form;

import java.util.List;
import com.qcloud.component.brokerage.model.UserDistributionGrade;

public class UserGradeForm {

    private long                gradeId;

    private List<UserGradeList> userGradeList;

    public long getGradeId() {

        return gradeId;
    }

    public void setGradeId(long gradeId) {

        this.gradeId = gradeId;
    }

    public List<UserGradeList> getUserGradeList() {

        return userGradeList;
    }

    public void setUserGradeList(List<UserGradeList> userGradeList) {

        this.userGradeList = userGradeList;
    }
}
