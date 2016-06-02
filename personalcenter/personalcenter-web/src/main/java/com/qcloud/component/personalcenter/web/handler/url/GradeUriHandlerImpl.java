package com.qcloud.component.personalcenter.web.handler.url;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class GradeUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/grade/list.do");
        list.add("/admin/grade/toAdd.do");
        list.add("/admin/grade/toEdit.do");
        list.add("/admin/grade/add.do");
        list.add("/admin/grade/edit.do");
        return list;
    }

    @Override
    public List<String> permissionUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/grade/list.do");
        list.add("/admin/grade/toAdd.do");
        list.add("/admin/grade/toEdit.do");
        list.add("/admin/grade/add.do");
        list.add("/admin/grade/edit.do");
        return list;
    }

    @Override
    public Map<String, List<String>> permissionRelaMap() {

        List<String> list = stringToList("/admin/grade/list.do");
        //
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        map.put("/admin/grade/toAdd.do", list);
        map.put("/admin/grade/toEdit.do", list);
        map.put("/admin/grade/add.do", list);
        map.put("/admin/grade/edit.do", list);
        //
        return map;
    }
}
