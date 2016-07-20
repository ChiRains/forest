package com.qcloud.component.goods.web.handler.url;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class MerchandiseEvaluationUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/merchandiseEvaluation/list.do");
        list.add("/admin/merchandiseEvaluation/toAdd.do");
        list.add("/admin/merchandiseEvaluation/toEdit.do");
        list.add("/admin/merchandiseEvaluation/add.do");
        list.add("/admin/merchandiseEvaluation/edit.do");
        list.add("/admin/merchandiseEvaluation/agree.do");
        list.add("/admin/merchandiseEvaluation/disagree.do");
        list.add("/admin/merchandiseEvaluation/delete.do");
        list.add("/admin/merchandiseEvaluation/toReplyContent.do");
        list.add("/admin/merchandiseEvaluation/replyContent.do");
        return list;
    }

    @Override
    public List<String> permissionUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/merchandiseEvaluation/list.do");
        list.add("/admin/merchandiseEvaluation/toAdd.do");
        list.add("/admin/merchandiseEvaluation/toEdit.do");
        list.add("/admin/merchandiseEvaluation/add.do");
        list.add("/admin/merchandiseEvaluation/edit.do");
        list.add("/admin/merchandiseEvaluation/agree.do");
        list.add("/admin/merchandiseEvaluation/disagree.do");
        list.add("/admin/merchandiseEvaluation/delete.do");
        return list;
    }

    @Override
    public Map<String, List<String>> permissionRelaMap() {

        Map<String, List<String>> map = new HashMap<String, List<String>>();
        return map;
    }

    @Override
    public List<String> whiteNameUris() {

        List<String> list = new ArrayList<String>();
        list.add("/merchandiseEvaluation/list.do");
        list.add("/app/merchandiseEvaluation/list.do");
        return list;
    }

    @Override
    public List<String> userUris() {

        List<String> list = new ArrayList<String>();
        list.add("/merchandiseEvaluation/evaluate.do");
        list.add("/merchandiseEvaluation/toEvaluate.do");
        list.add("/merchandiseEvaluation/toAdditional.do");
        list.add("/merchandiseEvaluation/additional.do");
        list.add("/app/merchandiseEvaluation/evaluate.do");
        list.add("/app/merchandiseEvaluation/toEvaluate.do");
        list.add("/app/merchandiseEvaluation/toAdditional.do");
        list.add("/app/merchandiseEvaluation/additional.do");
        return list;
    }

    @Override
    public List<String> appUris() {

        List<String> list = new ArrayList<String>();
        list.add("/app/merchandiseEvaluation/evaluate.do");
        list.add("/app/merchandiseEvaluation/toEvaluate.do");
        list.add("/app/merchandiseEvaluation/toAdditional.do");
        list.add("/app/merchandiseEvaluation/list.do");
        list.add("/app/merchandiseEvaluation/additional.do");
        return list;
    }
}
