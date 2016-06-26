package com.qcloud.component.goods.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.goods.dao.EnumerationDao;
import com.qcloud.component.goods.model.Enumeration;
import com.qcloud.component.goods.service.EnumerationService;
import com.qcloud.component.goods.model.query.EnumerationQuery;
		
@Service
public class EnumerationServiceImpl implements EnumerationService{
	
	@Autowired
	private EnumerationDao enumerationDao;
	
	@Autowired
	private AutoIdGenerator autoIdGenerator;
	
	private static final String ID_KEY = "goods_enumeration";

	@Override
	public boolean add(Enumeration enumeration) {
		long id = autoIdGenerator.get(ID_KEY);
		enumeration.setId(id);
		
		return enumerationDao.add(enumeration);
	}

	@Override
	public Enumeration get(Long id) {	
		return enumerationDao.get(id);		
	}

	@Override
	public boolean delete(Long id) {
		return enumerationDao.delete(id);
	}
	
	@Override
	public boolean update(Enumeration enumeration) {
		return enumerationDao.update(enumeration);
	}
	
	@Override
	public Page<Enumeration> page(EnumerationQuery query, int start, int count) {
		return enumerationDao.page(query, start, count);
	}
	
	public List<Enumeration> listAll(){
		return enumerationDao.listAll();
	}
}

