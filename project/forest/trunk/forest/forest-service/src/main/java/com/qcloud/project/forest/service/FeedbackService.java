package com.qcloud.project.forest.service;

import java.util.List;

import com.qcloud.pirates.data.Page;
import com.qcloud.project.forest.model.Feedback;
import com.qcloud.project.forest.model.query.FeedbackQuery;

public interface FeedbackService {
	
	public boolean add(Feedback feedback);	
	
	public Feedback get(Long id);
	
	public	boolean delete(Long id);
	
	public	boolean update(Feedback feedback);

	public Page<Feedback> page(FeedbackQuery query, int start, int count);
	
	public List<Feedback> listAll();
}

