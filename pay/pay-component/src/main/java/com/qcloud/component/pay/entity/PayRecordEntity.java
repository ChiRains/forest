package com.qcloud.component.pay.entity;

import java.util.Date;
import com.qcloud.component.pay.QPayRecord;
import com.qcloud.component.pay.model.PayRecord;

public class PayRecordEntity implements QPayRecord {

    private PayRecord payRecord;

    public PayRecordEntity(PayRecord payRecord) {

        super();
        this.payRecord = payRecord;
    }

    public long getId() {

        return payRecord.getId();
    }

    public long getObjectId() {

        return payRecord.getObjectId();
    }

    public Date getOccurTime() {

        return payRecord.getOccurTime();
    }

    public String getTradeId() {

        return payRecord.getTradeId();
    }

    public String getModule() {

        return payRecord.getModule();
    }

    public String getType() {

        return payRecord.getType();
    }

    public String getClient() {

        return payRecord.getClient();
    }

    public String getAttach() {

        return payRecord.getAttach();
    }

    public Date getTime() {

        return payRecord.getTime();
    }
}
