package com.qcloud.component.sellercenter.web.handler.url;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class MerchantMemberUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/merchantMember/list.do");
        list.add("/admin/merchantMember/list4Merchant.do");
        list.add("/admin/merchantMember/toAdd.do");
        list.add("/admin/merchantMember/toEdit.do");
        list.add("/admin/merchantMember/add.do");
        list.add("/admin/merchantMember/edit.do");
        list.add("/admin/merchantMember/listAllBySelectForAdmin.do");
        // list.add("/admin/merchantMember/toSetMembers.do");
        // list.add("/admin/merchantMember/setMember.do");
        return list;
    }

    @Override
    public List<String> permissionUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/merchantMember/list.do");
        list.add("/admin/merchantMember/list4Merchant.do");
        list.add("/admin/merchantMember/toAdd.do");
        list.add("/admin/merchantMember/toEdit.do");
        list.add("/admin/merchantMember/add.do");
        list.add("/admin/merchantMember/edit.do");
        return list;
    }

    @Override
    public Map<String, List<String>> permissionRelaMap() {

        List<String> list = stringToList("/admin/merchant/list.do", "/admin/merchantMember/list4Merchant.do");
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        map.put("/admin/merchantMember/list.do", list);
        map.put("/admin/merchantMember/toAdd.do", list);
        map.put("/admin/merchantMember/add.do", list);
        map.put("/admin/merchantMember/toEdit.do", list);
        map.put("/admin/merchantMember/edit.do", list);
        map.put("/admin/member/resetPwd.do", list);
        return map;
    }
}
