package com.qcloud.component.my.web.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qcloud.component.my.model.QuestionnaireAnswers;
import com.qcloud.component.my.service.QuestionnaireAnswersService;
import com.qcloud.component.my.web.form.QuestionnaireAnswersForm;
import com.qcloud.component.my.web.form.QuestionnaireQuestionnaireForm;
import com.qcloud.component.personalcenter.PersonalcenterClient;
import com.qcloud.component.personalcenter.QUser;
import com.qcloud.component.publicdata.PublicdataClient;
import com.qcloud.component.publicdata.QQuestion;
import com.qcloud.component.publicdata.QQuestionnaire;
import com.qcloud.pirates.mvc.FrontAjaxView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.web.page.PageParameterUtil;

@Controller
@RequestMapping(value = QuestionnaireAnswersController.DIR)
public class QuestionnaireAnswersController {

    public static final String          DIR = "/questionnaireAnswers";

    @Autowired
    private QuestionnaireAnswersService questionnaireAnswersService;

    @Autowired
    private PublicdataClient            publicdataClient;

    @RequestMapping
    public FrontAjaxView answer(HttpServletRequest request, QuestionnaireQuestionnaireForm questionnaireForm) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        AssertUtil.assertNotNull(questionnaireForm.getQuestionnaireId(), "问卷标识不能为空.");
        QQuestionnaire questionnaire = publicdataClient.getQuestionnaire(questionnaireForm.getQuestionnaireId());
        AssertUtil.assertNotNull(questionnaire, "问卷不存在.");
        List<QuestionnaireAnswersForm> qaList = questionnaireForm.getList();
        AssertUtil.assertNotEmpty(qaList, "问卷答案列表不能为空.");
        List<QuestionnaireAnswers> questionnaireAnswersList = new ArrayList<QuestionnaireAnswers>();
        for (QuestionnaireAnswersForm questionnaireAnswersForm : qaList) {
            QuestionnaireAnswers questionnaireAnswers = new QuestionnaireAnswers();
            questionnaireAnswers.setQuestionnaireId(questionnaireForm.getQuestionnaireId());
            questionnaireAnswers.setQuestionnaireName(questionnaire.getName());
            questionnaireAnswers.setQuestionId(questionnaireAnswersForm.getQuestionId());
            QQuestion question = questionnaire.getQuestion(questionnaireAnswersForm.getQuestionId());
            AssertUtil.assertNotNull(question, "问题不存在." + questionnaireAnswersForm.getQuestionId());
            questionnaireAnswers.setQuestionName(question.getName());
            questionnaireAnswers.setAnswer(question.getAnswer(questionnaireAnswersForm.getAnswer()));
            questionnaireAnswers.setUserId(user.getId());
            questionnaireAnswersList.add(questionnaireAnswers);
        }
        questionnaireAnswersService.add(questionnaireAnswersList);
        FrontAjaxView frontAjaxView = new FrontAjaxView();
        frontAjaxView.setMessage("问卷回答提交成功.");
        return frontAjaxView;
    }
}
