package com.qcloud.component.orderform.service;

import java.util.Date;
import java.util.List;
import com.qcloud.component.orderform.model.RecordStateTime;
import com.qcloud.component.orderform.model.query.RecordStateTimeQuery;
import com.qcloud.pirates.data.Page;

public interface RecordStateTimeService {

    public boolean add(RecordStateTime recordStateTime);

    public RecordStateTime get(Long id);

    public boolean delete(Long id);

    public boolean update(RecordStateTime recordStateTime);

    public Page<RecordStateTime> page(RecordStateTimeQuery query, int start, int count);

    public List<RecordStateTime> listAll();

    List<RecordStateTime> list4EndDateAndState(Date endDate, Date tableDate, int state, int dataIdType, int start, int size);
}
