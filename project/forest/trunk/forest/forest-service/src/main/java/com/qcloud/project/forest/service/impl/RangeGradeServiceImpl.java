package com.qcloud.project.forest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.pirates.data.Page;
import com.qcloud.project.forest.dao.RangeGradeDao;
import com.qcloud.project.forest.model.RangeGrade;
import com.qcloud.project.forest.service.RangeGradeService;
import com.qcloud.project.forest.model.query.RangeGradeQuery;
		
@Service
public class RangeGradeServiceImpl implements RangeGradeService{
	
	@Autowired
	private RangeGradeDao rangeGradeDao;
	
	@Autowired
	private AutoIdGenerator autoIdGenerator;
	
	private static final String ID_KEY = "forest_range_grade";

	@Override
	public boolean add(RangeGrade rangeGrade) {
		long id = autoIdGenerator.get(ID_KEY);
		rangeGrade.setId(id);
		
		return rangeGradeDao.add(rangeGrade);
	}

	@Override
	public RangeGrade get(Long id) {	
		return rangeGradeDao.get(id);		
	}

	@Override
	public boolean delete(Long id) {
		return rangeGradeDao.delete(id);
	}
	
	@Override
	public boolean update(RangeGrade rangeGrade) {
		return rangeGradeDao.update(rangeGrade);
	}
	
	@Override
	public Page<RangeGrade> page(RangeGradeQuery query, int start, int count) {
		return rangeGradeDao.page(query, start, count);
	}
	
	public List<RangeGrade> listAll(){
		return rangeGradeDao.listAll();
	}
}

