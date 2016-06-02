package com.qcloud.component.orderform.dao.cache;

import java.util.Map;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.orderform.dao.OrderDiscountDao;
import com.qcloud.component.orderform.model.OrderDiscount;
import com.qcloud.component.orderform.model.query.OrderDiscountQuery;

@Repository
public class OrderDiscountDaoCacheImpl implements OrderDiscountDao {
	
	@Autowired
	private OrderDiscountDao orderDiscountDaoMysqlImpl;
	
//	@Autowired
//	private OrderDiscountDao orderDiscountDaoRedisImpl;

	@Override
	public boolean add(OrderDiscount orderDiscount) {
		return orderDiscountDaoMysqlImpl.add(orderDiscount);
	}

	@Override
	public OrderDiscount get(Long id) {
	    return orderDiscountDaoMysqlImpl.get(id);
//		return CacheLoader.get(orderDiscountDaoRedisImpl, orderDiscountDaoMysqlImpl, id);
	}

	@Override
	public boolean delete(Long id){
		return orderDiscountDaoMysqlImpl.delete(id);
	}
	
	@Override
	public boolean update(OrderDiscount orderDiscount){
		return orderDiscountDaoMysqlImpl.update(orderDiscount);
	}
	
	@Override
	public List<OrderDiscount> list(List<Long> idList) {
		return CacheLoader.list(orderDiscountDaoMysqlImpl, idList);
	}

	@Override
	public Map<Long, OrderDiscount> map(Set<Long> idSet){
		return CacheLoader.map(orderDiscountDaoMysqlImpl, idSet);
	}

	@Override
	public Page<OrderDiscount> page(int start, int count){
		return orderDiscountDaoMysqlImpl.page(start, count);
	}
	
	@Override
	public Page<OrderDiscount> page(OrderDiscountQuery query, int start, int count){
		return orderDiscountDaoMysqlImpl.page(query, start, count);
	}
	
	public List<OrderDiscount> listAll(){
		return orderDiscountDaoMysqlImpl.listAll();
	}

    @Override
    public List<OrderDiscount> listBySubOrder(long subOrderId) {
        return orderDiscountDaoMysqlImpl.listBySubOrder(subOrderId);
    }
}

