package com.qcloud.component.personalcenter.entity;

import com.qcloud.component.personalcenter.QGrade;
import com.qcloud.component.personalcenter.model.Grade;

public class GradeEntity implements QGrade {

    private Grade grade;

    public GradeEntity(Grade grade) {

        super();
        this.grade = grade;
    }

    @Override
    public int getDiscount() {

        if (grade == null) {
            return 100;
        }
        return grade.getDiscount();
    }

    @Override
    public int getPoint() {

        if (grade == null) {
            return 0;
        }
        return grade.getPoint();
    }

    @Override
    public String getImage() {

        if (grade == null) {
            return "";
        }
        return grade.getImage();
    }

    @Override
    public String getName() {

        if (grade == null) {
            return "";
        }
        return grade.getName();
    }

    @Override
    public long getId() {

        return grade.getId();
    }
}
