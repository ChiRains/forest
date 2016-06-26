package com.qcloud.component.goods.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.component.goods.model.ClassifyAttribute;
import com.qcloud.component.goods.model.query.ClassifyAttributeQuery;
		
public interface ClassifyAttributeDao extends ISimpleDao<ClassifyAttribute, Long> {

	public boolean add(ClassifyAttribute classifyAttribute);	
	
	public ClassifyAttribute get(Long id);

	public boolean delete(Long id);
	
	public boolean update(ClassifyAttribute classifyAttribute);
	
	public List<ClassifyAttribute> list(List<Long> idList);
	
	public Map<Long, ClassifyAttribute> map(Set<Long> idSet);
	
	public Page<ClassifyAttribute> page(ClassifyAttributeQuery query, int start, int size);

	public List<ClassifyAttribute> listAll();
	
}
