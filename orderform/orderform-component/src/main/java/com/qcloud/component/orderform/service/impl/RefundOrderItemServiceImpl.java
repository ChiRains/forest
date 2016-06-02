package com.qcloud.component.orderform.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.orderform.dao.RefundOrderItemDao;
import com.qcloud.component.orderform.model.RefundOrderItem;
import com.qcloud.component.orderform.service.RefundOrderItemService;
import com.qcloud.component.orderform.model.query.RefundOrderItemQuery;

@Service
public class RefundOrderItemServiceImpl implements RefundOrderItemService {

    @Autowired
    private RefundOrderItemDao  refundOrderItemDao;

    @Autowired
    private AutoIdGenerator     autoIdGenerator;

    private static final String ID_KEY = "orderform_refund_order_item";

    @Override
    public boolean add(RefundOrderItem refundOrderItem) {

        long id = autoIdGenerator.get(ID_KEY);
        refundOrderItem.setId(id);
        return refundOrderItemDao.add(refundOrderItem);
    }

    @Override
    public RefundOrderItem get(Long id) {

        return refundOrderItemDao.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return refundOrderItemDao.delete(id);
    }

    @Override
    public boolean update(RefundOrderItem refundOrderItem) {

        return refundOrderItemDao.update(refundOrderItem);
    }

    @Override
    public boolean update(Long id, int state) {

        RefundOrderItem refundOrderItem = get(id);
        refundOrderItem.setState(state);
        return update(refundOrderItem);
    }

    @Override
    public Page<RefundOrderItem> page(RefundOrderItemQuery query, int start, int count) {

        return refundOrderItemDao.page(query, start, count);
    }

    public List<RefundOrderItem> listAll() {

        return refundOrderItemDao.listAll();
    }

    @Override
    public List<RefundOrderItem> listByRefund(Long refundId) {

        return refundOrderItemDao.listByRefund(refundId);
    }
}
