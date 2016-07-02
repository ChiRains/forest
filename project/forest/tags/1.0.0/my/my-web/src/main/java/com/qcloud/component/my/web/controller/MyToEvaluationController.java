package com.qcloud.component.my.web.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qcloud.component.my.model.MyToEvaluation;
import com.qcloud.component.my.service.MyToEvaluationService;
import com.qcloud.component.my.web.handler.MyToEvaluationHandler;
import com.qcloud.component.my.web.vo.MyToEvaluationVO;
import com.qcloud.component.personalcenter.PersonalcenterClient;
import com.qcloud.component.personalcenter.QUser;
import com.qcloud.pirates.mvc.FrontAjaxView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.web.mvc.annotation.PiratesApp;
import com.qcloud.pirates.web.page.PPage;
import com.qcloud.pirates.web.page.PageParameterUtil;

@Controller
@RequestMapping(value = MyToEvaluationController.DIR)
public class MyToEvaluationController {

    public static final String    DIR = "/myToEvaluation";

    @Autowired
    private MyToEvaluationService myToEvaluationService;

    @Autowired
    private MyToEvaluationHandler myToEvaluationHandler;

    @PiratesApp
    @RequestMapping
    public FrontAjaxView list(HttpServletRequest request, PPage pPage) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        List<MyToEvaluation> list = myToEvaluationService.listByUser(user.getId(), pPage.getPageStart(), pPage.getPageSize());
        List<MyToEvaluationVO> voList = myToEvaluationHandler.toVOList(list);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("获取待评价数据成功.");
        view.addObject("list", voList);
        return view;
    }

    // 2个方法
    @PiratesApp
    @RequestMapping
    public FrontAjaxView listBySubOrder(HttpServletRequest request, PPage pPage, Long subOrderId) {

        AssertUtil.assertNotNull(subOrderId, "子订单ID不能为空.");
        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        List<MyToEvaluation> list = myToEvaluationService.listByUserAndOrder(user.getId(), subOrderId, pPage.getPageStart(), pPage.getPageSize());
        List<MyToEvaluationVO> voList = myToEvaluationHandler.toVOList(list);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("获取待评价数据成功.");
        view.addObject("list", voList);
        return view;
    }
}
