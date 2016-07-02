package com.qcloud.component.brokerage.web.vo;

public class AllocationUser {

    // 用户ID
    private String recommedToken;

    // 用户ID
    private String userShareToken;

    // 用户姓名
    private String name;

    // 注册时间
    private String entryDateStr;

    // 佣金
    private double commission;

    private String image;

    // 下级人数
    private int    number;

    private String sexStr;

    private String mobile;

    private String nickName;

    public String getRecommedToken() {

        return recommedToken;
    }

    public void setRecommedToken(String recommedToken) {

        this.recommedToken = recommedToken;
    }

    public String getSexStr() {

        return sexStr;
    }

    public void setSexStr(String sexStr) {

        this.sexStr = sexStr;
    }

    public String getUserShareToken() {

        return userShareToken;
    }

    public void setUserShareToken(String userShareToken) {

        this.userShareToken = userShareToken;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getEntryDateStr() {

        return entryDateStr;
    }

    public void setEntryDateStr(String entryDateStr) {

        this.entryDateStr = entryDateStr;
    }

    public double getCommission() {

        return commission;
    }

    public void setCommission(double commission) {

        this.commission = commission;
    }

    public String getImage() {

        return image;
    }

    public void setImage(String image) {

        this.image = image;
    }

    public int getNumber() {

        return number;
    }

    public void setNumber(int number) {

        this.number = number;
    }

    public String getNickName() {

        return nickName;
    }

    public void setNickName(String nickName) {

        this.nickName = nickName;
    }

    public String getMobile() {

        return mobile;
    }

    public void setMobile(String mobile) {

        this.mobile = mobile;
    }
}
