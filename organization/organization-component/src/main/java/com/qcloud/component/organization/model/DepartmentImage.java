package com.qcloud.component.organization.model;

public class DepartmentImage {

    private long   id;

    // 门店ID
    private long   departmentId;

    // 图片
    private String image;

    // 顺序
    private int    orderNum;

    public DepartmentImage() {

    }

    public DepartmentImage(long id, long departmentId, String image, int orderNum) {

        super();
        this.id = id;
        this.departmentId = departmentId;
        this.image = image;
        this.orderNum = orderNum;
    }

    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
    }

    public long getDepartmentId() {

        return departmentId;
    }

    public void setDepartmentId(long departmentId) {

        this.departmentId = departmentId;
    }

    public String getImage() {

        return image;
    }

    public void setImage(String image) {

        this.image = image;
    }

    public int getOrderNum() {

        return orderNum;
    }

    public void setOrderNum(int orderNum) {

        this.orderNum = orderNum;
    }
}
