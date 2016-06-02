package com.qcloud.component.my.web.vo;

import java.util.ArrayList;
import java.util.List;

public class MyShoppingCartClassifyVO {

    // 商家分类ID
    private long                   classifyId;

    // 商家分类ID
    private String                 name;

    private String                 image;

    private List<MyShoppingCartVO> merchandiseList = new ArrayList<MyShoppingCartVO>();

    public long getClassifyId() {

        return classifyId;
    }

    public void setClassifyId(long classifyId) {

        this.classifyId = classifyId;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getImage() {

        return image;
    }

    public void setImage(String image) {

        this.image = image;
    }

    public List<MyShoppingCartVO> getMerchandiseList() {

        return merchandiseList;
    }

    public void setMerchandiseList(List<MyShoppingCartVO> merchandiseList) {

        this.merchandiseList = merchandiseList;
    }
}
