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
import com.qcloud.component.goods.dao.MerchandiseItemDao;
import com.qcloud.component.goods.model.MerchandiseItem;
import com.qcloud.component.goods.model.query.MerchandiseItemQuery;

@Repository
public class MerchandiseItemDaoRedisImpl implements MerchandiseItemDao {

	//@Resource(name = "redis-goods")
	//private Redis redis;

	@Override
	public boolean add(MerchandiseItem merchandiseItem) {			
		throw new NotImplementedException();
	}

	@Override
	public MerchandiseItem get(Long id) {		
		throw new NotImplementedException();
	}
	
	@Override
	public boolean delete(Long id){
		throw new NotImplementedException();
	}
	
	@Override
	public boolean update(MerchandiseItem merchandiseItem){
		throw new NotImplementedException();
	}
	
	@Override
	public List<MerchandiseItem> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, MerchandiseItem> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<MerchandiseItem> page(int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public Page<MerchandiseItem> page(MerchandiseItemQuery query, int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public List<MerchandiseItem> listAll(){	
		throw new NotImplementedException();
	}
}

