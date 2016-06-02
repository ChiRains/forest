package com.qcloud.component.personalcenter.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.qcloud.component.personalcenter.model.RegistrationGiftConfig;
import com.qcloud.component.personalcenter.model.WealthConfig;
import com.qcloud.component.personalcenter.service.RegistrationGiftConfigService;
import com.qcloud.component.personalcenter.service.WealthConfigService;
import com.qcloud.pirates.mvc.AceAjaxView;

@Controller
@RequestMapping(value = "/" + AdminPointConfigController.DIR)
public class AdminPointConfigController {

    public final static String    DIR = "admin/pointConfig";

    @Autowired
    WealthConfigService           wealthConfigService;

    @Autowired
    RegistrationGiftConfigService registrationGiftConfigService;

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
    //注册送积分
    @RequestMapping
    public ModelAndView getRegistrationPoint() {

        ModelAndView modelAndView = new ModelAndView("/admin/personalcenter-RegistrationPointConfig-edit");
        RegistrationGiftConfig registrationGiftConfig = registrationGiftConfigService.get();
        modelAndView.addObject("config", registrationGiftConfig);
        return modelAndView;
    }
  //注册送佣金
    @RequestMapping
    public ModelAndView getRegistrationBrokerage() {

        ModelAndView modelAndView = new ModelAndView("/admin/personalcenter-RegistrationBrokerageConfig-edit");
        RegistrationGiftConfig registrationGiftConfig = registrationGiftConfigService.get();
        modelAndView.addObject("config", registrationGiftConfig);
        return modelAndView;
    }
  //注册送兑兑券
    @RequestMapping
    public ModelAndView getRegistrationCurrency() {

        ModelAndView modelAndView = new ModelAndView("/admin/personalcenter-RegistrationCurrencyConfig-edit");
        RegistrationGiftConfig registrationGiftConfig = registrationGiftConfigService.get();
        modelAndView.addObject("config", registrationGiftConfig);
        return modelAndView;
    }

    @RequestMapping
    public AceAjaxView setRegistrationPoint(Integer point) {
        RegistrationGiftConfig gift=new RegistrationGiftConfig();
        gift.setPoint(point);
        registrationGiftConfigService.setPoint(gift);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("保存成功");
        aceAjaxView.setUrl(DIR + "/getRegistrationPoint");
        return aceAjaxView;
    }
    @RequestMapping
    public AceAjaxView setRegistrationBrokerage(Integer brokerage) {
        RegistrationGiftConfig gift=new RegistrationGiftConfig();
        gift.setBrokerage(brokerage);
        registrationGiftConfigService.setBrokerage(gift);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("保存成功");
        aceAjaxView.setUrl(DIR + "/getRegistrationBrokerage");
        return aceAjaxView;
    }
    @RequestMapping
    public AceAjaxView setRegistrationCurrency(Integer currency) {
        RegistrationGiftConfig gift=new RegistrationGiftConfig();
        gift.setCurrency(currency);
        registrationGiftConfigService.setCurrency(gift);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("保存成功");
        aceAjaxView.setUrl(DIR + "/getRegistrationCurrency");
        return aceAjaxView;
    }
}
