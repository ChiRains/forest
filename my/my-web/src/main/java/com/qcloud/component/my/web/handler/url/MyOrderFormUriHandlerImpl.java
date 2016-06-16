package com.qcloud.component.my.web.handler.url;

import java.util.ArrayList;
import java.util.List;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class MyOrderFormUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> userUris() {

        List<String> list = new ArrayList<String>();
        list.add("/myOrderForm/query.do");
        list.add("/myOrderForm/query4Merchandise.do");
        list.add("/myOrderForm/query4Merchant.do");
        list.add("/myOrderForm/statMyOrder.do");
        list.add("/myOrderForm/list.do");
        list.add("/app/myOrderForm/query.do");
        list.add("/app/myOrderForm/query4Merchandise.do");
        list.add("/app/myOrderForm/query4Merchant.do");
        list.add("/app/myOrderForm/statMyOrder.do");
        list.add("/app/myOrderForm/list.do");
        return list;
    }

    @Override
    public List<String> appUris() {

        List<String> list = new ArrayList<String>();
        list.add("/app/myOrderForm/query.do");
        list.add("/app/myOrderForm/query4Merchandise.do");
        list.add("/app/myOrderForm/query4Merchant.do");
        list.add("/app/myOrderForm/statMyOrder.do");
        list.add("/app/myOrderForm/count.do");
        list.add("/app/myOrderForm/list.do");
        return list;
    }
}
