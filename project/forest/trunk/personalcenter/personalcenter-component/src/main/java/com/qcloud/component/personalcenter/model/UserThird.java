package com.qcloud.component.personalcenter.model;

import java.util.Date;
import java.math.BigDecimal;

public class UserThird {

    // ID
    private long   id;

    // 用户
    private long   userId;

    // 第三方ID
    private String thirdId;

    // 类型 1注册用户 2QQ用户 3微信用户 4微博用户
    private int    accountType;

    // 注册时间
    private Date   createTime;

    public UserThird() {

    }

    public void setId(long id) {

        this.id = id;
    }

    public long getId() {

        return id;
    }

    public void setUserId(long userId) {

        this.userId = userId;
    }

    public long getUserId() {

        return userId;
    }

    public void setThirdId(String thirdId) {

        this.thirdId = thirdId;
    }

    public String getThirdId() {

        return thirdId;
    }

    public void setAccountType(int accountType) {

        this.accountType = accountType;
    }

    public int getAccountType() {

        return accountType;
    }

    public void setCreateTime(Date createTime) {

        this.createTime = createTime;
    }

    public Date getCreateTime() {

        return createTime;
    }
}
