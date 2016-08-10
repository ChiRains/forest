package com.qcloud.project.forest.web.vo.admin;

public class AdminBpCalculationVO {

    private long   id;

    // 名称
    private String name;

    // 描述
    private String description;

    // 搜索呀最大值
    private int    dbpMax;

    // 收缩压最小值
    private int    dbpMin;

    // 舒张压最大值
    private int    sbpMax;

    // 舒张压最小值
    private int    sbpMin;

    public AdminBpCalculationVO() {

    }

    public AdminBpCalculationVO(long id, String name, String description, int dbpMax, int dbpMin, int sbpMax, int sbpMin) {

        super();
        this.id = id;
        this.name = name;
        this.description = description;
        this.dbpMax = dbpMax;
        this.dbpMin = dbpMin;
        this.sbpMax = sbpMax;
        this.sbpMin = sbpMin;
    }

    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {

        this.description = description;
    }

    public int getDbpMax() {

        return dbpMax;
    }

    public void setDbpMax(int dbpMax) {

        this.dbpMax = dbpMax;
    }

    public int getDbpMin() {

        return dbpMin;
    }

    public void setDbpMin(int dbpMin) {

        this.dbpMin = dbpMin;
    }

    public int getSbpMax() {

        return sbpMax;
    }

    public void setSbpMax(int sbpMax) {

        this.sbpMax = sbpMax;
    }

    public int getSbpMin() {

        return sbpMin;
    }

    public void setSbpMin(int sbpMin) {

        this.sbpMin = sbpMin;
    }
}
