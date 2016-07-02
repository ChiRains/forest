package com.qcloud.component.orderform.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.component.orderform.model.RemindRecord;
import com.qcloud.component.orderform.model.query.RemindRecordQuery;

public interface RemindRecordDao extends ISimpleDao<RemindRecord, Long> {

    public boolean add(RemindRecord remindRecord);

    public RemindRecord get(Long id);

    public boolean delete(Long id);

    public boolean update(RemindRecord remindRecord);

    public List<RemindRecord> list(List<Long> idList);

    public Map<Long, RemindRecord> map(Set<Long> idSet);

    public Page<RemindRecord> page(RemindRecordQuery query, int start, int size);

    public List<RemindRecord> listAll();

    boolean canRemind(long subOrderId, Date orderDate, int splitMinutes);
}
