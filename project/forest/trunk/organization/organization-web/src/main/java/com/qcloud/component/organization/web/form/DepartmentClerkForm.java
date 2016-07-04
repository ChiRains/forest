package com.qcloud.component.organization.web.form;

public class DepartmentClerkForm {

    // ID
    private long    id;

    // 部门id
    private long    departmentId;

    // 职员id
    private long    clerkId;

    // 类型: 1.所属部门 2.解调部门
    private int     type;

    private Boolean isSelected;

    public DepartmentClerkForm() {

    }

    public DepartmentClerkForm(long id, long departmentId, long clerkId, int type) {

        this.id = id;
        this.departmentId = departmentId;
        this.clerkId = clerkId;
        this.type = type;
    }

    public void setId(long id) {

        this.id = id;
    }

    public long getId() {

        return id;
    }

    public void setDepartmentId(long departmentId) {

        this.departmentId = departmentId;
    }

    public long getDepartmentId() {

        return departmentId;
    }

    public void setClerkId(long clerkId) {

        this.clerkId = clerkId;
    }

    public long getClerkId() {

        return clerkId;
    }

    public void setType(int type) {

        this.type = type;
    }

    public int getType() {

        return type;
    }

    public Boolean getIsSelected() {

        return isSelected;
    }

    public void setIsSelected(Boolean isSelected) {

        this.isSelected = isSelected;
    }
}
