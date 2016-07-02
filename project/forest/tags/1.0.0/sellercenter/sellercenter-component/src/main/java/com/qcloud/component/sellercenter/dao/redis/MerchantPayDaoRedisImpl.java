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
import com.qcloud.component.sellercenter.dao.MerchantPayDao;
import com.qcloud.component.sellercenter.model.MerchantPay;
import com.qcloud.component.sellercenter.model.query.MerchantPayQuery;

@Repository
public class MerchantPayDaoRedisImpl implements MerchantPayDao {

	//@Resource(name = "redis-sellercenter")
	//private Redis redis;

	@Override
	public boolean add(MerchantPay merchantPay) {			
		throw new NotImplementedException();
	}

	@Override
	public MerchantPay get(Long id) {		
		throw new NotImplementedException();
	}
	
	@Override
	public boolean delete(Long id){
		throw new NotImplementedException();
	}
	
	@Override
	public boolean update(MerchantPay merchantPay){
		throw new NotImplementedException();
	}
	
	@Override
	public List<MerchantPay> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, MerchantPay> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<MerchantPay> page(int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public Page<MerchantPay> page(MerchantPayQuery query, int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public List<MerchantPay> listAll(){	
		throw new NotImplementedException();
	}
}

