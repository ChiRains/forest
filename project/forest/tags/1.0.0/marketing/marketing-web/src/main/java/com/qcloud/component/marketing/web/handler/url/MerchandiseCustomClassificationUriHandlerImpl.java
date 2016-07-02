package com.qcloud.component.marketing.web.handler.url;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class MerchandiseCustomClassificationUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/merchandiseCustomClassification/toAddClassifyForMerchantCustom.do");
        list.add("/admin/merchandiseCustomClassification/addClassifyForMerchantCustom.do");
        list.add("/admin/merchandiseCustomClassification/selectProductList.do");
        list.add("/admin/merchandiseCustomClassification/toAddClassifyForMallCustom.do");
        list.add("/admin/merchandiseCustomClassification/addClassifyForMallCustom.do");
        list.add("/admin/merchandiseCustomClassification/list.do");
        list.add("/admin/merchandiseCustomClassification/mallList.do");
        list.add("/admin/merchandiseCustomClassification/delete.do");
        list.add("/admin/merchandiseCustomClassification/deleteMall.do");
        // 8-31
        list.add("/admin/merchandiseCustomClassification/customMallList.do");
        list.add("/admin/merchandiseCustomClassification/toAddCustomMall.do");
        list.add("/admin/merchandiseCustomClassification/addCustomMall.do");
        //
        list.add("/admin/merchandiseCustomClassification/multipleSort.do");
        return list;
    }

    @Override
    public List<String> permissionUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/merchandiseCustomClassification/list.do");
        return list;
    }

    @Override
    public Map<String, List<String>> permissionRelaMap() {

        Map<String, List<String>> map = new HashMap<String, List<String>>();
        return map;
    }
}
