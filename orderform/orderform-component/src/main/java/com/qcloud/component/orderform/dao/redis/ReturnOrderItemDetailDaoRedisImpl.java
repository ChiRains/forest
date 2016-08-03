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
import com.qcloud.component.orderform.dao.ReturnOrderItemDetailDao;
import com.qcloud.component.orderform.model.ReturnOrderItemDetail;
import com.qcloud.component.orderform.model.query.ReturnOrderItemDetailQuery;

@Repository
public class ReturnOrderItemDetailDaoRedisImpl implements ReturnOrderItemDetailDao {

	//@Resource(name = "redis-orderform")
	//private Redis redis;

	@Override
	public boolean add(ReturnOrderItemDetail returnOrderItemDetail) {			
		throw new NotImplementedException();
	}

	@Override
	public ReturnOrderItemDetail get(Long id) {		
		throw new NotImplementedException();
	}
	
	@Override
	public boolean delete(Long id){
		throw new NotImplementedException();
	}
	
	@Override
	public boolean update(ReturnOrderItemDetail returnOrderItemDetail){
		throw new NotImplementedException();
	}
	
	@Override
	public List<ReturnOrderItemDetail> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, ReturnOrderItemDetail> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<ReturnOrderItemDetail> page(int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public Page<ReturnOrderItemDetail> page(ReturnOrderItemDetailQuery query, int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public List<ReturnOrderItemDetail> listAll(){	
		throw new NotImplementedException();
	}
}

