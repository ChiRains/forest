package com.qcloud.project.forest.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qcloud.pirates.core.json.Json;
import com.qcloud.pirates.core.xml.Xml;
import com.qcloud.pirates.core.xml.XmlFactory;
import com.qcloud.pirates.core.xml.XmlItem;
import com.qcloud.pirates.mvc.FrontAjaxView;
import com.qcloud.pirates.util.DateUtil;
import com.qcloud.pirates.util.HttpUtils;
import com.qcloud.pirates.web.mvc.annotation.PiratesApp;
import com.qcloud.project.forest.web.vo.ExpressQueryVO;
import com.qcloud.project.forest.web.vo.ExpressVO;

@Controller
@RequestMapping(value = ExpressQueryController.DIR)
public class ExpressQueryController {

    public static final String DIR             = "/expressQuery";

    public static final String XML_Express_KEY = "express";

    /**
     * 快递查询
     * @param code
     * @param expressNum
     * @return
     */
    @PiratesApp
    @RequestMapping
    public FrontAjaxView getExpressQuery(String code, String expressNum) {

        Map<String, String> map = new HashMap<String, String>();
        map.put("id", "a960241fb0d1ec1d");
        map.put("com", code);
        map.put("nu", expressNum);
        map.put("show", "0");
        map.put("order", "desc");
        String result = HttpUtils.doPost("http://api.kuaidi100.com/api", map);
        FrontAjaxView frontAjaxView = new FrontAjaxView();
        Map<String, Object> jsonMap = Json.toMap(result);
        if (jsonMap.get("status").equals("1")) {
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
        frontAjaxView.addObject("result", expressVOs);
        return frontAjaxView;
    }
}
