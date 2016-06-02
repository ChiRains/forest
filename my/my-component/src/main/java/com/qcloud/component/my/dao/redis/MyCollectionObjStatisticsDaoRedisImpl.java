package com.qcloud.component.my.dao.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.component.my.dao.MyCollectionObjStatisticsDao;
import com.qcloud.component.my.model.MyCollectionObjStatistics;
import com.qcloud.component.my.model.query.MyCollectionObjStatisticsQuery;
import com.qcloud.pirates.data.Page;

@Repository
public class MyCollectionObjStatisticsDaoRedisImpl implements MyCollectionObjStatisticsDao {

    // @Resource(name = "redis-my")
    // private Redis redis;
    @Override
    public boolean add(MyCollectionObjStatistics myCollectionObjStatistics) {

        throw new NotImplementedException();
    }

    @Override
    public MyCollectionObjStatistics get(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean delete(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean update(MyCollectionObjStatistics myCollectionObjStatistics) {

        throw new NotImplementedException();
    }

    @Override
    public List<MyCollectionObjStatistics> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, MyCollectionObjStatistics> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<MyCollectionObjStatistics> page(int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public Page<MyCollectionObjStatistics> page(MyCollectionObjStatisticsQuery query, int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public List<MyCollectionObjStatistics> listAll() {

        throw new NotImplementedException();
    }

    @Override
    public MyCollectionObjStatistics get(long objId, int type) {

        throw new NotImplementedException();
    }
}
