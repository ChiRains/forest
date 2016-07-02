package com.qcloud.component.goods.web.handler.url;

import java.util.ArrayList;
import java.util.List;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class ExchangeMerchandiseUrlHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> whiteNameUris() {

        List<String> list = new ArrayList<String>();
        list.add("/pointExchange/list.do");
        list.add("/currencyExchange/list.do");
        //
        list.add("/pointExchange/get.do");
        list.add("/currencyExchange/get.do");
        return list;
    }
}
