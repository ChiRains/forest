package com.qcloud.project.forest.web.handler.url;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class ArticleEvaluationUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/articleEvaluation/list.do");
        list.add("/admin/articleEvaluation/toAdd.do");
        list.add("/admin/articleEvaluation/toEdit.do");
        list.add("/admin/articleEvaluation/add.do");
        list.add("/admin/articleEvaluation/edit.do");
        list.add("/admin/articleEvaluation/delete.do");
        list.add("/admin/articleEvaluation/enable.do");
        return list;
    }

    @Override
    public List<String> permissionUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/articleEvaluation/list.do");
        list.add("/admin/articleEvaluation/toAdd.do");
        list.add("/admin/articleEvaluation/toEdit.do");
        list.add("/admin/articleEvaluation/add.do");
        list.add("/admin/articleEvaluation/edit.do");
        list.add("/admin/articleEvaluation/delete.do");
        list.add("/admin/articleEvaluation/enable.do");
        return list;
    }

    @Override
    public Map<String, List<String>> permissionRelaMap() {

        Map<String, List<String>> map = new HashMap<String, List<String>>();
        List<String> list = stringToList("/admin/articleEvaluation/list.do");
        //
        map.put("/admin/articleEvaluation/toAdd.do", list);
        map.put("/admin/articleEvaluation/toEdit.do", list);
        map.put("/admin/articleEvaluation/add.do", list);
        map.put("/admin/articleEvaluation/edit.do", list);
        map.put("/admin/articleEvaluation/delete.do", list);
        map.put("/admin/articleEvaluation/enable.do", list);
        return map;
    }

    @Override
    public List<String> whiteNameUris() {

        List<String> list = new ArrayList<String>();
        list.add("/articleEvaluation/list.do");
        return list;
    }

    @Override
    public List<String> appUris() {

        List<String> list = new ArrayList<String>();
        list.add("/app/articleEvaluation/list.do");
        list.add("/app/articleEvaluation/add.do");
        return list;
    }

    @Override
    public List<String> userUris() {

        List<String> list = new ArrayList<String>();
        list.add("/app/articleEvaluation/add.do");
        //
        list.add("/articleEvaluation/add.do");
        return list;
    }
}
