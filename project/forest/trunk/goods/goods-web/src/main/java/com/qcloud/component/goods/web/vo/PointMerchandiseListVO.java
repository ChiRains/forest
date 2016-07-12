package com.qcloud.component.goods.web.vo;

import java.util.ArrayList;
import java.util.List;

public class PointMerchandiseListVO {

    private String                   name;

    private long                     id;

    private List<PointMerchandiseVO> merchandiseList = new ArrayList<PointMerchandiseVO>();

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
    }

    public List<PointMerchandiseVO> getMerchandiseList() {

        return merchandiseList;
    }

    public void setMerchandiseList(List<PointMerchandiseVO> merchandiseList) {

        this.merchandiseList = merchandiseList;
    }
}
