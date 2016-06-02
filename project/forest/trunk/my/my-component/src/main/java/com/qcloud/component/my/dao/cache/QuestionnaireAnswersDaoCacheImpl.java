package com.qcloud.component.my.dao.cache;

import java.util.Map;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.my.dao.QuestionnaireAnswersDao;
import com.qcloud.component.my.model.QuestionnaireAnswers;
import com.qcloud.component.my.model.query.QuestionnaireAnswersQuery;

@Repository
public class QuestionnaireAnswersDaoCacheImpl implements QuestionnaireAnswersDao {
	
	@Autowired
	private QuestionnaireAnswersDao questionnaireAnswersDaoMysqlImpl;
	
	@Autowired
	private QuestionnaireAnswersDao questionnaireAnswersDaoRedisImpl;

	@Override
	public boolean add(QuestionnaireAnswers questionnaireAnswers) {
		return questionnaireAnswersDaoMysqlImpl.add(questionnaireAnswers);
	}

	@Override
	public QuestionnaireAnswers get(Long id) {
		return CacheLoader.get(questionnaireAnswersDaoRedisImpl, questionnaireAnswersDaoMysqlImpl, id);
	}

	@Override
	public boolean delete(Long id){
		return questionnaireAnswersDaoMysqlImpl.delete(id);
	}
	
	@Override
	public boolean update(QuestionnaireAnswers questionnaireAnswers){
		return questionnaireAnswersDaoMysqlImpl.update(questionnaireAnswers);
	}
	
	@Override
	public List<QuestionnaireAnswers> list(List<Long> idList) {
		return CacheLoader.list(questionnaireAnswersDaoRedisImpl, questionnaireAnswersDaoMysqlImpl, idList);
	}

	@Override
	public Map<Long, QuestionnaireAnswers> map(Set<Long> idSet){
		return CacheLoader.map(questionnaireAnswersDaoRedisImpl, questionnaireAnswersDaoMysqlImpl, idSet);
	}

	@Override
	public Page<QuestionnaireAnswers> page(int start, int count){
		return questionnaireAnswersDaoMysqlImpl.page(start, count);
	}
	
	@Override
	public Page<QuestionnaireAnswers> page(QuestionnaireAnswersQuery query, int start, int count){
		return questionnaireAnswersDaoMysqlImpl.page(query, start, count);
	}
	
	public List<QuestionnaireAnswers> listAll(){
		return questionnaireAnswersDaoMysqlImpl.listAll();
	}
}

