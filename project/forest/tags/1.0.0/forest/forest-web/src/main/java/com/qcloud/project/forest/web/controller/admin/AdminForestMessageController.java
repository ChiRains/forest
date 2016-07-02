package com.qcloud.project.forest.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.qcloud.component.publicservice.MessageClient;
import com.qcloud.pirates.mvc.AceAjaxView;
import com.qcloud.pirates.util.AssertUtil;

@Controller
@RequestMapping(value = AdminForestMessageController.DIR)
public class AdminForestMessageController {

    public final static String DIR = "admin/forestMessage";

    @Autowired
    private MessageClient      messageClient;

    @RequestMapping
    public ModelAndView modularList() {

        ModelAndView view = new ModelAndView("/admin/forest-Message-modularlist");
        return view;
    }

    /**
     * 发送站内消息
     * @param content
     * @param title
     * @return
     */
    @RequestMapping
    public AceAjaxView sendPrivateMessage(String content, String title) {

        AssertUtil.assertNotEmpty(content, "请填写好发送内容");
        AssertUtil.assertNotEmpty(title, "请填写好标题");
        AceAjaxView aceAjaxView = new AceAjaxView();
        return aceAjaxView;
    }
}
