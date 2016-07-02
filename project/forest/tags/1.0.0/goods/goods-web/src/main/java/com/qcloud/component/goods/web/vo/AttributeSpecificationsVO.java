package com.qcloud.component.goods.web.vo;

import java.util.ArrayList;
import java.util.List;

public class AttributeSpecificationsVO {

    private long         id;

    // 名称
    private String       name;

    private List<String> specificationsList = new ArrayList<String>();

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

    public List<String> getSpecificationsList() {

        return specificationsList;
    }

    public void setSpecificationsList(List<String> specificationsList) {

        this.specificationsList = specificationsList;
    }
}
