package com.qcloud.component.orderform.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.qcloud.component.orderform.model.RecordStateTime;
import com.qcloud.component.orderform.model.query.RecordStateTimeQuery;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;

public interface RecordStateTimeDao extends ISimpleDao<RecordStateTime, Long> {

    public boolean add(RecordStateTime recordStateTime);

    public RecordStateTime get(Long id);

    public boolean delete(Long id);

    public boolean update(RecordStateTime recordStateTime);

    public List<RecordStateTime> list(List<Long> idList);

    public Map<Long, RecordStateTime> map(Set<Long> idSet);

    public Page<RecordStateTime> page(RecordStateTimeQuery query, int start, int size);

    public List<RecordStateTime> listAll();

    List<RecordStateTime> list4EndDateAndState(Date endDate, Date tableDate, int state, int dataIdType, int start, int size);
}
