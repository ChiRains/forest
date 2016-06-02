package com.qcloud.component.orderform.service.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.orderform.dao.RemindRecordDao;
import com.qcloud.component.orderform.model.RemindRecord;
import com.qcloud.component.orderform.service.RemindRecordService;
import com.qcloud.component.orderform.model.query.RemindRecordQuery;

@Service
public class RemindRecordServiceImpl implements RemindRecordService {

    @Autowired
    private RemindRecordDao     remindRecordDao;

    @Autowired
    private AutoIdGenerator     autoIdGenerator;

    private static final String ID_KEY = "orderform_remind_record";

    @Override
    public boolean add(RemindRecord remindRecord) {

        long id = autoIdGenerator.get(ID_KEY);
        remindRecord.setId(id);
        return remindRecordDao.add(remindRecord);
    }

    @Override
    public RemindRecord get(Long id) {

        return remindRecordDao.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return remindRecordDao.delete(id);
    }

    @Override
    public boolean update(RemindRecord remindRecord) {

        return remindRecordDao.update(remindRecord);
    }

    @Override
    public Page<RemindRecord> page(RemindRecordQuery query, int start, int count) {

        return remindRecordDao.page(query, start, count);
    }

    public List<RemindRecord> listAll() {

        return remindRecordDao.listAll();
    }

    @Override
    public boolean canRemind(long subOrderId, Date orderDate) {

        return remindRecordDao.canRemind(subOrderId, orderDate, 30);
    }
}
