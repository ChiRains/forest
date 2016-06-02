package com.qcloud.component.personalcenter.model;

import java.util.Date;
import java.math.BigDecimal;

public class Grade {

    // ID
    private long   id;

    // 名称
    private String name;

    // 等级描述
    private String desc;

    // 积分
    private int    point;

    // 折扣
    private int    discount;
    
 
    
    //图标
    private String image;
   

    public int getPoint() {

        return point;
    }

    public void setPoint(int point) {

        this.point = point;
    }

    public int getDiscount() {

        return discount;
    }

    public void setDiscount(int discount) {

        this.discount = discount;
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

    public void setDesc(String desc) {

        this.desc = desc;
    }

    public String getDesc() {

        return desc;
    }

    public Grade() {

    }

    public Grade(long id, String name, String desc, int point, int discount,int priceType) {

        this.id = id;
        this.name = name;
        this.desc = desc;
        this.point = point;
        this.discount = discount;
    }

    


    
    public String getImage() {
    
        return image;
    }

    
    public void setImage(String image) {
    
        this.image = image;
    }
}
