package com.qcloud.component.goods.dao.cache;

import java.util.Map;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.goods.dao.MerchandiseSpecificationsRelationDao;
import com.qcloud.component.goods.model.MerchandiseSpecificationsRelation;
import com.qcloud.component.goods.model.query.MerchandiseSpecificationsRelationQuery;

@Repository
public class MerchandiseSpecificationsRelationDaoCacheImpl implements MerchandiseSpecificationsRelationDao {
	
	@Autowired
	private MerchandiseSpecificationsRelationDao merchandiseSpecificationsRelationDaoMysqlImpl;
	
	@Autowired
	private MerchandiseSpecificationsRelationDao merchandiseSpecificationsRelationDaoRedisImpl;

	@Override
	public boolean add(MerchandiseSpecificationsRelation merchandiseSpecificationsRelation) {
		return merchandiseSpecificationsRelationDaoMysqlImpl.add(merchandiseSpecificationsRelation);
	}

	@Override
	public MerchandiseSpecificationsRelation get(Long id) {
		return CacheLoader.get(merchandiseSpecificationsRelationDaoRedisImpl, merchandiseSpecificationsRelationDaoMysqlImpl, id);
	}

	@Override
	public boolean delete(Long id){
		return merchandiseSpecificationsRelationDaoMysqlImpl.delete(id);
	}
	
	@Override
	public boolean update(MerchandiseSpecificationsRelation merchandiseSpecificationsRelation){
		return merchandiseSpecificationsRelationDaoMysqlImpl.update(merchandiseSpecificationsRelation);
	}
	
	@Override
	public List<MerchandiseSpecificationsRelation> list(List<Long> idList) {
		return CacheLoader.list(merchandiseSpecificationsRelationDaoRedisImpl, merchandiseSpecificationsRelationDaoMysqlImpl, idList);
	}

	@Override
	public Map<Long, MerchandiseSpecificationsRelation> map(Set<Long> idSet){
		return CacheLoader.map(merchandiseSpecificationsRelationDaoRedisImpl, merchandiseSpecificationsRelationDaoMysqlImpl, idSet);
	}

	@Override
	public Page<MerchandiseSpecificationsRelation> page(int start, int count){
		return merchandiseSpecificationsRelationDaoMysqlImpl.page(start, count);
	}
	
	@Override
	public Page<MerchandiseSpecificationsRelation> page(MerchandiseSpecificationsRelationQuery query, int start, int count){
		return merchandiseSpecificationsRelationDaoMysqlImpl.page(query, start, count);
	}
	
	public List<MerchandiseSpecificationsRelation> listAll(){
		return merchandiseSpecificationsRelationDaoMysqlImpl.listAll();
	}
}

