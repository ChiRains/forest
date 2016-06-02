package com.qcloud.component.personalcenter.web.handler.url;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class MyCommissionWithdrawCashUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/myCommissionWithdrawCash/list.do");
        list.add("/admin/myCommissionWithdrawCash/toAdd.do");
        list.add("/admin/myCommissionWithdrawCash/toEdit.do");
        list.add("/admin/myCommissionWithdrawCash/add.do");
        list.add("/admin/myCommissionWithdrawCash/edit.do");
        list.add("/admin/myCommissionWithdrawCash/check.do");
        list.add("/admin/myCommissionWithdrawCash/transferlist.do");
        list.add("/admin/myCommissionWithdrawCash/export.do");
        return list;
    }

    @Override
    public List<String> permissionUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/myCommissionWithdrawCash/list.do");
        return list;
    }

    @Override
    public Map<String, List<String>> permissionRelaMap() {

        Map<String, List<String>> map = new HashMap<String, List<String>>();
        return map;
    }
}
