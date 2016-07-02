package com.qcloud.component.orderform.service;

import java.util.Date;
import java.util.List;
import com.qcloud.component.orderform.model.RemindRecord;
import com.qcloud.component.orderform.model.query.RemindRecordQuery;
import com.qcloud.pirates.data.Page;

public interface RemindRecordService {

    public boolean add(RemindRecord remindRecord);

    public RemindRecord get(Long id);

    public boolean delete(Long id);

    public boolean update(RemindRecord remindRecord);

    public Page<RemindRecord> page(RemindRecordQuery query, int start, int count);

    public List<RemindRecord> listAll();

    public boolean canRemind(long subOrderId, Date orderDate);
}
