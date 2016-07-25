package com.qcloud.project.forest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.pirates.data.Page;
import com.qcloud.project.forest.dao.GradeMerchandiseDao;
import com.qcloud.project.forest.model.GradeMerchandise;
import com.qcloud.project.forest.service.GradeMerchandiseService;
import com.qcloud.project.forest.model.query.GradeMerchandiseQuery;
		
@Service
public class GradeMerchandiseServiceImpl implements GradeMerchandiseService{
	
	@Autowired
	private GradeMerchandiseDao gradeMerchandiseDao;
	
	@Autowired
	private AutoIdGenerator autoIdGenerator;
	
	private static final String ID_KEY = "forest_grade_merchandise";

	@Override
	public boolean add(GradeMerchandise gradeMerchandise) {
		long id = autoIdGenerator.get(ID_KEY);
		gradeMerchandise.setId(id);
		
		return gradeMerchandiseDao.add(gradeMerchandise);
	}

	@Override
	public GradeMerchandise get(Long id) {	
		return gradeMerchandiseDao.get(id);		
	}

	@Override
	public boolean delete(Long id) {
		return gradeMerchandiseDao.delete(id);
	}
	
	@Override
	public boolean update(GradeMerchandise gradeMerchandise) {
		return gradeMerchandiseDao.update(gradeMerchandise);
	}
	
	@Override
	public Page<GradeMerchandise> page(GradeMerchandiseQuery query, int start, int count) {
		return gradeMerchandiseDao.page(query, start, count);
	}
	
	public List<GradeMerchandise> listAll(){
		return gradeMerchandiseDao.listAll();
	}
}

