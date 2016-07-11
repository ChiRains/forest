package com.qcloud.project.forest.web.handler.url;

import java.util.ArrayList;
import java.util.List;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class FeedbackUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/feedback/list.do");
        list.add("/admin/feedback/toAdd.do");
        list.add("/admin/feedback/toEdit.do");
        list.add("/admin/feedback/add.do");
        list.add("/admin/feedback/edit.do");
        return list;
    }

    @Override
    public List<String> permissionUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/feedback/list.do");
        return list;
    }

    @Override
    public List<String> userUris() {

        List<String> list = new ArrayList<String>();
        list.add("/feedback/commitFeedback.do");
        list.add("/app/feedback/commitFeedback.do");
        return list;
    }

    @Override
    public List<String> whiteNameUris() {

        List<String> list = new ArrayList<String>();
        list.add("/feedback/getFeedbackClassify.do");
        list.add("/app/feedback/getFeedbackClassify.do");
        return list;
    }

    @Override
    public List<String> appUris() {

        List<String> list = new ArrayList<String>();
        list.add("/app/feedback/commitFeedback.do");
        list.add("/app/feedback/getFeedbackClassify.do");
        return list;
    }
}
