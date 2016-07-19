package com.qcloud.component.orderform.web.handler.url;

import java.util.ArrayList;
import java.util.List;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class PayUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> userUris() {

        List<String> list = new ArrayList<String>();
        list.add("/pay/preparePay.do");
        list.add("/pay/prepareAlipayPay.do");
        //
        list.add("/app/pay/prepareAlipayPay.do");
        return list;
    }

    @Override
    public List<String> whiteNameUris() {

        List<String> list = new ArrayList<String>();
        list.add("/pay/paied.do");
        list.add("/pay/alipayPaied.do");
        //
        list.add("/pay/payModeList.do");
        list.add("/app/pay/payModeList.do");
        return list;
    }

    @Override
    public List<String> appUris() {

        List<String> list = new ArrayList<String>();
        list.add("/app/pay/prepareAlipayPay.do");
        //
        list.add("/app/pay/payModeList.do");
        return list;
    }
}
