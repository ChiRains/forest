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
import com.qcloud.component.brokerage.dao.DataPoolDao;
import com.qcloud.component.brokerage.model.DataPool;
import com.qcloud.component.brokerage.model.FormulaSqlResult;
import com.qcloud.component.brokerage.model.query.DataPoolQuery;

@Repository
public class DataPoolDaoRedisImpl implements DataPoolDao {

	//@Resource(name = "redis-brokerage")
	//private Redis redis;

	@Override
	public boolean add(DataPool dataPool) {			
		throw new NotImplementedException();
	}

	@Override
	public DataPool get(Long id) {		
		throw new NotImplementedException();
	}
	
	@Override
	public boolean delete(Long id){
		throw new NotImplementedException();
	}
	
	@Override
	public boolean update(DataPool dataPool){
		throw new NotImplementedException();
	}
	
	@Override
	public List<DataPool> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, DataPool> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<DataPool> page(int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public Page<DataPool> page(DataPoolQuery query, int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public List<DataPool> listAll(){	
		throw new NotImplementedException();
	}

    @Override
    public List<FormulaSqlResult> query(String sql) {
        throw new NotImplementedException();
    }
}

