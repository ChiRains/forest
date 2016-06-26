package com.qcloud.component.goods.dao.cache;

import java.util.Map;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.goods.dao.CombinationMerchandiseItemDao;
import com.qcloud.component.goods.model.CombinationMerchandiseItem;
import com.qcloud.component.goods.model.query.CombinationMerchandiseItemQuery;

@Repository
public class CombinationMerchandiseItemDaoCacheImpl implements CombinationMerchandiseItemDao {
	
	@Autowired
	private CombinationMerchandiseItemDao combinationMerchandiseItemDaoMysqlImpl;
	
	@Autowired
	private CombinationMerchandiseItemDao combinationMerchandiseItemDaoRedisImpl;

	@Override
	public boolean add(CombinationMerchandiseItem combinationMerchandiseItem) {
		return combinationMerchandiseItemDaoMysqlImpl.add(combinationMerchandiseItem);
	}

	@Override
	public CombinationMerchandiseItem get(Long id) {
		return CacheLoader.get(combinationMerchandiseItemDaoRedisImpl, combinationMerchandiseItemDaoMysqlImpl, id);
	}

	@Override
	public boolean delete(Long id){
		return combinationMerchandiseItemDaoMysqlImpl.delete(id);
	}
	
	@Override
	public boolean update(CombinationMerchandiseItem combinationMerchandiseItem){
		return combinationMerchandiseItemDaoMysqlImpl.update(combinationMerchandiseItem);
	}
	
	@Override
	public List<CombinationMerchandiseItem> list(List<Long> idList) {
		return CacheLoader.list(combinationMerchandiseItemDaoRedisImpl, combinationMerchandiseItemDaoMysqlImpl, idList);
	}

	@Override
	public Map<Long, CombinationMerchandiseItem> map(Set<Long> idSet){
		return CacheLoader.map(combinationMerchandiseItemDaoRedisImpl, combinationMerchandiseItemDaoMysqlImpl, idSet);
	}

	@Override
	public Page<CombinationMerchandiseItem> page(int start, int count){
		return combinationMerchandiseItemDaoMysqlImpl.page(start, count);
	}
	
	@Override
	public Page<CombinationMerchandiseItem> page(CombinationMerchandiseItemQuery query, int start, int count){
		return combinationMerchandiseItemDaoMysqlImpl.page(query, start, count);
	}
	
	public List<CombinationMerchandiseItem> listAll(){
		return combinationMerchandiseItemDaoMysqlImpl.listAll();
	}
}

