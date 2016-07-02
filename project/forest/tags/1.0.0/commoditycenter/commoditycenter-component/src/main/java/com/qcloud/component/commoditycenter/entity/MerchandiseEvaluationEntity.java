package com.qcloud.component.commoditycenter.entity;

import java.util.Date;
import com.qcloud.component.commoditycenter.QMerchandiseEvaluation;
import com.qcloud.component.commoditycenter.model.Merchandise;
import com.qcloud.component.commoditycenter.model.MerchandiseEvaluation;

public class MerchandiseEvaluationEntity implements QMerchandiseEvaluation {

    private MerchandiseEvaluation merchandiseEvaluation;

    private Merchandise           merchandise;

    public MerchandiseEvaluationEntity(MerchandiseEvaluation merchandiseEvaluation, Merchandise merchandise) {

        super();
        this.merchandiseEvaluation = merchandiseEvaluation;
        this.merchandise = merchandise;
    }

    public long getId() {

        return merchandiseEvaluation.getId();
    }

    public String getContent() {

        return merchandiseEvaluation.getContent();
    }

    public int getStar() {

        return merchandiseEvaluation.getStar();
    }

    public Date getTime() {

        return merchandiseEvaluation.getTime();
    }

    public int getStatus() {

        return merchandiseEvaluation.getStatus();
    }

    public String getSpecifications() {

        return merchandiseEvaluation.getSpecifications();
    }

    public long getUserId() {

        return merchandiseEvaluation.getUserId();
    }

    public String getMerchandiseName() {

        return merchandise.getName();
    }
}
