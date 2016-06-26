package com.qcloud.component.goods.dao.cache;

import java.util.Map;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.goods.dao.MerchandiseMarketingDao;
import com.qcloud.component.goods.model.MerchandiseMarketing;
import com.qcloud.component.goods.model.query.MerchandiseMarketingQuery;

@Repository
public class MerchandiseMarketingDaoCacheImpl implements MerchandiseMarketingDao {
	
	@Autowired
	private MerchandiseMarketingDao merchandiseMarketingDaoMysqlImpl;
	
	@Autowired
	private MerchandiseMarketingDao merchandiseMarketingDaoRedisImpl;

	@Override
	public boolean add(MerchandiseMarketing merchandiseMarketing) {
		return merchandiseMarketingDaoMysqlImpl.add(merchandiseMarketing);
	}

	@Override
	public MerchandiseMarketing get(Long id) {
		return CacheLoader.get(merchandiseMarketingDaoRedisImpl, merchandiseMarketingDaoMysqlImpl, id);
	}

	@Override
	public boolean delete(Long id){
		return merchandiseMarketingDaoMysqlImpl.delete(id);
	}
	
	@Override
	public boolean update(MerchandiseMarketing merchandiseMarketing){
		return merchandiseMarketingDaoMysqlImpl.update(merchandiseMarketing);
	}
	
	@Override
	public List<MerchandiseMarketing> list(List<Long> idList) {
		return CacheLoader.list(merchandiseMarketingDaoRedisImpl, merchandiseMarketingDaoMysqlImpl, idList);
	}

	@Override
	public Map<Long, MerchandiseMarketing> map(Set<Long> idSet){
		return CacheLoader.map(merchandiseMarketingDaoRedisImpl, merchandiseMarketingDaoMysqlImpl, idSet);
	}

	@Override
	public Page<MerchandiseMarketing> page(int start, int count){
		return merchandiseMarketingDaoMysqlImpl.page(start, count);
	}
	
	@Override
	public Page<MerchandiseMarketing> page(MerchandiseMarketingQuery query, int start, int count){
		return merchandiseMarketingDaoMysqlImpl.page(query, start, count);
	}
	
	public List<MerchandiseMarketing> listAll(){
		return merchandiseMarketingDaoMysqlImpl.listAll();
	}
}

