package com.qcloud.project.forest.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.qcloud.pirates.core.json.Json;
import com.qcloud.pirates.mvc.AceAjaxView;
import com.qcloud.project.forest.model.Config;
import com.qcloud.project.forest.model.key.TypeEnum.ConfigCodeType;
import com.qcloud.project.forest.model.key.TypeEnum.ConfigType;
import com.qcloud.project.forest.service.ConfigService;
import com.qcloud.project.forest.web.handler.ConfigHandler;
import com.qcloud.project.forest.web.vo.GiftCouponUseRuleVO;

@Controller
@RequestMapping(value = "/" + AdminConfigController.DIR)
public class AdminConfigController {

    public static final String DIR = "admin/config";

    @Autowired
    private ConfigService      configService;

    @Autowired
    private ConfigHandler      configHandler;

    /**
     * 跳转到编辑赠品券使用规则
     * @return
     */
    @RequestMapping
    public ModelAndView toEditGiftCouponUseRule() {

        final String code = ConfigCodeType.GIFTCOUPONUSERULE.getKey();
        Config config = configService.getByCode(code);
        // 未初始化脚本就新增一个
        if (config == null) {
            config = new Config();
            config.setCode(code);
            config.setRemark(ConfigCodeType.GIFTCOUPONUSERULE.getName());
            config.setType(ConfigType.GIFTCOUPONUSERULE.getKey());
            configService.add(config);
        }
        GiftCouponUseRuleVO giftCouponUseRuleVO = configHandler.toGiftCouponUseRuleVO(config.getConfig());
        ModelAndView model = new ModelAndView("/admin/forest-config-editGiftCouponUseRule");
        model.addObject("giftCouponUseRuleVO", giftCouponUseRuleVO);
        return model;
    }

    /**
     * 提交编辑赠品券使用规则
     * @param giftCouponUseRuleVO
     * @return
     */
    @RequestMapping
    public AceAjaxView editGiftCouponUseRule(GiftCouponUseRuleVO giftCouponUseRuleVO) {

        final String code = ConfigCodeType.GIFTCOUPONUSERULE.getKey();
        Config config = configService.getByCode(code);
        String str = Json.toJson(giftCouponUseRuleVO);
        config.setConfig(str);
        configService.update(config);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("编辑成功");
        aceAjaxView.setUrl(DIR + "/toEditGiftCouponUseRule");
        return aceAjaxView;
    }
}
