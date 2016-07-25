package com.qcloud.project.forest.dao.cache;

import java.util.Map;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.project.forest.dao.GradeMerchandiseDao;
import com.qcloud.project.forest.model.GradeMerchandise;
import com.qcloud.project.forest.model.query.GradeMerchandiseQuery;

@Repository
public class GradeMerchandiseDaoCacheImpl implements GradeMerchandiseDao {
	
	@Autowired
	private GradeMerchandiseDao gradeMerchandiseDaoMysqlImpl;
	
	@Autowired
	private GradeMerchandiseDao gradeMerchandiseDaoRedisImpl;

	@Override
	public boolean add(GradeMerchandise gradeMerchandise) {
		return gradeMerchandiseDaoMysqlImpl.add(gradeMerchandise);
	}

	@Override
	public GradeMerchandise get(Long id) {
		return CacheLoader.get(gradeMerchandiseDaoRedisImpl, gradeMerchandiseDaoMysqlImpl, id);
	}

	@Override
	public boolean delete(Long id){
		return gradeMerchandiseDaoMysqlImpl.delete(id);
	}
	
	@Override
	public boolean update(GradeMerchandise gradeMerchandise){
		return gradeMerchandiseDaoMysqlImpl.update(gradeMerchandise);
	}
	
	@Override
	public List<GradeMerchandise> list(List<Long> idList) {
		return CacheLoader.list(gradeMerchandiseDaoRedisImpl, gradeMerchandiseDaoMysqlImpl, idList);
	}

	@Override
	public Map<Long, GradeMerchandise> map(Set<Long> idSet){
		return CacheLoader.map(gradeMerchandiseDaoRedisImpl, gradeMerchandiseDaoMysqlImpl, idSet);
	}

	@Override
	public Page<GradeMerchandise> page(int start, int count){
		return gradeMerchandiseDaoMysqlImpl.page(start, count);
	}
	
	@Override
	public Page<GradeMerchandise> page(GradeMerchandiseQuery query, int start, int count){
		return gradeMerchandiseDaoMysqlImpl.page(query, start, count);
	}
	
	public List<GradeMerchandise> listAll(){
		return gradeMerchandiseDaoMysqlImpl.listAll();
	}
}

