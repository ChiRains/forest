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
import com.qcloud.component.goods.dao.MerchandiseBrowsingHistoryDao;
import com.qcloud.component.goods.model.MerchandiseBrowsingHistory;
import com.qcloud.component.goods.model.query.MerchandiseBrowsingHistoryQuery;

@Repository
public class MerchandiseBrowsingHistoryDaoRedisImpl implements MerchandiseBrowsingHistoryDao {

	//@Resource(name = "redis-goods")
	//private Redis redis;

	@Override
	public boolean add(MerchandiseBrowsingHistory merchandiseBrowsingHistory) {			
		throw new NotImplementedException();
	}

	@Override
	public MerchandiseBrowsingHistory get(Long id) {		
		throw new NotImplementedException();
	}
	
	@Override
	public boolean delete(Long id){
		throw new NotImplementedException();
	}
	
	@Override
	public boolean update(MerchandiseBrowsingHistory merchandiseBrowsingHistory){
		throw new NotImplementedException();
	}
	
	@Override
	public List<MerchandiseBrowsingHistory> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, MerchandiseBrowsingHistory> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<MerchandiseBrowsingHistory> page(int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public Page<MerchandiseBrowsingHistory> page(MerchandiseBrowsingHistoryQuery query, int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public List<MerchandiseBrowsingHistory> listAll(){	
		throw new NotImplementedException();
	}
}

