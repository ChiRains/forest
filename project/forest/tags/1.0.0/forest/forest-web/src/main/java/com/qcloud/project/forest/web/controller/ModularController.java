package com.qcloud.project.forest.web.controller;

import java.util.Iterator;
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
import com.qcloud.project.forest.service.ModularService;
import com.qcloud.project.forest.service.ModularUserService;
import com.qcloud.project.forest.web.form.ModularForm;
import com.qcloud.project.forest.web.handler.ModularHandler;
import com.qcloud.project.forest.web.handler.ModularUserHandler;
import com.qcloud.project.forest.web.vo.ModularUserVO;
import com.qcloud.project.forest.web.vo.ModularVO;

@Controller
@RequestMapping(value = ModularController.DIR)
public class ModularController {

    public static final String DIR = "/modular";

    @Autowired
    private ModularService     modularService;

    @Autowired
    private ModularHandler     modularHandler;

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
        frontAjaxView.addObject("result", voList);
        return frontAjaxView;
    }

    /**
     * 所有模块
     * @return
     */
    @PiratesApp
    @RequestMapping
    public FrontAjaxView allModular() {

        List<Modular> list = modularService.listAll();
        List<ModularVO> voList = modularHandler.toVOList(list);
        Iterator<ModularVO> iter = voList.iterator();
        while (iter.hasNext()) {
            ModularVO s = iter.next();
            if (s.getEnable() == 0) {
                iter.remove();
            }
        }
        FrontAjaxView frontAjaxView = new FrontAjaxView();
        frontAjaxView.addObject("result", voList);
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
            modularUser.setModularId(modular.getId());
            modularUser.setUserId(user.getId());
            modularUserService.add(modularUser);
        }
        FrontAjaxView frontAjaxView = new FrontAjaxView();
        frontAjaxView.setMessage("修改成功");
        return frontAjaxView;
    }
}
