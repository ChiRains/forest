package com.qcloud.component.personalcenter.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class User implements Comparable<User> {

    // ID
    private long   id;

    // 账号
    private String membershipCard;

    // 手机号
    private String mobile;

    // Email
    private String email;

    // 账号组别
    private String accountGroup;

    // 昵称
    private String nickname;

    // 姓名
    private String name;

    // 头像
    private String headImage;

    // 状态
    private int    state;

    // 用户类别
    private int    type;

    // 性别
    private int    sex;

    // 等级
    private long   gradeId;

    // 出生年
    private int    birthYear;

    // 出生月
    private int    birthMonth;

    // 出生日
    private int    birthDay;

    // 省
    private String province;

    // 市
    private String city;

    // 区
    private String district;

    private String address;

    // 注册时间
    private Date   registTime;

    public User() {

    }

    public User(long id, String membershipCard, String mobile, String email, String accountGroup, String nickname, String name, String headImage, int state, int type, int sex, long gradeId, int birthYear, int birthMonth, int birthDay, String province, String city, String district, String address, Date registTime) {

        this.id = id;
        this.membershipCard = membershipCard;
        this.mobile = mobile;
        this.email = email;
        this.accountGroup = accountGroup;
        this.nickname = nickname;
        this.name = name;
        this.headImage = headImage;
        this.state = state;
        this.type = type;
        this.sex = sex;
        this.gradeId = gradeId;
        this.birthYear = birthYear;
        this.birthMonth = birthMonth;
        this.birthDay = birthDay;
        this.province = province;
        this.city = city;
        this.district = district;
        this.address = address;
        this.registTime = registTime;
    }

    public void setId(long id) {

        this.id = id;
    }

    public long getId() {

        return id;
    }

    public void setMembershipCard(String membershipCard) {

        this.membershipCard = membershipCard;
    }

    public String getMembershipCard() {

        return membershipCard;
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

    public void setAccountGroup(String accountGroup) {

        this.accountGroup = accountGroup;
    }

    public String getAccountGroup() {

        return accountGroup;
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

    public void setHeadImage(String headImage) {

        this.headImage = headImage;
    }

    public String getHeadImage() {

        return headImage;
    }

    public void setState(int state) {

        this.state = state;
    }

    public int getState() {

        return state;
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

    public void setBirthYear(int birthYear) {

        this.birthYear = birthYear;
    }

    public int getBirthYear() {

        return birthYear;
    }

    public void setBirthMonth(int birthMonth) {

        this.birthMonth = birthMonth;
    }

    public int getBirthMonth() {

        return birthMonth;
    }

    public void setBirthDay(int birthDay) {

        this.birthDay = birthDay;
    }

    public int getBirthDay() {

        return birthDay;
    }

    public void setProvince(String province) {

        this.province = province;
    }

    public String getProvince() {

        return province;
    }

    public void setCity(String city) {

        this.city = city;
    }

    public String getCity() {

        return city;
    }

    public void setDistrict(String district) {

        this.district = district;
    }

    public String getDistrict() {

        return district;
    }

    public void setRegistTime(Date registTime) {

        this.registTime = registTime;
    }

    public Date getRegistTime() {

        return registTime;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        User user = (User) obj;
        if (this.id == user.getId()) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {

        get();
    }

    public static void get() {

        User a = new User();
        a.setId(1);
        User b = new User();
        b.setId(4);
        User c = new User();
        c.setId(2);
        User d = new User();
        d.setId(3);
        List<User> users = new ArrayList<User>();
        users.add(a);
        users.add(b);
        users.add(c);
        users.add(d);
        List<User> newUsers = new ArrayList<User>();
        for (User user : users) {
            if (!newUsers.contains(user)) {
                newUsers.add(user);
            }
        }
        Collections.sort(newUsers);
        for (User user : newUsers) {
            System.out.println(user.getId());
        }
    }

    @Override
    public int compareTo(User user) {

        // 这里比较的是什么 sort方法实现的就是按照此比较的东西从小到大排列
        if (id < user.id) {
            return -1;
        }
        if (id > user.id) {
            return 1;
        }
        return 0;
    }

    public String getAddress() {

        return address;
    }

    public void setAddress(String address) {

        this.address = address;
    }
}
