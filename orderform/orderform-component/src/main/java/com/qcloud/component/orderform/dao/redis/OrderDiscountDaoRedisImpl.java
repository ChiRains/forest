package com.qcloud.component.orderform.dao.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;

import com.qcloud.pirates.core.json.Json;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.redis.Redis;
import com.qcloud.component.orderform.dao.OrderDiscountDao;
import com.qcloud.component.orderform.model.OrderDiscount;
import com.qcloud.component.orderform.model.query.OrderDiscountQuery;

@Repository
public class OrderDiscountDaoRedisImpl implements OrderDiscountDao {

	//@Resource(name = "redis-orderform")
	//private Redis redis;

	@Override
	public boolean add(OrderDiscount orderDiscount) {			
		throw new NotImplementedException();
	}

	@Override
	public OrderDiscount get(Long id) {		
		throw new NotImplementedException();
	}
	
	@Override
	public boolean delete(Long id){
		throw new NotImplementedException();
	}
	
	@Override
	public boolean update(OrderDiscount orderDiscount){
		throw new NotImplementedException();
	}
	
	@Override
	public List<OrderDiscount> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, OrderDiscount> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<OrderDiscount> page(int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public Page<OrderDiscount> page(OrderDiscountQuery query, int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public List<OrderDiscount> listAll(){	
		throw new NotImplementedException();
	}

    @Override
    public List<OrderDiscount> listBySubOrder(long subOrderId) {
        throw new NotImplementedException();
    }
}

