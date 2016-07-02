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
import com.qcloud.component.brokerage.dao.AllocationSchemeDao;
import com.qcloud.component.brokerage.model.AllocationScheme;
import com.qcloud.component.brokerage.model.query.AllocationSchemeQuery;

@Repository
public class AllocationSchemeDaoRedisImpl implements AllocationSchemeDao {

	//@Resource(name = "redis-brokerage")
	//private Redis redis;

	@Override
	public boolean add(AllocationScheme allocationScheme) {			
		throw new NotImplementedException();
	}

	@Override
	public AllocationScheme get(Long id) {		
		throw new NotImplementedException();
	}
	
	@Override
	public boolean delete(Long id){
		throw new NotImplementedException();
	}
	
	@Override
	public boolean update(AllocationScheme allocationScheme){
		throw new NotImplementedException();
	}
	
	@Override
	public List<AllocationScheme> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, AllocationScheme> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<AllocationScheme> page(int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public Page<AllocationScheme> page(AllocationSchemeQuery query, int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public List<AllocationScheme> listAll(){	
		throw new NotImplementedException();
	}

    @Override
    public List<AllocationScheme> listByFormula(long formulaId) {
        throw new NotImplementedException();
    }
}

