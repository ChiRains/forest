package com.qcloud.component.my.web.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qcloud.component.my.service.SearchHistoryService;
import com.qcloud.component.personalcenter.PersonalcenterClient;
import com.qcloud.component.personalcenter.QUser;
import com.qcloud.pirates.mvc.FrontAjaxView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.web.mvc.annotation.PiratesApp;
import com.qcloud.pirates.web.page.PageParameterUtil;

@Controller
@RequestMapping(value = SearchHistoryController.DIR)
public class SearchHistoryController {

    public static final String   DIR = "/mySearchHistory";

    @Autowired
    private SearchHistoryService searchHistoryService;

    @PiratesApp
    @RequestMapping
    public FrontAjaxView list(HttpServletRequest request, Integer type, Integer number) {

        AssertUtil.assertNotNull(type, "获取我的搜索数据类型不能为空.");
        number = number == null || number <= 0 || number > 100 ? 20 : number;
        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        List<String> list = searchHistoryService.list(user.getId(), type, number);
        FrontAjaxView frontAjaxView = new FrontAjaxView();
        frontAjaxView.setMessage("查询我的搜索历史成功");
        frontAjaxView.addObject("list", list);
        return frontAjaxView;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView clear(HttpServletRequest request, Integer type) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        searchHistoryService.clear(user.getId(), type);
        FrontAjaxView frontAjaxView = new FrontAjaxView();
        frontAjaxView.setMessage("清空搜索历史成功.");
        return frontAjaxView;
    }
}
