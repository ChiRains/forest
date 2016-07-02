package com.qcloud.component.orderform.dao.cache;

import java.util.Map;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.orderform.dao.RefundOrderItemDao;
import com.qcloud.component.orderform.model.RefundOrderItem;
import com.qcloud.component.orderform.model.query.RefundOrderItemQuery;

@Repository
public class RefundOrderItemDaoCacheImpl implements RefundOrderItemDao {

    @Autowired
    private RefundOrderItemDao refundOrderItemDaoMysqlImpl;

    @Autowired
    private RefundOrderItemDao refundOrderItemDaoRedisImpl;

    @Override
    public boolean add(RefundOrderItem refundOrderItem) {

        return refundOrderItemDaoMysqlImpl.add(refundOrderItem);
    }

    @Override
    public RefundOrderItem get(Long id) {

        return refundOrderItemDaoMysqlImpl.get( id);
    }

    @Override
    public boolean delete(Long id) {

        return refundOrderItemDaoMysqlImpl.delete(id);
    }

    @Override
    public boolean update(RefundOrderItem refundOrderItem) {

        return refundOrderItemDaoMysqlImpl.update(refundOrderItem);
    }

    @Override
    public List<RefundOrderItem> list(List<Long> idList) {

        return CacheLoader.list(refundOrderItemDaoRedisImpl, refundOrderItemDaoMysqlImpl, idList);
    }

    @Override
    public Map<Long, RefundOrderItem> map(Set<Long> idSet) {

        return CacheLoader.map(refundOrderItemDaoRedisImpl, refundOrderItemDaoMysqlImpl, idSet);
    }

    @Override
    public Page<RefundOrderItem> page(int start, int count) {

        return refundOrderItemDaoMysqlImpl.page(start, count);
    }

    @Override
    public Page<RefundOrderItem> page(RefundOrderItemQuery query, int start, int count) {

        return refundOrderItemDaoMysqlImpl.page(query, start, count);
    }

    public List<RefundOrderItem> listAll() {

        return refundOrderItemDaoMysqlImpl.listAll();
    }

    @Override
    public List<RefundOrderItem> listByRefund(Long refundId) {

        return refundOrderItemDaoMysqlImpl.listByRefund(refundId);
    }
}
