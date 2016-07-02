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
        return list;
    }

    @Override
    public List<String> permissionUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/screeningsSlide/list.do");
        return list;
    }

    @Override
    public Map<String, List<String>> permissionRelaMap() {

        Map<String, List<String>> map = new HashMap<String, List<String>>();
        return map;
    }
}
