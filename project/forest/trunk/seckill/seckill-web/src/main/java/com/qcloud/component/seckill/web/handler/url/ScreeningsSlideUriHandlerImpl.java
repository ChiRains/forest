package com.qcloud.component.seckill.web.handler.url;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class ScreeningsSlideUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/screeningsSlide/list.do");
        list.add("/admin/screeningsSlide/toAdd.do");
        list.add("/admin/screeningsSlide/toEdit.do");
        list.add("/admin/screeningsSlide/add.do");
        list.add("/admin/screeningsSlide/edit.do");
        list.add("/admin/screeningsSlide/delete.do");
        return list;
    }

    @Override
    public List<String> permissionUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/screeningsSlide/toAdd.do");
        list.add("/admin/screeningsSlide/toEdit.do");
        list.add("/admin/screeningsSlide/add.do");
        list.add("/admin/screeningsSlide/edit.do");
        list.add("/admin/screeningsSlide/delete.do");
        list.add("/admin/screeningsSlide/list.do");
        return list;
    }

    @Override
    public Map<String, List<String>> permissionRelaMap() {

        Map<String, List<String>> map = new HashMap<String, List<String>>();
        List<String> strList = stringToList("/admin/screeningsSlide/list.do", "/admin/screenings/list.do");
        map.put("/admin/screeningsSlide/toAdd.do", strList);
        map.put("/admin/screeningsSlide/toEdit.do", strList);
        map.put("/admin/screeningsSlide/delete.do", strList);
        map.put("/admin/screeningsSlide/add.do", strList);
        map.put("/admin/screeningsSlide/edit.do", strList);
        map.put("/admin/screeningsSlide/delete.do", strList);
        //
        return map;
    }
}
