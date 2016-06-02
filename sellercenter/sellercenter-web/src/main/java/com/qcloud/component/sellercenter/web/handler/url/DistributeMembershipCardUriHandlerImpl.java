package com.qcloud.component.sellercenter.web.handler.url;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class DistributeMembershipCardUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/distributeMembershipCard/stat.do");
        list.add("/admin/distributeMembershipCard/toInitDistribute.do");
        list.add("/admin/distributeMembershipCard/initDistribute.do");
        list.add("/admin/distributeMembershipCard/listInMerchant.do");
        return list;
    }

    @Override
    public List<String> permissionUris() {

        List<String> list = new ArrayList<String>();
        //
        list.add("/admin/distributeMembershipCard/stat.do");
        list.add("/admin/distributeMembershipCard/toInitDistribute.do");
        list.add("/admin/distributeMembershipCard/initDistribute.do");
        list.add("/admin/distributeMembershipCard/listInMerchant.do");
        //
        return list;
    }

    @Override
    public Map<String, List<String>> permissionRelaMap() {

        List<String> list = stringToList("/admin/membershipCardWarehouse/list.do");
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        map.put("/admin/distributeMembershipCard/stat.do", list);
        map.put("/admin/distributeMembershipCard/toInitDistribute.do", list);
        map.put("/admin/distributeMembershipCard/initDistribute.do", list);
        map.put("/admin/distributeMembershipCard/listInMerchant.do", list);
        return map;
    }

    @Override
    public List<String> userUris() {

        List<String> list = new ArrayList<String>();
        list.add("/app/distributeMembershipCard/getToDistributeCard.do");
        list.add("/app/distributeMembershipCard/sendSmsMsg4DistributeCard.do");
        list.add("/app/distributeMembershipCard/distributeCard.do");
        return list;
    }

    @Override
    public List<String> appUris() {

        List<String> list = new ArrayList<String>();
        list.add("/app/distributeMembershipCard/getToDistributeCard.do");
        list.add("/app/distributeMembershipCard/sendSmsMsg4DistributeCard.do");
        list.add("/app/distributeMembershipCard/distributeCard.do");
        return list;
    }
}
