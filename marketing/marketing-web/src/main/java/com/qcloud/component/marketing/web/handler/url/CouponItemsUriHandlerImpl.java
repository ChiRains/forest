package com.qcloud.component.marketing.web.handler.url;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class CouponItemsUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/couponItems/list.do");
        list.add("/admin/couponItems/toAdd.do");
        list.add("/admin/couponItems/toEdit.do");
        list.add("/admin/couponItems/add.do");
        list.add("/admin/couponItems/edit.do");
        list.add("/admin/couponItems/toGet.do");
        list.add("/admin/couponItems/set.do");
        //
        list.add("/admin/couponItems/list4Merchant.do");
        list.add("/admin/couponItems/toAdd4Merchant.do");
        list.add("/admin/couponItems/toEdit4Merchant.do");
        list.add("/admin/couponItems/add4Merchant.do");
        list.add("/admin/couponItems/edit4Merchant.do");
        list.add("/admin/couponItems/toGet4Merchant.do");
        list.add("/admin/couponItems/set4Merchant.do");
        return list;
    }

    @Override
    public Map<String, List<String>> permissionRelaMap() {

        List<String> strList = stringToList("/admin/coupon/list.do");
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        map.put("/admin/couponItems/list.do", strList);
        map.put("/admin/couponItems/toAdd.do", strList);
        map.put("/admin/couponItems/toEdit.do", strList);
        map.put("/admin/couponItems/add.do", strList);
        map.put("/admin/couponItems/edit.do", strList);
        map.put("/admin/couponItems/toGet.do", strList);
        map.put("/admin/couponItems/set.do", strList);
        //
        List<String> merStrList = stringToList("/admin/coupon/list4Merchant.do");
        map.put("/admin/couponItems/list4Merchant.do", merStrList);
        map.put("/admin/couponItems/toAdd4Merchant.do", merStrList);
        map.put("/admin/couponItems/toEdit4Merchant.do", merStrList);
        map.put("/admin/couponItems/add4Merchant.do", merStrList);
        map.put("/admin/couponItems/edit4Merchant.do", merStrList);
        map.put("/admin/couponItems/toGet4Merchant.do", merStrList);
        map.put("/admin/couponItems/set4Merchant.do", merStrList);
        return map;
    }

    @Override
    public List<String> permissionUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/couponItems/list.do");
        list.add("/admin/couponItems/toAdd.do");
        list.add("/admin/couponItems/toEdit.do");
        list.add("/admin/couponItems/add.do");
        list.add("/admin/couponItems/edit.do");
        list.add("/admin/couponItems/toGet.do");
        list.add("/admin/couponItems/set.do");
        //
        list.add("/admin/couponItems/list4Merchant.do");
        list.add("/admin/couponItems/toAdd4Merchant.do");
        list.add("/admin/couponItems/toEdit4Merchant.do");
        list.add("/admin/couponItems/add4Merchant.do");
        list.add("/admin/couponItems/edit4Merchant.do");
        list.add("/admin/couponItems/toGet4Merchant.do");
        list.add("/admin/couponItems/set4Merchant.do");
        return list;
    }
}
