package com.qcloud.project.forest.web.handler.url;

import java.util.ArrayList;
import java.util.List;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class ForestUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/forest/advertiseSetting.do");
        return list;
    }

    @Override
    public List<String> permissionUris() {

        List<String> list = new ArrayList<String>();
        return list;
    }

    @Override
    public List<String> whiteNameUris() {

        List<String> list = new ArrayList<String>();
        list.add("/forest/classifyList.do");
        list.add("/forest/getMerchant.do");
        list.add("/forest/indexsModular.do");
        list.add("/forest/menuAccess.do");
        list.add("/forest/goodHealthy.do");
        list.add("/forest/latestArticle.do");
        list.add("/forest/shareSignIn.do");
        list.add("/forest/startPage.do");
        list.add("/forest/getIntergralRules.do");
        list.add("/forest/getHtmlIntergralRules.do");
        //
        list.add("/app/forest/classifyList.do");
        list.add("/app/forest/getMerchant.do");
        list.add("/app/forest/indexsModular.do");
        list.add("/app/forest/menuAccess.do");
        list.add("/app/forest/goodHealthy.do");
        list.add("/app/forest/latestArticle.do");
        list.add("/app/forest/shareSignIn.do");
        list.add("/app/forest/startPage.do");
        list.add("/app/forest/getIntergralRules.do");
        list.add("/app/forest/getHtmlIntergralRules.do");
        //
        list.add("/forest/test.do");
        return list;
    }

    @Override
    public List<String> appUris() {

        List<String> list = new ArrayList<String>();
        list.add("/app/forest/classifyList.do");
        list.add("/app/forest/getMerchant.do");
        list.add("/app/forest/indexsModular.do");
        list.add("/app/forest/menuAccess.do");
        list.add("/app/forest/goodHealthy.do");
        list.add("/app/forest/latestArticle.do");
        list.add("/app/forest/shareSignIn.do");
        list.add("/app/forest/startPage.do");
        list.add("/app/forest/getIntergralRules.do");
        list.add("/app/forest/getHtmlIntergralRules.do");
        return list;
    }
}
