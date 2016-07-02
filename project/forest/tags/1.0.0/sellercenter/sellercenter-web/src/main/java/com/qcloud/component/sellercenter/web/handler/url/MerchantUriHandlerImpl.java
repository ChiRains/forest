package com.qcloud.component.sellercenter.web.handler.url;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class MerchantUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/merchant/list.do");
        list.add("/admin/merchant/details4Admin.do");
        list.add("/admin/merchant/toAdd.do");
        list.add("/admin/merchant/toEdit.do");
        list.add("/admin/merchant/add.do");
        list.add("/admin/merchant/edit.do");
        list.add("/admin/merchant/toPerfect.do");
        list.add("/admin/merchant/perfect.do");
        list.add("/admin/merchant/toAddMember4Admin.do");
        list.add("/admin/merchant/addMember4Admin.do");
        list.add("/admin/merchant/listNeedAudit.do");
        list.add("/admin/merchant/allMerchantlist.do");
        list.add("/admin/merchant/selectUserList.do");
        list.add("/admin/merchant/disableMerchant.do");
        list.add("/admin/merchant/enableMerchant.do");
        list.add("/admin/merchant/notifyMerchant.do");
        list.add("/admin/merchant/unNotifyMerchant.do");
        list.add("/admin/merchant/toImport.do");
        list.add("/admin/merchant/importExcel.do");
        return list;
    }

    @Override
    public List<String> permissionUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/merchant/list.do");
        list.add("/admin/merchant/toAdd.do");
        list.add("/admin/merchant/toEdit.do");
        list.add("/admin/merchant/add.do");
        list.add("/admin/merchant/edit.do");
        list.add("/admin/merchant/toPerfect.do");
        list.add("/admin/merchant/perfect.do");
        list.add("/admin/merchant/toAddMember4Admin.do");
        list.add("/admin/merchant/addMember4Admin.do");
        list.add("/admin/merchant/listNeedAudit.do");
        list.add("/admin/merchant/allMerchantlist.do");
        list.add("/admin/merchant/selectUserList.do");
        list.add("/admin/merchant/disableMerchant.do");
        list.add("/admin/merchant/enableMerchant.do");
        list.add("/admin/merchant/notifyMerchant.do");
        list.add("/admin/merchant/unNotifyMerchant.do");
        list.add("/admin/merchant/toImport.do");
        list.add("/admin/merchant/importExcel.do");
        return list;
    }

    @Override
    public Map<String, List<String>> permissionRelaMap() {

        List<String> list = stringToList("/admin/merchant/list.do");
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        map.put("/admin/merchant/toAdd.do", list);
        map.put("/admin/merchant/add.do", list);
        map.put("/admin/merchant/toEdit.do", list);
        map.put("/admin/merchant/edit.do", list);
        map.put("/admin/merchant/enableMerchant.do", list);
        map.put("/admin/merchant/disableMerchant.do", list);
        map.put("/admin/merchant/notifyMerchant.do", list);
        map.put("/admin/merchant/unNotifyMerchant.do", list);
        map.put("/admin/merchant/toImport.do", list);
        map.put("/admin/merchant/importExcel.do", list);
        map.put("/admin/merchant/selectUserList.do", list);
        //
        List<String> pList = stringToList("/admin/merchant/toPerfect.do");
        map.put("/admin/merchant/perfect.do", pList);
        return map;
    }

    @Override
    public List<String> whiteNameUris() {

        List<String> list = new ArrayList<String>();
        list.add("/merchant/list.do");
        list.add("/merchant/listHot.do");
        list.add("/merchant/listFavourable.do");
        list.add("/merchant/listRecently.do");
        list.add("/merchant/listHighMerchant.do");
        list.add("/merchant/getMerchantInfo.do");
        list.add("/merchant/listHotSearch.do");
        // ///////////////////////////////////////////////////////////////
        list.add("/app/merchant/listHighMerchant.do");
        list.add("/app/merchant/getMerchantInfo.do");
        return list;
    }

    @Override
    public List<String> appUris() {

        List<String> list = new ArrayList<String>();
        list.add("/app/merchant/list.do");
        list.add("/app/merchant/listMySearch.do");
        list.add("/app/merchant/clearMySearch.do");
        return list;
    }

    @Override
    public List<String> userUris() {

        List<String> list = new ArrayList<String>();
        list.add("/merchant/listMySearch.do");
        list.add("/app/merchant/listMySearch.do");
        list.add("/merchant/clearMySearch.do");
        list.add("/app/merchant/clearMySearch.do");
        return list;
    }
}
