package com.qcloud.component.pay.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.pay.dao.PayRecordDao;
import com.qcloud.component.pay.model.PayRecord;
import com.qcloud.component.pay.service.PayRecordService;
import com.qcloud.component.pay.model.query.PayRecordQuery;

@Service
public class PayRecordServiceImpl implements PayRecordService {

    @Autowired
    private PayRecordDao        payRecordDao;

    @Autowired
    private AutoIdGenerator     autoIdGenerator;

    private static final String ID_KEY = "pay_pay_record";

    @Override
    public boolean add(PayRecord payRecord) {

        long id = autoIdGenerator.get(ID_KEY);
        payRecord.setId(id);
        return payRecordDao.add(payRecord);
    }

    @Override
    public PayRecord get(Long id) {

        return payRecordDao.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return payRecordDao.delete(id);
    }

    @Override
    public boolean update(PayRecord payRecord) {

        return payRecordDao.update(payRecord);
    }

    @Override
    public Page<PayRecord> page(PayRecordQuery query, int start, int count) {

        return payRecordDao.page(query, start, count);
    }

    public List<PayRecord> listAll() {

        return payRecordDao.listAll();
    }

    @Override
    public PayRecord getByObjectId(long objectId) {

        return payRecordDao.getByObjectId(objectId);
    }
}
