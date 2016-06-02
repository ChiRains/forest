package com.qcloud.component.marketing.web.form;

import java.util.ArrayList;
import java.util.List;

public class MerchandiseCustomClassificationForm {

    private long          classifyId;

    private List<Long>    unifiedMerchandiseIds = new ArrayList<Long>();

    private List<Integer> orderNums             = new ArrayList<Integer>();

    public List<Long> getUnifiedMerchandiseIds() {

        return unifiedMerchandiseIds;
    }

    public void setUnifiedMerchandiseIds(List<Long> unifiedMerchandiseIds) {

        this.unifiedMerchandiseIds = unifiedMerchandiseIds;
    }

    public List<Integer> getOrderNums() {

        return orderNums;
    }

    public void setOrderNums(List<Integer> orderNums) {

        this.orderNums = orderNums;
    }

    public long getClassifyId() {

        return classifyId;
    }

    public void setClassifyId(long classifyId) {

        this.classifyId = classifyId;
    }
}
