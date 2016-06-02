package com.qcloud.component.seckill.dao.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;

import com.qcloud.pirates.core.json.Json;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.redis.Redis;
import com.qcloud.component.seckill.dao.SeckillOrderDao;
import com.qcloud.component.seckill.model.SeckillOrder;
import com.qcloud.component.seckill.model.query.SeckillOrderQuery;

@Repository
public class SeckillOrderDaoRedisImpl implements SeckillOrderDao {

	//@Resource(name = "redis-seckill")
	//private Redis redis;

	@Override
	public boolean add(SeckillOrder seckillOrder) {			
		throw new NotImplementedException();
	}

	@Override
	public SeckillOrder get(Long id) {		
		throw new NotImplementedException();
	}
	
	@Override
	public boolean delete(Long id){
		throw new NotImplementedException();
	}
	
	@Override
	public boolean update(SeckillOrder seckillOrder){
		throw new NotImplementedException();
	}
	
	@Override
	public List<SeckillOrder> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, SeckillOrder> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<SeckillOrder> page(int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public Page<SeckillOrder> page(SeckillOrderQuery query, int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public List<SeckillOrder> listAll(){	
		throw new NotImplementedException();
	}
}

