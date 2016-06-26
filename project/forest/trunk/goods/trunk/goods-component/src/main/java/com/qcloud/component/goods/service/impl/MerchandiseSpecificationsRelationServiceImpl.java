package com.qcloud.component.goods.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.goods.dao.MerchandiseSpecificationsRelationDao;
import com.qcloud.component.goods.model.MerchandiseSpecificationsRelation;
import com.qcloud.component.goods.service.MerchandiseSpecificationsRelationService;
import com.qcloud.component.goods.model.query.MerchandiseSpecificationsRelationQuery;
		
@Service
public class MerchandiseSpecificationsRelationServiceImpl implements MerchandiseSpecificationsRelationService{
	
	@Autowired
	private MerchandiseSpecificationsRelationDao merchandiseSpecificationsRelationDao;
	
	@Autowired
	private AutoIdGenerator autoIdGenerator;
	
	private static final String ID_KEY = "goods_merchandise_specifications_relation";

	@Override
	public boolean add(MerchandiseSpecificationsRelation merchandiseSpecificationsRelation) {
		long id = autoIdGenerator.get(ID_KEY);
		merchandiseSpecificationsRelation.setId(id);
		
		return merchandiseSpecificationsRelationDao.add(merchandiseSpecificationsRelation);
	}

	@Override
	public MerchandiseSpecificationsRelation get(Long id) {	
		return merchandiseSpecificationsRelationDao.get(id);		
	}

	@Override
	public boolean delete(Long id) {
		return merchandiseSpecificationsRelationDao.delete(id);
	}
	
	@Override
	public boolean update(MerchandiseSpecificationsRelation merchandiseSpecificationsRelation) {
		return merchandiseSpecificationsRelationDao.update(merchandiseSpecificationsRelation);
	}
	
	@Override
	public Page<MerchandiseSpecificationsRelation> page(MerchandiseSpecificationsRelationQuery query, int start, int count) {
		return merchandiseSpecificationsRelationDao.page(query, start, count);
	}
	
	public List<MerchandiseSpecificationsRelation> listAll(){
		return merchandiseSpecificationsRelationDao.listAll();
	}
}

