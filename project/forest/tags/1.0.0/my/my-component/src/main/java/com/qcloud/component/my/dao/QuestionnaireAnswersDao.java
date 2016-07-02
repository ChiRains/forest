package com.qcloud.component.my.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.component.my.model.QuestionnaireAnswers;
import com.qcloud.component.my.model.query.QuestionnaireAnswersQuery;
		
public interface QuestionnaireAnswersDao extends ISimpleDao<QuestionnaireAnswers, Long> {

	public boolean add(QuestionnaireAnswers questionnaireAnswers);	
	
	public QuestionnaireAnswers get(Long id);

	public boolean delete(Long id);
	
	public boolean update(QuestionnaireAnswers questionnaireAnswers);
	
	public List<QuestionnaireAnswers> list(List<Long> idList);
	
	public Map<Long, QuestionnaireAnswers> map(Set<Long> idSet);
	
	public Page<QuestionnaireAnswers> page(QuestionnaireAnswersQuery query, int start, int size);

	public List<QuestionnaireAnswers> listAll();
	
}
