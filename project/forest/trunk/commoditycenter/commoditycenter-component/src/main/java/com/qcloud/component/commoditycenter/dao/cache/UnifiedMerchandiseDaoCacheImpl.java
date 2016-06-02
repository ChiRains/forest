package com.qcloud.component.commoditycenter.dao.cache;

import java.util.Map;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.commoditycenter.dao.UnifiedMerchandiseDao;
import com.qcloud.component.commoditycenter.model.UnifiedMerchandise;
import com.qcloud.component.commoditycenter.model.query.UnifiedMerchandiseQuery;

@Repository
public class UnifiedMerchandiseDaoCacheImpl implements UnifiedMerchandiseDao {
	
	@Autowired
	private UnifiedMerchandiseDao unifiedMerchandiseDaoMysqlImpl;
	
//	@Autowired
//	private UnifiedMerchandiseDao unifiedMerchandiseDaoRedisImpl;

	@Override
	public boolean add(UnifiedMerchandise unifiedMerchandise) {
		return unifiedMerchandiseDaoMysqlImpl.add(unifiedMerchandise);
	}

	@Override
	public UnifiedMerchandise get(Long id) {
		return unifiedMerchandiseDaoMysqlImpl.get(id);
	}

	@Override
	public boolean delete(Long id){
		return unifiedMerchandiseDaoMysqlImpl.delete(id);
	}
	
	@Override
	public boolean update(UnifiedMerchandise unifiedMerchandise){
		return unifiedMerchandiseDaoMysqlImpl.update(unifiedMerchandise);
	}
	
	@Override
	public List<UnifiedMerchandise> list(List<Long> idList) {
		return CacheLoader.list(unifiedMerchandiseDaoMysqlImpl, idList);
	}

	@Override
	public Map<Long, UnifiedMerchandise> map(Set<Long> idSet){
		return CacheLoader.map(unifiedMerchandiseDaoMysqlImpl, idSet);
	}

	@Override
	public Page<UnifiedMerchandise> page(int start, int count){
		return unifiedMerchandiseDaoMysqlImpl.page(start, count);
	}
	
	@Override
	public Page<UnifiedMerchandise> page(UnifiedMerchandiseQuery query, int start, int count){
		return unifiedMerchandiseDaoMysqlImpl.page(query, start, count);
	}
	
	public List<UnifiedMerchandise> listAll(){
		return unifiedMerchandiseDaoMysqlImpl.listAll();
	}
}

