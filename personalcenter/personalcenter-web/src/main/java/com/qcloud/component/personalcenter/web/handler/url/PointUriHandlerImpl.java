package com.qcloud.component.personalcenter.web.handler.url;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class PointUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/pointConfig/getPointConfig.do");
        list.add("/admin/pointConfig/setPointConfig.do");
        //
        list.add("/admin/pointConfig/getRegistrationPoint.do");
        list.add("/admin/pointConfig/getRegistrationBrokerage.do");
        list.add("/admin/pointConfig/getRegistrationCurrency.do");
        list.add("/admin/pointConfig/setRegistrationPoint.do");
        list.add("/admin/pointConfig/setRegistrationBrokerage.do");
        list.add("/admin/pointConfig/setRegistrationCurrency.do");
        return list;
    }

    @Override
    public List<String> permissionUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/pointConfig/getPointConfig.do");
        list.add("/admin/pointConfig/setPointConfig.do");
        //
        list.add("/admin/pointConfig/getRegistrationPoint.do");
        list.add("/admin/pointConfig/getRegistrationBrokerage.do");
        list.add("/admin/pointConfig/getRegistrationCurrency.do");
        list.add("/admin/pointConfig/setRegistrationPoint.do");
        list.add("/admin/pointConfig/setRegistrationBrokerage.do");
        list.add("/admin/pointConfig/setRegistrationCurrency.do");
        return list;
    }

    @Override
    public Map<String, List<String>> permissionRelaMap() {

        List<String> list = stringToList("/admin/grade/list.do");
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        map.put("/admin/pointConfig/getPointConfig.do", list);
        map.put("/admin/pointConfig/setPointConfig.do", list);
        List<String> commissionList = stringToList("/admin/myWealth/commissionList.do");
        map.put("/admin/pointConfig/getRegistrationBrokerage.do", commissionList);
        map.put("/admin/pointConfig/setRegistrationBrokerage.do", commissionList);
        List<String> couponbondList = stringToList("/admin/myWealth/couponbondList.do");
        map.put("/admin/pointConfig/getRegistrationCurrency.do", couponbondList);
        map.put("/admin/pointConfig/setRegistrationCurrency.do", couponbondList);
        return map;
    }

    @Override
    public List<String> whiteNameUris() {

        List<String> list = new ArrayList<String>();
        return list;
    }
}
