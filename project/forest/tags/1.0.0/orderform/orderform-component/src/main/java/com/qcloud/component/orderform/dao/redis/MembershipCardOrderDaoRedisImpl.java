package com.qcloud.component.orderform.dao.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;

import com.qcloud.pirates.core.json.Json;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.redis.Redis;
import com.qcloud.component.orderform.dao.MembershipCardOrderDao;
import com.qcloud.component.orderform.model.MembershipCardOrder;
import com.qcloud.component.orderform.model.query.MembershipCardOrderQuery;

@Repository
public class MembershipCardOrderDaoRedisImpl implements MembershipCardOrderDao {

	//@Resource(name = "redis-orderform")
	//private Redis redis;

	@Override
	public boolean add(MembershipCardOrder membershipCardOrder) {			
		throw new NotImplementedException();
	}

	@Override
	public MembershipCardOrder get(Long id) {		
		throw new NotImplementedException();
	}
	
	@Override
	public boolean delete(Long id){
		throw new NotImplementedException();
	}
	
	@Override
	public boolean update(MembershipCardOrder membershipCardOrder){
		throw new NotImplementedException();
	}
	
	@Override
	public List<MembershipCardOrder> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, MembershipCardOrder> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<MembershipCardOrder> page(int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public Page<MembershipCardOrder> page(MembershipCardOrderQuery query, int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public List<MembershipCardOrder> listAll(){	
		throw new NotImplementedException();
	}
}

