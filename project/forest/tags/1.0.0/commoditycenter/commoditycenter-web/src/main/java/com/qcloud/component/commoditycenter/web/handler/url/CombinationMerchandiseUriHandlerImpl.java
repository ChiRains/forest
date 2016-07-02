package com.qcloud.component.commoditycenter.web.handler.url;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class CombinationMerchandiseUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/combinationMerchandise/list.do");
        list.add("/admin/combinationMerchandise/edit.do");
        list.add("/admin/combinationMerchandise/update.do");
        list.add("/admin/combinationMerchandise/delete.do");
        return list;
    }

    @Override
    public List<String> permissionUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/combinationMerchandise/list.do");
        list.add("/admin/combinationMerchandise/edit.do");
        list.add("/admin/combinationMerchandise/update.do");
        list.add("/admin/combinationMerchandise/delete.do");
        return list;
    }

    @Override
    public Map<String, List<String>> permissionRelaMap() {

        List<String> list = stringToList("/admin/combinationMerchandise/list.do");
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        map.put("/admin/combinationMerchandise/edit.do", list);
        map.put("/admin/combinationMerchandise/update.do", list);
        //
        map.put("/admin/combinationMerchandise/delete.do", list);
        map.put("/admin/merchandiseItem/selectProductList.do", list);
        return map;
    }

    @Override
    public List<String> whiteNameUris() {

        List<String> list = new ArrayList<String>();
        list.add("/combinationMerchandise/existByUnifiedMerchandise.do");
        list.add("/combinationMerchandise/getBySingleUnifiedMerchandise.do");
        list.add("/combinationMerchandise/getByUnifiedMerchandise.do");
        list.add("/combinationMerchandise/listByUnifiedMerchandise.do");
        return list;
    }
}
