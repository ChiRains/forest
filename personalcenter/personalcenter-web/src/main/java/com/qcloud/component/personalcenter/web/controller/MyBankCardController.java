package com.qcloud.component.personalcenter.web.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qcloud.component.personalcenter.PersonalcenterClient;
import com.qcloud.component.personalcenter.QUser;
import com.qcloud.component.personalcenter.model.MyBankCard;
import com.qcloud.component.personalcenter.service.MyBankCardService;
import com.qcloud.component.personalcenter.web.handler.MyBankCardHandler;
import com.qcloud.component.personalcenter.web.vo.MyBankCardVO;
import com.qcloud.pirates.mvc.FrontAjaxView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.web.mvc.annotation.PiratesApp;
import com.qcloud.pirates.web.page.PageParameterUtil;

@Controller
@RequestMapping(value = MyBankCardController.DIR)
public class MyBankCardController {

    public static final String DIR = "/myBankCard";

    @Autowired
    private MyBankCardService  myBankCardService;

    @Autowired
    private MyBankCardHandler  myBankCardHandler;

    @PiratesApp
    @RequestMapping
    public FrontAjaxView list(HttpServletRequest request) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        List<MyBankCard> list = myBankCardService.listByUser(user.getId());
        List<MyBankCardVO> voList = myBankCardHandler.toVOList(list);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("获取 我的银行卡成功.");
        view.addObject("list", voList);
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView add(HttpServletRequest request, String bank, String card, String cardholder, String mobile) {

        AssertUtil.assertNotEmpty(bank, "银行不能为空");
        AssertUtil.assertNotEmpty(card, "卡号不能为空");
        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        MyBankCard myBankCard = new MyBankCard();
        myBankCard.setBank(bank);
        myBankCard.setCard(card);
        myBankCard.setMobile(mobile);
        myBankCard.setCardholder(cardholder);
        myBankCard.setUserId(user.getId());
        myBankCardService.add(myBankCard);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("添加我的银行卡成功.");
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView delete(HttpServletRequest request, Long id) {

        myBankCardService.delete(id);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("删除我的银行卡成功.");
        return view;
    }
}
