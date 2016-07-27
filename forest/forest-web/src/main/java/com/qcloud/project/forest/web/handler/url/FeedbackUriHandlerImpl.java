package com.qcloud.project.forest.web.handler.url;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class FeedbackUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/feedback/list.do");
        list.add("/admin/feedback/toAdd.do");
        list.add("/admin/feedback/toEdit.do");
        list.add("/admin/feedback/add.do");
        list.add("/admin/feedback/edit.do");
        list.add("/admin/feedback/listClassify.do");
        list.add("/admin/feedback/enableClassify.do");
        list.add("/admin/feedback/toAddClassify.do");
        list.add("/admin/feedback/addClassify.do");
        list.add("/admin/feedback/toEditClassify.do");
        list.add("/admin/feedback/editClassify.do");
        return list;
    }

    @Override
    public List<String> permissionUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/feedback/toAdd.do");
        list.add("/admin/feedback/toEdit.do");
        list.add("/admin/feedback/add.do");
        list.add("/admin/feedback/edit.do");
        list.add("/admin/feedback/enableClassify.do");
        list.add("/admin/feedback/toAddClassify.do");
        list.add("/admin/feedback/addClassify.do");
        list.add("/admin/feedback/toEditClassify.do");
        list.add("/admin/feedback/editClassify.do");
        return list;
    }

    @Override
    public Map<String, List<String>> permissionRelaMap() {

        Map<String, List<String>> map = new HashMap<String, List<String>>();
        List<String> list = stringToList("/admin/feedback/list.do", "/admin/feedback/listClassify.do");
        //
        map.put("/admin/feedback/toAdd.do", list);
        map.put("/admin/feedback/toEdit.do", list);
        map.put("/admin/feedback/add.do", list);
        map.put("/admin/feedback/edit.do", list);
        map.put("/admin/feedback/enableClassify.do", list);
        map.put("/admin/brandSales/edit.do", list);
        map.put("/admin/brandSales/listMerchandise.do", list);
        map.put("/admin/feedback/toAddClassify.do", list);
        map.put("/admin/feedback/addClassify.do", list);
        map.put("/admin/feedback/toEditClassify.do", list);
        map.put("/admin/feedback/editClassify.do", list);
        return map;
    }

    @Override
    public List<String> userUris() {

        List<String> list = new ArrayList<String>();
        list.add("/feedback/commitFeedback.do");
        list.add("/app/feedback/commitFeedback.do");
        return list;
    }

    @Override
    public List<String> whiteNameUris() {

        List<String> list = new ArrayList<String>();
        list.add("/feedback/getFeedbackClassify.do");
        list.add("/app/feedback/getFeedbackClassify.do");
        return list;
    }

    @Override
    public List<String> appUris() {

        List<String> list = new ArrayList<String>();
        list.add("/app/feedback/commitFeedback.do");
        list.add("/app/feedback/getFeedbackClassify.do");
        return list;
    }
}
