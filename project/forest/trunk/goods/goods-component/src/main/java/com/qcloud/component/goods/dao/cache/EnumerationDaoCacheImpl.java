package com.qcloud.component.goods.dao.cache;

import java.util.Map;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.goods.dao.EnumerationDao;
import com.qcloud.component.goods.model.Enumeration;
import com.qcloud.component.goods.model.query.EnumerationQuery;

@Repository
public class EnumerationDaoCacheImpl implements EnumerationDao {
	
	@Autowired
	private EnumerationDao enumerationDaoMysqlImpl;
	
	@Autowired
	private EnumerationDao enumerationDaoRedisImpl;

	@Override
	public boolean add(Enumeration enumeration) {
		return enumerationDaoMysqlImpl.add(enumeration);
	}

	@Override
	public Enumeration get(Long id) {
		return CacheLoader.get(enumerationDaoRedisImpl, enumerationDaoMysqlImpl, id);
	}

	@Override
	public boolean delete(Long id){
		return enumerationDaoMysqlImpl.delete(id);
	}
	
	@Override
	public boolean update(Enumeration enumeration){
		return enumerationDaoMysqlImpl.update(enumeration);
	}
	
	@Override
	public List<Enumeration> list(List<Long> idList) {
		return CacheLoader.list(enumerationDaoRedisImpl, enumerationDaoMysqlImpl, idList);
	}

	@Override
	public Map<Long, Enumeration> map(Set<Long> idSet){
		return CacheLoader.map(enumerationDaoRedisImpl, enumerationDaoMysqlImpl, idSet);
	}

	@Override
	public Page<Enumeration> page(int start, int count){
		return enumerationDaoMysqlImpl.page(start, count);
	}
	
	@Override
	public Page<Enumeration> page(EnumerationQuery query, int start, int count){
		return enumerationDaoMysqlImpl.page(query, start, count);
	}
	
	public List<Enumeration> listAll(){
		return enumerationDaoMysqlImpl.listAll();
	}
}

