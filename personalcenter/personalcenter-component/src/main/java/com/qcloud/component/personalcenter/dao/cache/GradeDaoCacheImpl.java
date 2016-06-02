package com.qcloud.component.personalcenter.dao.cache;

import java.util.Map;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.personalcenter.dao.GradeDao;
import com.qcloud.component.personalcenter.model.Grade;
import com.qcloud.component.personalcenter.model.query.GradeQuery;

@Repository
public class GradeDaoCacheImpl implements GradeDao {
	
	@Autowired
	private GradeDao gradeDaoMysqlImpl;
	
//	@Autowired
//	private GradeDao gradeDaoRedisImpl;

	@Override
	public boolean add(Grade grade) {
		return gradeDaoMysqlImpl.add(grade);
	}

	@Override
	public Grade get(Long id) {
	    return gradeDaoMysqlImpl.get(id);
//		return CacheLoader.get(gradeDaoRedisImpl, gradeDaoMysqlImpl, id);
	}

	@Override
	public boolean delete(Long id){
		return gradeDaoMysqlImpl.delete(id);
	}
	
	@Override
	public boolean update(Grade grade){
		return gradeDaoMysqlImpl.update(grade);
	}
	
	@Override
	public List<Grade> list(List<Long> idList) {
	    return CacheLoader.list(gradeDaoMysqlImpl, idList);
//		return CacheLoader.list(gradeDaoRedisImpl, gradeDaoMysqlImpl, idList);
	}

	@Override
	public Map<Long, Grade> map(Set<Long> idSet){
	    return CacheLoader.map(gradeDaoMysqlImpl, idSet);
//		return CacheLoader.map(gradeDaoRedisImpl, gradeDaoMysqlImpl, idSet);
	}

	@Override
	public Page<Grade> page(int start, int count){
		return gradeDaoMysqlImpl.page(start, count);
	}
	
	@Override
	public Page<Grade> page(GradeQuery query, int start, int count){
		return gradeDaoMysqlImpl.page(query, start, count);
	}
	
	public List<Grade> listAll(){
		return gradeDaoMysqlImpl.listAll();
	}
}

