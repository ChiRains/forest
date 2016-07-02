package com.qcloud.component.sellercenter.web.handler.url;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class MemberUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/member/resetPwd.do");
         list.add("/admin/member/list.do");
         list.add("/admin/member/toAdd.do");
        // list.add("/admin/member/toEdit.do");
//         list.add("/admin/member/add.do");
        // list.add("/admin/member/edit.do");
        list.add("/admin/member/toEditPwd.do");
        list.add("/admin/member/editPwd.do");
        list.add("/admin/member/selectMember.do");
        return list;
    }

    @Override
    public List<String> permissionUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/member/resetPwd.do");
        list.add("/admin/member/toEditPwd.do");
        list.add("/admin/member/editPwd.do");
        return list;
    }

    @Override
    public Map<String, List<String>> permissionRelaMap() {

        Map<String, List<String>> map = new HashMap<String, List<String>>();
        map.put("/admin/member/editPwd.do", stringToList("/admin/member/toEditPwd.do"));
        return map;
    }

    @Override
    public List<String> appUris() {

        List<String> list = new ArrayList<String>();
        list.add("/app/member/login.do");
        return list;
    }

    @Override
    public List<String> whiteNameUris() {

        List<String> list = new ArrayList<String>();
        list.add("/app/member/login.do");
        return list;
    }
}
