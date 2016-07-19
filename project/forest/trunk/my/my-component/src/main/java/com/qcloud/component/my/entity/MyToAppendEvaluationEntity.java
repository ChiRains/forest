package com.qcloud.component.my.entity;

import java.util.Date;
import com.qcloud.component.my.QMyToAppendEvaluation;
import com.qcloud.component.my.model.MyToAppendEvaluation;

public class MyToAppendEvaluationEntity implements QMyToAppendEvaluation {

    private MyToAppendEvaluation myToAppendEvaluation;

    public MyToAppendEvaluationEntity(MyToAppendEvaluation myToAppendEvaluation) {

        super();
        this.myToAppendEvaluation = myToAppendEvaluation;
    }

    @Override
    public long getId() {

        return myToAppendEvaluation.getId();
    }

    @Override
    public long getUserId() {

        return myToAppendEvaluation.getUserId();
    }

    @Override
    public long getUnifiedMerchandiseId() {

        return myToAppendEvaluation.getUnifiedMerchandiseId();
    }

    @Override
    public long getMerchandiseId() {

        return myToAppendEvaluation.getMerchandiseId();
    }

    @Override
    public String getName() {

        return myToAppendEvaluation.getName();
    }

    @Override
    public String getImage() {

        return myToAppendEvaluation.getImage();
    }

    @Override
    public double getDiscount() {

        return myToAppendEvaluation.getDiscount();
    }

    @Override
    public long getMerchantId() {

        return myToAppendEvaluation.getMerchantId();
    }

    @Override
    public long getOrderId() {

        return myToAppendEvaluation.getOrderId();
    }

    @Override
    public long getSubOrderId() {

        return myToAppendEvaluation.getSubOrderId();
    }

    @Override
    public long getOrderItemId() {

        return myToAppendEvaluation.getOrderItemId();
    }

    public long getOrderItemDetailId() {

        return myToAppendEvaluation.getOrderItemDetailId();
    }

    @Override
    public Date getOrderDate() {

        return myToAppendEvaluation.getOrderDate();
    }

    @Override
    public String getOrderNumber() {

        return myToAppendEvaluation.getOrderNumber();
    }

    @Override
    public String getSpecifications() {

        return myToAppendEvaluation.getSpecifications();
    }

    @Override
    public long getEvaluationId() {

        return myToAppendEvaluation.getEvaluationId();
    }
}
