package com.qcloud.component.orderform.dao.redis;

import java.util.Date;
import java.util.List;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.component.orderform.dao.SubOrderDao;
import com.qcloud.component.orderform.model.SubOrder;
import com.qcloud.component.orderform.model.query.SubOrderQuery;
import com.qcloud.pirates.data.Page;

@Repository
public class SubOrderDaoRedisImpl implements SubOrderDao {

    // @Resource(name = "redis-orderform")
    // private Redis redis;
    @Override
    public boolean add(SubOrder subOrder, Date time) {

        throw new NotImplementedException();
    }

    @Override
    public SubOrder get(Long id, Date time) {

        throw new NotImplementedException();
    }

    @Override
    public boolean update(SubOrder subOrder, Date time) {

        throw new NotImplementedException();
    }

    @Override
    public boolean delete(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public Page<SubOrder> page(SubOrderQuery query, int start, int size) {

        throw new NotImplementedException();
    }

    @Override
    public List<SubOrder> listByCollectOrder(Long collectOrderId, Date time) {

        throw new NotImplementedException();
    }

    @Override
    public SubOrder get(String orderNumber) {

        throw new NotImplementedException();
    }

    @Override
    public List<SubOrder> listByMerchantAndDay(Long merchantId, Date time) {
        throw new NotImplementedException();
    }
}
