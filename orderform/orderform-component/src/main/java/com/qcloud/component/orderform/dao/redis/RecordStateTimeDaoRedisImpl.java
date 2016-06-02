package com.qcloud.component.orderform.dao.redis;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.component.orderform.dao.RecordStateTimeDao;
import com.qcloud.component.orderform.model.RecordStateTime;
import com.qcloud.component.orderform.model.query.RecordStateTimeQuery;
import com.qcloud.pirates.data.Page;

@Repository
public class RecordStateTimeDaoRedisImpl implements RecordStateTimeDao {

    // @Resource(name = "redis-orderform")
    // private Redis redis;
    @Override
    public boolean add(RecordStateTime recordStateTime) {

        throw new NotImplementedException();
    }

    @Override
    public RecordStateTime get(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean delete(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean update(RecordStateTime recordStateTime) {

        throw new NotImplementedException();
    }

    @Override
    public List<RecordStateTime> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, RecordStateTime> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<RecordStateTime> page(int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public Page<RecordStateTime> page(RecordStateTimeQuery query, int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public List<RecordStateTime> listAll() {

        throw new NotImplementedException();
    }

    @Override
    public List<RecordStateTime> list4EndDateAndState(Date endDate, Date tableDate, int state, int dataIdType, int start, int size) {

        throw new NotImplementedException();
    }
}
