package com.qcloud.component.orderform.dao.cache;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.qcloud.component.orderform.dao.RecordStateTimeDao;
import com.qcloud.component.orderform.model.RecordStateTime;
import com.qcloud.component.orderform.model.query.RecordStateTimeQuery;
import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;

@Repository
public class RecordStateTimeDaoCacheImpl implements RecordStateTimeDao {

    @Autowired
    private RecordStateTimeDao recordStateTimeDaoMysqlImpl;

    // @Autowired
    // private RecordStateTimeDao recordStateTimeDaoRedisImpl;
    @Override
    public boolean add(RecordStateTime recordStateTime) {

        return recordStateTimeDaoMysqlImpl.add(recordStateTime);
    }

    @Override
    public RecordStateTime get(Long id) {

        return recordStateTimeDaoMysqlImpl.get(id);
        // return CacheLoader.get(recordStateTimeDaoRedisImpl, recordStateTimeDaoMysqlImpl, id);
    }

    @Override
    public boolean delete(Long id) {

        return recordStateTimeDaoMysqlImpl.delete(id);
    }

    @Override
    public boolean update(RecordStateTime recordStateTime) {

        return recordStateTimeDaoMysqlImpl.update(recordStateTime);
    }

    @Override
    public List<RecordStateTime> list(List<Long> idList) {

        return CacheLoader.list(recordStateTimeDaoMysqlImpl, idList);
    }

    @Override
    public Map<Long, RecordStateTime> map(Set<Long> idSet) {

        return CacheLoader.map(recordStateTimeDaoMysqlImpl, idSet);
    }

    @Override
    public Page<RecordStateTime> page(int start, int count) {

        return recordStateTimeDaoMysqlImpl.page(start, count);
    }

    @Override
    public Page<RecordStateTime> page(RecordStateTimeQuery query, int start, int count) {

        return recordStateTimeDaoMysqlImpl.page(query, start, count);
    }

    public List<RecordStateTime> listAll() {

        return recordStateTimeDaoMysqlImpl.listAll();
    }

    @Override
    public List<RecordStateTime> list4EndDateAndState(Date endDate, Date tableDate, int state, int dataIdType, int start, int size) {

        return recordStateTimeDaoMysqlImpl.list4EndDateAndState(endDate, tableDate, state, dataIdType, start, size);
    }
}
