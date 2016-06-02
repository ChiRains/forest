package com.qcloud.component.personalcenter.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.component.personalcenter.model.Grade;
import com.qcloud.component.personalcenter.model.query.GradeQuery;
		
public interface GradeDao extends ISimpleDao<Grade, Long> {

	public boolean add(Grade grade);	
	
	public Grade get(Long id);

	public boolean delete(Long id);
	
	public boolean update(Grade grade);
	
	public List<Grade> list(List<Long> idList);
	
	public Map<Long, Grade> map(Set<Long> idSet);
	
	public Page<Grade> page(GradeQuery query, int start, int size);

	public List<Grade> listAll();
	
}
