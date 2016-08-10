package com.qcloud.component.pay.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.pay.PayRecordClient;
import com.qcloud.component.pay.QPayRecord;
import com.qcloud.component.pay.entity.PayRecordEntity;
import com.qcloud.component.pay.model.PayRecord;
import com.qcloud.component.pay.service.PayRecordService;

@Component
public class PayRecordClientImpl implements PayRecordClient {

    @Autowired
    private PayRecordService payRecordService;

    @Override
    public QPayRecord getQPayRecord(Long objectId) {

        PayRecord payRecord = payRecordService.getByObjectId(objectId);
        return payRecord != null ? new PayRecordEntity(payRecord) : null;
    }
}