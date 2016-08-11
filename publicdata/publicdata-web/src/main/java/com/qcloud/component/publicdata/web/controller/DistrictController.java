package com.qcloud.component.publicdata.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qcloud.component.publicdata.KeyValueVO;
import com.qcloud.component.publicdata.PublicdataClient;
import com.qcloud.component.publicdata.model.District;
import com.qcloud.component.publicdata.web.Util.PinyinSort;
import com.qcloud.pirates.mvc.FrontAjaxView;
import com.qcloud.pirates.web.mvc.annotation.PiratesApp;

@Controller
@RequestMapping(value = DistrictController.DIR)
public class DistrictController {

    //
    public static final String DIR = "/district";

    @Autowired
    private PublicdataClient   publicdataClient;

    // 后台使用,不提供给app
    @RequestMapping
    public FrontAjaxView queryByCity(String city) {

        List<String> strList = publicdataClient.listDistrict(city);
        List<KeyValueVO> voList = publicdataClient.exchageStr(strList, null, "selected");
        FrontAjaxView frontAjaxView = new FrontAjaxView();
        frontAjaxView.setMessage("查询成功");
        frontAjaxView.addObject("districtList", voList);
        return frontAjaxView;
    }

    @RequestMapping
    public FrontAjaxView listByCity(String city) {

        List<String> strList = publicdataClient.listDistrict(city);
        FrontAjaxView frontAjaxView = new FrontAjaxView();
        frontAjaxView.setMessage("查询成功");
        frontAjaxView.addObject("districtList", strList);
        return frontAjaxView;
    }

    @Deprecated
    @RequestMapping
    public FrontAjaxView listDistrictByCity(String city) {

        List<District> strList = publicdataClient.listAllDistrict(city);
        FrontAjaxView frontAjaxView = new FrontAjaxView();
        frontAjaxView.setMessage("查询成功");
        frontAjaxView.addObject("districtList", strList);
        return frontAjaxView;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView listByInitial(String city) {

        List<District> strList = publicdataClient.listAllDistrict(city);
        String[] districts = new String[strList.size()];
        for (int i = 0, j = strList.size(); i < j; i++) {
            districts[i] = strList.get(i).getName();
        }
        List<Map<String, Object>> resultList = PinyinSort.SortByInitial(districts);
        List<String> strs = new ArrayList<String>();
        for (Map<String, Object> map : resultList) {
            for (Object string : map.values()) {
                strs.add(string.toString());
            }
        }
        FrontAjaxView frontAjaxView = new FrontAjaxView();
        frontAjaxView.setMessage("查询成功");
        frontAjaxView.addObject("districtList", strs);
        return frontAjaxView;
    }
}
