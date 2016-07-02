package com.qcloud.component.orderform.dao.cache;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.qcloud.component.orderform.dao.SubOrderDao;
import com.qcloud.component.orderform.model.SubOrder;
import com.qcloud.component.orderform.model.query.SubOrderQuery;
import com.qcloud.pirates.data.Page;

@Repository
public class SubOrderDaoCacheImpl implements SubOrderDao {

    @Autowired
    private SubOrderDao subOrderDaoMysqlImpl;

    // @Autowired
    // private SubOrderDao subOrderDaoRedisImpl;
    @Override
    public boolean add(SubOrder subOrder, Date time) {

        return subOrderDaoMysqlImpl.add(subOrder, time);
    }

    @Override
    public SubOrder get(Long id, Date time) {

        return subOrderDaoMysqlImpl.get(id, time);
    }

    @Override
    public boolean update(SubOrder subOrder, Date time) {

        return subOrderDaoMysqlImpl.update(subOrder, time);
    }

    @Override
    public boolean delete(Long id) {

        return subOrderDaoMysqlImpl.delete(id);
    }

    @Override
    public Page<SubOrder> page(SubOrderQuery query, int start, int size) {

        return subOrderDaoMysqlImpl.page(query, start, size);
    }

    @Override
    public List<SubOrder> listByCollectOrder(Long collectOrderId, Date time) {

        return subOrderDaoMysqlImpl.listByCollectOrder(collectOrderId, time);
    }

    @Override
    public SubOrder get(String orderNumber) {

        return subOrderDaoMysqlImpl.get(orderNumber);
    }

    @Override
    public List<SubOrder> listByMerchantAndDay(Long merchantId, Date time) {

        return subOrderDaoMysqlImpl.listByMerchantAndDay(merchantId, time);
    }
}
