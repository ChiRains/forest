package com.qcloud.component.my.entity;

import java.util.Date;
import com.qcloud.component.my.QMyToEvaluation;
import com.qcloud.component.my.model.MyToEvaluation;

public class MyToEvaluationEntity implements QMyToEvaluation {

    private MyToEvaluation myToEvaluation;

    public MyToEvaluationEntity(MyToEvaluation myToEvaluation) {

        super();
        this.myToEvaluation = myToEvaluation;
    }

    @Override
    public long getId() {

        return myToEvaluation.getId();
    }

    @Override
    public long getUserId() {

        return myToEvaluation.getUserId();
    }

    @Override
    public long getUnifiedMerchandiseId() {

        return myToEvaluation.getUnifiedMerchandiseId();
    }

    @Override
    public long getMerchandiseId() {

        return myToEvaluation.getMerchandiseId();
    }

    @Override
    public String getName() {

        return myToEvaluation.getName();
    }

    @Override
    public String getImage() {

        return myToEvaluation.getImage();
    }

    @Override
    public double getDiscount() {

        return myToEvaluation.getDiscount();
    }

    @Override
    public long getMerchantId() {

        return myToEvaluation.getMerchantId();
    }

    @Override
    public long getOrderId() {

        return myToEvaluation.getOrderId();
    }

    @Override
    public long getSubOrderId() {

        return myToEvaluation.getSubOrderId();
    }

    @Override
    public long getOrderItemId() {

        return myToEvaluation.getOrderItemId();
    }

    public long getOrderItemDetailId() {

        return myToEvaluation.getOrderItemDetailId();
    }

    @Override
    public Date getOrderDate() {

        return myToEvaluation.getOrderDate();
    }

    @Override
    public String getOrderNumber() {

        return myToEvaluation.getOrderNumber();
    }
}
