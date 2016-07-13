package com.qcloud.component.orderform.web.handler.url;

import java.util.ArrayList;
import java.util.List;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class OrderStateUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        list.add("/orderState/confirmOrder.do");
        list.add("/orderState/deliverOrder.do");
        return list;
    }

    @Override
    public List<String> userUris() {

        List<String> list = new ArrayList<String>();
        list.add("/orderState/cancelOrder.do");
        list.add("/orderState/signOrder.do");
        list.add("/orderState/returnOrder.do");
        list.add("/orderState/exchangeOrder.do");
        list.add("/orderState/remindDeliverGoods.do");
        list.add("/orderState/deleteOrder.do");
        //
        list.add("/app/orderState/cancelOrder.do");
        list.add("/app/orderState/signOrder.do");
        list.add("/app/orderState/returnOrder.do");
        list.add("/app/orderState/exchangeOrder.do");
        list.add("/app/orderState/remindDeliverGoods.do");
        //
        list.add("/app/orderState/confirmOrder.do");
        list.add("/app/orderState/deliverOrder.do");
        list.add("/app/orderState/deleteOrder.do");
        return list;
    }

    @Override
    public List<String> whiteNameUris() {

        List<String> list = new ArrayList<String>();
        list.add("/orderState/testExchagneOrderState.do");
        return list;
    }

    @Override
    public List<String> appUris() {

        List<String> list = new ArrayList<String>();
        list.add("/app/orderState/cancelOrder.do");
        list.add("/app/orderState/signOrder.do");
        list.add("/app/orderState/returnOrder.do");
        list.add("/app/orderState/exchangeOrder.do");
        list.add("/app/orderState/remindDeliverGoods.do");
        //
        list.add("/app/orderState/confirmOrder.do");
        list.add("/app/orderState/deliverOrder.do");
        list.add("/app/orderState/deleteOrder.do");
        return list;
    }
}
