package com.qcloud.component.commoditycenter.dao.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;

import com.qcloud.pirates.core.json.Json;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.redis.Redis;
import com.qcloud.component.commoditycenter.dao.MerchandiseAttributeDao;
import com.qcloud.component.commoditycenter.model.MerchandiseAttribute;
import com.qcloud.component.commoditycenter.model.query.MerchandiseAttributeQuery;

@Repository
public class MerchandiseAttributeDaoRedisImpl implements MerchandiseAttributeDao {

	//@Resource(name = "redis-commoditycenter")
	//private Redis redis;

	@Override
	public boolean add(MerchandiseAttribute merchandiseAttribute) {			
		throw new NotImplementedException();
	}

	@Override
	public MerchandiseAttribute get(Long id) {		
		throw new NotImplementedException();
	}
	
	@Override
	public boolean delete(Long id){
		throw new NotImplementedException();
	}
	
	@Override
	public boolean update(MerchandiseAttribute merchandiseAttribute){
		throw new NotImplementedException();
	}
	
	@Override
	public List<MerchandiseAttribute> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, MerchandiseAttribute> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<MerchandiseAttribute> page(int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public Page<MerchandiseAttribute> page(MerchandiseAttributeQuery query, int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public List<MerchandiseAttribute> listAll(){	
		throw new NotImplementedException();
	}

    @Override
    public List<MerchandiseAttribute> listByMerchandise(long merchandiseId) {
        throw new NotImplementedException();
    }
}

