package com.qcloud.component.my.dao.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.component.my.dao.MyOrderFormDao;
import com.qcloud.component.my.model.MyOrderForm;
import com.qcloud.component.my.model.query.MyOrderFormQuery;
import com.qcloud.pirates.data.Page;

@Repository
public class MyOrderFormDaoRedisImpl implements MyOrderFormDao {

    // @Resource(name = "redis-my")
    // private Redis redis;

    @Override
    public boolean add(MyOrderForm myOrderForm) {

        throw new NotImplementedException();
    }

    @Override
    public MyOrderForm get(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean delete(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean update(MyOrderForm myOrderForm) {

        throw new NotImplementedException();
    }

    @Override
    public List<MyOrderForm> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, MyOrderForm> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<MyOrderForm> page(int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public Page<MyOrderForm> page(MyOrderFormQuery query, int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public List<MyOrderForm> listAll() {

        throw new NotImplementedException();
    }

    @Override
    public List<MyOrderForm> list(MyOrderFormQuery query, long userId, int start, int count) {

        throw new NotImplementedException();
    }
 
    @Override
    public MyOrderForm getByOrder(long userId, long orderId, long subOrderId) {

        throw new NotImplementedException();
    }

    @Override
    public List<MyOrderForm> listByOrder(long userId, long orderId) {

        throw new NotImplementedException();
    }

    @Override
    public int statMyOrder(long userId, int state) {

        throw new NotImplementedException();
    }

    @Override
    public int statMyMerchantOrder(long userId, int state) {

        throw new NotImplementedException();
    }

    @Override
    public int count(MyOrderFormQuery query, long userId) {

        throw new NotImplementedException();
    }
}
