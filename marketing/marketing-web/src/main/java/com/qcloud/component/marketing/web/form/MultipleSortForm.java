package com.qcloud.component.marketing.web.form;

import java.util.List;
import com.qcloud.component.commoditycenter.model.MerchandiseMarketing;
import com.qcloud.component.marketing.model.MerchandiseCustomClassification;

public class MultipleSortForm {

    private List<MerchandiseMarketing>            list;

    private List<MerchandiseCustomClassification> customList;

    public List<MerchandiseMarketing> getList() {

        return list;
    }

    public void setList(List<MerchandiseMarketing> list) {

        this.list = list;
    }

    public List<MerchandiseCustomClassification> getCustomList() {

        return customList;
    }

    public void setCustomList(List<MerchandiseCustomClassification> customList) {

        this.customList = customList;
    }
}
