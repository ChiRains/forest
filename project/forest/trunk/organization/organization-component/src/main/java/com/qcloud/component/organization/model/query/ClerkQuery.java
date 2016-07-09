package com.qcloud.component.organization.model.query;

public class ClerkQuery {

    private String name;
    
    private long departmentId;

    public ClerkQuery() {

    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    
    public long getDepartmentId() {
    
        return departmentId;
    }

    
    public void setDepartmentId(long departmentId) {
    
        this.departmentId = departmentId;
    }
}
