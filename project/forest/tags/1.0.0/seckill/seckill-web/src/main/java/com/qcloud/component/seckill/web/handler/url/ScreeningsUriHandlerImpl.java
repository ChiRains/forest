package com.qcloud.component.seckill.web.handler.url;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class ScreeningsUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/screenings/list.do");
        list.add("/admin/screenings/toAdd.do");
        // list.add("/admin/screenings/toEdit.do");
        list.add("/admin/screenings/add.do");
        // list.add("/admin/screenings/edit.do");
        list.add("/admin/screenings/delete.do");
        return list;
    }

    @Override
    public List<String> permissionUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/screenings/list.do");
        list.add("/admin/screenings/toAdd.do");
        // list.add("/admin/screenings/toEdit.do");
        list.add("/admin/screenings/add.do");
        // list.add("/admin/screenings/edit.do");
        list.add("/admin/screenings/delete.do");
        return list;
    }

    @Override
    public Map<String, List<String>> permissionRelaMap() {

        List<String> strList = stringToList("/admin/screenings/list.do");
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        map.put("/admin/screenings/toAdd.do", strList);
        map.put("/admin/screenings/add.do", strList);
        map.put("/admin/screenings/delete.do", strList);
        return map;
    }

    @Override
    public List<String> whiteNameUris() {

        List<String> list = new ArrayList<String>();
        list.add("/screenings/list4classify.do");
        list.add("/screenings/list.do");
        list.add("/app/screenings/list4classify.do");
        list.add("/app/screenings/list.do");
        return list;
    }

    @Override
    public List<String> appUris() {

        List<String> list = new ArrayList<String>();
        list.add("/app/screenings/list4classify.do");
        list.add("/app/screenings/list.do");
        return list;
    }
}
