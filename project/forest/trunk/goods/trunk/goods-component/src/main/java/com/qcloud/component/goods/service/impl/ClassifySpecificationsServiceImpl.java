package com.qcloud.component.goods.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.goods.dao.ClassifySpecificationsDao;
import com.qcloud.component.goods.model.ClassifySpecifications;
import com.qcloud.component.goods.service.ClassifySpecificationsService;
import com.qcloud.component.goods.model.query.ClassifySpecificationsQuery;
		
@Service
public class ClassifySpecificationsServiceImpl implements ClassifySpecificationsService{
	
	@Autowired
	private ClassifySpecificationsDao classifySpecificationsDao;
	
	@Autowired
	private AutoIdGenerator autoIdGenerator;
	
	private static final String ID_KEY = "goods_classify_specifications";

	@Override
	public boolean add(ClassifySpecifications classifySpecifications) {
		long id = autoIdGenerator.get(ID_KEY);
		classifySpecifications.setId(id);
		
		return classifySpecificationsDao.add(classifySpecifications);
	}

	@Override
	public ClassifySpecifications get(Long id) {	
		return classifySpecificationsDao.get(id);		
	}

	@Override
	public boolean delete(Long id) {
		return classifySpecificationsDao.delete(id);
	}
	
	@Override
	public boolean update(ClassifySpecifications classifySpecifications) {
		return classifySpecificationsDao.update(classifySpecifications);
	}
	
	@Override
	public Page<ClassifySpecifications> page(ClassifySpecificationsQuery query, int start, int count) {
		return classifySpecificationsDao.page(query, start, count);
	}
	
	public List<ClassifySpecifications> listAll(){
		return classifySpecificationsDao.listAll();
	}
}

