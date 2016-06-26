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
import com.qcloud.component.goods.dao.MerchandiseDao;
import com.qcloud.component.goods.model.Merchandise;
import com.qcloud.component.goods.model.query.MerchandiseQuery;

@Repository
public class MerchandiseDaoRedisImpl implements MerchandiseDao {

	//@Resource(name = "redis-goods")
	//private Redis redis;

	@Override
	public boolean add(Merchandise merchandise) {			
		throw new NotImplementedException();
	}

	@Override
	public Merchandise get(Long id) {		
		throw new NotImplementedException();
	}
	
	@Override
	public boolean delete(Long id){
		throw new NotImplementedException();
	}
	
	@Override
	public boolean update(Merchandise merchandise){
		throw new NotImplementedException();
	}
	
	@Override
	public List<Merchandise> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, Merchandise> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<Merchandise> page(int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public Page<Merchandise> page(MerchandiseQuery query, int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public List<Merchandise> listAll(){	
		throw new NotImplementedException();
	}
}

