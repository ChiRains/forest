package com.qcloud.component.my.dao.cache;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.qcloud.component.my.dao.MyAfterSaleDao;
import com.qcloud.component.my.model.MyAfterSale;
import com.qcloud.component.my.model.query.MyAfterSaleQuery;
import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;

@Repository
public class MyAfterSaleDaoCacheImpl implements MyAfterSaleDao {

    @Autowired
    private MyAfterSaleDao myAfterSaleDaoMysqlImpl;

    // @Autowired
    // private MyAfterSaleDao myAfterSaleDaoRedisImpl;
    @Override
    public boolean add(MyAfterSale myAfterSale) {

        return myAfterSaleDaoMysqlImpl.add(myAfterSale);
    }

    @Override
    public MyAfterSale get(Long id) {

        return myAfterSaleDaoMysqlImpl.get(id);
        // return CacheLoader.get(myAfterSaleDaoRedisImpl, myAfterSaleDaoMysqlImpl, id);
    }

    @Override
    public boolean delete(Long id) {

        return myAfterSaleDaoMysqlImpl.delete(id);
    }

    @Override
    public boolean update(MyAfterSale myAfterSale) {

        return myAfterSaleDaoMysqlImpl.update(myAfterSale);
    }

    @Override
    public List<MyAfterSale> list(List<Long> idList) {

        return CacheLoader.list(myAfterSaleDaoMysqlImpl, idList);
    }

    @Override
    public Map<Long, MyAfterSale> map(Set<Long> idSet) {

        return CacheLoader.map(myAfterSaleDaoMysqlImpl, idSet);
    }

    @Override
    public Page<MyAfterSale> page(int start, int count) {

        return myAfterSaleDaoMysqlImpl.page(start, count);
    }

    @Override
    public Page<MyAfterSale> page(MyAfterSaleQuery query, int start, int count) {

        return myAfterSaleDaoMysqlImpl.page(query, start, count);
    }

    public List<MyAfterSale> listAll() {

        return myAfterSaleDaoMysqlImpl.listAll();
    }

    @Override
    public List<MyAfterSale> listByUser(long userId, int start, int count) {

        return myAfterSaleDaoMysqlImpl.listByUser(userId, start, count);
    }

    @Override
    public List<MyAfterSale> listByUserAndSubOrder(long userId, long subOrderId) {

        return myAfterSaleDaoMysqlImpl.listByUserAndSubOrder(userId, subOrderId);
    }

    @Override
    public List<MyAfterSale> listByUserAndOrder(long userId, long orderId) {

        return myAfterSaleDaoMysqlImpl.listByUserAndOrder(userId, orderId);
    }

    @Override
    public int stat(long userId) {

        return myAfterSaleDaoMysqlImpl.stat(userId);
    }

    @Override
    public int countByUser(long userId) {

        return myAfterSaleDaoMysqlImpl.countByUser(userId);
    }
}
