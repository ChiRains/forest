package com.qcloud.component.seckill.web.vo;

import java.util.ArrayList;
import java.util.List;

public class MerchandiseClassifyVO {

    private String                     name;

    private String                     image;

    private List<MerchandiseSeckillVO> merchandiseList = new ArrayList<MerchandiseSeckillVO>();

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

    public List<MerchandiseSeckillVO> getMerchandiseList() {

        return merchandiseList;
    }

    public void setMerchandiseList(List<MerchandiseSeckillVO> merchandiseList) {

        this.merchandiseList = merchandiseList;
    }
}
