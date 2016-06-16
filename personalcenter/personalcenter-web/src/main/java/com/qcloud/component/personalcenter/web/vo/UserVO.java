package com.qcloud.component.personalcenter.web.vo;

public class UserVO {

    // 昵称
    private String nickname;

    // 姓名
    private String name;

    // 手机号
    private String mobile;

    // email
    private String email;

    // 头像
    private String headImage;

    // 性别
    private String sexStr;

    // 等级
    private String grade;

    // 积分
    private long   integral;

    // 佣金
    private double commission;

    // 消费币
    private double consumptionCurrency;

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

    // 累计提现
    private double withdrawedCommission;

    // 正在提现
    private double withdrawingCommission;

    // 注册时间
    private String entryTimeStr;

    // 会员卡号
    private String membershipCard;

//    private int    merchandiseCollectNumber;
//
//    private int    merchantCollectNumber;

    public UserVO() {

    }

    public double getWithdrawedCommission() {

        return withdrawedCommission;
    }

    public void setWithdrawedCommission(double withdrawedCommission) {

        this.withdrawedCommission = withdrawedCommission;
    }

    public double getWithdrawingCommission() {

        return withdrawingCommission;
    }

    public void setWithdrawingCommission(double withdrawingCommission) {

        this.withdrawingCommission = withdrawingCommission;
    }

    public String getEntryTimeStr() {

        return entryTimeStr;
    }

    public void setEntryTimeStr(String entryTimeStr) {

        this.entryTimeStr = entryTimeStr;
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

    public long getIntegral() {

        return integral;
    }

    public void setIntegral(long integral) {

        this.integral = integral;
    }

    public double getCommission() {

        return commission;
    }

    public void setCommission(double commission) {

        this.commission = commission;
    }

    public double getConsumptionCurrency() {

        return consumptionCurrency;
    }

    public void setConsumptionCurrency(double consumptionCurrency) {

        this.consumptionCurrency = consumptionCurrency;
    }

    public int getBirthYear() {

        return birthYear;
    }

    public void setBirthYear(int birthYear) {

        this.birthYear = birthYear;
    }

    public int getBirthMonth() {

        return birthMonth;
    }

    public void setBirthMonth(int birthMonth) {

        this.birthMonth = birthMonth;
    }

    public int getBirthDay() {

        return birthDay;
    }

    public void setBirthDay(int birthDay) {

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

    public String getMembershipCard() {

        return membershipCard;
    }

    public void setMembershipCard(String membershipCard) {

        this.membershipCard = membershipCard;
    }

//    public int getMerchandiseCollectNumber() {
//
//        return merchandiseCollectNumber;
//    }
//
//    public void setMerchandiseCollectNumber(int merchandiseCollectNumber) {
//
//        this.merchandiseCollectNumber = merchandiseCollectNumber;
//    }
//
//    public int getMerchantCollectNumber() {
//
//        return merchantCollectNumber;
//    }
//
//    public void setMerchantCollectNumber(int merchantCollectNumber) {
//
//        this.merchantCollectNumber = merchantCollectNumber;
//    }
}
