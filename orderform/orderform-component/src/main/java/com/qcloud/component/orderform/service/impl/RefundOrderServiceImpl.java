package com.qcloud.component.orderform.service.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.component.orderform.dao.RefundOrderDao;
import com.qcloud.component.orderform.engine.AutoChangeService;
import com.qcloud.component.orderform.model.RefundOrder;
import com.qcloud.component.orderform.model.query.RefundOrderQuery;
import com.qcloud.component.orderform.service.RefundOrderService;
import com.qcloud.pirates.data.Page;

@Service
public class RefundOrderServiceImpl implements RefundOrderService {

    @Autowired
    private RefundOrderDao      refundOrderDao;

    @Autowired
    private AutoIdGenerator     autoIdGenerator;

    @Autowired
    private AutoChangeService   autoChangeService;

    private static final String ID_KEY = "orderform_refund_order";

    @Override
    public boolean add(RefundOrder refundOrder) {

        refundOrder.setLastUpdateTime(new Date());
        refundOrder.setStateValidTime(autoChangeService.getStateValidTime(2, refundOrder.getState(), refundOrder.getLastUpdateTime()));
        long id = autoIdGenerator.get(ID_KEY);
        refundOrder.setId(id);
        boolean result = refundOrderDao.add(refundOrder);
        //
        return result;
    }

    @Override
    public RefundOrder get(Long id) {

        return refundOrderDao.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return refundOrderDao.delete(id);
    }

    @Override
    public boolean update(RefundOrder refundOrder) {

        return refundOrderDao.update(refundOrder);
    }

    @Override
    public Page<RefundOrder> page(RefundOrderQuery query, int start, int count) {

        return refundOrderDao.page(query, start, count);
    }

    public List<RefundOrder> listAll() {

        return refundOrderDao.listAll();
    }

    @Override
    public List<RefundOrder> listBySubOrder(Long subOrderId) {

        return refundOrderDao.listBySubOrder(subOrderId);
    }

    @Override
    public boolean update(Long id, int state) {

        RefundOrder refundOrder = get(id);
        refundOrder.setLastUpdateTime(new Date());
        refundOrder.setStateValidTime(autoChangeService.getStateValidTime(2, state, refundOrder.getLastUpdateTime()));
        refundOrder.setState(state);
        return update(refundOrder);
    }

    @Override
    public List<RefundOrder> list4ExpireState(int state, int start, int size) {

        return refundOrderDao.list4ExpireState(state, start, size);
    }
}
