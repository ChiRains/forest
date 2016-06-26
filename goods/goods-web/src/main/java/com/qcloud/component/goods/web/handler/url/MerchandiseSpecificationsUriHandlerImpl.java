package com.qcloud.component.goods.web.handler.url;

import java.util.ArrayList;
import java.util.List;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class MerchandiseSpecificationsUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> whiteNameUris() {

        List<String> list = new ArrayList<String>();
        list.add("/merchandiseSpecifications/listByMerchandise.do");
        return list;
    }
}
