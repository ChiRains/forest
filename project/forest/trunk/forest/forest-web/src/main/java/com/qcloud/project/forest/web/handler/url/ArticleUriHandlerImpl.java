package com.qcloud.project.forest.web.handler.url;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class ArticleUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/article/list.do");
        list.add("/admin/article/toAdd.do");
        list.add("/admin/article/toEdit.do");
        list.add("/admin/article/add.do");
        list.add("/admin/article/edit.do");
        list.add("/admin/article/delete.do");
        list.add("/admin/article/enable.do");
        list.add("/admin/article/classifyList.do");
        list.add("/admin/article/addClassify.do");
        list.add("/admin/article/toAddClassify.do");
        list.add("/admin/article/deleteClassify.do");
        list.add("/admin/article/toEditClassify.do");
        list.add("/admin/article/editClassify.do");
        list.add("/admin/article/offshelves.do");
        list.add("/admin/article/enableClassify.do");
        return list;
    }

    @Override
    public List<String> permissionUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/article/list.do");
        list.add("/admin/article/toAdd.do");
        list.add("/admin/article/toEdit.do");
        list.add("/admin/article/add.do");
        list.add("/admin/article/edit.do");
        list.add("/admin/article/delete.do");
        list.add("/admin/article/enable.do");
        list.add("/admin/article/classifyList.do");
        list.add("/admin/article/addClassify.do");
        list.add("/admin/article/toAddClassify.do");
        list.add("/admin/article/deleteClassify.do");
        list.add("/admin/article/toEditClassify.do");
        list.add("/admin/article/editClassify.do");
        list.add("/admin/article/offshelves.do");
        list.add("/admin/article/enableClassify.do");
        return list;
    }

    @Override
    public Map<String, List<String>> permissionRelaMap() {

        Map<String, List<String>> map = new HashMap<String, List<String>>();
        List<String> list = stringToList("/admin/activity/list.do", "/admin/article/addClassify.do");
        //
        map.put("/admin/article/toAdd.do", list);
        map.put("/admin/article/toEdit.do", list);
        map.put("/admin/article/add.do", list);
        map.put("/admin/article/edit.do", list);
        map.put("/admin/article/delete.do", list);
        map.put("/admin/article/enable.do", list);
        map.put("/admin/article/classifyList.do", list);
        map.put("/admin/article/offshelves.do", list);
        map.put("/admin/article/addClassify.do", list);
        map.put("/admin/article/toAddClassify.do", list);
        map.put("/admin/article/deleteClassify.do", list);
        map.put("/admin/article/editClassify.do", list);
        map.put("/admin/article/enableClassify.do", list);
        return map;
    }

    @Override
    public List<String> whiteNameUris() {

        List<String> list = new ArrayList<String>();
        list.add("/article/list.do");
        list.add("/article/get.do");
        list.add("/article/getHtmlView.do");
        list.add("/article/articleClassify.do");
        list.add("/app/article/list.do");
        list.add("/app/article/get.do");
        list.add("/app/article/getHtmlView.do");
        list.add("/app/article/articleClassify.do");
        list.add("/app/article/articlePraise.do");
        return list;
    }

    @Override
    public List<String> appUris() {

        List<String> list = new ArrayList<String>();
        list.add("/app/article/list.do");
        list.add("/app/article/get.do");
        list.add("/app/article/getHtmlView.do");
        list.add("/app/article/articleClassify.do");
        list.add("/app/article/articlePraise.do");
        return list;
    }

    @Override
    public List<String> userUris() {

        List<String> list = new ArrayList<String>();
        list.add("/article/articlePraise.do");
        //
        list.add("/app/article/articlePraise.do");
        return list;
    }
}
