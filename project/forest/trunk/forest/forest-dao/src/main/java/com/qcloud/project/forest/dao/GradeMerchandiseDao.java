package com.qcloud.project.forest.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.project.forest.model.GradeMerchandise;
import com.qcloud.project.forest.model.query.GradeMerchandiseQuery;
		
public interface GradeMerchandiseDao extends ISimpleDao<GradeMerchandise, Long> {

	public boolean add(GradeMerchandise gradeMerchandise);	
	
	public GradeMerchandise get(Long id);

	public boolean delete(Long id);
	
	public boolean update(GradeMerchandise gradeMerchandise);
	
	public List<GradeMerchandise> list(List<Long> idList);
	
	public Map<Long, GradeMerchandise> map(Set<Long> idSet);
	
	public Page<GradeMerchandise> page(GradeMerchandiseQuery query, int start, int size);

	public List<GradeMerchandise> listAll();
	
}
