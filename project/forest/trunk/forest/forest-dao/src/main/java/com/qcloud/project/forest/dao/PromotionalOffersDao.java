package com.qcloud.project.forest.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.project.forest.model.PromotionalOffers;
import com.qcloud.project.forest.model.query.PromotionalOffersQuery;
		
public interface PromotionalOffersDao extends ISimpleDao<PromotionalOffers, Long> {

	public boolean add(PromotionalOffers promotionalOffers);	
	
	public PromotionalOffers get(Long id);

	public boolean delete(Long id);
	
	public boolean update(PromotionalOffers promotionalOffers);
	
	public List<PromotionalOffers> list(List<Long> idList);
	
	public Map<Long, PromotionalOffers> map(Set<Long> idSet);
	
	public Page<PromotionalOffers> page(PromotionalOffersQuery query, int start, int size);

	public List<PromotionalOffers> listAll();
	
	public List<PromotionalOffers> listByClassify(Long classify);

}
