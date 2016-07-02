package com.qcloud.component.seckill.web.handler.url;

import java.util.ArrayList;
import java.util.List;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class SeckillOrderUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> userUris() {

        List<String> list = new ArrayList<String>();
        list.add("/seckillOrder/order.do");
        list.add("/seckillOrder/prepareAlipayPay.do");
        list.add("/app/seckillOrder/order.do");
        list.add("/app/seckillOrder/prepareAlipayPay.do");
        return list;
    }

    @Override
    public List<String> appUris() {

        List<String> list = new ArrayList<String>();
        list.add("/app/seckillOrder/order.do");
        list.add("/app/seckillOrder/prepareAlipayPay.do");
        return list;
    }
}
