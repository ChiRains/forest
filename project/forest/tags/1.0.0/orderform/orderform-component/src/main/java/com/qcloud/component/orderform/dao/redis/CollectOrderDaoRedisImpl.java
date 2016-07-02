package com.qcloud.component.orderform.dao.redis;

import java.util.Date;
import java.util.List;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.component.orderform.dao.CollectOrderDao;
import com.qcloud.component.orderform.model.CollectOrder;
import com.qcloud.component.orderform.model.query.CollectOrderQuery;
import com.qcloud.pirates.data.Page;

@Repository
public class CollectOrderDaoRedisImpl implements CollectOrderDao {

    // @Resource(name = "redis-orderform")
    // private Redis redis;
    @Override
    public boolean add(CollectOrder collectOrder) {

        throw new NotImplementedException();
    }

    @Override
    public CollectOrder get(Long id, Date time) {

        throw new NotImplementedException();
    }

    @Override
    public boolean update(CollectOrder collectOrder) {

        throw new NotImplementedException();
    }

    @Override
    public boolean delete(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public Page<CollectOrder> page(CollectOrderQuery query, int start, int size) {

        throw new NotImplementedException();
    }

    @Override
    public Date[] getDatesByLatelyMinutes(int latelyMinutes) {

        throw new NotImplementedException();
    }

    @Override
    public CollectOrder get(String orderNumber) {

        throw new NotImplementedException();
    }

    @Override
    public List<CollectOrder> list4ExpireState(Date tableDate, int state, int start, int size) {

        throw new NotImplementedException();
    }
}
