package com.qcloud.project.forest.web.handler.url;

import java.util.ArrayList;
import java.util.List;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class ConfigUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/config/toEditGiftCouponUseRule.do");
        list.add("/admin/config/editGiftCouponUseRule.do");
        return list;
    }
    // @Override
    // public List<String> permissionUris() {
    // List<String> list = new ArrayList<String>();
    // list.add("/admin/userdb/list.do");
    // return list;
    // }
}
