package com.qcloud.project.forest.web.handler.url;

import java.util.ArrayList;
import java.util.List;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class ForestMailSenderUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        return list;
    }

    @Override
    public List<String> permissionUris() {

        List<String> list = new ArrayList<String>();
        return list;
    }

    @Override
    public List<String> whiteNameUris() {

        List<String> list = new ArrayList<String>();
        list.add("/forestMailSender/sendMail.do");
        list.add("/forestMailSender/editBingdingEMail.do");
        list.add("/app/forestMailSender/editBingdingEMail.do");
        list.add("/app/forestMailSender/sendMail.do");
        return list;
    }

    @Override
    public List<String> appUris() {

        List<String> list = new ArrayList<String>();
        list.add("/app/forestMailSender/sendMail.do");
        list.add("/app/forestMailSender/editBingdingEMail.do");
        return list;
    }
}
