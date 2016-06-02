package com.qcloud.component.my.web.handler.url;

import java.util.ArrayList;
import java.util.List;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class InvoiceModeUriHandlerImpl extends AbstractUriHandler {

    // @Override
    // public List<String> adminUris() {
    //
    // List<String> list = new ArrayList<String>();
    // list.add("/admin/invoiceMode/list.do");
    // list.add("/admin/invoiceMode/toAdd.do");
    // list.add("/admin/invoiceMode/toEdit.do");
    // list.add("/admin/invoiceMode/add.do");
    // list.add("/admin/invoiceMode/edit.do");
    // return list;
    // }
    //
    // @Override
    // public List<String> permissionUris() {
    //
    // List<String> list = new ArrayList<String>();
    // list.add("/admin/invoiceMode/list.do");
    // return list;
    // }
    @Override
    public List<String> userUris() {

        List<String> list = new ArrayList<String>();
        list.add("/invoiceMode/add.do");
        list.add("/invoiceMode/get.do");
        list.add("/invoiceMode/getDefault.do");
        list.add("/app/invoiceMode/add.do");
        list.add("/app/invoiceMode/get.do");
        list.add("/app/invoiceMode/getDefault.do");
        return list;
    }

    @Override
    public List<String> appUris() {

        List<String> list = new ArrayList<String>();
        list.add("/app/invoiceMode/add.do");
        list.add("/app/invoiceMode/get.do");
        list.add("/app/invoiceMode/getDefault.do");
        return list;
    }
}
