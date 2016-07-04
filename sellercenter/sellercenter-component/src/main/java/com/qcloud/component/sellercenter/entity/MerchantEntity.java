package com.qcloud.component.sellercenter.entity;

import com.qcloud.component.organization.QDepartment;
import com.qcloud.component.sellercenter.QMerchant;

public class MerchantEntity implements QMerchant {

    private QDepartment department;

    public MerchantEntity(QDepartment department) {

        super();
        this.department = department;
    }

    @Override
    public long getKey() {

        return department.getId();
    }

    @Override
    public String getValue() {

        return department.getName();
    }

    @Override
    public long getId() {

        return department.getId();
    }

    @Override
    public String getName() {

        return department.getName();
    }

    @Override
    public String getImage() {

        return department.getImage();
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
    public String getReceiveMobile() {

        return department.getPhone();
    }

    @Override
    public int hashCode() {

        return new Long(department.getId()).hashCode();
    }

    @Override
    public boolean equals(Object obj) {

        if (obj instanceof QMerchant) {
            return ((QMerchant) obj).getId() == getId();
        }
        return false;
    }

    @Override
    public String getCode() {

        return department.getCode();
    }

    public boolean isNotify() {

        // TODO
        return false;
    }

    @Override
    public int getIsIncludePost() {

        // TODO
        return 2;
    }

    @Override
    public int getIsCertified() {

        // TODO
        return 2;
    }

    @Override
    public int getIsSpecialService() {

        // TODO
        return 2;
    }

    @Override
    public int getIsNoReason() {

        // TODO
        return 2;
    }
}
