package com.qcloud.component.sellercenter.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qcloud.component.sellercenter.model.Store;
import com.qcloud.component.sellercenter.service.StoreService;
import com.qcloud.component.sellercenter.web.handler.StoreHandler;
import com.qcloud.pirates.mvc.FrontAjaxView;
import com.qcloud.pirates.web.security.annotation.NoReferer;

@Controller
@RequestMapping(value = StoreController.DIR)
public class StoreController {

    public static final String DIR = "/store";

    @Autowired
    private StoreService       storeService;

    @Autowired
    private StoreHandler       storeHandler;

    @RequestMapping
    @NoReferer
    public FrontAjaxView listByAddress(String province, String city, String district) {

        List<Store> list = storeService.listByAddress(province, city, district);
        List<Map<String, Object>> voList = new ArrayList<Map<String, Object>>();
        for (Store store : list) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", store.getId());
            map.put("name", store.getName());
            map.put("address", store.getAddress());
            map.put("phone", store.getPhone());
            voList.add(map);
        }
        FrontAjaxView view = new FrontAjaxView();
        view.addObject("addressList", voList);
        return view;
    }
}
