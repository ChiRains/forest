package com.qcloud.component.goods.web.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qcloud.component.goods.model.MerchandiseBrowsingHistory;
import com.qcloud.component.goods.service.MerchandiseBrowsingHistoryService;
import com.qcloud.component.goods.web.handler.MerchandiseBrowsingHistoryHandler;
import com.qcloud.component.goods.web.vo.MerchandiseBrowsingHistoryVO;
import com.qcloud.component.personalcenter.PersonalcenterClient;
import com.qcloud.component.personalcenter.QUser;
import com.qcloud.pirates.mvc.FrontAjaxView;
import com.qcloud.pirates.web.mvc.annotation.PiratesApp;
import com.qcloud.pirates.web.page.PPage;
import com.qcloud.pirates.web.page.PageParameterUtil;

@Controller
@RequestMapping(value = MerchandiseBrowsingHistoryController.DIR)
public class MerchandiseBrowsingHistoryController {

    public static final String                DIR = "/merchandiseBrowsingHistory";

    @Autowired
    private MerchandiseBrowsingHistoryService merchandiseBrowsingHistoryService;

    @Autowired
    private MerchandiseBrowsingHistoryHandler merchandiseBrowsingHistoryHandler;

    @PiratesApp
    @RequestMapping
    public FrontAjaxView list(HttpServletRequest request, PPage pPage) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        List<MerchandiseBrowsingHistory> list = merchandiseBrowsingHistoryService.listByUser(user.getId(), pPage.getPageStart(), pPage.getPageSize());
        List<MerchandiseBrowsingHistoryVO> voList = merchandiseBrowsingHistoryHandler.toVOList(list);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("查询我的浏览记录成功.");
        view.addObject("list", voList);
        return view;
    }
}
