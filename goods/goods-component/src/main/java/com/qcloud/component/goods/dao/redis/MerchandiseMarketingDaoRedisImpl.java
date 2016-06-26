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
import com.qcloud.component.goods.dao.MerchandiseMarketingDao;
import com.qcloud.component.goods.model.MerchandiseMarketing;
import com.qcloud.component.goods.model.query.MerchandiseMarketingQuery;

@Repository
public class MerchandiseMarketingDaoRedisImpl implements MerchandiseMarketingDao {

	//@Resource(name = "redis-goods")
	//private Redis redis;

	@Override
	public boolean add(MerchandiseMarketing merchandiseMarketing) {			
		throw new NotImplementedException();
	}

	@Override
	public MerchandiseMarketing get(Long id) {		
		throw new NotImplementedException();
	}
	
	@Override
	public boolean delete(Long id){
		throw new NotImplementedException();
	}
	
	@Override
	public boolean update(MerchandiseMarketing merchandiseMarketing){
		throw new NotImplementedException();
	}
	
	@Override
	public List<MerchandiseMarketing> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, MerchandiseMarketing> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<MerchandiseMarketing> page(int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public Page<MerchandiseMarketing> page(MerchandiseMarketingQuery query, int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public List<MerchandiseMarketing> listAll(){	
		throw new NotImplementedException();
	}
}

