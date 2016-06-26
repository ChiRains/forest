package com.qcloud.component.goods.web.handler.url;

import java.util.ArrayList;
import java.util.List;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class MonthHotSaleUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> whiteNameUris() {

        List<String> list = new ArrayList<String>();
        list.add("/monthHotSale/list4Mall.do");
        list.add("/monthHotSale/list4Merchant.do");
        return list;
    }
}
