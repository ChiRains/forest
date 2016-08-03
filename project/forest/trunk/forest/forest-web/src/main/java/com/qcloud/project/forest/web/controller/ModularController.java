package com.qcloud.project.forest.web.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qcloud.component.personalcenter.PersonalcenterClient;
import com.qcloud.component.personalcenter.QUser;
import com.qcloud.pirates.mvc.FrontAjaxView;
import com.qcloud.pirates.web.mvc.annotation.PiratesApp;
import com.qcloud.pirates.web.page.PageParameterUtil;
import com.qcloud.project.forest.model.Modular;
import com.qcloud.project.forest.model.ModularUser;
import com.qcloud.project.forest.model.key.TypeEnum.ModularType;
import com.qcloud.project.forest.service.ModularUserService;
import com.qcloud.project.forest.web.form.ModularForm;
import com.qcloud.project.forest.web.handler.ModularUserHandler;
import com.qcloud.project.forest.web.vo.ModularUserVO;

@Controller
@RequestMapping(value = ModularController.DIR)
public class ModularController {

    public static final String DIR = "/modular";

    @Autowired
    private ModularUserService modularUserService;

    @Autowired
    private ModularUserHandler modularUserHandler;

    /**
     * 我的模块
     * @param request
     * @return
     */
    @PiratesApp
    @RequestMapping
    public FrontAjaxView myModular(HttpServletRequest request) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        List<ModularUser> list = modularUserService.listByUserId(user.getId());
        List<ModularUserVO> voList = modularUserHandler.toVOList(list);
        FrontAjaxView frontAjaxView = new FrontAjaxView();
        frontAjaxView.addObject("list", voList);
        return frontAjaxView;
    }

    /**
     * 所有模块
     * @return
     */
    @PiratesApp
    @RequestMapping
    public FrontAjaxView allModular() {

        List<ModularUserVO> modularUserVOs = new ArrayList<ModularUserVO>(14);
        ModularUserVO modularUserVO = null;
        for (ModularType modularType : ModularType.values()) {
            modularUserVO = new ModularUserVO();
            modularUserVO.setCode(modularType.getKey());
            modularUserVO.setName(modularType.getName());
            modularUserVOs.add(modularUserVO);
        }
        FrontAjaxView frontAjaxView = new FrontAjaxView();
        frontAjaxView.addObject("list", modularUserVOs);
        return frontAjaxView;
    }

    /**
     * 添加模块
     * @param modularForm
     * @param request
     * @return
     */
    @PiratesApp
    @RequestMapping
    public FrontAjaxView addModular(ModularForm modularForm, HttpServletRequest request) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        modularUserService.deleteByUserId(user.getId());
        ModularUser modularUser = null;
        for (Modular modular : modularForm.getModulars()) {
            modularUser = new ModularUser();
            modularUser.setUserId(user.getId());
            modularUser.setModularCode(modular.getCode());
            modularUserService.add(modularUser);
        }
        FrontAjaxView frontAjaxView = new FrontAjaxView();
        frontAjaxView.setMessage("修改成功");
        return frontAjaxView;
    }
}
