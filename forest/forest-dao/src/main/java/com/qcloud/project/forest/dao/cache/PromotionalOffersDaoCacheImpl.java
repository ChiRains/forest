package com.qcloud.project.forest.dao.cache;

import java.util.Map;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.project.forest.dao.PromotionalOffersDao;
import com.qcloud.project.forest.model.PromotionalOffers;
import com.qcloud.project.forest.model.query.PromotionalOffersQuery;

@Repository
public class PromotionalOffersDaoCacheImpl implements PromotionalOffersDao {
	
	@Autowired
	private PromotionalOffersDao promotionalOffersDaoMysqlImpl;
	
	@Autowired
	private PromotionalOffersDao promotionalOffersDaoRedisImpl;

	@Override
	public boolean add(PromotionalOffers promotionalOffers) {
		return promotionalOffersDaoMysqlImpl.add(promotionalOffers);
	}

	@Override
	public PromotionalOffers get(Long id) {
		return CacheLoader.get(promotionalOffersDaoRedisImpl, promotionalOffersDaoMysqlImpl, id);
	}

	@Override
	public boolean delete(Long id){
		return promotionalOffersDaoMysqlImpl.delete(id);
	}
	
	@Override
	public boolean update(PromotionalOffers promotionalOffers){
		return promotionalOffersDaoMysqlImpl.update(promotionalOffers);
	}
	
	@Override
	public List<PromotionalOffers> list(List<Long> idList) {
		return CacheLoader.list(promotionalOffersDaoRedisImpl, promotionalOffersDaoMysqlImpl, idList);
	}

	@Override
	public Map<Long, PromotionalOffers> map(Set<Long> idSet){
		return CacheLoader.map(promotionalOffersDaoRedisImpl, promotionalOffersDaoMysqlImpl, idSet);
	}
			
		public List<PromotionalOffers> listByClassify(Long classify){
			return promotionalOffersDaoMysqlImpl.listByClassify(classify);
		}
	
	@Override
	public Page<PromotionalOffers> page(int start, int count){
		return promotionalOffersDaoMysqlImpl.page(start, count);
	}
	
	@Override
	public Page<PromotionalOffers> page(PromotionalOffersQuery query, int start, int count){
		return promotionalOffersDaoMysqlImpl.page(query, start, count);
	}
	
	public List<PromotionalOffers> listAll(){
		return promotionalOffersDaoMysqlImpl.listAll();
	}
}

