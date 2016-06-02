package com.qcloud.component.my.web.handler.url;

import java.util.ArrayList;
import java.util.List;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class MyToEvaluationUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        // list.add("/admin/myToEvaluation/list.do");
        // list.add("/admin/myToEvaluation/toAdd.do");
        // list.add("/admin/myToEvaluation/toEdit.do");
        // list.add("/admin/myToEvaluation/add.do");
        // list.add("/admin/myToEvaluation/edit.do");
        return list;
    }

    @Override
    public List<String> permissionUris() {

        List<String> list = new ArrayList<String>();
        // list.add("/admin/myToEvaluation/list.do");
        return list;
    }

    @Override
    public List<String> userUris() {

        List<String> list = new ArrayList<String>();
        list.add("/myToEvaluation/list.do");
        list.add("/myToEvaluation/listBySubOrder.do");
        list.add("/app/myToEvaluation/list.do");
        list.add("/app/myToEvaluation/listBySubOrder.do");
        return list;
    }

    @Override
    public List<String> appUris() {

        List<String> list = new ArrayList<String>();
        list.add("/app/myToEvaluation/list.do");
        list.add("/app/myToEvaluation/listBySubOrder.do");
        return list;
    }
}
