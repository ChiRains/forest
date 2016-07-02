package com.qcloud.component.sellercenter.web.handler.url;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class StoreMemberUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/storeMember/list.do");
        list.add("/admin/storeMember/toAdd.do");
        list.add("/admin/storeMember/toEdit.do");
        list.add("/admin/storeMember/add.do");
        list.add("/admin/storeMember/edit.do");
        list.add("/admin/storeMember/resetPwd.do");
        return list;
    }

    @Override
    public List<String> permissionUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/storeMember/list.do");
        list.add("/admin/storeMember/toAdd.do");
        list.add("/admin/storeMember/toEdit.do");
        list.add("/admin/storeMember/add.do");
        list.add("/admin/storeMember/edit.do");
        list.add("/admin/storeMember/resetPwd.do");
        return list;
    }

    @Override
    public Map<String, List<String>> permissionRelaMap() {

        List<String> list = stringToList("/admin/store/list.do");
        //
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        map.put("/admin/storeMember/list.do", list);
        map.put("/admin/storeMember/toAdd.do", list);
        map.put("/admin/storeMember/toEdit.do", list);
        map.put("/admin/storeMember/add.do", list);
        map.put("/admin/storeMember/edit.do", list);
        map.put("/admin/storeMember/resetPwd.do", list);
        return map;
    }
}
