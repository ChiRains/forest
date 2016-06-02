package com.qcloud.component.brokerage.web.vo.admin;

public class AdminUserRelationshipVO {

    // 用户ID
    private long   userId;

    private String user;

    private String path;

    // 推荐人ID
    private long   recommedId;

    private String recommed;

    // 推荐人帐号
    private String recommedAccount;

    // 推荐人名称
    private String recommedName;

    public AdminUserRelationshipVO() {

    }

    public void setUserId(long userId) {

        this.userId = userId;
    }

    public long getUserId() {

        return userId;
    }

    public void setRecommedId(long recommedId) {

        this.recommedId = recommedId;
    }

    public long getRecommedId() {

        return recommedId;
    }

    public String getRecommed() {

        return recommed;
    }

    public void setRecommed(String recommed) {

        this.recommed = recommed;
    }

    public String getUser() {

        return user;
    }

    public void setUser(String user) {

        this.user = user;
    }

    public String getPath() {

        return path;
    }

    public void setPath(String path) {

        this.path = path;
    }

    public String getRecommedAccount() {

        return recommedAccount;
    }

    public void setRecommedAccount(String recommedAccount) {

        this.recommedAccount = recommedAccount;
    }

    public String getRecommedName() {

        return recommedName;
    }

    public void setRecommedName(String recommedName) {

        this.recommedName = recommedName;
    }
}
