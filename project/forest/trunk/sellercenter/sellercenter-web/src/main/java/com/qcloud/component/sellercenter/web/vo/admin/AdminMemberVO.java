package com.qcloud.component.sellercenter.web.vo.admin;
import java.util.Date;
import java.math.BigDecimal;
public class AdminMemberVO {
    // ID
    private long   id;
    // 账号 唯一 登录使用
    private String account;
    // 姓名
    private String name;
    // 性别
    private int    sex;
    private String sexStr;
    // 昵称
    private String nickname;
    // 密码
    private String password;
    // 手机号
    private String mobile;
    // qq
    private String qq;
    // 头像
    private String headImage;
    // 是否启用,在职
    private int    enable;
    
    private Long userId;

    public AdminMemberVO() {
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

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getSex() {
        return sex;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
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

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getQq() {
        return qq;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }

    public int getEnable() {
        return enable;
    }

    public String getSexStr() {
        return sexStr;
    }

    public void setSexStr(String sexStr) {
        this.sexStr = sexStr;
    }

    
    public Long getUserId() {
    
        return userId;
    }

    
    public void setUserId(Long userId) {
    
        this.userId = userId;
    }
}
