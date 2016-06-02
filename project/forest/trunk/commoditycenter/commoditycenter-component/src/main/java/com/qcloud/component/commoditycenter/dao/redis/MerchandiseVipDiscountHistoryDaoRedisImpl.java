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
import com.qcloud.component.commoditycenter.dao.MerchandiseVipDiscountHistoryDao;
import com.qcloud.component.commoditycenter.model.MerchandiseVipDiscountHistory;
import com.qcloud.component.commoditycenter.model.query.MerchandiseVipDiscountHistoryQuery;

@Repository
public class MerchandiseVipDiscountHistoryDaoRedisImpl implements MerchandiseVipDiscountHistoryDao {

	//@Resource(name = "redis-commoditycenter")
	//private Redis redis;

	@Override
	public boolean add(MerchandiseVipDiscountHistory merchandiseVipDiscountHistory) {			
		throw new NotImplementedException();
	}

	@Override
	public MerchandiseVipDiscountHistory get(Long id) {		
		throw new NotImplementedException();
	}
	
	@Override
	public boolean delete(Long id){
		throw new NotImplementedException();
	}
	
	@Override
	public boolean update(MerchandiseVipDiscountHistory merchandiseVipDiscountHistory){
		throw new NotImplementedException();
	}
	
	@Override
	public List<MerchandiseVipDiscountHistory> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, MerchandiseVipDiscountHistory> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<MerchandiseVipDiscountHistory> page(int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public Page<MerchandiseVipDiscountHistory> page(MerchandiseVipDiscountHistoryQuery query, int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public List<MerchandiseVipDiscountHistory> listAll(){	
		throw new NotImplementedException();
	}
}

