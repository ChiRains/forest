package com.qcloud.component.my.dao.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;

import com.qcloud.pirates.core.json.Json;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.redis.Redis;
import com.qcloud.component.my.dao.QuestionnaireAnswersDao;
import com.qcloud.component.my.model.QuestionnaireAnswers;
import com.qcloud.component.my.model.query.QuestionnaireAnswersQuery;

@Repository
public class QuestionnaireAnswersDaoRedisImpl implements QuestionnaireAnswersDao {

	//@Resource(name = "redis-my")
	//private Redis redis;

	@Override
	public boolean add(QuestionnaireAnswers questionnaireAnswers) {			
		throw new NotImplementedException();
	}

	@Override
	public QuestionnaireAnswers get(Long id) {		
		throw new NotImplementedException();
	}
	
	@Override
	public boolean delete(Long id){
		throw new NotImplementedException();
	}
	
	@Override
	public boolean update(QuestionnaireAnswers questionnaireAnswers){
		throw new NotImplementedException();
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
		throw new NotImplementedException();
	}
	
	@Override
	public Page<QuestionnaireAnswers> page(QuestionnaireAnswersQuery query, int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public List<QuestionnaireAnswers> listAll(){	
		throw new NotImplementedException();
	}
}

