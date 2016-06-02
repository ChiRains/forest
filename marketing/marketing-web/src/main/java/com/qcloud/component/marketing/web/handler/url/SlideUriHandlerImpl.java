package com.qcloud.component.marketing.web.handler.url;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class SlideUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/slide/list.do");
        list.add("/admin/slide/toAdd.do");
        list.add("/admin/slide/toEdit.do");
        list.add("/admin/slide/add.do");
        list.add("/admin/slide/edit.do");
        list.add("/admin/slide/delete.do");
        return list;
    }

    @Override
    public List<String> permissionUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/slide/list.do");
        list.add("/admin/slide/toAdd.do");
        list.add("/admin/slide/toEdit.do");
        list.add("/admin/slide/add.do");
        list.add("/admin/slide/edit.do");
        list.add("/admin/slide/delete.do");
        return list;
    }

    @Override
    public Map<String, List<String>> permissionRelaMap() {

        List<String> strList = stringToList("/admin/slide/list.do");
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        map.put("/admin/slide/toAdd.do", strList);
        map.put("/admin/slide/toEdit.do", strList);
        map.put("/admin/slide/add.do", strList);
        map.put("/admin/slide/edit.do", strList);
        map.put("/admin/slide/delete.do", strList);
        return map;
    }

    @Override
    public List<String> whiteNameUris() {

        List<String> list = new ArrayList<String>();
        list.add("/slide/listSlides.do");
        return list;
    }
}
