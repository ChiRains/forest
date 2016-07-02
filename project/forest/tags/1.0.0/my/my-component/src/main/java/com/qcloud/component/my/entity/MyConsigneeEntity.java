package com.qcloud.component.my.entity;

import com.qcloud.component.my.QMyConsignee;
import com.qcloud.component.my.model.Consignee;

public class MyConsigneeEntity implements QMyConsignee {

    private Consignee myConsignee;

    public MyConsigneeEntity(Consignee myConsignee) {

        super();
        this.myConsignee = myConsignee;
    }

    @Override
    public String getName() {

        return myConsignee.getName();
    }

    @Override
    public String getMobile() {

        return myConsignee.getMobile();
    }

    @Override
    public String getEmail() {

        return myConsignee.getEmail();
    }

    @Override
    public String getProvince() {

        return myConsignee.getProvince();
    }

    @Override
    public String getCity() {

        return myConsignee.getCity();
    }

    @Override
    public String getDistrict() {

        return myConsignee.getDistrict();
    }

    @Override
    public String getAddress() {

        return myConsignee.getAddress();
    }
}
