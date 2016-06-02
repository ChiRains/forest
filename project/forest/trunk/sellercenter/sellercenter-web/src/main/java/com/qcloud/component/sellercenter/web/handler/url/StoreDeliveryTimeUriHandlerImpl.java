package com.qcloud.component.sellercenter.web.handler.url;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class StoreDeliveryTimeUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/storeDeliveryTime/edit.do");
        list.add("/admin/storeDeliveryTime/toEditByStore.do");
        return list;
    }

    @Override
    public List<String> permissionUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/storeDeliveryTime/edit.do");
        list.add("/admin/storeDeliveryTime/toEditByStore.do");
        return list;
    }

    @Override
    public Map<String, List<String>> permissionRelaMap() {

        List<String> list = stringToList("/admin/store/list.do");
        //
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        map.put("/admin/storeDeliveryTime/toEditByStore.do", list);
        map.put("/admin/storeDeliveryTime/edit.do", list);
        return map;
    }

    @Override
    public List<String> whiteNameUris() {

        List<String> list = new ArrayList<String>();
        list.add("/storeDeliveryTime/listPickupTimeList.do");
        list.add("/storeDeliveryTime/listPickupTimeListByDate.do");
        list.add("/storeDeliveryTime/listDeliveryTimeList.do");
        list.add("/storeDeliveryTime/getPickupDesc.do");
        return list;
    }
}
