package com.qcloud.component.goods.web.vo;

public class MerchandiseItemExportVO {

    private String id;

    private int    index;

    private double discount;

    private String name;

    public String getId() {

        return id;
    }

    public void setId(String id) {

        this.id = id;
    }

    public int getIndex() {

        return index;
    }

    public void setIndex(int index) {

        this.index = index;
    }

    public double getDiscount() {

        return discount;
    }

    public void setDiscount(double discount) {

        this.discount = discount;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }
}
