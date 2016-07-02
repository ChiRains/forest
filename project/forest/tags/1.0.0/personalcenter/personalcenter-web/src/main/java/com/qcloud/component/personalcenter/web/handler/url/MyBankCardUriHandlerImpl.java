package com.qcloud.component.personalcenter.web.handler.url;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class MyBankCardUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        // list.add("/admin/myBankCard/list.do");
        // list.add("/admin/myBankCard/toAdd.do");
        // list.add("/admin/myBankCard/toEdit.do");
        // list.add("/admin/myBankCard/add.do");
        // list.add("/admin/myBankCard/edit.do");
        return list;
    }

    @Override
    public List<String> permissionUris() {

        List<String> list = new ArrayList<String>();
        // list.add("/admin/myBankCard/list.do");
        return list;
    }

    @Override
    public Map<String, List<String>> permissionRelaMap() {

        Map<String, List<String>> map = new HashMap<String, List<String>>();
        return map;
    }

    @Override
    public List<String> userUris() {

        List<String> list = new ArrayList<String>();
        list.add("/myBankCard/list.do");
        list.add("/myBankCard/add.do");
        list.add("/myBankCard/delete.do");
        //
        list.add("/app/myBankCard/list.do");
        list.add("/app/myBankCard/add.do");
        list.add("/app/myBankCard/delete.do");
        return list;
    }

    @Override
    public List<String> appUris() {

        List<String> list = new ArrayList<String>();
        list.add("/app/myBankCard/list.do");
        list.add("/app/myBankCard/add.do");
        list.add("/app/myBankCard/delete.do");
        return list;
    }
}
