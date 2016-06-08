package com.qcloud.project.forest.web.handler.url;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class GiftCouponUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/giftCoupon/list.do");
        list.add("/admin/giftCoupon/toAdd.do");
        list.add("/admin/giftCoupon/toEdit.do");
        list.add("/admin/giftCoupon/add.do");
        list.add("/admin/giftCoupon/edit.do");
        list.add("/admin/giftCoupon/enable.do");
        list.add("/admin/giftCoupon/delete.do");
        return list;
    }

    @Override
    public List<String> permissionUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/giftCoupon/list.do");
        list.add("/admin/giftCoupon/toAdd.do");
        list.add("/admin/giftCoupon/toEdit.do");
        list.add("/admin/giftCoupon/add.do");
        list.add("/admin/giftCoupon/edit.do");
        list.add("/admin/giftCoupon/enable.do");
        list.add("/admin/giftCoupon/delete.do");
        return list;
    }

    @Override
    public Map<String, List<String>> permissionRelaMap() {

        Map<String, List<String>> map = new HashMap<String, List<String>>();
        List<String> list = stringToList("/admin/enable/list.do");
        //
        map.put("/admin/giftCoupon/toAdd.do", list);
        map.put("/admin/giftCoupon/toEdit.do", list);
        map.put("/admin/giftCoupon/add.do", list);
        map.put("/admin/giftCoupon/edit.do", list);
        map.put("/admin/giftCoupon/delete.do", list);
        map.put("/admin/giftCoupon/enable.do", list);
        map.put("/admin/giftCoupon/delete.do", list);
        return map;
    }

    @Override
    public List<String> whiteNameUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/giftCoupon/list.do");
        return list;
    }
}
