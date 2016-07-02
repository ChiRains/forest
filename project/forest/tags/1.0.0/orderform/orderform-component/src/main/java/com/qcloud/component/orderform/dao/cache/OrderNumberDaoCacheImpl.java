package com.qcloud.component.orderform.dao.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.qcloud.component.orderform.dao.OrderNumberDao;

@Repository
public class OrderNumberDaoCacheImpl implements OrderNumberDao {

    @Autowired
    private OrderNumberDao orderNumberDaoOutsideImpl;

    @Override
    public String getNextNumber() {

        return orderNumberDaoOutsideImpl.getNextNumber();
    }
}
