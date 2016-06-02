package com.qcloud.component.personalcenter.entity;

import com.qcloud.component.personalcenter.QMyWealth;
import com.qcloud.component.personalcenter.model.MyWealth;

public class MyWealthEntity implements QMyWealth {

    private MyWealth myWealth;

    public MyWealthEntity(MyWealth myWealth) {

        super();
        this.myWealth = myWealth;
    }

    @Override
    public long getIntegral() {

        return myWealth.getIntegral();
    }

    @Override
    public double getCommission() {

        return myWealth.getCommission();
    }

    @Override
    public double getConsumptionCurrency() {

        return myWealth.getConsumptionCurrency();
    }
}
