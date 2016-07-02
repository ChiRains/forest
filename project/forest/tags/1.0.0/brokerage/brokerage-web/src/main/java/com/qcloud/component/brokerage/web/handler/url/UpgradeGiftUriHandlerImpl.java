package com.qcloud.component.brokerage.web.handler.url;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class UpgradeGiftUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/upgradeGift/list.do");
        list.add("/admin/upgradeGift/toAdd.do");
        list.add("/admin/upgradeGift/add.do");
        list.add("/admin/upgradeGift/delete.do");
        return list;
    }

    @Override
    public List<String> permissionUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/upgradeGift/list.do");
        list.add("/admin/upgradeGift/toAdd.do");
        list.add("/admin/upgradeGift/add.do");
        list.add("/admin/upgradeGift/delete.do");
        return list;
    }

    @Override
    public Map<String, List<String>> permissionRelaMap() {

        Map<String, List<String>> map = new HashMap<String, List<String>>();
        List<String> list = stringToList("/admin/distributionGrade/list.do");
        //
        map.put("/admin/upgradeGift/list.do", list);
        map.put("/admin/upgradeGift/toAdd.do", list);
        map.put("/admin/upgradeGift/add.do", list);
        map.put("/admin/upgradeGift/delete.do", list);
        return map;
    }
}
