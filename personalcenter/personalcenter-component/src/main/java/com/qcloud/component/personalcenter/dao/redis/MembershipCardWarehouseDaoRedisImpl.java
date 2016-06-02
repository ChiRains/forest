package com.qcloud.component.personalcenter.dao.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;

import com.qcloud.pirates.core.json.Json;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.redis.Redis;
import com.qcloud.component.personalcenter.dao.MembershipCardWarehouseDao;
import com.qcloud.component.personalcenter.model.MembershipCardWarehouse;
import com.qcloud.component.personalcenter.model.query.MembershipCardWarehouseQuery;

@Repository
public class MembershipCardWarehouseDaoRedisImpl implements MembershipCardWarehouseDao {

	//@Resource(name = "redis-personalcenter")
	//private Redis redis;

	@Override
	public boolean add(MembershipCardWarehouse membershipCardWarehouse) {			
		throw new NotImplementedException();
	}

	@Override
	public MembershipCardWarehouse get(Long id) {		
		throw new NotImplementedException();
	}
	
	@Override
	public boolean delete(Long id){
		throw new NotImplementedException();
	}
	
	@Override
	public boolean update(MembershipCardWarehouse membershipCardWarehouse){
		throw new NotImplementedException();
	}
	
	@Override
	public List<MembershipCardWarehouse> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, MembershipCardWarehouse> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<MembershipCardWarehouse> page(int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public Page<MembershipCardWarehouse> page(MembershipCardWarehouseQuery query, int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public List<MembershipCardWarehouse> listAll(){	
		throw new NotImplementedException();
	}

    @Override
    public MembershipCardWarehouse getByCardNumber(String number) {
        throw new NotImplementedException();
    }
}

