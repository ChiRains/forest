package com.qcloud.component.my.web.handler.url;

import java.util.ArrayList;
import java.util.List;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class MyCouponUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        // list.add("/admin/myCoupon/list.do");
        // list.add("/admin/myCoupon/toAdd.do");
        // list.add("/admin/myCoupon/toEdit.do");
        // list.add("/admin/myCoupon/add.do");
        // list.add("/admin/myCoupon/edit.do");
        return list;
    }

    @Override
    public List<String> permissionUris() {

        List<String> list = new ArrayList<String>();
        // list.add("/admin/myCoupon/list.do");
        return list;
    }

    @Override
    public List<String> userUris() {

        List<String> list = new ArrayList<String>();
        list.add("/myCoupon/list.do");
        list.add("/myCoupon/page.do");
        list.add("/myCoupon/count.do");
        list.add("/myCoupon/listCanUse.do");
        list.add("/myCoupon/delete.do");
        list.add("/app/myCoupon/list.do");
        list.add("/app/myCoupon/listCanUse.do");
        list.add("/app/myCoupon/getByCode.do");
        list.add("/app/myCoupon/delete.do");
        list.add("/app/myCoupon/page.do");
        return list;
    }

    @Override
    public List<String> appUris() {

        List<String> list = new ArrayList<String>();
        list.add("/app/myCoupon/list.do");
        list.add("/app/myCoupon/listCanUse.do");
        list.add("/app/myCoupon/getByCode.do");
        list.add("/app/myCoupon/delete.do");
        list.add("/app/myCoupon/page.do");
        return list;
    }
}
