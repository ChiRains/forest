package com.qcloud.component.personalcenter.dao.cache;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.qcloud.component.personalcenter.dao.MyWealthDao;
import com.qcloud.component.personalcenter.model.MyWealth;
import com.qcloud.component.personalcenter.model.query.MyWealthQuery;
import com.qcloud.pirates.data.Page;

@Repository
public class MyWealthDaoCacheImpl implements MyWealthDao {

    @Autowired
    private MyWealthDao myWealthDaoMysqlImpl;

//    @Autowired
//    private MyWealthDao myWealthDaoRedisImpl;

    @Override
    public boolean add(MyWealth myWealth) {

        return myWealthDaoMysqlImpl.add(myWealth);
    }

    @Override
    public MyWealth get(Long id) {

        return myWealthDaoMysqlImpl.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return myWealthDaoMysqlImpl.delete(id);
    }

    @Override
    public boolean update(MyWealth myWealth) {

        return myWealthDaoMysqlImpl.update(myWealth);
    }

    @Override
    public List<MyWealth> list(List<Long> idList) {

        return myWealthDaoMysqlImpl.list(idList);
    }

    @Override
    public Map<Long, MyWealth> map(Set<Long> idSet) {

        return myWealthDaoMysqlImpl.map(idSet);
    }

    @Override
    public Page<MyWealth> page(int start, int count) {

        return myWealthDaoMysqlImpl.page(start, count);
    }

    @Override
    public Page<MyWealth> page(MyWealthQuery query, int start, int count) {

        return myWealthDaoMysqlImpl.page(query, start, count);
    }

    public List<MyWealth> listAll() {

        return myWealthDaoMysqlImpl.listAll();
    }

    @Override
    public boolean updateLock(MyWealth myWealth) {

        boolean result = myWealthDaoMysqlImpl.updateLock(myWealth);
//        if (result) {
//            myWealthDaoRedisImpl.deleteByUser(myWealth.getUserId());
//        }
        return result;
    }

    @Override
    public MyWealth getByUserId(long userId) {

//        MyWealth wealth = myWealthDaoRedisImpl.getByUserId(userId);
//        if (wealth == null) {
        MyWealth  wealth = myWealthDaoMysqlImpl.getByUserId(userId);
//            if (wealth != null) {
//                myWealthDaoRedisImpl.add(wealth);
//            }
//        }
        return wealth;
    }

    @Override
    public boolean deleteByUser(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public List<MyWealth> listTop(int number, int type) {
        return myWealthDaoMysqlImpl.listTop(number, type);
    }
}
