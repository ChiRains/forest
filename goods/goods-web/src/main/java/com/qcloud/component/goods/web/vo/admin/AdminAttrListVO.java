package com.qcloud.component.goods.web.vo.admin;

import java.util.ArrayList;
import java.util.List;

public class AdminAttrListVO {

    private long         id;

    private String       name;

    private List<String> valueList = new ArrayList<String>();

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

    public List<String> getValueList() {

        return valueList;
    }

    public void setValueList(List<String> valueList) {

        this.valueList = valueList;
    }
}
