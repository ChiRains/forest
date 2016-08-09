package com.qcloud.component.orderform.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.orderform.dao.ReturnOrderItemDetailDao;
import com.qcloud.component.orderform.model.ReturnOrderItemDetail;
import com.qcloud.component.orderform.service.ReturnOrderItemDetailService;
import com.qcloud.component.orderform.model.query.ReturnOrderItemDetailQuery;

@Service
public class ReturnOrderItemDetailServiceImpl implements ReturnOrderItemDetailService {

    @Autowired
    private ReturnOrderItemDetailDao returnOrderItemDetailDao;

    @Autowired
    private AutoIdGenerator          autoIdGenerator;

    private static final String      ID_KEY = "orderform_return_order_item_detail";

    @Override
    public boolean add(ReturnOrderItemDetail returnOrderItemDetail) {

        long id = autoIdGenerator.get(ID_KEY);
        returnOrderItemDetail.setId(id);
        return returnOrderItemDetailDao.add(returnOrderItemDetail);
    }

    @Override
    public ReturnOrderItemDetail get(Long id) {

        return returnOrderItemDetailDao.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return returnOrderItemDetailDao.delete(id);
    }

    @Override
    public boolean update(ReturnOrderItemDetail returnOrderItemDetail) {

        return returnOrderItemDetailDao.update(returnOrderItemDetail);
    }

    @Override
    public Page<ReturnOrderItemDetail> page(ReturnOrderItemDetailQuery query, int start, int count) {

        return returnOrderItemDetailDao.page(query, start, count);
    }

    public List<ReturnOrderItemDetail> listAll() {

        return returnOrderItemDetailDao.listAll();
    }

    @Override
    public List<ReturnOrderItemDetail> listByReturn(long returnId) {

        return returnOrderItemDetailDao.listByReturn(returnId);
    }
}
