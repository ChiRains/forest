package com.qcloud.project.forest.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.project.forest.model.Feedback;
import com.qcloud.project.forest.model.query.FeedbackQuery;
		
public interface FeedbackDao extends ISimpleDao<Feedback, Long> {

	public boolean add(Feedback feedback);	
	
	public Feedback get(Long id);

	public boolean delete(Long id);
	
	public boolean update(Feedback feedback);
	
	public List<Feedback> list(List<Long> idList);
	
	public Map<Long, Feedback> map(Set<Long> idSet);
	
	public Page<Feedback> page(FeedbackQuery query, int start, int size);

	public List<Feedback> listAll();
	
}
