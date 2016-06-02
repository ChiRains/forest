package com.qcloud.component.orderform.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.component.orderform.model.OrderDiscount;
import com.qcloud.component.orderform.model.query.OrderDiscountQuery;
		
public interface OrderDiscountDao extends ISimpleDao<OrderDiscount, Long> {

	public boolean add(OrderDiscount orderDiscount);	
	
	public OrderDiscount get(Long id);

	public boolean delete(Long id);
	
	public boolean update(OrderDiscount orderDiscount);
	
	public List<OrderDiscount> list(List<Long> idList);
	
	public Map<Long, OrderDiscount> map(Set<Long> idSet);
	
	public Page<OrderDiscount> page(OrderDiscountQuery query, int start, int size);

	public List<OrderDiscount> listAll();
	
	List<OrderDiscount> listBySubOrder(long subOrderId);
}
