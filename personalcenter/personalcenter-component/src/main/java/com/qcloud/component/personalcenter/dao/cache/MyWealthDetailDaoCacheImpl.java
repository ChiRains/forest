package com.qcloud.component.personalcenter.dao.cache;

import java.util.Date;
import java.util.Map;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.personalcenter.dao.MyWealthDetailDao;
import com.qcloud.component.personalcenter.model.MyWealthDetail;
import com.qcloud.component.personalcenter.model.query.MyWealthDetailQuery;

@Repository
public class MyWealthDetailDaoCacheImpl implements MyWealthDetailDao {

    @Autowired
    private MyWealthDetailDao myWealthDetailDaoMysqlImpl;

    // @Autowired
    // private MyWealthDetailDao myWealthDetailDaoRedisImpl;
    @Override
    public boolean add(MyWealthDetail myWealthDetail) {

        return myWealthDetailDaoMysqlImpl.add(myWealthDetail);
    }

    @Override
    public MyWealthDetail get(Long id) {

        return myWealthDetailDaoMysqlImpl.get(id);
        // return CacheLoader.get(myWealthDetailDaoRedisImpl, myWealthDetailDaoMysqlImpl, id);
    }

    @Override
    public boolean delete(Long id) {

        return myWealthDetailDaoMysqlImpl.delete(id);
    }

    @Override
    public boolean update(MyWealthDetail myWealthDetail) {

        return myWealthDetailDaoMysqlImpl.update(myWealthDetail);
    }

    @Override
    public List<MyWealthDetail> list(List<Long> idList) {

        return CacheLoader.list(myWealthDetailDaoMysqlImpl, idList);
    }

    @Override
    public Map<Long, MyWealthDetail> map(Set<Long> idSet) {

        return CacheLoader.map(myWealthDetailDaoMysqlImpl, idSet);
    }

    @Override
    public Page<MyWealthDetail> page(int start, int count) {

        return myWealthDetailDaoMysqlImpl.page(start, count);
    }

    @Override
    public Page<MyWealthDetail> page(MyWealthDetailQuery query, int start, int count) {

        return myWealthDetailDaoMysqlImpl.page(query, start, count);
    }

    public List<MyWealthDetail> listAll() {

        return myWealthDetailDaoMysqlImpl.listAll();
    }

    @Override
    public Page<MyWealthDetail> getWealthDetails(Long wealthId, Long userId, int type, int start, int size) {

        return myWealthDetailDaoMysqlImpl.getWealthDetails(wealthId, userId, type, start, size);
    }

    @Override
    public List<MyWealthDetail> listByUser(Long wealthId, Long userId, Integer type, Integer detailType, int start, int size) {

        return myWealthDetailDaoMysqlImpl.listByUser(wealthId, userId, type, detailType, start, size);
    }

    @Override
    public List<MyWealthDetail> listByTime(Long wealthId, Long userId, Integer type, Date begin, Date end) {

        return myWealthDetailDaoMysqlImpl.listByTime(wealthId, userId, type, begin, end);
    }

    @Override
    public List<MyWealthDetail> listByUserAndTime(Long wealthId, Long userId, Integer type, Integer detailType, Date begin, Date end, int start, int size) {

        return myWealthDetailDaoMysqlImpl.listByUserAndTime(wealthId, userId, type, detailType, begin, end, start, size);
    }

    @Override
    public double sumByUserAndTime(long wealthId, long userId, int type, Integer detailType, Date begin, Date end, int start, int size) {

        return myWealthDetailDaoMysqlImpl.sumByUserAndTime(wealthId, userId, type, detailType, begin, end, start, size);
    }

    @Override
    public MyWealthDetail getByIdandUserId(Long id, Long userId) {

        return myWealthDetailDaoMysqlImpl.getByIdandUserId(id, userId);
    }
}
