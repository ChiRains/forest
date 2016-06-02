package com.qcloud.component.my.dao.cache;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.qcloud.component.my.dao.MyCollectionObjStatisticsDao;
import com.qcloud.component.my.model.MyCollectionObjStatistics;
import com.qcloud.component.my.model.query.MyCollectionObjStatisticsQuery;
import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;

@Repository
public class MyCollectionObjStatisticsDaoCacheImpl implements MyCollectionObjStatisticsDao {

    @Autowired
    private MyCollectionObjStatisticsDao myCollectionObjStatisticsDaoMysqlImpl;

    @Autowired
    private MyCollectionObjStatisticsDao myCollectionObjStatisticsDaoRedisImpl;

    @Override
    public boolean add(MyCollectionObjStatistics myCollectionObjStatistics) {

        return myCollectionObjStatisticsDaoMysqlImpl.add(myCollectionObjStatistics);
    }

    @Override
    public MyCollectionObjStatistics get(Long id) {

        return CacheLoader.get(myCollectionObjStatisticsDaoRedisImpl, myCollectionObjStatisticsDaoMysqlImpl, id);
    }

    @Override
    public boolean delete(Long id) {

        return myCollectionObjStatisticsDaoMysqlImpl.delete(id);
    }

    @Override
    public boolean update(MyCollectionObjStatistics myCollectionObjStatistics) {

        return myCollectionObjStatisticsDaoMysqlImpl.update(myCollectionObjStatistics);
    }

    @Override
    public List<MyCollectionObjStatistics> list(List<Long> idList) {

        return CacheLoader.list(myCollectionObjStatisticsDaoRedisImpl, myCollectionObjStatisticsDaoMysqlImpl, idList);
    }

    @Override
    public Map<Long, MyCollectionObjStatistics> map(Set<Long> idSet) {

        return CacheLoader.map(myCollectionObjStatisticsDaoRedisImpl, myCollectionObjStatisticsDaoMysqlImpl, idSet);
    }

    @Override
    public Page<MyCollectionObjStatistics> page(int start, int count) {

        return myCollectionObjStatisticsDaoMysqlImpl.page(start, count);
    }

    @Override
    public Page<MyCollectionObjStatistics> page(MyCollectionObjStatisticsQuery query, int start, int count) {

        return myCollectionObjStatisticsDaoMysqlImpl.page(query, start, count);
    }

    public List<MyCollectionObjStatistics> listAll() {

        return myCollectionObjStatisticsDaoMysqlImpl.listAll();
    }

    @Override
    public MyCollectionObjStatistics get(long objId, int type) {

        return myCollectionObjStatisticsDaoMysqlImpl.get(objId, type);
    }
}
