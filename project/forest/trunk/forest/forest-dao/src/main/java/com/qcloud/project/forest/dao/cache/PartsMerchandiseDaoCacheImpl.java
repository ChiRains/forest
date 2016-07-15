package com.qcloud.project.forest.dao.cache;

import java.util.Map;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.project.forest.dao.PartsMerchandiseDao;
import com.qcloud.project.forest.model.PartsMerchandise;
import com.qcloud.project.forest.model.query.PartsMerchandiseQuery;

@Repository
public class PartsMerchandiseDaoCacheImpl implements PartsMerchandiseDao {
	
	@Autowired
	private PartsMerchandiseDao partsMerchandiseDaoMysqlImpl;
	
	@Autowired
	private PartsMerchandiseDao partsMerchandiseDaoRedisImpl;

	@Override
	public boolean add(PartsMerchandise partsMerchandise) {
		return partsMerchandiseDaoMysqlImpl.add(partsMerchandise);
	}

	@Override
	public PartsMerchandise get(Long id) {
		return CacheLoader.get(partsMerchandiseDaoRedisImpl, partsMerchandiseDaoMysqlImpl, id);
	}

	@Override
	public boolean delete(Long id){
		return partsMerchandiseDaoMysqlImpl.delete(id);
	}
	
	@Override
	public boolean update(PartsMerchandise partsMerchandise){
		return partsMerchandiseDaoMysqlImpl.update(partsMerchandise);
	}
	
	@Override
	public List<PartsMerchandise> list(List<Long> idList) {
		return CacheLoader.list(partsMerchandiseDaoRedisImpl, partsMerchandiseDaoMysqlImpl, idList);
	}

	@Override
	public Map<Long, PartsMerchandise> map(Set<Long> idSet){
		return CacheLoader.map(partsMerchandiseDaoRedisImpl, partsMerchandiseDaoMysqlImpl, idSet);
	}
			
		public List<PartsMerchandise> listByClassifyId(Long classifyId){
			return partsMerchandiseDaoMysqlImpl.listByClassifyId(classifyId);
		}
	
	@Override
	public Page<PartsMerchandise> page(int start, int count){
		return partsMerchandiseDaoMysqlImpl.page(start, count);
	}
	
	@Override
	public Page<PartsMerchandise> page(PartsMerchandiseQuery query, int start, int count){
		return partsMerchandiseDaoMysqlImpl.page(query, start, count);
	}
	
	public List<PartsMerchandise> listAll(){
		return partsMerchandiseDaoMysqlImpl.listAll();
	}
}

