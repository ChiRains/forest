package com.qcloud.project.forest.web.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qcloud.component.personalcenter.PersonalcenterClient;
import com.qcloud.component.personalcenter.web.controller.UserController;
import com.qcloud.pirates.mvc.FrontAjaxView;
import com.qcloud.pirates.web.page.PageParameterUtil;

@Controller
@RequestMapping(value = ForestUserController.DIR)
public class ForestUserController {

    public static final String DIR = "/forestUser";

    @Autowired
    private UserController     userController;

    @RequestMapping
    public FrontAjaxView editUser(HttpServletRequest request, String headImage, String nickname, String email) {

        PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        userController.editUserHeadImage(request, headImage);
        userController.editNickname(request, nickname);
        userController.editEmail(request, email);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("修改个人资料成功");
        return view;
    }
}
