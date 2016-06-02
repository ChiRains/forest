package com.qcloud.component.commoditycenter.model.query;

import java.util.List;

public class MerchandiseMarketingQuery {

    private Long   activityId;

    private int    sence;

    private String name;
    
    private List<Long> activityIdList;

    public MerchandiseMarketingQuery() {

    }

    public Long getActivityId() {

        return activityId;
    }

    public void setActivityId(Long activityId) {

        this.activityId = activityId;
    }

    public int getSence() {

        return sence;
    }

    public void setSence(int sence) {

        this.sence = sence;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    
    public List<Long> getActivityIdList() {
    
        return activityIdList;
    }

    
    public void setActivityIdList(List<Long> activityIdList) {
    
        this.activityIdList = activityIdList;
    }
}
