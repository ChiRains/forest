package com.qcloud.component.goods.web.vo;

public class MerchandiseSpecificationsVO {

    private long id;

    private long merchandiseId;

    private long attributeId0;

    //值
    private String value0;

    private long attributeId1;

    //值
    private String value1;

    private long attributeId2;

    //值
    private String value2;

    //库存
    private int state;

    public MerchandiseSpecificationsVO() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getMerchandiseId() {
        return merchandiseId;
    }

    public void setMerchandiseId(long merchandiseId) {
        this.merchandiseId = merchandiseId;
    }

    public long getAttributeId0() {
        return attributeId0;
    }

    public void setAttributeId0(long attributeId0) {
        this.attributeId0 = attributeId0;
    }

    public String getValue0() {
        return value0;
    }

    public void setValue0(String value0) {
        this.value0 = value0;
    }

    public long getAttributeId1() {
        return attributeId1;
    }

    public void setAttributeId1(long attributeId1) {
        this.attributeId1 = attributeId1;
    }

    public String getValue1() {
        return value1;
    }

    public void setValue1(String value1) {
        this.value1 = value1;
    }

    public long getAttributeId2() {
        return attributeId2;
    }

    public void setAttributeId2(long attributeId2) {
        this.attributeId2 = attributeId2;
    }

    public String getValue2() {
        return value2;
    }

    public void setValue2(String value2) {
        this.value2 = value2;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
