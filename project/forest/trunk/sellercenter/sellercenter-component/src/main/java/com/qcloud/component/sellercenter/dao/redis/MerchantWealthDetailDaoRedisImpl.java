package com.qcloud.component.sellercenter.dao.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;

import com.qcloud.pirates.core.json.Json;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.redis.Redis;
import com.qcloud.component.sellercenter.dao.MerchantWealthDetailDao;
import com.qcloud.component.sellercenter.model.MerchantWealthDetail;
import com.qcloud.component.sellercenter.model.query.MerchantWealthDetailQuery;

@Repository
public class MerchantWealthDetailDaoRedisImpl implements MerchantWealthDetailDao {

	//@Resource(name = "redis-sellercenter")
	//private Redis redis;

	@Override
	public boolean add(MerchantWealthDetail merchantWealthDetail) {			
		throw new NotImplementedException();
	}

	@Override
	public MerchantWealthDetail get(Long id) {		
		throw new NotImplementedException();
	}
	
	@Override
	public boolean delete(Long id){
		throw new NotImplementedException();
	}
	
	@Override
	public boolean update(MerchantWealthDetail merchantWealthDetail){
		throw new NotImplementedException();
	}
	
	@Override
	public List<MerchantWealthDetail> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, MerchantWealthDetail> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<MerchantWealthDetail> page(int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public Page<MerchantWealthDetail> page(MerchantWealthDetailQuery query, int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public List<MerchantWealthDetail> listAll(){	
		throw new NotImplementedException();
	}
}

