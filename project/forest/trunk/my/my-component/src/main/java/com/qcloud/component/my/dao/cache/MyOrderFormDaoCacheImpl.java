package com.qcloud.component.my.dao.cache;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.qcloud.component.my.dao.MyOrderFormDao;
import com.qcloud.component.my.model.MyOrderForm;
import com.qcloud.component.my.model.query.MyOrderFormQuery;
import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;

@Repository
public class MyOrderFormDaoCacheImpl implements MyOrderFormDao {

    @Autowired
    private MyOrderFormDao myOrderFormDaoMysqlImpl;

    // @Autowired
    // private MyOrderFormDao myOrderFormDaoRedisImpl;
    @Override
    public boolean add(MyOrderForm myOrderForm) {

        return myOrderFormDaoMysqlImpl.add(myOrderForm);
    }

    @Override
    public MyOrderForm get(Long id) {

        return myOrderFormDaoMysqlImpl.get(id);
        // return CacheLoader.get(myOrderFormDaoRedisImpl, myOrderFormDaoMysqlImpl, id);
    }

    @Override
    public boolean delete(Long id) {

        return myOrderFormDaoMysqlImpl.delete(id);
    }

    @Override
    public boolean update(MyOrderForm myOrderForm) {

        return myOrderFormDaoMysqlImpl.update(myOrderForm);
    }

    @Override
    public List<MyOrderForm> list(List<Long> idList) {

        return CacheLoader.list(myOrderFormDaoMysqlImpl, idList);
    }

    @Override
    public Map<Long, MyOrderForm> map(Set<Long> idSet) {

        return CacheLoader.map(myOrderFormDaoMysqlImpl, idSet);
    }

    @Override
    public Page<MyOrderForm> page(int start, int count) {

        return myOrderFormDaoMysqlImpl.page(start, count);
    }

    @Override
    public Page<MyOrderForm> page(MyOrderFormQuery query, int start, int count) {

        return myOrderFormDaoMysqlImpl.page(query, start, count);
    }

    public List<MyOrderForm> listAll() {

        return myOrderFormDaoMysqlImpl.listAll();
    }

    @Override
    public List<MyOrderForm> list(MyOrderFormQuery query, long userId, int start, int count) {

        return myOrderFormDaoMysqlImpl.list(query, userId, start, count);
    }

    @Override
    public MyOrderForm getByOrder(long userId, long orderId, long subOrderId) {

        return myOrderFormDaoMysqlImpl.getByOrder(userId, orderId, subOrderId);
    }

    @Override
    public List<MyOrderForm> listByOrder(long userId, long orderId) {

        return myOrderFormDaoMysqlImpl.listByOrder(userId, orderId);
    }

    @Override
    public int statMyOrder(long userId, int state) {
        
        return myOrderFormDaoMysqlImpl.statMyOrder(userId, state);
    }

    @Override
    public int statMyMerchantOrder(long userId, int state) {

        return myOrderFormDaoMysqlImpl.statMyMerchantOrder(userId, state);
    }

    @Override
    public int count(MyOrderFormQuery query, long userId) {
        
        return myOrderFormDaoMysqlImpl.count(query, userId);
    }
}
