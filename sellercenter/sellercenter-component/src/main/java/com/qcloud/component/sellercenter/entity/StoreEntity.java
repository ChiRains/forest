package com.qcloud.component.sellercenter.entity;

import com.qcloud.component.organization.QDepartment;
import com.qcloud.component.sellercenter.QMerchant;
import com.qcloud.component.sellercenter.QStore;
import com.qcloud.pirates.util.StringUtil;

public class StoreEntity implements QStore {

    private MerchantEntity merchant;

    private QDepartment    parent;

    private QDepartment    department;

    public StoreEntity(MerchantEntity merchant, QDepartment parent, QDepartment department) {

        super();
        this.merchant = merchant;
        this.department = department;
        this.parent = parent;
    }

    public Long getId() {

        return department.getId();
    }

    public String getName() {

        return department.getName();
    }

    public String getAddress() {

        return StringUtil.nullToEmpty(department.getProvince()) + StringUtil.nullToEmpty(department.getCity()) + StringUtil.nullToEmpty(department.getDistrict()) + StringUtil.nullToEmpty(department.getAddress());
    }

    public MerchantEntity getMerchant() {

        return merchant;
    }

    @Override
    public String getSmsMobile() {

        return department.getPhone();
    }

    @Override
    public Double getLatitude() {

        return department.getLatitude();
    }

    @Override
    public Double getLongitude() {

        return department.getLongitude();
    }

    @Override
    public QStore getParent() {

        if (parent == null || parent.getType() != 2) {
            return null;
        }
        return new StoreEntity(merchant, parent.getParent(), parent);
    }

    @Override
    public boolean isRoot() {

        return parent != null && merchant != null && parent.getId() == merchant.getId();
    }
}
