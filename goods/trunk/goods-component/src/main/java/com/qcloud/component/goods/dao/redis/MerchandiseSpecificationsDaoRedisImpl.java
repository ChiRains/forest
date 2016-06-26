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
import com.qcloud.component.goods.dao.MerchandiseSpecificationsDao;
import com.qcloud.component.goods.model.MerchandiseSpecifications;
import com.qcloud.component.goods.model.query.MerchandiseSpecificationsQuery;

@Repository
public class MerchandiseSpecificationsDaoRedisImpl implements MerchandiseSpecificationsDao {

	//@Resource(name = "redis-goods")
	//private Redis redis;

	@Override
	public boolean add(MerchandiseSpecifications merchandiseSpecifications) {			
		throw new NotImplementedException();
	}

	@Override
	public MerchandiseSpecifications get(Long id) {		
		throw new NotImplementedException();
	}
	
	@Override
	public boolean delete(Long id){
		throw new NotImplementedException();
	}
	
	@Override
	public boolean update(MerchandiseSpecifications merchandiseSpecifications){
		throw new NotImplementedException();
	}
	
	@Override
	public List<MerchandiseSpecifications> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, MerchandiseSpecifications> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<MerchandiseSpecifications> page(int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public Page<MerchandiseSpecifications> page(MerchandiseSpecificationsQuery query, int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public List<MerchandiseSpecifications> listAll(){	
		throw new NotImplementedException();
	}
}

