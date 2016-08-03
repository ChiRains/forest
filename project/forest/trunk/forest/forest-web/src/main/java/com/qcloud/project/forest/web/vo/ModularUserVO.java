package com.qcloud.project.forest.web.vo;

public class ModularUserVO {

    private long   id;

    // 模块编码
    private int    modularCode;

    // 用户Id
    private long   userId;

    private String name;

    private int    code;

    public ModularUserVO() {

    }

    public ModularUserVO(long id, int modularCode, long userId, int enable, String name, int code) {

        super();
        this.id = id;
        this.modularCode = modularCode;
        this.userId = userId;
        this.name = name;
        this.code = code;
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

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public void setCode(int code) {

        this.code = code;
    }

    public int getModularCode() {

        return modularCode;
    }

    public void setModularCode(int modularCode) {

        this.modularCode = modularCode;
    }

    public int getCode() {

        return code;
    }
}
