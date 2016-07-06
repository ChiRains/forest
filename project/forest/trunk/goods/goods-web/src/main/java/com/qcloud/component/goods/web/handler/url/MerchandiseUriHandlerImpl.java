package com.qcloud.component.goods.web.handler.url;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class MerchandiseUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/merchandise/list.do");
        list.add("/admin/merchandise/toAdd.do");
        list.add("/admin/merchandise/toEdit.do");
        list.add("/admin/merchandise/add.do");
        list.add("/admin/merchandise/edit.do");
        // list.add("/admin/merchandise/toEditSpecifications.do");
        // list.add("/admin/merchandise/editSpecifications.do");
        list.add("/admin/merchandise/toEditAttributes.do");
        list.add("/admin/merchandise/editAttributes.do");
        // list.add("/admin/merchandise/createSpecs.do");
        list.add("/admin/merchandise/list4UnAudit.do");
        list.add("/admin/merchandise/online.do");
        list.add("/admin/merchandise/offline.do");
        list.add("/admin/merchandise/notAudited.do");
        list.add("/admin/merchandise/onlineSelected.do");
        list.add("/admin/merchandise/toAuditid.do");
        list.add("/admin/merchandise/toEditSpec.do");
        list.add("/admin/merchandise/editSpec.do");
        // list.add("/admin/merchandise/getSpecMerchandiseList.do");
        list.add("/admin/merchandise/getRelation.do");
        list.add("/admin/merchandise/editRelation.do");
        //
        list.add("/admin/merchandise/list4Admin.do");
        list.add("/admin/merchandise/details4Admin.do");
        //
        list.add("/admin/merchandise/listDefaultSpec.do");
        //
        list.add("/admin/merchandise/toEditItemState.do");
        list.add("/admin/merchandise/offShelf.do");
        list.add("/admin/merchandise/shelves.do");
        return list;
    }

    @Override
    public List<String> permissionUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/merchandise/list.do");
        list.add("/admin/merchandise/toAdd.do");
        list.add("/admin/merchandise/toEdit.do");
        list.add("/admin/merchandise/add.do");
        list.add("/admin/merchandise/edit.do");
        // list.add("/admin/merchandise/toEditSpecifications.do");
        // list.add("/admin/merchandise/editSpecifications.do");
        list.add("/admin/merchandise/toEditAttributes.do");
        list.add("/admin/merchandise/editAttributes.do");
        // list.add("/admin/merchandise/createSpecs.do");
        list.add("/admin/merchandise/list4UnAudit.do");
        list.add("/admin/merchandise/online.do");
        list.add("/admin/merchandise/offline.do");
        list.add("/admin/merchandise/notAudited.do");
        list.add("/admin/merchandise/onlineSelected.do");
        list.add("/admin/merchandise/toAuditid.do");
        list.add("/admin/merchandise/toEditSpec.do");
        list.add("/admin/merchandise/editSpec.do");
        // list.add("/admin/merchandise/getSpecMerchandiseList.do");
        list.add("/admin/merchandise/getRelation.do");
        list.add("/admin/merchandise/editRelation.do");
        //
        list.add("/admin/merchandise/list4Admin.do");
        list.add("/admin/merchandise/details4Admin.do");
        list.add("/admin/merchandise/toEditItemState.do");
        return list;
    }

    @Override
    public Map<String, List<String>> permissionRelaMap() {

        List<String> list = stringToList("/admin/merchandise/list.do");
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        map.put("/admin/merchandise/toAdd.do", list);
        map.put("/admin/merchandise/add.do", list);
        map.put("/admin/merchandise/toEdit.do", list);
        map.put("/admin/merchandise/edit.do", list);
        map.put("/admin/merchandise/online.do", list);
        map.put("/admin/merchandise/offline.do", list);
        map.put("/admin/merchandise/toAuditid.do", list);
        map.put("/admin/merchandise/toEditSpec.do", list);
        map.put("/admin/merchandise/editSpec.do", list);
        map.put("/admin/merchandise/editRelation.do", list);
        map.put("/admin/merchandise/getRelation.do", list);
        map.put("/admin/merchandise/toEditAttributes.do", list);
        map.put("/admin/merchandise/editAttributes.do", list);
        map.put("/admin/merchandise/toEditItemState.do", list);
        //
        map.put("/admin/merchandise/details4Admin.do", stringToList("/admin/merchandise/list4Admin.do"));
        //
        map.put("/admin/merchandise/notAudited.do", stringToList("/admin/merchandise/list4UnAudit.do"));
        map.put("/admin/merchandise/online.do", stringToList("/admin/merchandise/list.do", "/admin/merchandise/list4UnAudit.do"));
        map.put("/admin/merchandise/onlineSelected.do", stringToList("/admin/merchandise/list4UnAudit.do"));
        map.put("/admin/merchant/listNeedAudit.do", stringToList("/admin/merchandise/list4UnAudit.do"));
        return map;
    }

    @Override
    public List<String> whiteNameUris() {

        List<String> list = new ArrayList<String>();
        list.add("/merchandise/query.do");
        list.add("/merchandise/queryBySpecifications.do");
        list.add("/merchandise/getByMerchandise.do");
        list.add("/merchandise/getBySpecifications.do");
        list.add("/merchandise/getByUnifiedMerchandise.do");
        list.add("/merchandise/getPriceAndStockBySpecifications.do");
        //
        list.add("/merchandise/getHtmlDetailByUnifiedMerchandise.do");
        list.add("/merchandise/getHtmlDetailByMerchandise.do");
        list.add("/merchandise/getDetailByUnifiedMerchandise.do");
        list.add("/merchandise/getDetailByMerchandise.do");
        //
        list.add("/merchandise/guess.do");
        list.add("/merchandise/guess4Merchant.do");
        list.add("/merchandise/listHotSearch.do");
        //
        list.add("/merchandise/listShopRecommend.do");
        list.add("/app/merchandise/listShopRecommend.do");
        return list;
    }

    @Override
    public List<String> userUris() {

        List<String> list = new ArrayList<String>();
        list.add("/merchandise/listMySearch.do");
        list.add("/app/merchandise/listMySearch.do");
        list.add("/merchandise/clearMySearch.do");
        list.add("/app/merchandise/clearMySearch.do");
        list.add("/merchandise/listMySearchAndTags.do");
        list.add("/app/merchandise/listMySearchAndTags.do");
        return list;
    }

    @Override
    public List<String> appUris() {

        List<String> list = new ArrayList<String>();
        list.add("/app/merchandise/listMySearch.do");
        list.add("/app/merchandise/clearMySearch.do");
        list.add("/app/merchandise/listShopRecommend.do");
        list.add("/app/merchandise/listMySearchAndTags.do");
        return list;
    }
}
