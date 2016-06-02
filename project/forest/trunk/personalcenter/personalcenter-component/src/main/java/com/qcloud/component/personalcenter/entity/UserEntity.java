package com.qcloud.component.personalcenter.entity;

import java.util.Date;
import com.qcloud.component.personalcenter.QGrade;
import com.qcloud.component.personalcenter.QUser;
import com.qcloud.component.personalcenter.model.Grade;
import com.qcloud.component.personalcenter.model.User;

public class UserEntity implements QUser {

    private User        user;

    private GradeEntity gradeEntity;

    public UserEntity(User user, Grade grade) {

        super();
        this.user = user;
        this.gradeEntity = new GradeEntity(grade);
    }

    @Override
    public long getId() {

        return user.getId();
    }

    @Override
    public String getHeadImage() {

        return user.getHeadImage();
    }

    @Override
    public String getName() {

        return user.getName();
    }

    @Override
    public String getNickname() {

        return user.getNickname();
    }

    @Override
    public String getMobile() {

        return user.getMobile();
    }

    @Override
    public Date getEntryDate() {

        return user.getRegistTime();
    }

    @Override
    public int getSex() {

        return user.getSex();
    }

    @Override
    public QGrade getGrade() {

        return gradeEntity;
    }

    @Override
    public String getEmail() {

        return user.getEmail();
    }

    @Override
    public String getProvince() {

        return user.getProvince();
    }

    @Override
    public String getCity() {

        return user.getCity();
    }

    @Override
    public String getDistrict() {

        return user.getDistrict();
    }

    @Override
    public String getAdress() {

        return user.getAddress();
    }
}
