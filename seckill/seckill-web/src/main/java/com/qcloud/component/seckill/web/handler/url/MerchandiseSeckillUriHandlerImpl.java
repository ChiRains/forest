package com.qcloud.component.seckill.web.handler.url;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class MerchandiseSeckillUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/merchandiseSeckill/list.do");
        list.add("/admin/merchandiseSeckill/toAdd.do");
        // list.add("/admin/merchandiseSeckill/toEdit.do");
        list.add("/admin/merchandiseSeckill/add.do");
        // list.add("/admin/merchandiseSeckill/edit.do");
        list.add("/admin/merchandiseSeckill/delete.do");
        return list;
    }

    @Override
    public List<String> permissionUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/merchandiseSeckill/list.do");
        list.add("/admin/merchandiseSeckill/toAdd.do");
        // list.add("/admin/merchandiseSeckill/toEdit.do");
        list.add("/admin/merchandiseSeckill/add.do");
        // list.add("/admin/merchandiseSeckill/edit.do");
        list.add("/admin/merchandiseSeckill/delete.do");
        return list;
    }

    @Override
    public Map<String, List<String>> permissionRelaMap() {

        List<String> strList = stringToList("/admin/screenings/list.do");
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        map.put("/admin/merchandiseSeckill/list.do", strList);
        map.put("/admin/merchandiseSeckill/toAdd.do", strList);
        map.put("/admin/merchandiseSeckill/add.do", strList);
        map.put("/admin/merchandiseSeckill/delete.do", strList);
        //
        map.put("/admin/merchandiseItem/list4Select4Admin.do", strList);
        return map;
    }
}
