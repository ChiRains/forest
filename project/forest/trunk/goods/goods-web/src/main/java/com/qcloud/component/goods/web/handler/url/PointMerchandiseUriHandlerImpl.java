package com.qcloud.component.goods.web.handler.url;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class PointMerchandiseUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/pointMerchandise/list.do");
        list.add("/admin/pointMerchandise/toAdd.do");
        list.add("/admin/pointMerchandise/toEdit.do");
        list.add("/admin/pointMerchandise/add.do");
        list.add("/admin/pointMerchandise/edit.do");
        return list;
    }

    @Override
    public List<String> permissionUris() {

        List<String> list = new ArrayList<String>();
        return list;
    }

    @Override
    public Map<String, List<String>> permissionRelaMap() {

        Map<String, List<String>> map = new HashMap<String, List<String>>();
        return map;
    }

    @Override
    public List<String> whiteNameUris() {

        List<String> list = new ArrayList<String>();
        return list;
    }

    @Override
    public List<String> appUris() {

        List<String> list = new ArrayList<String>();
        //
        return list;
    }
}
