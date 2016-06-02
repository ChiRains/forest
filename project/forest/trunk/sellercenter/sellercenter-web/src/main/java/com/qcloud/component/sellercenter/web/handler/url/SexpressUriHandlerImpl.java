package com.qcloud.component.sellercenter.web.handler.url;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class SexpressUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/sexpress/list.do");
        list.add("/admin/sexpress/toAdd.do");
        list.add("/admin/sexpress/toEdit.do");
        list.add("/admin/sexpress/add.do");
        list.add("/admin/sexpress/edit.do");
        list.add("/admin/sexpress/enable.do");
        list.add("/admin/sexpress/disable.do");
        //
        list.add("/admin/sexpress/list4Admin.do");
        list.add("/admin/sexpress/toAdd4Admin.do");
        list.add("/admin/sexpress/toEdit4Admin.do");
        list.add("/admin/sexpress/add4Admin.do");
        list.add("/admin/sexpress/edit4Admin.do");
        return list;
    }

    @Override
    public List<String> permissionUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/sexpress/list.do");
        list.add("/admin/sexpress/list4Admin.do");
        list.add("/admin/sexpress/toAdd.do");
        list.add("/admin/sexpress/toEdit.do");
        list.add("/admin/sexpress/add.do");
        list.add("/admin/sexpress/edit.do");
        list.add("/admin/sexpress/enable.do");
        list.add("/admin/sexpress/disable.do");
        //
        list.add("/admin/sexpress/toAdd4Admin.do");
        list.add("/admin/sexpress/toEdit4Admin.do");
        list.add("/admin/sexpress/add4Admin.do");
        list.add("/admin/sexpress/edit4Admin.do");
        return list;
    }

    @Override
    public Map<String, List<String>> permissionRelaMap() {

        List<String> list = stringToList("/admin/sexpress/list.do");
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        map.put("/admin/sexpress/toAdd.do", list);
        map.put("/admin/sexpress/add.do", list);
        map.put("/admin/sexpress/toEdit.do", list);
        map.put("/admin/sexpress/edit.do", list);
        map.put("/admin/express/toList.do", list);
        map.put("/admin/express/provinceList.do", list);
        map.put("/admin/express/cityList.do", list);
        //
        List<String> list4Admin = stringToList("/admin/sexpress/list4Admin.do");
        map.put("/admin/sexpress/toAdd4Admin.do", list4Admin);
        map.put("/admin/express/toEdit4Admin.do", list4Admin);
        map.put("/admin/express/add4Admin.do", list4Admin);
        map.put("/admin/express/edit4Admin.do", list4Admin);
        map.put("/admin/express/toList.do", list4Admin);
        map.put("/admin/express/provinceList.do", list4Admin);
        map.put("/admin/express/cityList.do", list4Admin);
        return map;
    }
}
