package com.qcloud.component.goods.dao.cache;

import java.util.Map;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.goods.dao.MerchandiseAttributeDao;
import com.qcloud.component.goods.model.MerchandiseAttribute;
import com.qcloud.component.goods.model.query.MerchandiseAttributeQuery;

@Repository
public class MerchandiseAttributeDaoCacheImpl implements MerchandiseAttributeDao {
	
	@Autowired
	private MerchandiseAttributeDao merchandiseAttributeDaoMysqlImpl;
	
	@Autowired
	private MerchandiseAttributeDao merchandiseAttributeDaoRedisImpl;

	@Override
	public boolean add(MerchandiseAttribute merchandiseAttribute) {
		return merchandiseAttributeDaoMysqlImpl.add(merchandiseAttribute);
	}

	@Override
	public MerchandiseAttribute get(Long id) {
		return CacheLoader.get(merchandiseAttributeDaoRedisImpl, merchandiseAttributeDaoMysqlImpl, id);
	}

	@Override
	public boolean delete(Long id){
		return merchandiseAttributeDaoMysqlImpl.delete(id);
	}
	
	@Override
	public boolean update(MerchandiseAttribute merchandiseAttribute){
		return merchandiseAttributeDaoMysqlImpl.update(merchandiseAttribute);
	}
	
	@Override
	public List<MerchandiseAttribute> list(List<Long> idList) {
		return CacheLoader.list(merchandiseAttributeDaoRedisImpl, merchandiseAttributeDaoMysqlImpl, idList);
	}

	@Override
	public Map<Long, MerchandiseAttribute> map(Set<Long> idSet){
		return CacheLoader.map(merchandiseAttributeDaoRedisImpl, merchandiseAttributeDaoMysqlImpl, idSet);
	}

	@Override
	public Page<MerchandiseAttribute> page(int start, int count){
		return merchandiseAttributeDaoMysqlImpl.page(start, count);
	}
	
	@Override
	public Page<MerchandiseAttribute> page(MerchandiseAttributeQuery query, int start, int count){
		return merchandiseAttributeDaoMysqlImpl.page(query, start, count);
	}
	
	public List<MerchandiseAttribute> listAll(){
		return merchandiseAttributeDaoMysqlImpl.listAll();
	}
}

