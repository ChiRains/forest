package com.qcloud.component.personalcenter.model;

public class CardNumberConfig {

    String start      = "100000001";

    String end        = "100000999";

    int    minLength  = 8;

    int    maxLength  = 12;

    String defaultPwd = "123456";

    public String getStart() {

        return start;
    }

    public void setStart(String start) {

        this.start = start;
    }

    public String getEnd() {

        return end;
    }

    public void setEnd(String end) {

        this.end = end;
    }

    public String getDefaultPwd() {

        return defaultPwd;
    }

    public void setDefaultPwd(String defaultPwd) {

        this.defaultPwd = defaultPwd;
    }

    public int getMinLength() {

        return minLength;
    }

    public void setMinLength(int minLength) {

        this.minLength = minLength;
    }

    public int getMaxLength() {

        return maxLength;
    }

    public void setMaxLength(int maxLength) {

        this.maxLength = maxLength;
    }
}
