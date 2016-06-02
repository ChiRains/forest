package com.qcloud.component.personalcenter.dao.cache;

import java.util.Map;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.personalcenter.dao.MySignInMonthDao;
import com.qcloud.component.personalcenter.model.MySignInMonth;
import com.qcloud.component.personalcenter.model.query.MySignInMonthQuery;

@Repository
public class MySignInMonthDaoCacheImpl implements MySignInMonthDao {

    @Autowired
    private MySignInMonthDao mySignInMonthDaoMysqlImpl;

    // @Autowired
    // private MySignInMonthDao mySignInMonthDaoRedisImpl;
    @Override
    public boolean add(MySignInMonth mySignInMonth) {

        return mySignInMonthDaoMysqlImpl.add(mySignInMonth);
    }

    @Override
    public MySignInMonth get(Long id) {

        return mySignInMonthDaoMysqlImpl.get(id);
        // return CacheLoader.get(mySignInMonthDaoRedisImpl, mySignInMonthDaoMysqlImpl, id);
    }

    @Override
    public boolean delete(Long id) {

        return mySignInMonthDaoMysqlImpl.delete(id);
    }

    @Override
    public boolean update(MySignInMonth mySignInMonth) {

        return mySignInMonthDaoMysqlImpl.update(mySignInMonth);
    }

    @Override
    public List<MySignInMonth> list(List<Long> idList) {

        return CacheLoader.list(mySignInMonthDaoMysqlImpl, idList);
    }

    @Override
    public Map<Long, MySignInMonth> map(Set<Long> idSet) {

        return CacheLoader.map(mySignInMonthDaoMysqlImpl, idSet);
    }

    @Override
    public Page<MySignInMonth> page(int start, int count) {

        return mySignInMonthDaoMysqlImpl.page(start, count);
    }

    @Override
    public Page<MySignInMonth> page(MySignInMonthQuery query, int start, int count) {

        return mySignInMonthDaoMysqlImpl.page(query, start, count);
    }

    public List<MySignInMonth> listAll() {

        return mySignInMonthDaoMysqlImpl.listAll();
    }

    @Override
    public MySignInMonth getByYearAndMonth(long userId, int year, int month) {

        return mySignInMonthDaoMysqlImpl.getByYearAndMonth(userId, year, month);
    }
}
