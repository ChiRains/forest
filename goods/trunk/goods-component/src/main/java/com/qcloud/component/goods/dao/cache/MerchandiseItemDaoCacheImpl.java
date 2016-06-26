package com.qcloud.component.goods.dao.cache;

import java.util.Map;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.goods.dao.MerchandiseItemDao;
import com.qcloud.component.goods.model.MerchandiseItem;
import com.qcloud.component.goods.model.query.MerchandiseItemQuery;

@Repository
public class MerchandiseItemDaoCacheImpl implements MerchandiseItemDao {
	
	@Autowired
	private MerchandiseItemDao merchandiseItemDaoMysqlImpl;
	
	@Autowired
	private MerchandiseItemDao merchandiseItemDaoRedisImpl;

	@Override
	public boolean add(MerchandiseItem merchandiseItem) {
		return merchandiseItemDaoMysqlImpl.add(merchandiseItem);
	}

	@Override
	public MerchandiseItem get(Long id) {
		return CacheLoader.get(merchandiseItemDaoRedisImpl, merchandiseItemDaoMysqlImpl, id);
	}

	@Override
	public boolean delete(Long id){
		return merchandiseItemDaoMysqlImpl.delete(id);
	}
	
	@Override
	public boolean update(MerchandiseItem merchandiseItem){
		return merchandiseItemDaoMysqlImpl.update(merchandiseItem);
	}
	
	@Override
	public List<MerchandiseItem> list(List<Long> idList) {
		return CacheLoader.list(merchandiseItemDaoRedisImpl, merchandiseItemDaoMysqlImpl, idList);
	}

	@Override
	public Map<Long, MerchandiseItem> map(Set<Long> idSet){
		return CacheLoader.map(merchandiseItemDaoRedisImpl, merchandiseItemDaoMysqlImpl, idSet);
	}

	@Override
	public Page<MerchandiseItem> page(int start, int count){
		return merchandiseItemDaoMysqlImpl.page(start, count);
	}
	
	@Override
	public Page<MerchandiseItem> page(MerchandiseItemQuery query, int start, int count){
		return merchandiseItemDaoMysqlImpl.page(query, start, count);
	}
	
	public List<MerchandiseItem> listAll(){
		return merchandiseItemDaoMysqlImpl.listAll();
	}
}

