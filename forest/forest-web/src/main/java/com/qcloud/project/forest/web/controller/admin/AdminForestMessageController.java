package com.qcloud.project.forest.web.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = AdminForestMessageController.DIR)
public class AdminForestMessageController {

    public final static String DIR = "admin/forestMessage";

    @RequestMapping
    public ModelAndView modularList() {

        ModelAndView view = new ModelAndView("/admin/forest-Message-modularlist");
        return view;
    }
}
