package com.qcloud.component.orderform.dao.cache;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.qcloud.component.orderform.dao.CollectOrderDao;
import com.qcloud.component.orderform.model.CollectOrder;
import com.qcloud.component.orderform.model.query.CollectOrderQuery;
import com.qcloud.pirates.data.Page;

@Repository
public class CollectOrderDaoCacheImpl implements CollectOrderDao {

    @Autowired
    private CollectOrderDao collectOrderDaoMysqlImpl;

    // @Autowired
    // private CollectOrderDao collectOrderDaoRedisImpl;
    @Override
    public boolean add(CollectOrder collectOrder) {

        return collectOrderDaoMysqlImpl.add(collectOrder);
    }

    @Override
    public boolean update(CollectOrder collectOrder) {

        return collectOrderDaoMysqlImpl.update(collectOrder);
    }

    @Override
    public CollectOrder get(Long id, Date time) {

        return collectOrderDaoMysqlImpl.get(id, time);
    }

    @Override
    public boolean delete(Long id) {

        return collectOrderDaoMysqlImpl.delete(id);
    }

    @Override
    public Page<CollectOrder> page(CollectOrderQuery query, int start, int size) {

        return collectOrderDaoMysqlImpl.page(query, start, size);
    }

    @Override
    public Date[] getDatesByLatelyMinutes(int latelyMinutes) {

        return collectOrderDaoMysqlImpl.getDatesByLatelyMinutes(latelyMinutes);
    }

    @Override
    public CollectOrder get(String orderNumber) {

        return collectOrderDaoMysqlImpl.get(orderNumber);
    }

    @Override
    public List<CollectOrder> list4ExpireState(Date tableDate, int state, int start, int size) {

        return collectOrderDaoMysqlImpl.list4ExpireState(tableDate, state, start, size);
    }
}
