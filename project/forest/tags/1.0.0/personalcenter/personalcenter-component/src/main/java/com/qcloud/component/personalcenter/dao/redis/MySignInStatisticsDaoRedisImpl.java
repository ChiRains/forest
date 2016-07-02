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
import com.qcloud.component.personalcenter.dao.MySignInStatisticsDao;
import com.qcloud.component.personalcenter.model.MySignInStatistics;
import com.qcloud.component.personalcenter.model.query.MySignInStatisticsQuery;

@Repository
public class MySignInStatisticsDaoRedisImpl implements MySignInStatisticsDao {

	//@Resource(name = "redis-personalcenter")
	//private Redis redis;

	@Override
	public boolean add(MySignInStatistics mySignInStatistics) {			
		throw new NotImplementedException();
	}

	@Override
	public MySignInStatistics get(Long id) {		
		throw new NotImplementedException();
	}
	
	@Override
	public boolean delete(Long id){
		throw new NotImplementedException();
	}
	
	@Override
	public boolean update(MySignInStatistics mySignInStatistics){
		throw new NotImplementedException();
	}
	
	@Override
	public List<MySignInStatistics> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, MySignInStatistics> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<MySignInStatistics> page(int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public Page<MySignInStatistics> page(MySignInStatisticsQuery query, int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public List<MySignInStatistics> listAll(){	
		throw new NotImplementedException();
	}

    @Override
    public MySignInStatistics getByUser(Long userId) {
        throw new NotImplementedException();
    }
}

