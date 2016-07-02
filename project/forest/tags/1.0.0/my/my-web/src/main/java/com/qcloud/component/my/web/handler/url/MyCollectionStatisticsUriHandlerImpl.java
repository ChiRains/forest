package com.qcloud.component.my.web.handler.url;

import java.util.ArrayList;
import java.util.List;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class MyCollectionStatisticsUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> userUris() {

        List<String> list = new ArrayList<String>();
        list.add("/myCollectionStatistics/listMerchandiseCollectClassify.do");
        list.add("/app/myCollectionStatistics/listMerchandiseCollectClassify.do");
        return list;
    }

    @Override
    public List<String> appUris() {

        List<String> list = new ArrayList<String>();
        list.add("/app/myCollectionStatistics/listMerchandiseCollectClassify.do");
        return list;
    }
}
