package com.qcloud.project.forest.model;

public class Modular {

    private long   id;

    // 模块名称
    private String name;

    // 模块编码
    private int    code;

    // 是否启用
    private int    enable;

    public Modular() {

    }

    public Modular(long id, String name, int code, int enable) {

        this.id = id;
        this.name = name;
        this.code = code;
        this.enable = enable;
    }

    public void setId(long id) {

        this.id = id;
    }

    public long getId() {

        return id;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getName() {

        return name;
    }

    public void setEnable(int enable) {

        this.enable = enable;
    }

    public int getEnable() {

        return enable;
    }

    public int getCode() {

        return code;
    }

    public void setCode(int code) {

        this.code = code;
    }
}
