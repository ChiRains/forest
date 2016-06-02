package com.qcloud.component.orderform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qcloud.component.orderform.dao.OrderNumberDao;
import com.qcloud.component.orderform.service.OrderNumberService;

@Service
public class OrderNumberServiceImpl implements OrderNumberService {

    @Autowired
    private OrderNumberDao orderNumberDao;

    @Override
    public String generate() {

        return orderNumberDao.getNextNumber();
    }
}
