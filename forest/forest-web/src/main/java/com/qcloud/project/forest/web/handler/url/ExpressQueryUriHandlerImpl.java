package com.qcloud.project.forest.web.handler.url;

import java.util.ArrayList;
import java.util.List;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class ExpressQueryUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/userdb/list.do");
        list.add("/admin/userdb/toAdd.do");
        list.add("/admin/userdb/toEdit.do");
        list.add("/admin/userdb/add.do");
        list.add("/admin/userdb/edit.do");
        return list;
    }

    @Override
    public List<String> permissionUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/userdb/list.do");
        return list;
    }

    @Override
    public List<String> whiteNameUris() {

        List<String> list = new ArrayList<String>();
        list.add("/expressQuery/getExpressQuery.do");
        list.add("/app/expressQuery/getExpressQuery.do");
        list.add("/expressQuery/getAllExpress.do");
        list.add("/app/expressQuery/getAllExpress.do");
        return list;
    }

    @Override
    public List<String> appUris() {

        List<String> list = new ArrayList<String>();
        list.add("/app/expressQuery/getExpressQuery.do");
        list.add("/app/expressQuery/getAllExpress.do");
        list.add("/app/expressQuery/getQueryHistory.do");
        return list;
    }

    @Override
    public List<String> userUris() {

        List<String> list = new ArrayList<String>();
        list.add("/expressQuery/getQueryHistory.do");
        list.add("/app/expressQuery/getQueryHistory.do");
        return list;
    }
}
