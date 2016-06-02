package com.qcloud.component.brokerage.web.handler.url;

import java.util.ArrayList;
import java.util.List;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class UserRelationshipUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/userRelationship/list.do");
        list.add("/admin/userRelationship/listDetail.do");
        return list;
    }

    @Override
    public List<String> permissionUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/userRelationship/list.do");
        return list;
    }

    @Override
    public List<String> userUris() {

        List<String> list = new ArrayList<String>();
        list.add("/userRelationship/build.do");
        list.add("/userRelationship/buildByToken.do");
        list.add("/userRelationship/list.do");
        list.add("/userRelationship/listNotAllocation.do");
        list.add("/userRelationship/stat.do");
        list.add("/userRelationship/getRecommedUserMobile.do");
        list.add("/userRelationship/listByType.do");
        list.add("/userRelationship/listByCount.do");
        //
        list.add("/app/userRelationship/build.do");
        list.add("/app/userRelationship/buildByToken.do");
        list.add("/app/userRelationship/list.do");
        list.add("/app/userRelationship/listNotAllocation.do");
        list.add("/app/userRelationship/stat.do");
        list.add("/app/userRelationship/getRecommedUserMobile.do");
        return list;
    }

    @Override
    public List<String> appUris() {

        List<String> list = new ArrayList<String>();
        list.add("/app/userRelationship/build.do");
        list.add("/app/userRelationship/buildByToken.do");
        list.add("/app/userRelationship/list.do");
        list.add("/app/userRelationship/listNotAllocation.do");
        list.add("/app/userRelationship/stat.do");
        list.add("/app/userRelationship/getRecommedUserMobile.do");
        return list;
    }
}
