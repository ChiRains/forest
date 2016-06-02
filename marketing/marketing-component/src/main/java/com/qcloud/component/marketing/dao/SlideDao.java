package com.qcloud.component.marketing.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.component.marketing.model.Slide;
import com.qcloud.component.marketing.model.query.SlideQuery;
		
public interface SlideDao extends ISimpleDao<Slide, Long> {

	public boolean add(Slide slide);	
	
	public Slide get(Long id);

	public boolean delete(Long id);
	
	public boolean update(Slide slide);
	
	public List<Slide> list(List<Long> idList);
	
	public Map<Long, Slide> map(Set<Long> idSet);
	
	public Page<Slide> page(SlideQuery query, int start, int size);

	public List<Slide> listAll();
	
	List<Slide> listBySence(int sence);
}
