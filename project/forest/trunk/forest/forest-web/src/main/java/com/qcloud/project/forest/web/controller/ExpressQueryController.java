package com.qcloud.project.forest.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qcloud.component.personalcenter.PersonalcenterClient;
import com.qcloud.component.personalcenter.QUser;
import com.qcloud.pirates.core.json.Json;
import com.qcloud.pirates.core.xml.Xml;
import com.qcloud.pirates.core.xml.XmlFactory;
import com.qcloud.pirates.core.xml.XmlItem;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.mvc.FrontAjaxView;
import com.qcloud.pirates.mvc.FrontPagingView;
import com.qcloud.pirates.util.DateUtil;
import com.qcloud.pirates.util.HttpUtils;
import com.qcloud.pirates.web.mvc.annotation.PiratesApp;
import com.qcloud.pirates.web.page.PPage;
import com.qcloud.pirates.web.page.PageParameterUtil;
import com.qcloud.project.forest.model.ExpressQueryHistory;
import com.qcloud.project.forest.model.ExpressQueryVO;
import com.qcloud.project.forest.model.query.ExpressQueryHistoryQuery;
import com.qcloud.project.forest.service.ExpressQueryHistoryService;
import com.qcloud.project.forest.service.ExpressQueryService;
import com.qcloud.project.forest.web.handler.ExpressQueryHistoryHandler;
import com.qcloud.project.forest.web.vo.ExpressQueryHistoryVO;
import com.qcloud.project.forest.web.vo.ExpressVO;

@Controller
@RequestMapping(value = ExpressQueryController.DIR)
public class ExpressQueryController {

    public static final String         DIR             = "/expressQuery";

    public static final String         XML_Express_KEY = "express";

    @Autowired
    private ExpressQueryHistoryHandler expressQueryHistoryHandler;

    @Autowired
    private ExpressQueryHistoryService expressQueryHistoryService;

    @Autowired
    private ExpressQueryService        expressQueryService;

    /**
     * 快递查询
     * @param code
     * @param expressNum
     * @return
     */
    @PiratesApp
    @RequestMapping
    public FrontAjaxView getExpressQuery(HttpServletRequest request, String code, String expressNum, String expressName) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        Map<String, String> map = expressQueryService.getApiConfig();
        map.put("com", code);
        map.put("nu", expressNum);
        String result = HttpUtils.doPost(ExpressQueryService.apiUrl, map);
        FrontAjaxView frontAjaxView = new FrontAjaxView();
        Map<String, Object> jsonMap = Json.toMap(result);
        if (jsonMap.get("status").equals("1")) {
            ExpressQueryHistoryQuery expressQueryHistoryQuery = new ExpressQueryHistoryQuery();
            expressQueryHistoryQuery.setExpressNum(expressNum);
            expressQueryHistoryQuery.setUserId(user.getId());
            ExpressQueryHistory expressQueryHistory1 = expressQueryHistoryService.getByUserIdAndExpressNum(expressQueryHistoryQuery);
            if (expressQueryHistory1 != null) {
                expressQueryHistoryService.delete(expressQueryHistory1.getId());
            }
            ExpressQueryHistory expressQueryHistory = new ExpressQueryHistory();
            expressQueryHistory.setExpressName(expressName);
            expressQueryHistory.setExpressNum(expressNum);
            expressQueryHistory.setUserId(user.getId());
            expressQueryHistory.setTime(new Date());
            expressQueryHistoryService.add(expressQueryHistory);
            Object[] objects = JSONArray.fromObject(jsonMap.get("data")).toArray();
            List<ExpressQueryVO> list = new ArrayList<ExpressQueryVO>();
            for (int i = 0, j = objects.length; i < j; i++) {
                list.add(Json.toObject(Json.toFormatJson(objects[i]), ExpressQueryVO.class));
            }
            for (ExpressQueryVO expressQueryVO : list) {
                expressQueryVO.setTime(DateUtil.date2String(DateUtil.str2Date(expressQueryVO.getTime()), "MM-dd HH:mm"));
            }
            frontAjaxView.addObject("resultStatus", 1);
            frontAjaxView.addObject("result", list);
        } else if (jsonMap.get("status").equals("2")) {
            frontAjaxView.addObject("resultStatus", 2);
            frontAjaxView.setMessage("接口异常");
        } else if (jsonMap.get("status").equals("0")) {
            frontAjaxView.addObject("resultStatus", 0);
            frontAjaxView.setMessage("暂时没有物流信息");
        }
        return frontAjaxView;
    }

    /**
     * 获取快递企业
     * @return
     */
    @PiratesApp
    @RequestMapping
    public FrontAjaxView getAllExpress() {

        Xml xml = XmlFactory.get(XML_Express_KEY);
        List<ExpressVO> expressVOs = new ArrayList<ExpressVO>();
        if (xml != null) {
            List<XmlItem> messageList = xml.getItemList();
            ExpressVO expressVO = null;
            for (XmlItem xmlItem : messageList) {
                expressVO = new ExpressVO();
                expressVO.setName(xmlItem.getAttrMap().get("name"));
                expressVO.setCode(xmlItem.getText());
                expressVOs.add(expressVO);
            }
        }
        FrontAjaxView frontAjaxView = new FrontAjaxView();
        frontAjaxView.addObject("list", expressVOs);
        return frontAjaxView;
    }

    /**
     * 快递历史查询
     * @param request
     * @param pPage
     * @return
     */
    @PiratesApp
    @RequestMapping
    public FrontPagingView getQueryHistory(HttpServletRequest request, PPage pPage) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        ExpressQueryHistoryQuery expressQueryHistoryQuery = new ExpressQueryHistoryQuery();
        expressQueryHistoryQuery.setUserId(user.getId());
        Page<ExpressQueryHistory> page = expressQueryHistoryService.page(expressQueryHistoryQuery, pPage.getPageStart(), pPage.getPageSize());
        List<ExpressQueryHistoryVO> list = expressQueryHistoryHandler.toVOList(page.getData());
        FrontPagingView frontPagingView = new FrontPagingView(pPage.getPageNum(), pPage.getPageSize(), page.getCount());
        frontPagingView.setList(list);
        return frontPagingView;
    }
}
