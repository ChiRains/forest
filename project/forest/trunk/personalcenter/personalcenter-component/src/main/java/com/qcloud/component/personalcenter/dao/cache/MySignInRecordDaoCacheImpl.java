package com.qcloud.component.personalcenter.dao.cache;

import java.util.Map;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.personalcenter.dao.MySignInRecordDao;
import com.qcloud.component.personalcenter.model.MySignInRecord;
import com.qcloud.component.personalcenter.model.query.MySignInRecordQuery;
import com.qcloud.component.personalcenter.service.MySignInRecordService;

@Repository
public class MySignInRecordDaoCacheImpl implements MySignInRecordDao {
	
	@Autowired
	private MySignInRecordDao mySignInRecordDaoMysqlImpl;
	
	@Autowired
	private MySignInRecordDao mySignInRecordDaoRedisImpl;

	@Override
	public boolean add(MySignInRecord mySignInRecord) {
		return mySignInRecordDaoMysqlImpl.add(mySignInRecord);
	}

	@Override
	public MySignInRecord get(Long id) {
		return  mySignInRecordDaoMysqlImpl.get(id);
	}

	@Override
	public boolean delete(Long id){
		return mySignInRecordDaoMysqlImpl.delete(id);
	}
	
	@Override
	public boolean update(MySignInRecord mySignInRecord){
		return mySignInRecordDaoMysqlImpl.update(mySignInRecord);
	}
	
	@Override
	public List<MySignInRecord> list(List<Long> idList) {
		return CacheLoader.list(mySignInRecordDaoRedisImpl, mySignInRecordDaoMysqlImpl, idList);
	}

	@Override
	public Map<Long, MySignInRecord> map(Set<Long> idSet){
		return CacheLoader.map(mySignInRecordDaoRedisImpl, mySignInRecordDaoMysqlImpl, idSet);
	}

	@Override
	public Page<MySignInRecord> page(int start, int count){
		return mySignInRecordDaoMysqlImpl.page(start, count);
	}
	
	@Override
	public Page<MySignInRecord> page(MySignInRecordQuery query, int start, int count){
		return mySignInRecordDaoMysqlImpl.page(query, start, count);
	}
	
	public List<MySignInRecord> listAll(){
		return mySignInRecordDaoMysqlImpl.listAll();
	}

	@Override
	public MySignInRecord listByUserId(Long userId) {
		return mySignInRecordDaoMysqlImpl.listByUserId(userId);
	}
}

