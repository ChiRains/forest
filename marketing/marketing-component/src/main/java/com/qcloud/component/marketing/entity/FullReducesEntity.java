package com.qcloud.component.marketing.entity;

import com.qcloud.component.marketing.QFullReduces;
import com.qcloud.component.marketing.model.FullReduces;

public class FullReducesEntity implements QFullReduces {

    private FullReduces fullReduces;

    public FullReducesEntity(FullReduces fullReduces) {

        this.fullReduces = fullReduces;
    }

    @Override
    public long getMerchantId() {

        return fullReduces.getMerchantId();
    }

    @Override
    public String getName() {

        return fullReduces.getName();
    }

    @Override
    public double getBenefit() {

        return fullReduces.getBenefit();
    }

    @Override
    public double getLimitPrice() {

        return fullReduces.getLimitPrice();
    }
}
