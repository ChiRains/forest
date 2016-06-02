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
import com.qcloud.component.personalcenter.dao.MySignInMonthDao;
import com.qcloud.component.personalcenter.model.MySignInMonth;
import com.qcloud.component.personalcenter.model.query.MySignInMonthQuery;

@Repository
public class MySignInMonthDaoRedisImpl implements MySignInMonthDao {

	//@Resource(name = "redis-personalcenter")
	//private Redis redis;

	@Override
	public boolean add(MySignInMonth mySignInMonth) {			
		throw new NotImplementedException();
	}

	@Override
	public MySignInMonth get(Long id) {		
		throw new NotImplementedException();
	}
	
	@Override
	public boolean delete(Long id){
		throw new NotImplementedException();
	}
	
	@Override
	public boolean update(MySignInMonth mySignInMonth){
		throw new NotImplementedException();
	}
	
	@Override
	public List<MySignInMonth> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, MySignInMonth> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<MySignInMonth> page(int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public Page<MySignInMonth> page(MySignInMonthQuery query, int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public List<MySignInMonth> listAll(){	
		throw new NotImplementedException();
	}

    @Override
    public MySignInMonth getByYearAndMonth(long userId, int year, int month) {
        throw new NotImplementedException();
    }
}

