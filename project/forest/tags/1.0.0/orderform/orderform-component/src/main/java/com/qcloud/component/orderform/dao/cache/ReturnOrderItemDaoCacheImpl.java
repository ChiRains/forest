package com.qcloud.component.orderform.dao.cache;

import java.util.Map;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.orderform.dao.ReturnOrderItemDao;
import com.qcloud.component.orderform.model.ReturnOrderItem;
import com.qcloud.component.orderform.model.query.ReturnOrderItemQuery;

@Repository
public class ReturnOrderItemDaoCacheImpl implements ReturnOrderItemDao {

    @Autowired
    private ReturnOrderItemDao returnOrderItemDaoMysqlImpl;

    @Autowired
    private ReturnOrderItemDao returnOrderItemDaoRedisImpl;

    @Override
    public boolean add(ReturnOrderItem returnOrderItem) {

        return returnOrderItemDaoMysqlImpl.add(returnOrderItem);
    }

    @Override
    public ReturnOrderItem get(Long id) {

        return CacheLoader.get(returnOrderItemDaoRedisImpl, returnOrderItemDaoMysqlImpl, id);
    }

    @Override
    public boolean delete(Long id) {

        return returnOrderItemDaoMysqlImpl.delete(id);
    }

    @Override
    public boolean update(ReturnOrderItem returnOrderItem) {

        return returnOrderItemDaoMysqlImpl.update(returnOrderItem);
    }

    @Override
    public List<ReturnOrderItem> list(List<Long> idList) {

        return CacheLoader.list(returnOrderItemDaoRedisImpl, returnOrderItemDaoMysqlImpl, idList);
    }

    @Override
    public Map<Long, ReturnOrderItem> map(Set<Long> idSet) {

        return CacheLoader.map(returnOrderItemDaoRedisImpl, returnOrderItemDaoMysqlImpl, idSet);
    }

    @Override
    public Page<ReturnOrderItem> page(int start, int count) {

        return returnOrderItemDaoMysqlImpl.page(start, count);
    }

    @Override
    public Page<ReturnOrderItem> page(ReturnOrderItemQuery query, int start, int count) {

        return returnOrderItemDaoMysqlImpl.page(query, start, count);
    }

    public List<ReturnOrderItem> listAll() {

        return returnOrderItemDaoMysqlImpl.listAll();
    }

    @Override
    public List<ReturnOrderItem> listByReturn(Long returnId) {

        return returnOrderItemDaoMysqlImpl.listByReturn(returnId);
    }
}
