package com.qcloud.component.brokerage.dao.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;

import com.qcloud.pirates.core.json.Json;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.redis.Redis;
import com.qcloud.component.brokerage.dao.DistributionBrokerageDao;
import com.qcloud.component.brokerage.model.DistributionBrokerage;
import com.qcloud.component.brokerage.model.query.DistributionBrokerageQuery;

@Repository
public class DistributionBrokerageDaoRedisImpl implements DistributionBrokerageDao {

	//@Resource(name = "redis-brokerage")
	//private Redis redis;

	@Override
	public boolean add(DistributionBrokerage distributionBrokerage) {			
		throw new NotImplementedException();
	}

	@Override
	public DistributionBrokerage get(Long id) {		
		throw new NotImplementedException();
	}
	
	@Override
	public boolean delete(Long id){
		throw new NotImplementedException();
	}
	
	@Override
	public boolean update(DistributionBrokerage distributionBrokerage){
		throw new NotImplementedException();
	}
	
	@Override
	public List<DistributionBrokerage> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, DistributionBrokerage> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<DistributionBrokerage> page(int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public Page<DistributionBrokerage> page(DistributionBrokerageQuery query, int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public List<DistributionBrokerage> listAll(){	
		throw new NotImplementedException();
	}
}

