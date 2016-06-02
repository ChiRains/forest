package com.qcloud.component.my.web.handler;

import java.util.List;

import com.qcloud.component.my.model.QuestionnaireAnswers;
import com.qcloud.component.my.web.vo.QuestionnaireAnswersVO;
import com.qcloud.component.my.web.vo.admin.AdminQuestionnaireAnswersVO;

public interface QuestionnaireAnswersHandler {

	List<QuestionnaireAnswersVO> toVOList(List<QuestionnaireAnswers> list);

	QuestionnaireAnswersVO toVO(QuestionnaireAnswers questionnaireAnswers);

	List<AdminQuestionnaireAnswersVO> toVOList4Admin(List<QuestionnaireAnswers> list);

	AdminQuestionnaireAnswersVO toVO4Admin(QuestionnaireAnswers questionnaireAnswers);
}
