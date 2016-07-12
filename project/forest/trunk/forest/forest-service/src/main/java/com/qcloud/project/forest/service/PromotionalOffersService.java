package com.qcloud.project.forest.service;

import java.util.List;

import com.qcloud.pirates.data.Page;
import com.qcloud.project.forest.model.PromotionalOffers;
import com.qcloud.project.forest.model.query.PromotionalOffersQuery;

public interface PromotionalOffersService {
	
	public boolean add(PromotionalOffers promotionalOffers);	
	
	public PromotionalOffers get(Long id);
	
	public	boolean delete(Long id);
	
	public	boolean update(PromotionalOffers promotionalOffers);

	public List<PromotionalOffers> listByClassify(Long classify);

	public Page<PromotionalOffers> page(PromotionalOffersQuery query, int start, int count);
	
	public List<PromotionalOffers> listAll();
}

