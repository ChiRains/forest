package com.qcloud.component.personalcenter.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.qcloud.component.personalcenter.model.WealthConfig;
import com.qcloud.component.personalcenter.service.WealthConfigService;
import com.qcloud.pirates.mvc.AceAjaxView;

@Controller
@RequestMapping(value = "/" + AdminPointConfigController.DIR)
public class AdminPointConfigController {

    public final static String DIR = "admin/pointConfig";

    @Autowired
    WealthConfigService        wealthConfigService;

    // 获取积分兑换参数
    @RequestMapping
    public ModelAndView getPointConfig() {

        ModelAndView modelAndView = new ModelAndView("/admin/personalcenter-PointConfig-edit");
        WealthConfig wealthConfig = wealthConfigService.get();
        modelAndView.addObject("pointConfig", wealthConfig.getIntegral());
        modelAndView.addObject("currencyConfig", wealthConfig.getCurrency());
        return modelAndView;
    }

    // 设置积分兑换参数
    @RequestMapping
    public AceAjaxView setPointConfig(Integer pointConfig, Integer currencyConfig) {

        WealthConfig wealthConfig = new WealthConfig();
        wealthConfig.setIntegral(pointConfig == null ? 0 : pointConfig);
        wealthConfig.setCurrency(currencyConfig == null ? 0 : currencyConfig);
        wealthConfigService.set(wealthConfig);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("保存成功");
        aceAjaxView.setUrl(DIR + "/getPointConfig");
        return aceAjaxView;
    }
}
