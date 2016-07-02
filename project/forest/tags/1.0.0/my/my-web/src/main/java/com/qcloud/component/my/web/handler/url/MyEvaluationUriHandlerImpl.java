package com.qcloud.component.my.web.handler.url;

import java.util.ArrayList;
import java.util.List;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class MyEvaluationUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        // list.add("/admin/myEvaluation/list.do");
        // list.add("/admin/myEvaluation/toAdd.do");
        // list.add("/admin/myEvaluation/toEdit.do");
        // list.add("/admin/myEvaluation/add.do");
        // list.add("/admin/myEvaluation/edit.do");
        return list;
    }

    @Override
    public List<String> permissionUris() {

        List<String> list = new ArrayList<String>();
        // list.add("/admin/myEvaluation/list.do");
        return list;
    }

    @Override
    public List<String> whiteNameUris() {

        List<String> list = new ArrayList<String>();
        list.add("/myEvaluation/evaluate.do");
        list.add("/myEvaluation/list.do");
        return list;
    }
}
