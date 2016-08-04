package com.qcloud.project.forest.web.handler.url;

import java.util.ArrayList;
import java.util.List;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class ShareGiftUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/shareGift/list.do");
        list.add("/admin/shareGift/toAdd.do");
        list.add("/admin/shareGift/toEdit.do");
        list.add("/admin/shareGift/add.do");
        list.add("/admin/shareGift/edit.do");
        return list;
    }

    @Override
    public List<String> permissionUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/shareGift/list.do");
        return list;
    }

    @Override
    public List<String> userUris() {

        List<String> list = new ArrayList<String>();
        list.add("/shareGift/showCode.do");
        list.add("/shareGift/myInvitation.do");
        list.add("/app/shareGift/myInvitation.do");
        list.add("/app/shareGift/showCode.do");
        return list;
    }

    @Override
    public List<String> appUris() {

        List<String> list = new ArrayList<String>();
        list.add("/app/shareGift/myInvitation.do");
        list.add("/app/shareGift/showCode.do");
        return list;
    }
}
