package com.qcloud.project.forest.web.handler.url;

import java.util.ArrayList;
import java.util.List;
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
        return list;
    }

    @Override
    public List<String> permissionUris() {

        List<String> list = new ArrayList<String>();
        // list.add("/admin/article/list.do");
        return list;
    }

    @Override
    public List<String> whiteNameUris() {

        List<String> list = new ArrayList<String>();
        list.add("/article/list.do");
        list.add("/article/get.do");
        list.add("/article/articleClassify.do");
        return list;
    }

    @Override
    public List<String> userUris() {

        List<String> list = new ArrayList<String>();
        list.add("/article/articlePraise.do");
        return list;
    }
}
