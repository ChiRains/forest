package com.qcloud.project.forest.web.handler.url;

import java.util.ArrayList;
import java.util.List;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class ModularUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        return list;
    }

    @Override
    public List<String> appUris() {

        List<String> list = new ArrayList<String>();
        list.add("/app/modular/myModular.do");
        list.add("/app/modular/allModular.do");
        list.add("/app/modular/addModular.do");
        return list;
    }

    @Override
    public List<String> whiteNameUris() {

        List<String> list = new ArrayList<String>();
        list.add("/app/modular/allModular.do");
        //
        list.add("/modular/allModular.do");
        return list;
    }

    @Override
    public List<String> userUris() {

        List<String> list = new ArrayList<String>();
        list.add("/app/modular/myModular.do");
        list.add("/app/modular/addModular.do");
        //
        list.add("/modular/myModular.do");
        list.add("/modular/addModular.do");
        return list;
    }
}
