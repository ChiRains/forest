package com.qcloud.project.forest.web.controller.outside;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.qcloud.pirates.mvc.FrontAjaxView;
import com.qcloud.project.forest.web.outside.eximpl.OmsDispatcherClient;

// 提供给管易的接口
@Controller
@RequestMapping(value = OutsideToOmsController.DIR)
public class OutsideToOmsController {

    public static final String DIR    = "/";

    @Autowired
    public OmsDispatcherClient client;

    private Log                logger = LogFactory.getLog(getClass());

    @RequestMapping(value = "/omsToDispatcher", method = RequestMethod.GET)
    public FrontAjaxView omsToDispatcher(String method) {

        FrontAjaxView view = new FrontAjaxView();
        client.request(method);
        view.setMessage("测试访问成功.=================================================");
        return view;
    }
}
