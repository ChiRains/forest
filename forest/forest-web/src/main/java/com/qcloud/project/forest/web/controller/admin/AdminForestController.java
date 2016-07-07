package com.qcloud.project.forest.web.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/" + AdminForestController.DIR)
public class AdminForestController {

    public static final String DIR = "admin/forest";

    @RequestMapping
    public ModelAndView advertiseSetting() {

        ModelAndView view = new ModelAndView("/admin/forest-Setting-advertise");
        return view;
    }
}
