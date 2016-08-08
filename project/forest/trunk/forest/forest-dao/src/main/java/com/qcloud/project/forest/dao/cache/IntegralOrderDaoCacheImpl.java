package com.qcloud.project.forest.dao.cache;

import java.util.Map;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.project.forest.dao.IntegralOrderDao;
import com.qcloud.project.forest.model.IntegralOrder;
import com.qcloud.project.forest.model.query.IntegralOrderQuery;

@Repository
public class IntegralOrderDaoCacheImpl implements IntegralOrderDao {

    @Autowired
    private IntegralOrderDao integralOrderDaoMysqlImpl;

    @Autowired
    private IntegralOrderDao integralOrderDaoRedisImpl;

    @Override
    public boolean add(IntegralOrder integralOrder) {

        return integralOrderDaoMysqlImpl.add(integralOrder);
    }

    @Override
    public IntegralOrder get(Long id) {

        return integralOrderDaoMysqlImpl.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return integralOrderDaoMysqlImpl.delete(id);
    }

    @Override
    public boolean update(IntegralOrder integralOrder) {

        return integralOrderDaoMysqlImpl.update(integralOrder);
    }

    @Override
    public List<IntegralOrder> list(List<Long> idList) {

        return CacheLoader.list(integralOrderDaoRedisImpl, integralOrderDaoMysqlImpl, idList);
    }

    @Override
    public Map<Long, IntegralOrder> map(Set<Long> idSet) {

        return CacheLoader.map(integralOrderDaoRedisImpl, integralOrderDaoMysqlImpl, idSet);
    }

    @Override
    public Page<IntegralOrder> page(int start, int count) {

        return integralOrderDaoMysqlImpl.page(start, count);
    }

    @Override
    public Page<IntegralOrder> page(IntegralOrderQuery query, int start, int count) {

        return integralOrderDaoMysqlImpl.page(query, start, count);
    }

    public List<IntegralOrder> listAll() {

        return integralOrderDaoMysqlImpl.listAll();
    }

    @Override
    public List<IntegralOrder> listByUser(long userId,int state, int type, int start, int size) {

        return integralOrderDaoMysqlImpl.listByUser(userId,  state,type, start, size);
    }

    @Override
    public int countByUser(long userId, int state,int type) {

        return integralOrderDaoMysqlImpl.countByUser(userId, state, type);
    }

    @Override
    public IntegralOrder getByOrder(long orderId) {

        return integralOrderDaoMysqlImpl.getByOrder(orderId);
    }
}
