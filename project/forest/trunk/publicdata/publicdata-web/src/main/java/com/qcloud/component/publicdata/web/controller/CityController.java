package com.qcloud.component.publicdata.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qcloud.component.publicdata.KeyValueVO;
import com.qcloud.component.publicdata.PublicdataClient;
import com.qcloud.component.publicdata.model.City;
import com.qcloud.component.publicdata.web.util.PinyinSort;
import com.qcloud.pirates.mvc.FrontAjaxView;
import com.qcloud.pirates.web.mvc.annotation.PiratesApp;

@Controller
@RequestMapping(value = CityController.DIR)
public class CityController {

    //
    public static final String DIR = "/city";

    @Autowired
    private PublicdataClient   publicdataClient;

    // 后端在使用
    @PiratesApp
    @RequestMapping
    public FrontAjaxView queryByProvince(String province) {

        List<String> strList = publicdataClient.listCity(province);
        List<KeyValueVO> voList = publicdataClient.exchageStr(strList, null, "selected");
        FrontAjaxView frontAjaxView = new FrontAjaxView();
        frontAjaxView.setMessage("查询成功");
        frontAjaxView.addObject("cityList", voList);
        return frontAjaxView;
    }

    // 前端 APP 接口
    @PiratesApp
    @RequestMapping
    public FrontAjaxView listByProvince(String province) {

        List<String> strList = publicdataClient.listCity(province);
        FrontAjaxView frontAjaxView = new FrontAjaxView();
        frontAjaxView.setMessage("查询成功");
        frontAjaxView.addObject("cityList", strList);
        return frontAjaxView;
    }

    // 前端 APP 接口
    @PiratesApp
    @Deprecated
    @RequestMapping
    public FrontAjaxView listCityByProvince(String province) {

        List<City> strList = publicdataClient.listAllCity(province);
        FrontAjaxView frontAjaxView = new FrontAjaxView();
        frontAjaxView.setMessage("查询成功");
        frontAjaxView.addObject("cityList", strList);
        return frontAjaxView;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView listAllCity() {

        List<City> cityList = publicdataClient.listAllCity();
        List<String> strList = new ArrayList<String>();
        for (City city : cityList) {
            strList.add(city.getName());
        }
        FrontAjaxView frontAjaxView = new FrontAjaxView();
        frontAjaxView.setMessage("查询成功");
        frontAjaxView.addObject("cityList", strList);
        return frontAjaxView;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView listByInitial(String province) {

        List<City> cityList = publicdataClient.listAllCity(province);
        String[] cities = new String[cityList.size()];
        for (int i = 0, j = cityList.size(); i < j; i++) {
            cities[i] = cityList.get(i).getName();
        }
        List<Map<String, Object>> resultList = PinyinSort.SortByInitial(cities);
        List<String> strs = new ArrayList<String>();
        for (Map<String, Object> map : resultList) {
            for (Object string : map.values()) {
                strs.add(string.toString());
            }
        }
        FrontAjaxView frontAjaxView = new FrontAjaxView();
        frontAjaxView.setMessage("查询成功");
        frontAjaxView.addObject("cityList", strs);
        return frontAjaxView;
    }
}
