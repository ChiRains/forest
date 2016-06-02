package com.qcloud.component.personalcenter.web.form;

public class UserForm {

    // 昵称
    private String  nickname;

    // 姓名
    private String  name;

    // 手机号
    private String  email;

    // 性别
    private Integer sex;

    // 出生年
    private Integer birthYear;

    // 出生月
    private Integer birthMonth;

    // 出生日
    private Integer birthDay;

    // 省
    private String  province;

    // 市
    private String  city;

    // 区
    private String  district;

    //
    private String  headImage;

    public String getNickname() {

        return nickname;
    }

    public void setNickname(String nickname) {

        this.nickname = nickname;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {

        this.email = email;
    }

    public Integer getSex() {

        return sex;
    }

    public void setSex(Integer sex) {

        this.sex = sex;
    }

    public Integer getBirthYear() {

        return birthYear;
    }

    public void setBirthYear(Integer birthYear) {

        this.birthYear = birthYear;
    }

    public Integer getBirthMonth() {

        return birthMonth;
    }

    public void setBirthMonth(Integer birthMonth) {

        this.birthMonth = birthMonth;
    }

    public Integer getBirthDay() {

        return birthDay;
    }

    public void setBirthDay(Integer birthDay) {

        this.birthDay = birthDay;
    }

    public String getProvince() {

        return province;
    }

    public void setProvince(String province) {

        this.province = province;
    }

    public String getCity() {

        return city;
    }

    public void setCity(String city) {

        this.city = city;
    }

    public String getDistrict() {

        return district;
    }

    public void setDistrict(String district) {

        this.district = district;
    }

    public String getHeadImage() {

        return headImage;
    }

    public void setHeadImage(String headImage) {

        this.headImage = headImage;
    }
}
