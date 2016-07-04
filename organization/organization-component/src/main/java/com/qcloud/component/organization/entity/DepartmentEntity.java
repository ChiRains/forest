package com.qcloud.component.organization.entity;

import com.qcloud.component.organization.QDepartment;
import com.qcloud.component.organization.model.Department;

public class DepartmentEntity implements QDepartment {

    private Department       department;

    private DepartmentEntity parent;

    public DepartmentEntity(Department department, DepartmentEntity parent) {

        super();
        this.department = department;
        this.parent = parent;
    }

    public Long getId() {

        return department.getId();
    }

    public String getName() {

        return department.getName();
    }

    public Long getManager() {

        return department.getManager();
    }

    @Override
    public String getProvince() {

        return department.getProvince();
    }

    @Override
    public String getCity() {

        return department.getCity();
    }

    @Override
    public String getDistrict() {

        return department.getDistrict();
    }

    @Override
    public String getAddress() {

        return department.getAddress();
    }

    @Override
    public double getLongitude() {

        return department.getLongitude();
    }

    @Override
    public double getLatitude() {

        return department.getLatitude();
    }

    @Override
    public String getCode() {

        return department.getCode();
    }

    @Override
    public String getImage() {

        return department.getImage();
    }

    @Override
    public String getPhone() {

        // TODO
        return null;
        // return department.getPhone();
    }

    @Override
    public QDepartment getParent() {

        return parent;
    }

    @Override
    public int getType() {

        return department.getType();
    }
}
