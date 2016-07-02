package com.qcloud.component.orderform.web.vo.personal;

public class AfterSaleItemVO {

    // 商品名称
    private String name;

    // 缩略图
    private String image;

    // 规格说明
    private String specifications;

    // 商品编号
    private String code;

    // 数量
    private int    number;

    // 缩略图
    private String explain;

    // 商品编号
    private String reason;

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

    public String getSpecifications() {

        return specifications;
    }

    public void setSpecifications(String specifications) {

        this.specifications = specifications;
    }

    public String getCode() {

        return code;
    }

    public void setCode(String code) {

        this.code = code;
    }

    public int getNumber() {

        return number;
    }

    public void setNumber(int number) {

        this.number = number;
    }

    public String getExplain() {

        return explain;
    }

    public void setExplain(String explain) {

        this.explain = explain;
    }

    public String getReason() {

        return reason;
    }

    public void setReason(String reason) {

        this.reason = reason;
    }
}
