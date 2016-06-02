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
import com.qcloud.component.commoditycenter.dao.MerchandiseDealRecordDao;
import com.qcloud.component.commoditycenter.model.MerchandiseDealRecord;
import com.qcloud.component.commoditycenter.model.query.MerchandiseDealRecordQuery;

@Repository
public class MerchandiseDealRecordDaoRedisImpl implements MerchandiseDealRecordDao {

	//@Resource(name = "redis-commoditycenter")
	//private Redis redis;

	@Override
	public boolean add(MerchandiseDealRecord merchandiseDealRecord) {			
		throw new NotImplementedException();
	}

	@Override
	public MerchandiseDealRecord get(Long id) {		
		throw new NotImplementedException();
	}
	
	@Override
	public boolean delete(Long id){
		throw new NotImplementedException();
	}
	
	@Override
	public boolean update(MerchandiseDealRecord merchandiseDealRecord){
		throw new NotImplementedException();
	}
	
	@Override
	public List<MerchandiseDealRecord> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, MerchandiseDealRecord> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<MerchandiseDealRecord> page(int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public Page<MerchandiseDealRecord> page(MerchandiseDealRecordQuery query, int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public List<MerchandiseDealRecord> listAll(){	
		throw new NotImplementedException();
	}

    @Override
    public List<MerchandiseDealRecord> listByMerchandise(Long merchandiseId, int start, int count) {
        throw new NotImplementedException();
    }
}

