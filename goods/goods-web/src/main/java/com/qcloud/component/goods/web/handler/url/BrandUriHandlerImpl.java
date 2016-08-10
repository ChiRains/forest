package com.qcloud.component.goods.web.handler.url;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class BrandUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/brand/list.do");
        list.add("/admin/brand/toAdd.do");
        list.add("/admin/brand/toEdit.do");
        list.add("/admin/brand/add.do");
        list.add("/admin/brand/edit.do");
        list.add("/admin/brand/enable.do");
        list.add("/admin/brand/listMerchandise.do");
        list.add("/admin/brand/toAddMerchandise.do");
        list.add("/admin/brand/addMerchandise.do");
        list.add("/admin/brand/deleteMerchandise.do");
        return list;
    }

    @Override
    public List<String> permissionUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/brand/list.do");
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
        list.add("/brand/list.do");
        list.add("/brand/brandOnSale.do");
        //
        list.add("/app/brand/list.do");
        list.add("/app/brand/brandOnSale.do");
        return list;
    }

    @Override
    public List<String> appUris() {

        List<String> list = new ArrayList<String>();
        //
        list.add("/app/brand/list.do");
        list.add("/app/brand/brandOnSale.do");
        return list;
    }
}
