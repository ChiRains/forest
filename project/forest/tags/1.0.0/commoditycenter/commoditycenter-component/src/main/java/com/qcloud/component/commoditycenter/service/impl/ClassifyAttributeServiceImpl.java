package com.qcloud.component.commoditycenter.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.commoditycenter.dao.ClassifyAttributeDao;
import com.qcloud.component.commoditycenter.model.ClassifyAttribute;
import com.qcloud.component.commoditycenter.service.ClassifyAttributeService;
import com.qcloud.component.commoditycenter.model.query.ClassifyAttributeQuery;
		
@Service
public class ClassifyAttributeServiceImpl implements ClassifyAttributeService{
	
	@Autowired
	private ClassifyAttributeDao classifyAttributeDao;
	
	@Autowired
	private AutoIdGenerator autoIdGenerator;
	
	private static final String ID_KEY = "commoditycenter_classify_attribute";

	@Override
	public boolean add(ClassifyAttribute classifyAttribute) {
		long id = autoIdGenerator.get(ID_KEY);
		classifyAttribute.setId(id);
		
		return classifyAttributeDao.add(classifyAttribute);
	}

	@Override
	public ClassifyAttribute get(Long id) {	
		return classifyAttributeDao.get(id);		
	}

	@Override
	public boolean delete(Long id) {
		return classifyAttributeDao.delete(id);
	}
	
	@Override
	public boolean update(ClassifyAttribute classifyAttribute) {
		return classifyAttributeDao.update(classifyAttribute);
	}
	
	@Override
	public Page<ClassifyAttribute> page(ClassifyAttributeQuery query, int start, int count) {
		return classifyAttributeDao.page(query, start, count);
	}
	
	public List<ClassifyAttribute> listAll(){
		return classifyAttributeDao.listAll();
	}

    @Override
    public ClassifyAttribute get(Long classifyId, Long attributeId) {
        return classifyAttributeDao.get(classifyId, attributeId);
    }

    @Override
    public List<ClassifyAttribute> listByClassify(Long classifyId) {
        return classifyAttributeDao.listByClassify(classifyId);
    }
}

