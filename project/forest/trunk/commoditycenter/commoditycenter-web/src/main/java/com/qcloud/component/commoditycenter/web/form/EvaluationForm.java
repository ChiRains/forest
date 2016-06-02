package com.qcloud.component.commoditycenter.web.form;

import java.util.ArrayList;
import java.util.List;
import com.qcloud.component.publicdata.EnableType;

public class EvaluationForm {

    private Integer                         anonymous              = EnableType.DISABLE.getKey();

    private List<MerchandiseEvaluationForm> merchandiseEvaluations = new ArrayList<MerchandiseEvaluationForm>();

    public List<MerchandiseEvaluationForm> getMerchandiseEvaluations() {

        return merchandiseEvaluations;
    }

    public void setMerchandiseEvaluations(List<MerchandiseEvaluationForm> merchandiseEvaluations) {

        this.merchandiseEvaluations = merchandiseEvaluations;
    }

    public Integer getAnonymous() {

        return anonymous;
    }

    public void setAnonymous(Integer anonymous) {

        this.anonymous = anonymous;
    }
}
