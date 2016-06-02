package com.qcloud.component.seckill.web.handler.url;

import java.util.ArrayList;
import java.util.List;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class SeckillUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> whiteNameUris() {

        List<String> list = new ArrayList<String>();
        list.add("/seckill/listByScreenings4Classify.do");
        list.add("/seckill/listByScreeings4List.do");
        return list;
    }
}
