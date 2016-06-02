package com.qcloud.component.pay.dao.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;

import com.qcloud.pirates.core.json.Json;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.redis.Redis;
import com.qcloud.component.pay.dao.PayRequestDao;
import com.qcloud.component.pay.model.PayRequest;
import com.qcloud.component.pay.model.query.PayRequestQuery;

@Repository
public class PayRequestDaoRedisImpl implements PayRequestDao {

	//@Resource(name = "redis-pay")
	//private Redis redis;

	@Override
	public boolean add(PayRequest payRequest) {			
		throw new NotImplementedException();
	}

	@Override
	public PayRequest get(Long id) {		
		throw new NotImplementedException();
	}
	
	@Override
	public boolean delete(Long id){
		throw new NotImplementedException();
	}
	
	@Override
	public boolean update(PayRequest payRequest){
		throw new NotImplementedException();
	}
	
	@Override
	public List<PayRequest> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, PayRequest> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<PayRequest> page(int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public Page<PayRequest> page(PayRequestQuery query, int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public List<PayRequest> listAll(){	
		throw new NotImplementedException();
	}

    @Override
    public PayRequest getByObj(String module, Long objId, String type, String client) {
        throw new NotImplementedException();
    }

    @Override
    public PayRequest getByAttach(String module, String attach, String type, String client) {
        throw new NotImplementedException();
    }
}

