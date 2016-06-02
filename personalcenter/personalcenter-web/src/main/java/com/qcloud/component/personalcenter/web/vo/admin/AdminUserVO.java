package com.qcloud.component.personalcenter.web.vo.admin;

import java.util.Date;

public class AdminUserVO {

    // ID
    private long   id;

    // 账号
    private String account;

    // 昵称
    private String nickname;

    // 姓名
    private String name;

    // 密码
    private String password;

    // 手机号
    private String mobile;

    // 手机号
    private String email;

    // 头像
    private String headImage;

    private String headImageUid;

    // 是否启用
    private int    state;

    private String stateStr;

    // 是否启用
    private int    enable;

    private String enableSelected;

    // 用户类别
    private int    type;

    private String typeStr;

    // 性别
    private int    sex;

    private String sexStr;

    // 等级
    private long   gradeId;

    private String grade;

    // 注册时间
    private Date   registTime;

    public AdminUserVO() {

    }

    public void setId(long id) {

        this.id = id;
    }

    public long getId() {

        return id;
    }

    public void setAccount(String account) {

        this.account = account;
    }

    public String getAccount() {

        return account;
    }

    public void setNickname(String nickname) {

        this.nickname = nickname;
    }

    public String getNickname() {

        return nickname;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getName() {

        return name;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    public String getPassword() {

        return password;
    }

    public void setMobile(String mobile) {

        this.mobile = mobile;
    }

    public String getMobile() {

        return mobile;
    }

    public void setEmail(String email) {

        this.email = email;
    }

    public String getEmail() {

        return email;
    }

    public void setHeadImage(String headImage) {

        this.headImage = headImage;
    }

    public String getHeadImage() {

        return headImage;
    }

    public String getHeadImageUid() {

        return headImageUid;
    }

    public void setHeadImageUid(String headImageUid) {

        this.headImageUid = headImageUid;
    }

    public void setType(int type) {

        this.type = type;
    }

    public int getType() {

        return type;
    }

    public void setSex(int sex) {

        this.sex = sex;
    }

    public int getSex() {

        return sex;
    }

    public void setGradeId(long gradeId) {

        this.gradeId = gradeId;
    }

    public long getGradeId() {

        return gradeId;
    }

    public void setRegistTime(Date registTime) {

        this.registTime = registTime;
    }

    public Date getRegistTime() {

        return registTime;
    }

    public String getSexStr() {

        return sexStr;
    }

    public void setSexStr(String sexStr) {

        this.sexStr = sexStr;
    }

    public String getGrade() {

        return grade;
    }

    public void setGrade(String grade) {

        this.grade = grade;
    }

    public String getTypeStr() {

        return typeStr;
    }

    public void setTypeStr(String typeStr) {

        this.typeStr = typeStr;
    }

    public int getState() {

        return state;
    }

    public void setState(int state) {

        this.state = state;
    }

    public String getStateStr() {

        return stateStr;
    }

    public void setStateStr(String stateStr) {

        this.stateStr = stateStr;
    }

    public int getEnable() {

        return enable;
    }

    public void setEnable(int enable) {

        this.enable = enable;
    }

    public String getEnableSelected() {

        return enableSelected;
    }

    public void setEnableSelected(String enableSelected) {

        this.enableSelected = enableSelected;
    }
}
