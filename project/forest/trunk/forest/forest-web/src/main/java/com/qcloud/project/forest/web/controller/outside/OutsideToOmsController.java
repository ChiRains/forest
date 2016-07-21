package com.qcloud.project.forest.web.controller.outside;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.qcloud.pirates.mvc.FrontAjaxView;

// 提供给管易的接口
@Controller
@RequestMapping(value = OutsideToOmsController.DIR)
public class OutsideToOmsController {

    public static final String DIR    = "/";

    private Log                logger = LogFactory.getLog(getClass());

    @RequestMapping(value = "/dispatcherOMS", method = RequestMethod.GET)
    public FrontAjaxView dispatcherOMS() {

        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("测试访问成功.=================================================");
        return view;
    }
}
