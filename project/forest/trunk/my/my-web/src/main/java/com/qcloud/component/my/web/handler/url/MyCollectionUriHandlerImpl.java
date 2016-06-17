package com.qcloud.component.my.web.handler.url;

import java.util.ArrayList;
import java.util.List;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class MyCollectionUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> userUris() {

        List<String> list = new ArrayList<String>();
        list.add("/myMerchandiseCollection/collect.do");
        list.add("/myMerchandiseCollection/remove.do");
        list.add("/myMerchandiseCollection/removeList.do");
        list.add("/myMerchandiseCollection/removeByUnifiedMerchandise.do");
        list.add("/myMerchandiseCollection/removeByUnifiedMerchandiseList.do");
        list.add("/myMerchandiseCollection/list.do");
        list.add("/myMerchandiseCollection/isCollect.do");
        // ############################################################
        list.add("/app/myMerchandiseCollection/collect.do");
        list.add("/app/myMerchandiseCollection/remove.do");
        list.add("/app/myMerchandiseCollection/removeList.do");
        list.add("/app/myMerchandiseCollection/removeByUnifiedMerchandise.do");
        list.add("/app/myMerchandiseCollection/removeByUnifiedMerchandiseList.do");
        list.add("/app/myMerchandiseCollection/list.do");
        list.add("/app/myMerchandiseCollection/isCollect.do");
        // ############################################################
        list.add("/myMerchantCollection/collect.do");
        list.add("/myMerchantCollection/remove.do");
        list.add("/myMerchantCollection/removeByMerchant.do");
        list.add("/myMerchantCollection/list.do");
        list.add("/myMerchantCollection/isCollect.do");
        // ############################################################
        list.add("/app/myMerchantCollection/collect.do");
        list.add("/app/myMerchantCollection/remove.do");
        list.add("/app/myMerchantCollection/removeByMerchant.do");
        list.add("/app/myMerchantCollection/list.do");
        list.add("/app/myMerchantCollection/isCollect.do");
        // ############################################################
        list.add("/myMerchandiseCollection/clearMyCollect.do");
        list.add("/app/myMerchandiseCollection/clearMyCollect.do");
        // ############################################################
        // 店铺收藏
        list.add("/myStoreCollection/list.do");
        list.add("/app/myStoreCollection/list.do");
        list.add("/myStoreCollection/collect.do");
        list.add("/app/myStoreCollection/collect.do");
        list.add("/myStoreCollection/isCollect.do");
        list.add("/app/myStoreCollection/isCollect.do");
        list.add("/myStoreCollection/remove.do");
        list.add("/app/myStoreCollection/remove.do");
        list.add("/myStoreCollection/get.do");
        list.add("/app/myStoreCollection/get.do");
        return list;
    }

    @Override
    public List<String> appUris() {

        List<String> list = new ArrayList<String>();
        list.add("/app/myMerchandiseCollection/collect.do");
        list.add("/app/myMerchandiseCollection/remove.do");
        list.add("/app/myMerchandiseCollection/removeList.do");
        list.add("/app/myMerchandiseCollection/removeByUnifiedMerchandise.do");
        list.add("/app/myMerchandiseCollection/removeByUnifiedMerchandiseList.do");
        list.add("/app/myMerchandiseCollection/list.do");
        list.add("/app/myMerchandiseCollection/isCollect.do");
        list.add("/app/myMerchantCollection/collect.do");
        list.add("/app/myMerchantCollection/remove.do");
        list.add("/app/myMerchantCollection/removeByMerchant.do");
        list.add("/app/myMerchantCollection/list.do");
        list.add("/app/myMerchantCollection/isCollect.do");
        // ############################################################
        list.add("/app/myMerchandiseCollection/clearMyCollect.do");
        // 店铺收藏
        list.add("/app/myStoreCollection/list.do");
        list.add("/app/myStoreCollection/collect.do");
        list.add("/app/myStoreCollection/isCollect.do");
        list.add("/app/myStoreCollection/remove.do");
        list.add("/app/myStoreCollection/get.do");
        return list;
    }
}
