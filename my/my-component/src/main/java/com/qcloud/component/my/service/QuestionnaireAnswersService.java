package com.qcloud.component.my.service;

import java.util.List;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.my.model.QuestionnaireAnswers;
import com.qcloud.component.my.model.query.QuestionnaireAnswersQuery;

public interface QuestionnaireAnswersService {

    public boolean add(QuestionnaireAnswers questionnaireAnswers);

    boolean add(List<QuestionnaireAnswers> questionnaireAnswersList);

    public QuestionnaireAnswers get(Long id);

    public boolean delete(Long id);

    public boolean update(QuestionnaireAnswers questionnaireAnswers);

    public Page<QuestionnaireAnswers> page(QuestionnaireAnswersQuery query, int start, int count);

    public List<QuestionnaireAnswers> listAll();
}
