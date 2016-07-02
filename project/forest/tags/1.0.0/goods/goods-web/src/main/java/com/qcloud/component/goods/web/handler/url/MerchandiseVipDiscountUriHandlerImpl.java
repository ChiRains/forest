package com.qcloud.component.goods.web.handler.url;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class MerchandiseVipDiscountUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/merchandiseVipDiscount/list.do");
        list.add("/admin/merchandiseVipDiscount/toAdd.do");
        list.add("/admin/merchandiseVipDiscount/toEdit.do");
        list.add("/admin/merchandiseVipDiscount/add.do");
        list.add("/admin/merchandiseVipDiscount/addVipDiscounts.do");
        list.add("/admin/merchandiseVipDiscount/edit.do");
        return list;
    }

    @Override
    public List<String> permissionUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/merchandiseVipDiscount/list.do");
        list.add("/admin/merchandiseVipDiscount/toAdd.do");
        list.add("/admin/merchandiseVipDiscount/toEdit.do");
        list.add("/admin/merchandiseVipDiscount/add.do");
        list.add("/admin/merchandiseVipDiscount/addVipDiscounts.do");
        list.add("/admin/merchandiseVipDiscount/edit.do");
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
        list.add("/merchandiseVipDiscount/page.do");
        list.add("/merchandiseVipDiscount/list.do");
        list.add("/merchandiseVipDiscount/count.do");
        list.add("/merchandiseVipDiscount/listClassify.do");
        return list;
    }
}
