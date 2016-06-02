package com.qcloud.component.my.web.handler.url;

import java.util.ArrayList;
import java.util.List;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class DeliveryModeUriHandlerImpl extends AbstractUriHandler {

    // @Override
    // public List<String> adminUris() {
    //
    // List<String> list = new ArrayList<String>();
    // list.add("/admin/deliveryMode/list.do");
    // list.add("/admin/deliveryMode/toAdd.do");
    // list.add("/admin/deliveryMode/toEdit.do");
    // list.add("/admin/deliveryMode/add.do");
    // list.add("/admin/deliveryMode/edit.do");
    // return list;
    // }
    //
    // @Override
    // public List<String> permissionUris() {
    //
    // List<String> list = new ArrayList<String>();
    // list.add("/admin/deliveryMode/list.do");
    // return list;
    // }
    @Override
    public List<String> userUris() {

        List<String> list = new ArrayList<String>();
        list.add("/deliveryMode/add.do");
        list.add("/deliveryMode/get.do");
        list.add("/deliveryMode/getDefault.do");
        list.add("/app/deliveryMode/add.do");
        list.add("/app/deliveryMode/get.do");
        list.add("/app/deliveryMode/getDefault.do");
        return list;
    }

    @Override
    public List<String> appUris() {

        List<String> list = new ArrayList<String>();
        list.add("/app/deliveryMode/add.do");
        list.add("/app/deliveryMode/get.do");
        list.add("/app/deliveryMode/getDefault.do");
        return list;
    }
}
