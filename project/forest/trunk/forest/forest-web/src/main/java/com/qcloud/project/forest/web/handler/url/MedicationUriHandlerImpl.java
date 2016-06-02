package com.qcloud.project.forest.web.handler.url;

import java.util.ArrayList;
import java.util.List;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class MedicationUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/medication/list.do");
        list.add("/admin/medication/toAdd.do");
        list.add("/admin/medication/toEdit.do");
        list.add("/admin/medication/add.do");
        list.add("/admin/medication/edit.do");
        return list;
    }

    @Override
    public List<String> permissionUris() {

        List<String> list = new ArrayList<String>();
//        list.add("/admin/medication/list.do");
        return list;
    }

    @Override
    public List<String> whiteNameUris() {

        List<String> list = new ArrayList<String>();
        list.add("/medication/add.do");
        list.add("/medication/list.do");
        list.add("/medication/enable.do");
        list.add("/medication/delete.do");
        list.add("/medication/get.do");
        list.add("/medication/edit.do");
        //
        list.add("/app/medication/add.do");
        list.add("/app/medication/list.do");
        list.add("/app/medication/enable.do");
        list.add("/app/medication/delete.do");
        list.add("/app/medication/get.do");
        list.add("/app/medication/edit.do");
        return list;
    }

    @Override
    public List<String> appUris() {
        List<String> list = new ArrayList<String>();
        list.add("/app/medication/add.do");
        list.add("/app/medication/list.do");
        list.add("/app/medication/enable.do");
        list.add("/app/medication/delete.do");
        list.add("/app/medication/get.do");
        list.add("/app/medication/edit.do");
        return list;
    }

    
}
