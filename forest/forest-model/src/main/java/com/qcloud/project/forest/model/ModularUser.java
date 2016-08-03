package com.qcloud.project.forest.model;

public class ModularUser {

    private long id;

    // 模块编码
    private int  modularCode;

    // 用户Id
    private long userId;

    public ModularUser() {

    }

    public ModularUser(long id, int modularCode, long userId) {

        super();
        this.id = id;
        this.modularCode = modularCode;
        this.userId = userId;
    }

    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
    }

    public long getUserId() {

        return userId;
    }

    public void setUserId(long userId) {

        this.userId = userId;
    }

    public int getModularCode() {

        return modularCode;
    }

    public void setModularCode(int modularCode) {

        this.modularCode = modularCode;
    }
}
