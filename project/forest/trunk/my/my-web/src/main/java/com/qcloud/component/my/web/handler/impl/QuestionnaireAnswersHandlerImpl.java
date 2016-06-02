package com.qcloud.component.my.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qcloud.pirates.core.json.Json;
import com.qcloud.component.my.web.handler.QuestionnaireAnswersHandler;
import com.qcloud.component.my.model.QuestionnaireAnswers;
import com.qcloud.component.my.web.vo.QuestionnaireAnswersVO;
import com.qcloud.component.my.web.vo.admin.AdminQuestionnaireAnswersVO;
import com.qcloud.component.personalcenter.PersonalcenterClient;
import com.qcloud.component.personalcenter.QUser;

@Component
public class QuestionnaireAnswersHandlerImpl implements QuestionnaireAnswersHandler {
    @Autowired
    private PersonalcenterClient personalcenterClient;

	@Override
	public List<QuestionnaireAnswersVO> toVOList(List<QuestionnaireAnswers> list){
		List<QuestionnaireAnswersVO> voList = new ArrayList<QuestionnaireAnswersVO>();
		for (QuestionnaireAnswers questionnaireAnswers : list) {
			voList.add(toVO(questionnaireAnswers));
		}
		return voList;
	}

	@Override
	public QuestionnaireAnswersVO toVO(QuestionnaireAnswers questionnaireAnswers){
		String json = Json.toJson(questionnaireAnswers);
		return Json.toObject(json, QuestionnaireAnswersVO.class, true);

	}

	@Override
	public List<AdminQuestionnaireAnswersVO> toVOList4Admin(List<QuestionnaireAnswers> list){
		List<AdminQuestionnaireAnswersVO> voList = new ArrayList<AdminQuestionnaireAnswersVO>();
		for (QuestionnaireAnswers adminQuestionnaireAnswers : list) {
			voList.add(toVO4Admin(adminQuestionnaireAnswers));
		}
		return voList;
	}

	@Override
	public AdminQuestionnaireAnswersVO toVO4Admin(QuestionnaireAnswers questionnaireAnswers){
		String json = Json.toJson(questionnaireAnswers);
		AdminQuestionnaireAnswersVO vo=Json.toObject(json, AdminQuestionnaireAnswersVO.class, true);
		QUser user=personalcenterClient.getUser(vo.getUserId());
		vo.setUserName(user.getName()==null?user.getNickname():user.getName());
		return vo;
	}
}
