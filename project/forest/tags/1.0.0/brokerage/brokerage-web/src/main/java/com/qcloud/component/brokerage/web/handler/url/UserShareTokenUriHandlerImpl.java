package com.qcloud.component.brokerage.web.handler.url;

import java.util.ArrayList;
import java.util.List;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class UserShareTokenUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> userUris() {

        List<String> list = new ArrayList<String>();
        list.add("/userShareToken/getUserShareToken.do");
        list.add("/userShareToken/getLoginAllocationUser.do");
        list.add("/app/userShareToken/getUserShareToken.do");
        list.add("/app/userShareToken/getLoginAllocationUser.do");
        list.add("/app/userShareToken/getQRCodeUrl.do");
        return list;
    }

    @Override
    public List<String> whiteNameUris() {

        List<String> list = new ArrayList<String>();
        list.add("/app/userShareToken/getMobileByToken.do");
        return list;
    }

    @Override
    public List<String> appUris() {

        List<String> list = new ArrayList<String>();
        list.add("/app/userShareToken/getUserShareToken.do");
        list.add("/app/userShareToken/getLoginAllocationUser.do");
        list.add("/app/userShareToken/getMobileByToken.do");
        list.add("/app/userShareToken/getQRCodeUrl.do");
        return list;
    }
}
