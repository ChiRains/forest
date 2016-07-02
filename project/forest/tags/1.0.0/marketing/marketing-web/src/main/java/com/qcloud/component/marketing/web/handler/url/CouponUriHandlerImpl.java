package com.qcloud.component.marketing.web.handler.url;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class CouponUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/coupon/list.do");
        list.add("/admin/coupon/toAdd.do");
        list.add("/admin/coupon/toEdit.do");
        list.add("/admin/coupon/add.do");
        list.add("/admin/coupon/edit.do");
        list.add("/admin/coupon/showList.do");
        list.add("/admin/coupon/delete.do");
        //
        list.add("/admin/coupon/list4Merchant.do");
        list.add("/admin/coupon/toAdd4Merchant.do");
        list.add("/admin/coupon/toEdit4Merchant.do");
        list.add("/admin/coupon/add4Merchant.do");
        list.add("/admin/coupon/edit4Merchant.do");
        return list;
    }

    @Override
    public List<String> permissionUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/coupon/list.do");
        list.add("/admin/coupon/toAdd.do");
        list.add("/admin/coupon/toEdit.do");
        list.add("/admin/coupon/add.do");
        list.add("/admin/coupon/edit.do");
        list.add("/admin/coupon/showList.do");
        list.add("/admin/coupon/delete.do");
        //
        list.add("/admin/coupon/list4Merchant.do");
        list.add("/admin/coupon/toAdd4Merchant.do");
        list.add("/admin/coupon/toEdit4Merchant.do");
        list.add("/admin/coupon/add4Merchant.do");
        list.add("/admin/coupon/edit4Merchant.do");
        return list;
    }

    @Override
    public List<String> whiteNameUris() {

        List<String> list = new ArrayList<String>();
        list.add("/coupon/existActivityCoupon.do");
        list.add("/coupon/existMerchantActivityCoupon.do");
        list.add("/coupon/getActivityCoupon.do");
        list.add("/coupon/getMerchantActivityCoupon.do");
        list.add("/coupon/listActivityCouponItem.do");
        return list;
    }

    @Override
    public Map<String, List<String>> permissionRelaMap() {

        List<String> strList = stringToList("/admin/coupon/list.do");
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        map.put("/admin/coupon/toAdd.do", strList);
        map.put("/admin/coupon/add.do", strList);
        map.put("/admin/coupon/toEdit.do", strList);
        map.put("/admin/coupon/edit.do", strList);
        //
        List<String> merStrList = stringToList("/admin/coupon/list4Merchant.do");
        map.put("/admin/coupon/toAdd4Merchant.do", merStrList);
        map.put("/admin/coupon/toEdit4Merchant.do", merStrList);
        map.put("/admin/coupon/add4Merchant.do", merStrList);
        map.put("/admin/coupon/edit4Merchant.do", merStrList);
        //
        map.put("/admin/coupon/delete.do", stringToList("/admin/coupon/list.do", "/admin/coupon/list4Merchant.do"));
        return map;
    }

    @Override
    public List<String> userUris() {

        List<String> list = new ArrayList<String>();
        list.add("/coupon/canExtract.do");
        list.add("/coupon/extractCoupon.do");
        // list.add("/coupon/extractCouponItem.do");
        list.add("/coupon/judgeExistAndCanExtract.do");
        list.add("/coupon/judgeMerchantExistAndCanExtract.do");
        list.add("/coupon/listActivityCoupon.do");
        list.add("/coupon/listMerchantActivityCoupon.do");
        //
        list.add("/app/coupon/canExtract.do");
        list.add("/app/coupon/extractCoupon.do");
        // list.add("/app/coupon/extractCouponItem.do");
        list.add("/app/coupon/judgeExistAndCanExtract.do");
        list.add("/app/coupon/judgeMerchantExistAndCanExtract.do");
        list.add("/app/coupon/listActivityCoupon.do");
        list.add("/app/coupon/listMerchantActivityCoupon.do");
        return list;
    }

    @Override
    public List<String> appUris() {

        List<String> list = new ArrayList<String>();
        list.add("/app/coupon/canExtract.do");
        list.add("/app/coupon/extractCoupon.do");
        // list.add("/app/coupon/extractCouponItem.do");
        list.add("/app/coupon/judgeExistAndCanExtract.do");
        list.add("/app/coupon/judgeMerchantExistAndCanExtract.do");
        list.add("/app/coupon/listActivityCoupon.do");
        list.add("/app/coupon/listMerchantActivityCoupon.do");
        return list;
    }
}
