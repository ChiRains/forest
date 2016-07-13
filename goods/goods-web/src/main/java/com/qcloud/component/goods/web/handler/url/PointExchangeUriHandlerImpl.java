package com.qcloud.component.goods.web.handler.url;

import java.util.ArrayList;
import java.util.List;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class PointExchangeUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> appUris() {

        List<String> list = new ArrayList<String>();
        list.add("/app/pointExchange/exchangRange.do");
        list.add("/app/pointExchange/listMerchandise.do");
        list.add("/app/pointExchange/getDetails.do");
        return list;
    }

    @Override
    public List<String> userUris() {

        List<String> list = new ArrayList<String>();
        return list;
    }

    @Override
    public List<String> whiteNameUris() {

        List<String> list = new ArrayList<String>();
        list.add("/pointExchange/exchangRange.do");
        list.add("/pointExchange/listMerchandise.do");
        list.add("/pointExchange/getDetails.do");
        //
        list.add("/app/pointExchange/exchangRange.do");
        list.add("/app/pointExchange/listMerchandise.do");
        list.add("/app/pointExchange/getDetails.do");
        return list;
    }
}
