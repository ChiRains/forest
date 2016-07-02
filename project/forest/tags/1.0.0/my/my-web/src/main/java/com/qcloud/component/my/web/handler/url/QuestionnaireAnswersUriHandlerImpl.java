package com.qcloud.component.my.web.handler.url;

import java.util.ArrayList;
import java.util.List;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class QuestionnaireAnswersUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/questionnaireAnswers/list.do");
        list.add("/admin/questionnaireAnswers/toAdd.do");
        list.add("/admin/questionnaireAnswers/toEdit.do");
        list.add("/admin/questionnaireAnswers/add.do");
        list.add("/admin/questionnaireAnswers/edit.do");
        return list;
    }

    @Override
    public List<String> permissionUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/questionnaireAnswers/list.do");
        return list;
    }

    @Override
    public List<String> userUris() {

        List<String> list = new ArrayList<String>();
        list.add("/questionnaireAnswers/answer.do");
        list.add("/app/questionnaireAnswers/answer.do");
        return list;
    }

    @Override
    public List<String> appUris() {

        List<String> list = new ArrayList<String>();
        list.add("/app/questionnaireAnswers/answer.do");
        return list;
    }
}
