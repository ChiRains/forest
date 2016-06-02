package com.qcloud.component.my.dao.mysql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.apache.commons.lang.NotImplementedException;

import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.sql.mybatis.SqlOperator;
import com.qcloud.component.my.dao.QuestionnaireAnswersDao;
import com.qcloud.component.my.model.QuestionnaireAnswers;
import com.qcloud.component.my.model.query.QuestionnaireAnswersQuery;
		
@Repository
public class QuestionnaireAnswersDaoMysqlImpl implements QuestionnaireAnswersDao {

	@Resource(name = "sqlOperator-my")
	private SqlOperator sqlOperator;

	@Override
	public boolean add(QuestionnaireAnswers questionnaireAnswers) {
		return sqlOperator.insert("com.qcloud.component.my.dao.mysql.mapper.QuestionnaireAnswersMapper.insert", questionnaireAnswers) == 1;
	}	
	
	@Override
	public QuestionnaireAnswers get(Long id) {
		return sqlOperator.selectOne("com.qcloud.component.my.dao.mysql.mapper.QuestionnaireAnswersMapper.get", id);
	}	
	
	@Override
	public boolean delete(Long id){
		return sqlOperator.delete("com.qcloud.component.my.dao.mysql.mapper.QuestionnaireAnswersMapper.delete", id) > 0;
	}	
		
	@Override
	public boolean update(QuestionnaireAnswers questionnaireAnswers){
		return sqlOperator.update("com.qcloud.component.my.dao.mysql.mapper.QuestionnaireAnswersMapper.update", questionnaireAnswers) > 0;
	}
	
	@Override
	public List<QuestionnaireAnswers> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, QuestionnaireAnswers> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<QuestionnaireAnswers> page(int start, int count){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("count", count);

		List<QuestionnaireAnswers> list = sqlOperator.selectList(
				"com.qcloud.component.my.dao.mysql.mapper.QuestionnaireAnswersMapper.list4page",
				param);
		int total = sqlOperator.selectOne(
				"com.qcloud.component.my.dao.mysql.mapper.QuestionnaireAnswersMapper.count4page",
				param);
		Page<QuestionnaireAnswers> page = new Page<QuestionnaireAnswers>();
		page.setCount(total);
		page.setData(list);
		return page;
	}
	
	@Override
	public Page<QuestionnaireAnswers> page(QuestionnaireAnswersQuery query, int start, int count){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("count", count);
		param.put("questionId", query.getQuestionId());

		List<QuestionnaireAnswers> list = sqlOperator.selectList(
				"com.qcloud.component.my.dao.mysql.mapper.QuestionnaireAnswersMapper.list4query",
				param);
		int total = sqlOperator.selectOne(
				"com.qcloud.component.my.dao.mysql.mapper.QuestionnaireAnswersMapper.count4query",
				param);
		Page<QuestionnaireAnswers> page = new Page<QuestionnaireAnswers>();
		page.setCount(total);
		page.setData(list);
		return page;
	}
	
	@Override
	public List<QuestionnaireAnswers> listAll(){	
		List<QuestionnaireAnswers> list = sqlOperator.selectList(
				"com.qcloud.component.my.dao.mysql.mapper.QuestionnaireAnswersMapper.listAll");
		return list;
	}
}

