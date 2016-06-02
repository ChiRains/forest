package com.qcloud.component.personalcenter.service;

import java.util.List;

import com.qcloud.pirates.data.Page;
import com.qcloud.component.personalcenter.model.Grade;
import com.qcloud.component.personalcenter.model.query.GradeQuery;

public interface GradeService {
	
	public boolean add(Grade grade);	
	
	public Grade get(Long id);
	
	public	boolean delete(Long id);
	
	public	boolean update(Grade grade);

	public Page<Grade> page(GradeQuery query, int start, int count);
	
	public List<Grade> listAll();
}

