package com.qcloud.component.personalcenter.dao.cache;

import java.util.Date;
import java.util.Map;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.personalcenter.dao.MySignInDayDao;
import com.qcloud.component.personalcenter.model.MySignInDay;
import com.qcloud.component.personalcenter.model.query.MySignInDayQuery;

@Repository
public class MySignInDayDaoCacheImpl implements MySignInDayDao {

    @Autowired
    private MySignInDayDao mySignInDayDaoMysqlImpl;

    // @Autowired
    // private MySignInDayDao mySignInDayDaoRedisImpl;
    @Override
    public boolean add(MySignInDay mySignInDay) {

        return mySignInDayDaoMysqlImpl.add(mySignInDay);
    }

    @Override
    public MySignInDay get(Long id) {

        return mySignInDayDaoMysqlImpl.get(id);
        // return CacheLoader.get(mySignInDayDaoRedisImpl, mySignInDayDaoMysqlImpl, id);
    }

    @Override
    public boolean delete(Long id) {

        return mySignInDayDaoMysqlImpl.delete(id);
    }

    @Override
    public boolean update(MySignInDay mySignInDay) {

        return mySignInDayDaoMysqlImpl.update(mySignInDay);
    }

    @Override
    public List<MySignInDay> list(List<Long> idList) {

        return CacheLoader.list(mySignInDayDaoMysqlImpl, idList);
    }

    @Override
    public Map<Long, MySignInDay> map(Set<Long> idSet) {

        return CacheLoader.map(mySignInDayDaoMysqlImpl, idSet);
    }

    @Override
    public Page<MySignInDay> page(int start, int count) {

        return mySignInDayDaoMysqlImpl.page(start, count);
    }

    @Override
    public Page<MySignInDay> page(MySignInDayQuery query, int start, int count) {

        return mySignInDayDaoMysqlImpl.page(query, start, count);
    }

    public List<MySignInDay> listAll() {

        return mySignInDayDaoMysqlImpl.listAll();
    }

    @Override
    public MySignInDay getByDate(long userId, Date date) {

        return mySignInDayDaoMysqlImpl.getByDate(userId, date);
    }

    @Override
    public List<MySignInDay> listByYearAndMonth(long userId, int year, int month) {

        return mySignInDayDaoMysqlImpl.listByYearAndMonth(userId, year, month);
    }
}
