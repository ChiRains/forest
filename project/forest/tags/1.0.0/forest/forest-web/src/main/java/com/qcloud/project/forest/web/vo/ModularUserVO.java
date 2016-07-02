package com.qcloud.project.forest.web.vo;

public class ModularUserVO {

    private long   id;

    // 模块Id
    private long   modularId;

    // 用户Id
    private long   userId;

    private int    enable;

    private String name;

    private String code;

    public ModularUserVO() {

    }

    public ModularUserVO(long id, long modularId, long userId) {

        this.id = id;
        this.modularId = modularId;
        this.userId = userId;
    }

    public int getEnable() {

        return enable;
    }

    public void setEnable(int enable) {

        this.enable = enable;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getCode() {

        return code;
    }

    public void setCode(String code) {

        this.code = code;
    }

    public void setId(long id) {

        this.id = id;
    }

    public long getId() {

        return id;
    }

    public void setModularId(long modularId) {

        this.modularId = modularId;
    }

    public long getModularId() {

        return modularId;
    }

    public void setUserId(long userId) {

        this.userId = userId;
    }

    public long getUserId() {

        return userId;
    }
}
