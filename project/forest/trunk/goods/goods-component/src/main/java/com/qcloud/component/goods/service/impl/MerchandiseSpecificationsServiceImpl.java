package com.qcloud.component.goods.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.goods.dao.MerchandiseSpecificationsDao;
import com.qcloud.component.goods.model.MerchandiseSpecifications;
import com.qcloud.component.goods.service.MerchandiseSpecificationsService;
import com.qcloud.component.goods.model.query.MerchandiseSpecificationsQuery;
		
@Service
public class MerchandiseSpecificationsServiceImpl implements MerchandiseSpecificationsService{
	
	@Autowired
	private MerchandiseSpecificationsDao merchandiseSpecificationsDao;
	
	@Autowired
	private AutoIdGenerator autoIdGenerator;
	
	private static final String ID_KEY = "goods_merchandise_specifications";

	@Override
	public boolean add(MerchandiseSpecifications merchandiseSpecifications) {
		long id = autoIdGenerator.get(ID_KEY);
		merchandiseSpecifications.setId(id);
		
		return merchandiseSpecificationsDao.add(merchandiseSpecifications);
	}

	@Override
	public MerchandiseSpecifications get(Long id) {	
		return merchandiseSpecificationsDao.get(id);		
	}

	@Override
	public boolean delete(Long id) {
		return merchandiseSpecificationsDao.delete(id);
	}
	
	@Override
	public boolean update(MerchandiseSpecifications merchandiseSpecifications) {
		return merchandiseSpecificationsDao.update(merchandiseSpecifications);
	}
	
	@Override
	public Page<MerchandiseSpecifications> page(MerchandiseSpecificationsQuery query, int start, int count) {
		return merchandiseSpecificationsDao.page(query, start, count);
	}
	
	public List<MerchandiseSpecifications> listAll(){
		return merchandiseSpecificationsDao.listAll();
	}
}

