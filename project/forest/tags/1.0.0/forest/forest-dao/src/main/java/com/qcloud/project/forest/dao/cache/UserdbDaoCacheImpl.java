package com.qcloud.project.forest.dao.cache;

import java.util.Map;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.project.forest.dao.UserdbDao;
import com.qcloud.project.forest.model.Userdb;
import com.qcloud.project.forest.model.query.UserdbQuery;

@Repository
public class UserdbDaoCacheImpl implements UserdbDao {
	
	@Autowired
	private UserdbDao userdbDaoMysqlImpl;
	
	@Autowired
	private UserdbDao userdbDaoRedisImpl;

	@Override
	public boolean add(Userdb userdb) {
		return userdbDaoMysqlImpl.add(userdb);
	}

	@Override
	public Userdb get(Long id) {
		//return CacheLoader.get(userdbDaoRedisImpl, userdbDaoMysqlImpl, id);
		return userdbDaoMysqlImpl.get(id);
	}

	@Override
	public boolean delete(Long id){
		return userdbDaoMysqlImpl.delete(id);
	}
	
	@Override
	public boolean update(Userdb userdb){
		return userdbDaoMysqlImpl.update(userdb);
	}
	
	@Override
	public List<Userdb> list(List<Long> idList) {
		return CacheLoader.list(userdbDaoRedisImpl, userdbDaoMysqlImpl, idList);
	}

	@Override
	public Map<Long, Userdb> map(Set<Long> idSet){
		return CacheLoader.map(userdbDaoRedisImpl, userdbDaoMysqlImpl, idSet);
	}

	@Override
	public Page<Userdb> page(int start, int count){
		return userdbDaoMysqlImpl.page(start, count);
	}
	
	@Override
	public Page<Userdb> page(UserdbQuery query, int start, int count){
		return userdbDaoMysqlImpl.page(query, start, count);
	}
	
	public List<Userdb> listAll(){
		return userdbDaoMysqlImpl.listAll();
	}
}

