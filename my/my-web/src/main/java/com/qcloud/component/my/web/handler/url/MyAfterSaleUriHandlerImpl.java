package com.qcloud.component.my.web.handler.url;

import java.util.ArrayList;
import java.util.List;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class MyAfterSaleUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> userUris() {

        List<String> list = new ArrayList<String>();
        list.add("/myAfterSale/list.do");
        list.add("/app/myAfterSale/list.do");
        //
        list.add("/myAfterSale/listByOrder.do");
        list.add("/app/myAfterSale/listByOrder.do");
        //
        list.add("/myAfterSale/listBySubOrder.do");
        list.add("/app/myAfterSale/listBySubOrder.do");
        return list;
    }

    @Override
    public List<String> appUris() {

        List<String> list = new ArrayList<String>();
        list.add("/app/myAfterSale/list.do");
        list.add("/app/myAfterSale/listByOrder.do");
        list.add("/app/myAfterSale/listBySubOrder.do");
        return list;
    }
}
