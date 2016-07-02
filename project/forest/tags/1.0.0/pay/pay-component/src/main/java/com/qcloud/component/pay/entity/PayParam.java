package com.qcloud.component.pay.entity;

import java.util.Date;

public class PayParam {

    private Long   objectId;

    private Date   occurTime;

    private String module;

    public PayParam(Long objectId, Date occurTime, String module) {

        super();
        this.objectId = objectId;
        this.occurTime = occurTime;
        this.module = module;
    }

    public String getModule() {

        return module;
    }

    public Long getObjectId() {

        return objectId;
    }

    public Date getOccurTime() {

        return occurTime;
    }
}
