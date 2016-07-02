package com.qcloud.component.orderform.dao.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.component.orderform.dao.ReturnOrderItemDao;
import com.qcloud.component.orderform.model.ReturnOrderItem;
import com.qcloud.component.orderform.model.query.ReturnOrderItemQuery;
import com.qcloud.pirates.data.Page;

@Repository
public class ReturnOrderItemDaoRedisImpl implements ReturnOrderItemDao {

    // @Resource(name = "redis-orderform")
    // private Redis redis;
    @Override
    public boolean add(ReturnOrderItem returnOrderItem) {

        throw new NotImplementedException();
    }

    @Override
    public ReturnOrderItem get(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean delete(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean update(ReturnOrderItem returnOrderItem) {

        throw new NotImplementedException();
    }

    @Override
    public List<ReturnOrderItem> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, ReturnOrderItem> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<ReturnOrderItem> page(int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public Page<ReturnOrderItem> page(ReturnOrderItemQuery query, int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public List<ReturnOrderItem> listAll() {

        throw new NotImplementedException();
    }

    @Override
    public List<ReturnOrderItem> listByReturn(Long returnId) {

        throw new NotImplementedException();
    }
}
