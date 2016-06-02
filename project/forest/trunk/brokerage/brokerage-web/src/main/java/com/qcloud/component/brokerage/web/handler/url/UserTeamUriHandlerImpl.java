package com.qcloud.component.brokerage.web.handler.url;

import java.util.ArrayList;
import java.util.List;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class UserTeamUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/userTeam/list.do");
        list.add("/admin/userTeam/listDetail.do");
        return list;
    }

    @Override
    public List<String> permissionUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/userTeam/list.do");
        return list;
    }

    @Override
    public List<String> userUris() {

        List<String> list = new ArrayList<String>();
        list.add("/userTeam/build.do");
        list.add("/userTeam/list.do");
        //
        list.add("/app/userTeam/build.do");
        list.add("/app/userTeam/list.do");
        return list;
    }

    @Override
    public List<String> appUris() {

        List<String> list = new ArrayList<String>();
        list.add("/app/userTeam/build.do");
        list.add("/app/userTeam/list.do");
        return list;
    }
}
