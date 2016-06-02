package com.qcloud.component.personalcenter.model;

import java.util.List;

public class MySignInRuleConfig {

    private String             rule;

    private int                firstIntegral;

    private List<SignIntegral> list;

    public String getRule() {

        return rule;
    }

    public void setRule(String rule) {

        this.rule = rule;
    }

    public int getFirstIntegral() {

        return firstIntegral;
    }

    public void setFirstIntegral(int firstIntegral) {

        this.firstIntegral = firstIntegral;
    }

    public List<SignIntegral> getList() {

        return list;
    }

    public void setList(List<SignIntegral> list) {

        this.list = list;
    }
}
