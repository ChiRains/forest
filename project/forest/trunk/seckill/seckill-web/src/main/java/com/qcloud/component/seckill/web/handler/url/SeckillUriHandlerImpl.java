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
        list.add("/seckill/current.do");
        //
        list.add("/app/seckill/listByScreenings4Classify.do");
        list.add("/app/seckill/listByScreeings4List.do");
        list.add("/app/seckill/current.do");
        //
        list.add("/seckill/listByScreenings.do");
        list.add("/app/seckill/listByScreenings.do");
        return list;
    }

    @Override
    public List<String> appUris() {

        List<String> list = new ArrayList<String>();
        list.add("/app/seckill/listByScreenings4Classify.do");
        list.add("/app/seckill/listByScreeings4List.do");
        list.add("/app/seckill/current.do");
        //
        list.add("/app/seckill/listByScreenings.do");
        return list;
    }
}
