package com.qcloud.component.orderform.entity;

import com.qcloud.component.commoditycenter.QUnifiedMerchandise;

public class OrderMerchandise {

    private QUnifiedMerchandise unifiedMerchandise;

    private int                 number;

    public QUnifiedMerchandise getUnifiedMerchandise() {

        return unifiedMerchandise;
    }

    public void setUnifiedMerchandise(QUnifiedMerchandise unifiedMerchandise) {

        this.unifiedMerchandise = unifiedMerchandise;
    }

    public int getNumber() {

        return number;
    }

    public void setNumber(int number) {

        this.number = number;
    }
}
