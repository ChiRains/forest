package com.qcloud.component.my.dao.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.component.my.dao.MyAfterSaleDao;
import com.qcloud.component.my.model.MyAfterSale;
import com.qcloud.component.my.model.query.MyAfterSaleQuery;
import com.qcloud.pirates.data.Page;

@Repository
public class MyAfterSaleDaoRedisImpl implements MyAfterSaleDao {

    // @Resource(name = "redis-my")
    // private Redis redis;
    @Override
    public boolean add(MyAfterSale myAfterSale) {

        throw new NotImplementedException();
    }

    @Override
    public MyAfterSale get(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean delete(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean update(MyAfterSale myAfterSale) {

        throw new NotImplementedException();
    }

    @Override
    public List<MyAfterSale> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, MyAfterSale> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<MyAfterSale> page(int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public Page<MyAfterSale> page(MyAfterSaleQuery query, int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public List<MyAfterSale> listAll() {

        throw new NotImplementedException();
    }

    @Override
    public List<MyAfterSale> listByUser(long userId, int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public List<MyAfterSale> listByUserAndSubOrder(long userId, long subOrderId) {

        throw new NotImplementedException();
    }

    @Override
    public List<MyAfterSale> listByUserAndOrder(long userId, long orderId) {

        throw new NotImplementedException();
    }

    @Override
    public int stat(long userId) {

        throw new NotImplementedException();
    }

    @Override
    public int countByUser(long userId) {

        throw new NotImplementedException();
    }
}
