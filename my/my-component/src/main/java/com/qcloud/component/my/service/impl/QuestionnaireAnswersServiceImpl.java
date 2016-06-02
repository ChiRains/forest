package com.qcloud.component.my.service.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.component.my.dao.QuestionnaireAnswersDao;
import com.qcloud.component.my.model.QuestionnaireAnswers;
import com.qcloud.component.my.model.query.QuestionnaireAnswersQuery;
import com.qcloud.component.my.service.QuestionnaireAnswersService;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.util.StringUtil;

@Service
public class QuestionnaireAnswersServiceImpl implements QuestionnaireAnswersService {

    @Autowired
    private QuestionnaireAnswersDao questionnaireAnswersDao;

    @Autowired
    private AutoIdGenerator         autoIdGenerator;

    private static final String     ID_KEY = "my_questionnaire_answers";

    @Override
    public boolean add(QuestionnaireAnswers questionnaireAnswers) {

        long id = autoIdGenerator.get(ID_KEY);
        questionnaireAnswers.setId(id);
        questionnaireAnswers.setTime(new Date());
        return questionnaireAnswersDao.add(questionnaireAnswers);
    }

    @Override
    public QuestionnaireAnswers get(Long id) {

        return questionnaireAnswersDao.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return questionnaireAnswersDao.delete(id);
    }

    @Override
    public boolean update(QuestionnaireAnswers questionnaireAnswers) {

        return questionnaireAnswersDao.update(questionnaireAnswers);
    }

    @Override
    public Page<QuestionnaireAnswers> page(QuestionnaireAnswersQuery query, int start, int count) {

        return questionnaireAnswersDao.page(query, start, count);
    }

    public List<QuestionnaireAnswers> listAll() {

        return questionnaireAnswersDao.listAll();
    }

    @Transactional
    @Override
    public boolean add(List<QuestionnaireAnswers> questionnaireAnswersList) {

        String uuid = StringUtil.uuid();
        for (QuestionnaireAnswers questionnaireAnswers : questionnaireAnswersList) {
            questionnaireAnswers.setPaper(uuid);
            add(questionnaireAnswers);
        }
        return true;
    }
}
