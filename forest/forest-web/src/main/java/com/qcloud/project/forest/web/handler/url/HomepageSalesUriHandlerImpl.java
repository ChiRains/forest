package com.qcloud.project.forest.web.handler.url;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class HomepageSalesUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/homepageSales/listHomepageSalesClassify.do");
        list.add("/admin/homepageSales/enableHomepageSalesClassify.do");
        list.add("/admin/homepageSales/toAddHomepageSalesClassify.do");
        list.add("/admin/homepageSales/addHomepageSalesClassify.do");
        list.add("/admin/homepageSales/toEditHomepageSalesClassify.do");
        list.add("/admin/homepageSales/editHomepageSalesClassify.do");
        list.add("/admin/homepageSales/listHomepageSales.do");
        list.add("/admin/homepageSales/toAddHomepageSales.do");
        list.add("/admin/homepageSales/addHomepageSales.do");
        list.add("/admin/homepageSales/deleteHomepageSales.do");
        return list;
    }

    @Override
    public List<String> permissionUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/homepageSales/listHomepageSalesClassify.do");
        list.add("/admin/homepageSales/enableHomepageSalesClassify.do");
        list.add("/admin/homepageSales/toAddHomepageSalesClassify.do");
        list.add("/admin/homepageSales/addHomepageSalesClassify.do");
        list.add("/admin/homepageSales/toEditHomepageSalesClassify.do");
        list.add("/admin/homepageSales/editHomepageSalesClassify.do");
        list.add("/admin/homepageSales/listHomepageSales.do");
        list.add("/admin/homepageSales/toAddHomepageSales.do");
        list.add("/admin/homepageSales/addHomepageSales.do");
        list.add("/admin/homepageSales/deleteHomepageSales.do");
        return list;
    }

    @Override
    public Map<String, List<String>> permissionRelaMap() {

        Map<String, List<String>> map = new HashMap<String, List<String>>();
        List<String> list = stringToList("/admin/homepageSales/listHomepageSalesClassify.do", "/admin/homepageSales/listHomepageSales.do");
        //
        map.put("/admin/homepageSales/listHomepageSalesClassify.do", list);
        map.put("/admin/homepageSales/enableHomepageSalesClassify.do", list);
        map.put("/admin/homepageSales/toAddHomepageSalesClassify.do", list);
        map.put("/admin/homepageSales/addHomepageSalesClassify.do", list);
        map.put("/admin/homepageSales/toEditHomepageSalesClassify.do", list);
        map.put("/admin/homepageSales/editHomepageSalesClassify.do", list);
        map.put("/admin/homepageSales/listHomepageSales.do", list);
        map.put("/admin/homepageSales/toAddHomepageSales.do", list);
        map.put("/admin/homepageSales/addHomepageSales.do", list);
        map.put("/admin/homepageSales/deleteHomepageSales.do", list);
        return map;
    }

    @Override
    public List<String> whiteNameUris() {

        List<String> list = new ArrayList<String>();
        list.add("/homepageSales/getHomepageSalesClassify.do");
        list.add("/homepageSales/getHomepageSaleslist.do");
        list.add("/app/homepageSales/getHomepageSalesClassify.do");
        list.add("/app/homepageSales/getHomepageSaleslist.do");
        return list;
    }

    @Override
    public List<String> appUris() {

        List<String> list = new ArrayList<String>();
        list.add("/app/homepageSales/getHomepageSalesClassify.do");
        list.add("/app/homepageSales/getHomepageSaleslist.do");
        return list;
    }
}
