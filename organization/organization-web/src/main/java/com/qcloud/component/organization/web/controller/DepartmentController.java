package com.qcloud.component.organization.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qcloud.component.organization.model.Department;
import com.qcloud.component.organization.model.query.DepartmentQuery;
import com.qcloud.component.organization.service.DepartmentService;
import com.qcloud.component.publicdata.IntKeyValue;
import com.qcloud.component.publicdata.KeyValueVO;
import com.qcloud.component.publicdata.PublicdataClient;
import com.qcloud.pirates.core.xml.Xml;
import com.qcloud.pirates.core.xml.XmlFactory;
import com.qcloud.pirates.mvc.FrontAjaxView;
import com.qcloud.pirates.mvc.FrontPagingView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.web.mvc.annotation.PiratesApp;
import com.qcloud.pirates.web.page.PPage;

@Controller
@RequestMapping(value = DepartmentController.DIR)
public class DepartmentController {

    public static final String DIR = "/department";

    @Autowired
    private DepartmentService  departmentService;

    @Autowired
    private PublicdataClient   publicdataClient;

    @RequestMapping
    public FrontAjaxView list() {

        List<Department> list = departmentService.listAll();
        List<IntKeyValue> ikList = new ArrayList<IntKeyValue>();
        for (IntKeyValue intKeyValue : list) {
            ikList.add(intKeyValue);
        }
        List<KeyValueVO> voList = publicdataClient.exchageObj(ikList, -1L, "");
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("获取部门列表成功");
        view.addObject("list", voList);
        return view;
    }

    @RequestMapping
    public FrontAjaxView add(Department department) {

        // department.setAddress("大参林");
        // department.setCity("广州市");
        // department.setName("大参林药店");
        // department.setType(1);
        // department.setParentId(-1l);
        // department.setProvince("广东省");
        // department.setDistrict("荔湾区");
        // department.setRegistTime(new Date());
        // departmentService.add(department);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("啊啊啊啊");
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontPagingView listStoreByAddress(DepartmentQuery query, PPage pPage) {

        List<Department> list = departmentService.listByAddress(query, pPage.getPageStart(), pPage.getPageSize());
        List<Map<String, Object>> voList = new ArrayList<Map<String, Object>>();
        for (Department department : list) {
            if (department.getParentId() == -1) {
                continue;
            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", department.getId());
            map.put("name", department.getName());
            map.put("address", department.getAddress());
            map.put("phone", department.getPhone());
            voList.add(map);
        }
        int total = departmentService.countByAddress(query);
        FrontPagingView view = new FrontPagingView(pPage.getPageNum(), pPage.getPageSize(), total);
        view.setMessage("获取门店列表成功．");
        view.setList(voList);
        return view;
    }
}
