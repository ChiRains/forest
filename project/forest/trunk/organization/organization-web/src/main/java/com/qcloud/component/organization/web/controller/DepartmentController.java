package com.qcloud.component.organization.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qcloud.component.organization.model.Department;
import com.qcloud.component.organization.service.DepartmentService;
import com.qcloud.component.publicdata.IntKeyValue;
import com.qcloud.component.publicdata.KeyValueVO;
import com.qcloud.component.publicdata.PublicdataClient;
import com.qcloud.pirates.core.xml.Xml;
import com.qcloud.pirates.core.xml.XmlFactory;
import com.qcloud.pirates.mvc.FrontAjaxView;
import com.qcloud.pirates.util.AssertUtil;

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
Xml xml=XmlFactory.get("organization-org-code");
    AssertUtil.assertNotNull(xml, "111");
    department.setAddress("大参林");
        department.setCity("广州市");
        department.setName("大参林药店");
        department.setType(1);
        department.setParentId(-1l);
        department.setProvince("广东省");
        department.setDistrict("荔湾区");
        department.setRegistTime(new Date());
        departmentService.add(department);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("啊啊啊啊");
        return view;
    }
}
