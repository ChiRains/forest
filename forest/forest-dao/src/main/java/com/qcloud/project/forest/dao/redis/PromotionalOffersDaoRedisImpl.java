package com.qcloud.project.forest.dao.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;

import com.qcloud.pirates.core.json.Json;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.redis.Redis;
import com.qcloud.project.forest.dao.PromotionalOffersDao;
import com.qcloud.project.forest.model.PromotionalOffers;
import com.qcloud.project.forest.model.query.PromotionalOffersQuery;

@Repository
public class PromotionalOffersDaoRedisImpl implements PromotionalOffersDao {

	//@Resource(name = "redis-forest")
	//private Redis redis;

	@Override
	public boolean add(PromotionalOffers promotionalOffers) {			
		throw new NotImplementedException();
	}

	@Override
	public PromotionalOffers get(Long id) {		
		throw new NotImplementedException();
	}
	
	@Override
	public boolean delete(Long id){
		throw new NotImplementedException();
	}
	
	@Override
	public boolean update(PromotionalOffers promotionalOffers){
		throw new NotImplementedException();
	}
	
	@Override
	public List<PromotionalOffers> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, PromotionalOffers> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
			
																					public List<PromotionalOffers> listByClassify(Long classify){
					throw new NotImplementedException();
				}

					
	@Override
	public Page<PromotionalOffers> page(int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public Page<PromotionalOffers> page(PromotionalOffersQuery query, int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public List<PromotionalOffers> listAll(){	
		throw new NotImplementedException();
	}
}

