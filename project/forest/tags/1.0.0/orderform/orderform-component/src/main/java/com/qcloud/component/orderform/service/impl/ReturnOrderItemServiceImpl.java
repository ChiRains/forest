package com.qcloud.component.orderform.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.orderform.dao.ReturnOrderItemDao;
import com.qcloud.component.orderform.model.ReturnOrderItem;
import com.qcloud.component.orderform.service.ReturnOrderItemService;
import com.qcloud.component.orderform.model.query.ReturnOrderItemQuery;

@Service
public class ReturnOrderItemServiceImpl implements ReturnOrderItemService {

    @Autowired
    private ReturnOrderItemDao  returnOrderItemDao;

    @Autowired
    private AutoIdGenerator     autoIdGenerator;

    private static final String ID_KEY = "orderform_return_order_item";

    @Override
    public boolean add(ReturnOrderItem returnOrderItem) {

        long id = autoIdGenerator.get(ID_KEY);
        returnOrderItem.setId(id);
        return returnOrderItemDao.add(returnOrderItem);
    }

    @Override
    public ReturnOrderItem get(Long id) {

        return returnOrderItemDao.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return returnOrderItemDao.delete(id);
    }

    @Override
    public boolean update(ReturnOrderItem returnOrderItem) {

        return returnOrderItemDao.update(returnOrderItem);
    }

    @Override
    public boolean update(Long id, int state) {

        ReturnOrderItem returnOrderItem = get(id);
        returnOrderItem.setState(state);
        return update(returnOrderItem);
    }

    @Override
    public Page<ReturnOrderItem> page(ReturnOrderItemQuery query, int start, int count) {

        return returnOrderItemDao.page(query, start, count);
    }

    public List<ReturnOrderItem> listAll() {

        return returnOrderItemDao.listAll();
    }

    @Override
    public List<ReturnOrderItem> listByReturn(Long returnId) {

        return returnOrderItemDao.listByReturn(returnId);
    }
}
