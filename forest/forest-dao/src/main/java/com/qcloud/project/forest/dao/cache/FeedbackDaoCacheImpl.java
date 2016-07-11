package com.qcloud.project.forest.dao.cache;

import java.util.Map;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.project.forest.dao.FeedbackDao;
import com.qcloud.project.forest.model.Feedback;
import com.qcloud.project.forest.model.query.FeedbackQuery;

@Repository
public class FeedbackDaoCacheImpl implements FeedbackDao {
	
	@Autowired
	private FeedbackDao feedbackDaoMysqlImpl;
	
	@Autowired
	private FeedbackDao feedbackDaoRedisImpl;

	@Override
	public boolean add(Feedback feedback) {
		return feedbackDaoMysqlImpl.add(feedback);
	}

	@Override
	public Feedback get(Long id) {
		return CacheLoader.get(feedbackDaoRedisImpl, feedbackDaoMysqlImpl, id);
	}

	@Override
	public boolean delete(Long id){
		return feedbackDaoMysqlImpl.delete(id);
	}
	
	@Override
	public boolean update(Feedback feedback){
		return feedbackDaoMysqlImpl.update(feedback);
	}
	
	@Override
	public List<Feedback> list(List<Long> idList) {
		return CacheLoader.list(feedbackDaoRedisImpl, feedbackDaoMysqlImpl, idList);
	}

	@Override
	public Map<Long, Feedback> map(Set<Long> idSet){
		return CacheLoader.map(feedbackDaoRedisImpl, feedbackDaoMysqlImpl, idSet);
	}

	@Override
	public Page<Feedback> page(int start, int count){
		return feedbackDaoMysqlImpl.page(start, count);
	}
	
	@Override
	public Page<Feedback> page(FeedbackQuery query, int start, int count){
		return feedbackDaoMysqlImpl.page(query, start, count);
	}
	
	public List<Feedback> listAll(){
		return feedbackDaoMysqlImpl.listAll();
	}
}

