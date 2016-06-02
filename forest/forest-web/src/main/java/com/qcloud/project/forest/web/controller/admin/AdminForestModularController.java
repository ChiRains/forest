package com.qcloud.project.forest.web.controller.admin;

import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.qcloud.component.filesdk.FileSDKClient;
import com.qcloud.component.parameter.ParamType;
import com.qcloud.component.parameter.ParameterClient;
import com.qcloud.pirates.mvc.AceAjaxView;
import com.qcloud.project.forest.model.ForestModular;
import com.qcloud.project.forest.service.ForestModularService;
import com.qcloud.project.forest.web.form.ForestModularForm;

@Controller
@RequestMapping(value = "/" + AdminForestModularController.DIR)
public class AdminForestModularController {

    public static final String   DIR = "admin/forestModular";

    @Autowired
    private ForestModularService forestModularService;

    @Autowired
    private ParameterClient      parameterClient;

    @Autowired
    private FileSDKClient        fileSDKClient;

    @RequestMapping
    public ModelAndView indexModular() {

        List<ForestModular> list = forestModularService.listAppIndexModular();
        ModelAndView view = new ModelAndView("admin/forest-App-Index-image");
        view.addObject("result", list);
        return view;
    }

    @RequestMapping
    public AceAjaxView editIndexModular(ForestModularForm modularForm) {

        List<ForestModular> modulars = modularForm.getModulars();
        for (ForestModular modular : modulars) {
            if (StringUtils.isNotEmpty(modular.getValue())) {
                modular.setValue(fileSDKClient.uidToUrl(modular.getValue()));
                parameterClient.reg(modular.getCode(), modular.getValue(), ParamType.getParamType(Integer.parseInt(modular.getType())));
            }
        }
        AceAjaxView view = new AceAjaxView();
        view.setMessage("添加成功");
        view.setUrl(DIR + "/indexModular");
        return view;
    }
}
