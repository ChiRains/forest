package com.qcloud.component.orderform.service.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.component.orderform.dao.RecordStateTimeDao;
import com.qcloud.component.orderform.model.RecordStateTime;
import com.qcloud.component.orderform.model.query.RecordStateTimeQuery;
import com.qcloud.component.orderform.service.RecordStateTimeService;
import com.qcloud.pirates.data.Page;

@Service
public class RecordStateTimeServiceImpl implements RecordStateTimeService {

    @Autowired
    private RecordStateTimeDao  recordStateTimeDao;

    @Autowired
    private AutoIdGenerator     autoIdGenerator;

    private static final String ID_KEY = "orderform_record_state_time";

    @Override
    public boolean add(RecordStateTime recordStateTime) {

        long id = autoIdGenerator.get(ID_KEY);
        recordStateTime.setId(id);
        return recordStateTimeDao.add(recordStateTime);
    }

    @Override
    public RecordStateTime get(Long id) {

        return recordStateTimeDao.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return recordStateTimeDao.delete(id);
    }

    @Override
    public boolean update(RecordStateTime recordStateTime) {

        return recordStateTimeDao.update(recordStateTime);
    }

    @Override
    public Page<RecordStateTime> page(RecordStateTimeQuery query, int start, int count) {

        return recordStateTimeDao.page(query, start, count);
    }

    public List<RecordStateTime> listAll() {

        return recordStateTimeDao.listAll();
    }

    @Override
    public List<RecordStateTime> list4EndDateAndState(Date endDate, Date tableDate, int state, int dataIdType, int start, int size) {

        return recordStateTimeDao.list4EndDateAndState(endDate, tableDate, state, dataIdType, start, size);
    }
}
