package com.qcloud.project.forest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.pirates.data.Page;
import com.qcloud.project.forest.dao.PromotionalOffersDao;
import com.qcloud.project.forest.model.PromotionalOffers;
import com.qcloud.project.forest.service.PromotionalOffersService;
import com.qcloud.project.forest.model.query.PromotionalOffersQuery;
		
@Service
public class PromotionalOffersServiceImpl implements PromotionalOffersService{
	
	@Autowired
	private PromotionalOffersDao promotionalOffersDao;
	
	@Autowired
	private AutoIdGenerator autoIdGenerator;
	
	private static final String ID_KEY = "forest_promotional_offers";

	@Override
	public boolean add(PromotionalOffers promotionalOffers) {
		long id = autoIdGenerator.get(ID_KEY);
		promotionalOffers.setId(id);
		
		return promotionalOffersDao.add(promotionalOffers);
	}

	@Override
	public PromotionalOffers get(Long id) {	
		return promotionalOffersDao.get(id);		
	}

	@Override
	public boolean delete(Long id) {
		return promotionalOffersDao.delete(id);
	}
	
	@Override
	public boolean update(PromotionalOffers promotionalOffers) {
		return promotionalOffersDao.update(promotionalOffers);
	}
			
	public List<PromotionalOffers> listByClassify(Long classify){
		return promotionalOffersDao.listByClassify(classify);
	}
	
	@Override
	public Page<PromotionalOffers> page(PromotionalOffersQuery query, int start, int count) {
		return promotionalOffersDao.page(query, start, count);
	}
	
	public List<PromotionalOffers> listAll(){
		return promotionalOffersDao.listAll();
	}
}

