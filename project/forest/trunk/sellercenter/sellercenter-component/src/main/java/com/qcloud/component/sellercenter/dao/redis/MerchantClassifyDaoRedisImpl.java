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
import com.qcloud.component.sellercenter.dao.MerchantClassifyDao;
import com.qcloud.component.sellercenter.model.MerchantClassify;
import com.qcloud.component.sellercenter.model.query.MerchantClassifyQuery;

@Repository
public class MerchantClassifyDaoRedisImpl implements MerchantClassifyDao {

	//@Resource(name = "redis-sellercenter")
	//private Redis redis;

	@Override
	public boolean add(MerchantClassify merchantClassify) {			
		throw new NotImplementedException();
	}

	@Override
	public MerchantClassify get(Long id) {		
		throw new NotImplementedException();
	}
	
	@Override
	public boolean delete(Long id){
		throw new NotImplementedException();
	}
	
	@Override
	public boolean update(MerchantClassify merchantClassify){
		throw new NotImplementedException();
	}
	
	@Override
	public List<MerchantClassify> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, MerchantClassify> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<MerchantClassify> page(int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public Page<MerchantClassify> page(MerchantClassifyQuery query, int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public List<MerchantClassify> listAll(){	
		throw new NotImplementedException();
	}

    @Override
    public List<MerchantClassify> listByMerchant(Long merchantId) {
        throw new NotImplementedException();
    }

    @Override
    public MerchantClassify get(Long classifyId, Long merchantId) {
        throw new NotImplementedException();
    }
}

