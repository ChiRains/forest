package com.qcloud.component.orderform.dao.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.component.orderform.dao.RefundOrderItemDao;
import com.qcloud.component.orderform.model.RefundOrderItem;
import com.qcloud.component.orderform.model.query.RefundOrderItemQuery;
import com.qcloud.pirates.data.Page;

@Repository
public class RefundOrderItemDaoRedisImpl implements RefundOrderItemDao {

    // @Resource(name = "redis-orderform")
    // private Redis redis;
    @Override
    public boolean add(RefundOrderItem refundOrderItem) {

        throw new NotImplementedException();
    }

    @Override
    public RefundOrderItem get(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean delete(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean update(RefundOrderItem refundOrderItem) {

        throw new NotImplementedException();
    }

    @Override
    public List<RefundOrderItem> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, RefundOrderItem> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<RefundOrderItem> page(int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public Page<RefundOrderItem> page(RefundOrderItemQuery query, int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public List<RefundOrderItem> listAll() {

        throw new NotImplementedException();
    }

    @Override
    public List<RefundOrderItem> listByRefund(Long refundId) {

        throw new NotImplementedException();
    }
}
