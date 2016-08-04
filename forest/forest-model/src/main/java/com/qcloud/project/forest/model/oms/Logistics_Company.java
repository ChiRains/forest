package com.qcloud.project.forest.model.oms;

/**
 * 物流公司接口
 */
public class Logistics_Company {

    // 平台物流公司id
    private long   id;

    // 平台物流公司编码
    private String code;

    // 物流公司名称
    private String name;

    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
    }

    public String getCode() {

        return code;
    }

    public void setCode(String code) {

        this.code = code;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }
}
