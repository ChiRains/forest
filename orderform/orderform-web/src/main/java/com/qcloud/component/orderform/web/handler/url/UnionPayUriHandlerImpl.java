package com.qcloud.component.orderform.web.handler.url;

import java.util.ArrayList;
import java.util.List;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class UnionPayUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> userUris() {

        List<String> list = new ArrayList<String>();
        list.add("/unionPay/prepareUnionPay.do");
        return list;
    }

    @Override
    public List<String> whiteNameUris() {

        List<String> list = new ArrayList<String>();
        list.add("/unionPay/unionPaied.do");
        list.add("/unionPay/prepareUnionPay.do");
        list.add("/unionPay/prepareUnionPay4Muti.do");
        list.add("/unionPay/frontUrl.do");
        return list;
    }
}
