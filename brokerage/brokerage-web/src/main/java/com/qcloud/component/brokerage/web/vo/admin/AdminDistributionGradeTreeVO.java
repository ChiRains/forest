package com.qcloud.component.brokerage.web.vo.admin;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

public class AdminDistributionGradeTreeVO {

    // ID
    private long                              id;

    // 名称
    private String                            name;

    // 有效月数
    private int                               monthLimit;

    // 等级描述
    private String                            desc;

    // 金额
    private double                            cash;

    // 用户来源 (1 指定用户,2 系统用户)
    private int                               userResource;

    // 分类ID
    private long                              classifyId;

    // 树编码
    private String                            bsid;

    // 等级类别 1用户配置 2系统内置
    private int                               type;

    private List<AdminDistributionGradeTreeVO> childrenList;

    public AdminDistributionGradeTreeVO() {

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

    public void setMonthLimit(int monthLimit) {

        this.monthLimit = monthLimit;
    }

    public int getMonthLimit() {

        return monthLimit;
    }

    public void setDesc(String desc) {

        this.desc = desc;
    }

    public String getDesc() {

        return desc;
    }

    public void setCash(double cash) {

        this.cash = cash;
    }

    public double getCash() {

        return cash;
    }

    public void setUserResource(int userResource) {

        this.userResource = userResource;
    }

    public int getUserResource() {

        return userResource;
    }

    public void setClassifyId(long classifyId) {

        this.classifyId = classifyId;
    }

    public long getClassifyId() {

        return classifyId;
    }

    public void setBsid(String bsid) {

        this.bsid = bsid;
    }

    public String getBsid() {

        return bsid;
    }

    public void setType(int type) {

        this.type = type;
    }

    public int getType() {

        return type;
    }

    public List<AdminDistributionGradeTreeVO> getChildrenList() {

        return childrenList;
    }

    public void setChildrenList(List<AdminDistributionGradeTreeVO> childrenList) {

        this.childrenList = childrenList;
    }
}
