package com.qcloud.component.publicservice.web.handler.url;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class MessageSourceUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/messageSource/list.do");
        list.add("/admin/messageSource/toAdd.do");
        list.add("/admin/messageSource/toEdit.do");
        list.add("/admin/messageSource/add.do");
        list.add("/admin/messageSource/edit.do");
        return list;
    }

    @Override
    public List<String> permissionUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/messageSource/list.do");
        list.add("/admin/messageSource/toAdd.do");
        list.add("/admin/messageSource/toEdit.do");
        list.add("/admin/messageSource/add.do");
        list.add("/admin/messageSource/edit.do");
        return list;
    }

    @Override
    public Map<String, List<String>> permissionRelaMap() {

        Map<String, List<String>> map = new HashMap<String, List<String>>();
        List<String> list = stringToList("/admin/messageSource/list.do", "/admin/forestMessage/modularList.do");
        //
        map.put("/admin/messageSource/list.do", list);
        map.put("/admin/messageSource/toAdd.do", list);
        map.put("/admin/messageSource/toEdit.do", list);
        map.put("/admin/messageSource/edit.do", list);
        map.put("/admin/messageSource/add.do", list);
        return map;
    }
}
