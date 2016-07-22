package com.qcloud.component.my.web.handler.url;

import java.util.ArrayList;
import java.util.List;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class MyShoppingCartUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> userUris() {

        List<String> list = new ArrayList<String>();
        list.add("/myShoppingCart/clear.do");
        list.add("/myShoppingCart/reduce.do");
        list.add("/myShoppingCart/add.do");
        list.add("/myShoppingCart/addList.do");
        list.add("/myShoppingCart/remove.do");
        list.add("/myShoppingCart/removeList.do");
        list.add("/myShoppingCart/editor.do");
        list.add("/myShoppingCart/list.do");
        list.add("/myShoppingCart/list4Merchant.do");
        list.add("/myShoppingCart/list4Classify.do");
        list.add("/myShoppingCart/listAll.do");
        list.add("/myShoppingCart/listAll4Classify.do");
        list.add("/myShoppingCart/listAll4Merchant.do");
        list.add("/myShoppingCart/syncList.do");
        // 2016-07 ryuma
        list.add("/myShoppingCart/list4Combination.do");
        list.add("/myShoppingCart/addFreeList.do");
        list.add("/myShoppingCart/list4Group.do");
        list.add("/myShoppingCart/editorList.do");
        // ###############################################
        list.add("/app/myShoppingCart/clear.do");
        list.add("/app/myShoppingCart/reduce.do");
        list.add("/app/myShoppingCart/add.do");
        list.add("/app/myShoppingCart/addList.do");
        list.add("/app/myShoppingCart/remove.do");
        list.add("/app/myShoppingCart/removeList.do");
        list.add("/app/myShoppingCart/editor.do");
        //
        list.add("/app/myShoppingCart/list.do");
        list.add("/app/myShoppingCart/list4Merchant.do");
        list.add("/app/myShoppingCart/list4Classify.do");
        list.add("/app/myShoppingCart/listAll.do");
        list.add("/app/myShoppingCart/listAll4Classify.do");
        list.add("/app/myShoppingCart/listAll4Merchant.do");
        list.add("/app/myShoppingCart/syncList.do");
        list.add("/app/myShoppingCart/list4Combination.do");
        list.add("/app/myShoppingCart/addFreeList.do");
        list.add("/app/myShoppingCart/list4Group.do");
        list.add("/app/myShoppingCart/editorList.do");
        return list;
    }

    @Override
    public List<String> appUris() {

        List<String> list = new ArrayList<String>();
        list.add("/app/myShoppingCart/clear.do");
        list.add("/app/myShoppingCart/reduce.do");
        list.add("/app/myShoppingCart/add.do");
        list.add("/app/myShoppingCart/addList.do");
        list.add("/app/myShoppingCart/remove.do");
        list.add("/app/myShoppingCart/removeList.do");
        list.add("/app/myShoppingCart/editor.do");
        //
        list.add("/app/myShoppingCart/list.do");
        list.add("/app/myShoppingCart/list4Merchant.do");
        list.add("/app/myShoppingCart/list4Classify.do");
        //
        list.add("/app/myShoppingCart/listAll.do");
        list.add("/app/myShoppingCart/listAll4Classify.do");
        list.add("/app/myShoppingCart/listAll4Merchant.do");
        //
        list.add("/app/myShoppingCart/syncList.do");
        list.add("/app/myShoppingCart/list4Combination.do");
        list.add("/app/myShoppingCart/addFreeList.do");
        list.add("/app/myShoppingCart/list4Group.do");
        list.add("/app/myShoppingCart/editorList.do");
        return list;
    }
}
