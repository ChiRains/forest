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
import com.qcloud.component.personalcenter.dao.MySignInRecordDao;
import com.qcloud.component.personalcenter.model.MySignInRecord;
import com.qcloud.component.personalcenter.model.query.MySignInRecordQuery;
import com.qcloud.component.personalcenter.service.MySignInRecordService;

@Repository
public class MySignInRecordDaoRedisImpl implements MySignInRecordDao {

	//@Resource(name = "redis-personalcenter")
	//private Redis redis;

	@Override
	public boolean add(MySignInRecord mySignInRecord) {			
		throw new NotImplementedException();
	}

	@Override
	public MySignInRecord get(Long id) {		
		throw new NotImplementedException();
	}
	
	@Override
	public boolean delete(Long id){
		throw new NotImplementedException();
	}
	
	@Override
	public boolean update(MySignInRecord mySignInRecord){
		throw new NotImplementedException();
	}
	
	@Override
	public List<MySignInRecord> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, MySignInRecord> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<MySignInRecord> page(int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public Page<MySignInRecord> page(MySignInRecordQuery query, int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public List<MySignInRecord> listAll(){	
		throw new NotImplementedException();
	}

	@Override
	public MySignInRecord listByUserId(Long userId) {
		throw new NotImplementedException();
	}
}

