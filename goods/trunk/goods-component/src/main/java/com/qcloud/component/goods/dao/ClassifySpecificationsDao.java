package com.qcloud.component.goods.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.component.goods.model.ClassifySpecifications;
import com.qcloud.component.goods.model.query.ClassifySpecificationsQuery;
		
public interface ClassifySpecificationsDao extends ISimpleDao<ClassifySpecifications, Long> {

	public boolean add(ClassifySpecifications classifySpecifications);	
	
	public ClassifySpecifications get(Long id);

	public boolean delete(Long id);
	
	public boolean update(ClassifySpecifications classifySpecifications);
	
	public List<ClassifySpecifications> list(List<Long> idList);
	
	public Map<Long, ClassifySpecifications> map(Set<Long> idSet);
	
	public Page<ClassifySpecifications> page(ClassifySpecificationsQuery query, int start, int size);

	public List<ClassifySpecifications> listAll();
	
}
