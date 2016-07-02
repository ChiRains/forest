package com.qcloud.component.personalcenter.web.handler.url;

import java.util.ArrayList;
import java.util.List;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class MySignInUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> userUris() {

        List<String> list = new ArrayList<String>();
        list.add("/mySignIn/signIn.do");
        list.add("/mySignIn/getSignInMseeage.do");
        list.add("/mySignIn/listSignInMonth.do");
        list.add("/mySignIn/simpleSignIn.do");
        //
        list.add("/app/mySignIn/signIn.do");
        list.add("/app/mySignIn/getSignInMseeage.do");
        list.add("/app/mySignIn/listSignInMonth.do");
        list.add("/app/mySignIn/simpleSignIn.do");
        return list;
    }

    @Override
    public List<String> appUris() {

        List<String> list = new ArrayList<String>();
        list.add("/app/mySignIn/signIn.do");
        list.add("/app/mySignIn/getSignInMseeage.do");
        list.add("/app/mySignIn/listSignInMonth.do");
        list.add("/app/mySignIn/getHtmlRule.do");
        list.add("/app/mySignIn/getRule.do");
        list.add("/app/mySignIn/simpleSignIn.do");
        return list;
    }

    @Override
    public List<String> whiteNameUris() {

        List<String> list = new ArrayList<String>();
        list.add("/mySignIn/getHtmlRule.do");
        list.add("/app/mySignIn/getHtmlRule.do");
        //
        list.add("/mySignIn/getRule.do");
        list.add("/app/mySignIn/getRule.do");
        return list;
    }
}
