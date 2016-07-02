package com.qcloud.component.personalcenter.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qcloud.component.personalcenter.service.MyCommissionWithdrawCashService;
import com.qcloud.component.personalcenter.web.handler.MyCommissionWithdrawCashHandler;

@Controller
@RequestMapping(value = MyCommissionWithdrawCashController.DIR)
public class MyCommissionWithdrawCashController {

    public static final String              DIR = "/myCommissionWithdrawCash";

    @Autowired
    private MyCommissionWithdrawCashService myCommissionWithdrawCashService;

    @Autowired
    private MyCommissionWithdrawCashHandler myCommissionWithdrawCashHandler;
}
