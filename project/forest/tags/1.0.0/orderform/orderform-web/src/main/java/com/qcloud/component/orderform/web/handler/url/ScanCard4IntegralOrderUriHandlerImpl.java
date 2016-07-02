package com.qcloud.component.orderform.web.handler.url;

import java.util.ArrayList;
import java.util.List;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class ScanCard4IntegralOrderUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> userUris() {

        List<String> list = new ArrayList<String>();
        list.add("/app/scanCard/getMembershipCard.do");
        list.add("/app/scanCard/order4Integral.do");
        return list;
    }

    @Override
    public List<String> appUris() {

        List<String> list = new ArrayList<String>();
        list.add("/app/scanCard/getMembershipCard.do");
        list.add("/app/scanCard/order4Integral.do");
        return list;
    }
}
