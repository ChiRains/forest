package com.qcloud.component.orderform.dao.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.pirates.core.json.Json;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.redis.Redis;
import com.qcloud.component.orderform.dao.RefundOrderDao;
import com.qcloud.component.orderform.model.RefundOrder;
import com.qcloud.component.orderform.model.query.RefundOrderQuery;

@Repository
public class RefundOrderDaoRedisImpl implements RefundOrderDao {

    // @Resource(name = "redis-orderform")
    // private Redis redis;
    @Override
    public boolean add(RefundOrder refundOrder) {

        throw new NotImplementedException();
    }

    @Override
    public RefundOrder get(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean delete(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean update(RefundOrder refundOrder) {

        throw new NotImplementedException();
    }

    @Override
    public List<RefundOrder> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, RefundOrder> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<RefundOrder> page(int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public Page<RefundOrder> page(RefundOrderQuery query, int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public List<RefundOrder> listAll() {

        throw new NotImplementedException();
    }

    @Override
    public List<RefundOrder> listBySubOrder(Long subOrderId) {

        throw new NotImplementedException();
    }

    @Override
    public List<RefundOrder> list4ExpireState(int state, int start, int size) {

        throw new NotImplementedException();
    }
}
