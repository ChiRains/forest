package com.qcloud.project.forest.dao.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.pirates.data.Page;
import com.qcloud.project.forest.dao.IntegralOrderDao;
import com.qcloud.project.forest.model.IntegralOrder;
import com.qcloud.project.forest.model.query.IntegralOrderQuery;

@Repository
public class IntegralOrderDaoRedisImpl implements IntegralOrderDao {

    // @Resource(name = "redis-forest")
    // private Redis redis;
    @Override
    public boolean add(IntegralOrder integralOrder) {

        throw new NotImplementedException();
    }

    @Override
    public IntegralOrder get(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean delete(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean update(IntegralOrder integralOrder) {

        throw new NotImplementedException();
    }

    @Override
    public List<IntegralOrder> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, IntegralOrder> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<IntegralOrder> page(int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public Page<IntegralOrder> page(IntegralOrderQuery query, int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public List<IntegralOrder> listAll() {

        throw new NotImplementedException();
    }

    @Override
    public List<IntegralOrder> listByUser(long userId, int state, int type, int start, int size) {

        throw new NotImplementedException();
    }

    @Override
    public int countByUser(long userId, int state, int type) {

        throw new NotImplementedException();
    }

    @Override
    public IntegralOrder getByOrder(long orderId) {

        throw new NotImplementedException();
    }
}
