package com.qcloud.component.my.dao.cache;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.qcloud.component.my.dao.MyCollectionStatisticsDao;
import com.qcloud.component.my.model.MyCollectionStatistics;
import com.qcloud.component.my.model.query.MyCollectionStatisticsQuery;
import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;

@Repository
public class MyCollectionStatisticsDaoCacheImpl implements MyCollectionStatisticsDao {

    @Autowired
    private MyCollectionStatisticsDao myCollectionStatisticsDaoMysqlImpl;

    // @Autowired
    // private MyCollectionStatisticsDao myCollectionStatisticsDaoRedisImpl;
    @Override
    public boolean add(MyCollectionStatistics myCollectionStatistics) {

        return myCollectionStatisticsDaoMysqlImpl.add(myCollectionStatistics);
    }

    @Override
    public MyCollectionStatistics get(Long id) {

        return myCollectionStatisticsDaoMysqlImpl.get(id);
        // return CacheLoader.get(myCollectionStatisticsDaoRedisImpl, myCollectionStatisticsDaoMysqlImpl, id);
    }

    @Override
    public boolean delete(Long id) {

        return myCollectionStatisticsDaoMysqlImpl.delete(id);
    }

    @Override
    public boolean update(MyCollectionStatistics myCollectionStatistics) {

        return myCollectionStatisticsDaoMysqlImpl.update(myCollectionStatistics);
    }

    @Override
    public List<MyCollectionStatistics> list(List<Long> idList) {

        return CacheLoader.list(myCollectionStatisticsDaoMysqlImpl, idList);
    }

    @Override
    public Map<Long, MyCollectionStatistics> map(Set<Long> idSet) {

        return CacheLoader.map(myCollectionStatisticsDaoMysqlImpl, idSet);
    }

    @Override
    public Page<MyCollectionStatistics> page(int start, int count) {

        return myCollectionStatisticsDaoMysqlImpl.page(start, count);
    }

    @Override
    public Page<MyCollectionStatistics> page(MyCollectionStatisticsQuery query, int start, int count) {

        return myCollectionStatisticsDaoMysqlImpl.page(query, start, count);
    }

    public List<MyCollectionStatistics> listAll() {

        return myCollectionStatisticsDaoMysqlImpl.listAll();
    }

    @Override
    public MyCollectionStatistics get(Long userId, long classifyId, int type) {

        return myCollectionStatisticsDaoMysqlImpl.get(userId, classifyId, type);
    }

    @Override
    public List<MyCollectionStatistics> listByUserAndType(long userId, int type) {

        return myCollectionStatisticsDaoMysqlImpl.listByUserAndType(userId, type);
    }
}
