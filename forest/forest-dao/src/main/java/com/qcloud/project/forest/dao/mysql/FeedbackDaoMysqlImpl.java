package com.qcloud.project.forest.dao.mysql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.apache.commons.lang.NotImplementedException;

import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.sql.mybatis.SqlOperator;
import com.qcloud.project.forest.dao.FeedbackDao;
import com.qcloud.project.forest.model.Feedback;
import com.qcloud.project.forest.model.query.FeedbackQuery;
		
@Repository
public class FeedbackDaoMysqlImpl implements FeedbackDao {

	@Resource(name = "sqlOperator-forest")
	private SqlOperator sqlOperator;

	@Override
	public boolean add(Feedback feedback) {
		return sqlOperator.insert("com.qcloud.project.forest.dao.mysql.mapper.FeedbackMapper.insert", feedback) == 1;
	}	
	
	@Override
	public Feedback get(Long id) {
		return sqlOperator.selectOne("com.qcloud.project.forest.dao.mysql.mapper.FeedbackMapper.get", id);
	}	
	
	@Override
	public boolean delete(Long id){
		return sqlOperator.delete("com.qcloud.project.forest.dao.mysql.mapper.FeedbackMapper.delete", id) > 0;
	}	
		
	@Override
	public boolean update(Feedback feedback){
		return sqlOperator.update("com.qcloud.project.forest.dao.mysql.mapper.FeedbackMapper.update", feedback) > 0;
	}
	
	@Override
	public List<Feedback> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, Feedback> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<Feedback> page(int start, int count){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("count", count);

		List<Feedback> list = sqlOperator.selectList(
				"com.qcloud.project.forest.dao.mysql.mapper.FeedbackMapper.list4page",
				param);
		int total = sqlOperator.selectOne(
				"com.qcloud.project.forest.dao.mysql.mapper.FeedbackMapper.count4page",
				param);
		Page<Feedback> page = new Page<Feedback>();
		page.setCount(total);
		page.setData(list);
		return page;
	}
	
	@Override
	public Page<Feedback> page(FeedbackQuery query, int start, int count){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("count", count);

		List<Feedback> list = sqlOperator.selectList(
				"com.qcloud.project.forest.dao.mysql.mapper.FeedbackMapper.list4query",
				param);
		int total = sqlOperator.selectOne(
				"com.qcloud.project.forest.dao.mysql.mapper.FeedbackMapper.count4query",
				param);
		Page<Feedback> page = new Page<Feedback>();
		page.setCount(total);
		page.setData(list);
		return page;
	}
	
	@Override
	public List<Feedback> listAll(){	
		List<Feedback> list = sqlOperator.selectList(
				"com.qcloud.project.forest.dao.mysql.mapper.FeedbackMapper.listAll");
		return list;
	}
}

