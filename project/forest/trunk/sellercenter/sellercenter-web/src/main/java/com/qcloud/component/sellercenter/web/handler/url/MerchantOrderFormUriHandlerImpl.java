package com.qcloud.component.sellercenter.web.handler.url;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class MerchantOrderFormUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/merchantOrderForm/list.do");
        list.add("/admin/merchantOrderForm/toEdit.do");
        list.add("/admin/merchantOrderForm/edit.do");
        list.add("/admin/merchantOrderForm/toEditStore.do");
        list.add("/admin/merchantOrderForm/listStore.do");
        list.add("/admin/merchantOrderForm/toAddLogistics.do");
        list.add("/admin/merchantOrderForm/merchantReportForm.do");
        list.add("/admin/merchantOrderForm/storeReportForm.do");
        return list;
    }

    @Override
    public List<String> permissionUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/merchantOrderForm/list.do");
        return list;
    }

    @Override
    public Map<String, List<String>> permissionRelaMap() {

        Map<String, List<String>> map = new HashMap<String, List<String>>();
        return map;
    }

    @Override
    public List<String> userUris() {

        List<String> list = new ArrayList<String>();
        list.add("/app/merchantOrderForm/list.do");
        return list;
    }

    @Override
    public List<String> appUris() {

        List<String> list = new ArrayList<String>();
        list.add("/app/merchantOrderForm/list.do");
        return list;
    }
}
