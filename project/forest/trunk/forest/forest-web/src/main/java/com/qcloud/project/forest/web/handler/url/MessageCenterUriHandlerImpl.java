package com.qcloud.project.forest.web.handler.url;

import java.util.ArrayList;
import java.util.List;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class MessageCenterUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        return list;
    }

    @Override
    public List<String> userUris() {

        List<String> list = new ArrayList<String>();
        list.add("/messageCenter/getMessageTypeAndNum.do");
        list.add("/app/messageCenter/getMessageTypeAndNum.do");
        list.add("/messageCenter/getMessageList.do");
        list.add("/app/messageCenter/getMessageList.do");
        return list;
    }

    @Override
    public List<String> appUris() {

        List<String> list = new ArrayList<String>();
        list.add("/app/messageCenter/getMessageTypeAndNum.do");
        list.add("/app/messageCenter/getMessageList.do");
        return list;
    }
}
