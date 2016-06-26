package com.qcloud.component.goods.dao.cache;

import java.util.Map;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.goods.dao.MerchandiseSpecificationsDao;
import com.qcloud.component.goods.model.MerchandiseSpecifications;
import com.qcloud.component.goods.model.query.MerchandiseSpecificationsQuery;

@Repository
public class MerchandiseSpecificationsDaoCacheImpl implements MerchandiseSpecificationsDao {
	
	@Autowired
	private MerchandiseSpecificationsDao merchandiseSpecificationsDaoMysqlImpl;
	
	@Autowired
	private MerchandiseSpecificationsDao merchandiseSpecificationsDaoRedisImpl;

	@Override
	public boolean add(MerchandiseSpecifications merchandiseSpecifications) {
		return merchandiseSpecificationsDaoMysqlImpl.add(merchandiseSpecifications);
	}

	@Override
	public MerchandiseSpecifications get(Long id) {
		return CacheLoader.get(merchandiseSpecificationsDaoRedisImpl, merchandiseSpecificationsDaoMysqlImpl, id);
	}

	@Override
	public boolean delete(Long id){
		return merchandiseSpecificationsDaoMysqlImpl.delete(id);
	}
	
	@Override
	public boolean update(MerchandiseSpecifications merchandiseSpecifications){
		return merchandiseSpecificationsDaoMysqlImpl.update(merchandiseSpecifications);
	}
	
	@Override
	public List<MerchandiseSpecifications> list(List<Long> idList) {
		return CacheLoader.list(merchandiseSpecificationsDaoRedisImpl, merchandiseSpecificationsDaoMysqlImpl, idList);
	}

	@Override
	public Map<Long, MerchandiseSpecifications> map(Set<Long> idSet){
		return CacheLoader.map(merchandiseSpecificationsDaoRedisImpl, merchandiseSpecificationsDaoMysqlImpl, idSet);
	}

	@Override
	public Page<MerchandiseSpecifications> page(int start, int count){
		return merchandiseSpecificationsDaoMysqlImpl.page(start, count);
	}
	
	@Override
	public Page<MerchandiseSpecifications> page(MerchandiseSpecificationsQuery query, int start, int count){
		return merchandiseSpecificationsDaoMysqlImpl.page(query, start, count);
	}
	
	public List<MerchandiseSpecifications> listAll(){
		return merchandiseSpecificationsDaoMysqlImpl.listAll();
	}
}

