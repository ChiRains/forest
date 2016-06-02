package com.qcloud.component.orderform.service.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.component.orderform.dao.ReturnOrderDao;
import com.qcloud.component.orderform.engine.AutoChangeService;
import com.qcloud.component.orderform.model.ReturnOrder;
import com.qcloud.component.orderform.model.query.ReturnOrderQuery;
import com.qcloud.component.orderform.service.ReturnOrderService;
import com.qcloud.pirates.data.Page;

@Service
public class ReturnOrderServiceImpl implements ReturnOrderService {

    @Autowired
    private ReturnOrderDao      returnOrderDao;

    @Autowired
    private AutoIdGenerator     autoIdGenerator;

    @Autowired
    private AutoChangeService   autoChangeService;

    private static final String ID_KEY = "orderform_return_order";

    @Override
    public boolean add(ReturnOrder returnOrder) {

        returnOrder.setLastUpdateTime(new Date());
        returnOrder.setStateValidTime(autoChangeService.getStateValidTime(3, returnOrder.getState(), returnOrder.getLastUpdateTime()));
        long id = autoIdGenerator.get(ID_KEY);
        returnOrder.setId(id);
        return returnOrderDao.add(returnOrder);
    }

    @Override
    public ReturnOrder get(Long id) {

        return returnOrderDao.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return returnOrderDao.delete(id);
    }

    @Override
    public boolean update(ReturnOrder returnOrder) {

        return returnOrderDao.update(returnOrder);
    }

    @Override
    public Page<ReturnOrder> page(ReturnOrderQuery query, int start, int count) {

        return returnOrderDao.page(query, start, count);
    }

    public List<ReturnOrder> listAll() {

        return returnOrderDao.listAll();
    }

    @Override
    public boolean update(Long id, int state) {

        ReturnOrder returnOrder = get(id);
        returnOrder.setLastUpdateTime(new Date());
        returnOrder.setStateValidTime(autoChangeService.getStateValidTime(3, state, returnOrder.getLastUpdateTime()));
        returnOrder.setState(state);
        return update(returnOrder);
    }

    @Override
    public List<ReturnOrder> listBySubOrder(Long subOrderId) {

        return returnOrderDao.listBySubOrder(subOrderId);
    }

    @Override
    public List<ReturnOrder> list4ExpireState(int state, int start, int size) {

        return returnOrderDao.list4ExpireState(state, start, size);
    }
}
