package com.qcloud.component.my.web.form;

import java.util.ArrayList;
import java.util.List;

public class QuestionnaireQuestionnaireForm {

    private Long                           questionnaireId;

    private List<QuestionnaireAnswersForm> list = new ArrayList<QuestionnaireAnswersForm>();

    public Long getQuestionnaireId() {

        return questionnaireId;
    }

    public void setQuestionnaireId(Long questionnaireId) {

        this.questionnaireId = questionnaireId;
    }

    public List<QuestionnaireAnswersForm> getList() {

        return list;
    }

    public void setList(List<QuestionnaireAnswersForm> list) {

        this.list = list;
    }
}