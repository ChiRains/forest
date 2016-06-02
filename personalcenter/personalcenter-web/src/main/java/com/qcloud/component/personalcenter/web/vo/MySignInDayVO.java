package com.qcloud.component.personalcenter.web.vo;


public class MySignInDayVO {

    // 日
    private int     day;

    private boolean sign;

    public MySignInDayVO() {

    }

    public void setDay(int day) {

        this.day = day;
    }

    public int getDay() {

        return day;
    }

    public boolean isSign() {

        return sign;
    }

    public void setSign(boolean sign) {

        this.sign = sign;
    }
}
