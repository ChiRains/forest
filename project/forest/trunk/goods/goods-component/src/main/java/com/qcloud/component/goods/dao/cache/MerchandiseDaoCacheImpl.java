package com.qcloud.component.goods.dao.cache;

import java.util.Map;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.goods.dao.MerchandiseDao;
import com.qcloud.component.goods.model.Merchandise;
import com.qcloud.component.goods.model.query.MerchandiseQuery;

@Repository
public class MerchandiseDaoCacheImpl implements MerchandiseDao {
	
	@Autowired
	private MerchandiseDao merchandiseDaoMysqlImpl;
	
	@Autowired
	private MerchandiseDao merchandiseDaoRedisImpl;

	@Override
	public boolean add(Merchandise merchandise) {
		return merchandiseDaoMysqlImpl.add(merchandise);
	}

	@Override
	public Merchandise get(Long id) {
		return CacheLoader.get(merchandiseDaoRedisImpl, merchandiseDaoMysqlImpl, id);
	}

	@Override
	public boolean delete(Long id){
		return merchandiseDaoMysqlImpl.delete(id);
	}
	
	@Override
	public boolean update(Merchandise merchandise){
		return merchandiseDaoMysqlImpl.update(merchandise);
	}
	
	@Override
	public List<Merchandise> list(List<Long> idList) {
		return CacheLoader.list(merchandiseDaoRedisImpl, merchandiseDaoMysqlImpl, idList);
	}

	@Override
	public Map<Long, Merchandise> map(Set<Long> idSet){
		return CacheLoader.map(merchandiseDaoRedisImpl, merchandiseDaoMysqlImpl, idSet);
	}

	@Override
	public Page<Merchandise> page(int start, int count){
		return merchandiseDaoMysqlImpl.page(start, count);
	}
	
	@Override
	public Page<Merchandise> page(MerchandiseQuery query, int start, int count){
		return merchandiseDaoMysqlImpl.page(query, start, count);
	}
	
	public List<Merchandise> listAll(){
		return merchandiseDaoMysqlImpl.listAll();
	}
}

