package com.qcloud.project.forest.web.handler.url;

import java.util.ArrayList;
import java.util.List;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class IntegralOrderUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/integralOrder/list.do");
        list.add("/admin/integralOrder/toAdd.do");
        list.add("/admin/integralOrder/toEdit.do");
        list.add("/admin/integralOrder/add.do");
        list.add("/admin/integralOrder/edit.do");
        list.add("/admin/integralOrder/shipOrder.do");
        list.add("/admin/integralOrder/shipOrderForm.do");
        return list;
    }

    @Override
    public List<String> permissionUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/integralOrder/list.do");
        return list;
    }

    @Override
    public List<String> userUris() {

        List<String> list = new ArrayList<String>();
        list.add("/integralOrder/list.do");
        list.add("/integralOrder/get.do");
        list.add("/integralOrder/remindOrder.do");
        list.add("/integralOrder/signOrder.do");
        list.add("/integralOrder/getAdvitise.do");
        list.add("/integralOrder/order.do");
        list.add("/integralOrder/expressByOrder.do");
        list.add("/integralOrder/getSimple.do");
        //
        list.add("/app/integralOrder/list.do");
        list.add("/app/integralOrder/get.do");
        list.add("/app/integralOrder/remindOrder.do");
        list.add("/app/integralOrder/signOrder.do");
        list.add("/app/integralOrder/getAdvitise.do");
        list.add("/app/integralOrder/order.do");
        list.add("/app/integralOrder/expressByOrder.do");
        list.add("/app/integralOrder/getSimple.do");
        return list;
    }

    @Override
    public List<String> appUris() {

        List<String> list = new ArrayList<String>();
        list.add("/app/integralOrder/list.do");
        list.add("/app/integralOrder/get.do");
        list.add("/app/integralOrder/remindOrder.do");
        list.add("/app/integralOrder/signOrder.do");
        list.add("/app/integralOrder/getAdvitise.do");
        list.add("/app/integralOrder/order.do");
        list.add("/app/integralOrder/expressByOrder.do");
        list.add("/app/integralOrder/getSimple.do");
        return list;
    }
}
