package com.qcloud.project.forest.web.handler.url;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class ModularUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/modular/list.do");
        list.add("/admin/modular/toAdd.do");
        list.add("/admin/modular/toEdit.do");
        list.add("/admin/modular/add.do");
        list.add("/admin/modular/edit.do");
        list.add("/admin/modular/enable.do");
        list.add("/admin/modular/validate.do");
        return list;
    }

    @Override
    public List<String> permissionUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/modular/list.do");
        list.add("/admin/modular/toAdd.do");
        list.add("/admin/modular/toEdit.do");
        list.add("/admin/modular/add.do");
        list.add("/admin/modular/edit.do");
        list.add("/admin/modular/enable.do");
        list.add("/admin/modular/validate.do");
        return list;
    }

    @Override
    public Map<String, List<String>> permissionRelaMap() {

        Map<String, List<String>> map = new HashMap<String, List<String>>();
        List<String> list = stringToList("/admin/modular/list.do");
        //
        map.put("/admin/modular/toAdd.do", list);
        map.put("/admin/modular/toEdit.do", list);
        map.put("/admin/modular/add.do", list);
        map.put("/admin/modular/edit.do", list);
        map.put("/admin/modular/enable.do", list);
        map.put("/admin/modular/validate.do", list);
        return map;
    }

    @Override
    public List<String> appUris() {

        List<String> list = new ArrayList<String>();
        list.add("/app/modular/myModular.do");
        list.add("/app/modular/allModular.do");
        list.add("/app/modular/addModular.do");
        return list;
    }

    @Override
    public List<String> whiteNameUris() {

        List<String> list = new ArrayList<String>();
        list.add("/app/modular/allModular.do");
        //
        list.add("/modular/allModular.do");
        return list;
    }

    @Override
    public List<String> userUris() {

        List<String> list = new ArrayList<String>();
        list.add("/app/modular/myModular.do");
        list.add("/app/modular/addModular.do");
        //
        list.add("/modular/myModular.do");
        list.add("/modular/addModular.do");
        return list;
    }
}
