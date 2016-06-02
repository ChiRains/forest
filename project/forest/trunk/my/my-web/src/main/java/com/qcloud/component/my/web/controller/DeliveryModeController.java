package com.qcloud.component.my.web.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qcloud.component.my.DeliveryModeType;
import com.qcloud.component.my.model.DeliveryMode;
import com.qcloud.component.my.service.DeliveryModeService;
import com.qcloud.component.my.web.form.DeliveryForm;
import com.qcloud.component.my.web.handler.DeliveryModeHandler;
import com.qcloud.component.my.web.vo.DeliveryModeVO;
import com.qcloud.component.personalcenter.PersonalcenterClient;
import com.qcloud.component.personalcenter.QUser;
import com.qcloud.pirates.mvc.FrontAjaxView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.web.mvc.annotation.PiratesApp;
import com.qcloud.pirates.web.page.PageParameterUtil;

@Controller
@RequestMapping(value = DeliveryModeController.DIR)
public class DeliveryModeController {

    public static final String  DIR = "/deliveryMode";

    @Autowired
    private DeliveryModeService deliveryModeService;

    @Autowired
    private DeliveryModeHandler deliveryModeHandler;

    @PiratesApp
    @RequestMapping
    public FrontAjaxView add(HttpServletRequest request, DeliveryForm deliveryForm) {

        DeliveryModeType deliveryModeType = null;
        DeliveryModeType[] deliveryModeTypes = DeliveryModeType.values();
        for (DeliveryModeType dt : deliveryModeTypes) {
            if (dt.getKey() == deliveryForm.getType()) {
                deliveryModeType = dt;
                break;
            }
        }
        AssertUtil.assertNotNull(deliveryModeType, "是否配送方式数据不正确." + deliveryForm.getType());
        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        DeliveryMode deliveryMode = deliveryModeService.getByUser(user.getId());
        boolean result = false;
        if (deliveryMode == null) {
            deliveryMode = new DeliveryMode();
            deliveryMode.setUserId(user.getId());
            deliveryMode.setDesc(deliveryForm.getDesc());
            deliveryMode.setStoreId(deliveryForm.getStoreId());
            deliveryMode.setTime(deliveryForm.getTime());
            deliveryMode.setType(deliveryForm.getType());
            result = deliveryModeService.add(deliveryMode);
        } else {
            deliveryMode.setDesc(deliveryForm.getDesc());
            deliveryMode.setStoreId(deliveryForm.getStoreId());
            deliveryMode.setTime(deliveryForm.getTime());
            deliveryMode.setType(deliveryForm.getType());
            result = deliveryModeService.update(deliveryMode);
        }
        AssertUtil.assertTrue(result, "添加配送信息失败.");
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("添加配送信息成功.");
        view.addObject("id", deliveryMode.getId());
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView get(HttpServletRequest request) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        DeliveryMode deliveryMode = deliveryModeService.getByUser(user.getId());
        if (deliveryMode == null) {
            deliveryMode = new DeliveryMode();
            deliveryMode.setType(DeliveryModeType.DELIVERY.getKey());
        }
        DeliveryModeVO deliveryModeVO = deliveryModeHandler.toVO(deliveryMode);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("获取配送信息成功.");
        view.addObject("delivery", deliveryModeVO);
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView getDefault(HttpServletRequest request) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        DeliveryMode deliveryMode = deliveryModeService.getByUser(user.getId());
        if (deliveryMode == null) {
            FrontAjaxView view = new FrontAjaxView();
            view.setMessage("获取配送信息成功.");
            return view;
        }
        DeliveryModeVO deliveryModeVO = deliveryModeHandler.toVO(deliveryMode);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("获取配送信息成功.");
        view.addObject("delivery", deliveryModeVO);
        return view;
    }
}
