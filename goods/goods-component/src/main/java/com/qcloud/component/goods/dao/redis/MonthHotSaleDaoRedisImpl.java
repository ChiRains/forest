package com.qcloud.component.goods.dao.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;

import com.qcloud.pirates.core.json.Json;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.redis.Redis;
import com.qcloud.component.goods.dao.MonthHotSaleDao;
import com.qcloud.component.goods.model.MonthHotSale;
import com.qcloud.component.goods.model.query.MonthHotSaleQuery;

@Repository
public class MonthHotSaleDaoRedisImpl implements MonthHotSaleDao {

	//@Resource(name = "redis-goods")
	//private Redis redis;

	@Override
	public boolean add(MonthHotSale monthHotSale) {			
		throw new NotImplementedException();
	}

	@Override
	public MonthHotSale get(Long id) {		
		throw new NotImplementedException();
	}
	
	@Override
	public boolean delete(Long id){
		throw new NotImplementedException();
	}
	
	@Override
	public boolean update(MonthHotSale monthHotSale){
		throw new NotImplementedException();
	}
	
	@Override
	public List<MonthHotSale> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, MonthHotSale> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<MonthHotSale> page(int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public Page<MonthHotSale> page(MonthHotSaleQuery query, int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public List<MonthHotSale> listAll(){	
		throw new NotImplementedException();
	}
}

