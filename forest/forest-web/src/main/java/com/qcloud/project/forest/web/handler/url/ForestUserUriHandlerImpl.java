package com.qcloud.project.forest.web.handler.url;

import java.util.ArrayList;
import java.util.List;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class ForestUserUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        return list;
    }

    @Override
    public List<String> permissionUris() {

        List<String> list = new ArrayList<String>();
        return list;
    }

    @Override
    public List<String> userUris() {

        List<String> list = new ArrayList<String>();
        list.add("/forestUser/editUser.do");
        list.add("/forestUser/getCodeForChangeBingdingMobileByOldNumber.do");
        list.add("/forestUser/verifyCodeForChangeBingdingMobileByOldNumber.do");
        list.add("/forestUser/editBingdingMobile.do");
        return list;
    }

    @Override
    public List<String> whiteNameUris() {

        List<String> list = new ArrayList<String>();
        list.add("/forestUser/safetyLogin.do");
        list.add("/forestUser/sendSafetyCode.do");
        list.add("/forestUser/getCodeForChangeBingdingMobileByNewNumber.do");
        return list;
    }

    @Override
    public List<String> appUris() {

        List<String> list = new ArrayList<String>();
        list.add("/app/forestUser/safetyLogin.do");
        list.add("/app/forestUser/getCodeForChangeBingdingMobileByOldNumber.do");
        list.add("/app/forestUser/verifyCodeForChangeBingdingMobileByOldNumber.do");
        list.add("/app/forestUser/editBingdingMobile.do");
        list.add("/app/forestUser/getCodeForChangeBingdingMobileByNewNumber.do");
        return list;
    }
}
