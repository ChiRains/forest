package com.qcloud.component.pay.web.handler.url;

import java.util.ArrayList;
import java.util.List;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class WeixinPayUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> userUris() {

        List<String> list = new ArrayList<String>();
        list.add("/weixinPay/preparePay4WeiXin.do");
        list.add("/weixinPay/preparePay4Web.do");
        list.add("/app/weixinPay/preparePay4App.do");
        return list;
    }

    @Override
    public List<String> appUris() {

        List<String> list = new ArrayList<String>();
        list.add("/app/weixinPay/preparePay4App.do");
        return list;
    }

    @Override
    public List<String> whiteNameUris() {

        List<String> list = new ArrayList<String>();
        list.add("/weixinPay/paied4WeiXin.do");
        list.add("/weixinPay/paied4Web.do");        
        return list;
    }
}
