package com.qcloud.component.orderform.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.orderform.dao.OrderDiscountDao;
import com.qcloud.component.orderform.model.OrderDiscount;
import com.qcloud.component.orderform.service.OrderDiscountService;
import com.qcloud.component.orderform.model.query.OrderDiscountQuery;
		
@Service
public class OrderDiscountServiceImpl implements OrderDiscountService{
	
	@Autowired
	private OrderDiscountDao orderDiscountDao;
	
	@Autowired
	private AutoIdGenerator autoIdGenerator;
	
	private static final String ID_KEY = "orderform_order_discount";

	@Override
	public boolean add(OrderDiscount orderDiscount) {
		long id = autoIdGenerator.get(ID_KEY);
		orderDiscount.setId(id);
		
		return orderDiscountDao.add(orderDiscount);
	}

	@Override
	public OrderDiscount get(Long id) {	
		return orderDiscountDao.get(id);		
	}

	@Override
	public boolean delete(Long id) {
		return orderDiscountDao.delete(id);
	}
	
	@Override
	public boolean update(OrderDiscount orderDiscount) {
		return orderDiscountDao.update(orderDiscount);
	}
	
	@Override
	public Page<OrderDiscount> page(OrderDiscountQuery query, int start, int count) {
		return orderDiscountDao.page(query, start, count);
	}
	
	public List<OrderDiscount> listAll(){
		return orderDiscountDao.listAll();
	}

    @Override
    public List<OrderDiscount> listBySubOrder(long subOrderId) {
        return orderDiscountDao.listBySubOrder(subOrderId);
    }
}

