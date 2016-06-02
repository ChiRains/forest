package com.qcloud.component.my.web.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qcloud.component.my.model.MyCollectionStatistics;
import com.qcloud.component.my.model.key.TypeEnum.CollectionType;
import com.qcloud.component.my.service.MyCollectionStatisticsService;
import com.qcloud.component.my.web.handler.MyCollectionStatisticsHandler;
import com.qcloud.component.my.web.vo.MyCollectionStatisticsVO;
import com.qcloud.component.personalcenter.PersonalcenterClient;
import com.qcloud.component.personalcenter.QUser;
import com.qcloud.pirates.mvc.FrontAjaxView;
import com.qcloud.pirates.web.mvc.annotation.PiratesApp;
import com.qcloud.pirates.web.page.PageParameterUtil;

@Controller
@RequestMapping(value = MyCollectionStatisticsController.DIR)
public class MyCollectionStatisticsController {

    public static final String            DIR = "/myCollectionStatistics";

    @Autowired
    private MyCollectionStatisticsService myCollectionStatisticsService;

    @Autowired
    private MyCollectionStatisticsHandler myCollectionStatisticsHandler;

    @PiratesApp
    @RequestMapping
    public FrontAjaxView listMerchandiseCollectClassify(HttpServletRequest request) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        List<MyCollectionStatistics> list = myCollectionStatisticsService.listByUserAndType(user.getId(), CollectionType.MERCHANDISE.getKey());
        List<MyCollectionStatisticsVO> voList = myCollectionStatisticsHandler.toVOList(list);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("获取收藏统计成功.");
        view.addObject("classifyList", voList);
        return view;
    }
}
