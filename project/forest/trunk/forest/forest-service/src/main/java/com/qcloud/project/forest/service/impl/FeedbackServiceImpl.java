package com.qcloud.project.forest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.pirates.data.Page;
import com.qcloud.project.forest.dao.FeedbackDao;
import com.qcloud.project.forest.model.Feedback;
import com.qcloud.project.forest.service.FeedbackService;
import com.qcloud.project.forest.model.query.FeedbackQuery;
		
@Service
public class FeedbackServiceImpl implements FeedbackService{
	
	@Autowired
	private FeedbackDao feedbackDao;
	
	@Autowired
	private AutoIdGenerator autoIdGenerator;
	
	private static final String ID_KEY = "forest_feedback";

	@Override
	public boolean add(Feedback feedback) {
		long id = autoIdGenerator.get(ID_KEY);
		feedback.setId(id);
		
		return feedbackDao.add(feedback);
	}

	@Override
	public Feedback get(Long id) {	
		return feedbackDao.get(id);		
	}

	@Override
	public boolean delete(Long id) {
		return feedbackDao.delete(id);
	}
	
	@Override
	public boolean update(Feedback feedback) {
		return feedbackDao.update(feedback);
	}
	
	@Override
	public Page<Feedback> page(FeedbackQuery query, int start, int count) {
		return feedbackDao.page(query, start, count);
	}
	
	public List<Feedback> listAll(){
		return feedbackDao.listAll();
	}
}

