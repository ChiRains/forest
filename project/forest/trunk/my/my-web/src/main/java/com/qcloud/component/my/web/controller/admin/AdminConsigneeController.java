package com.qcloud.component.my.web.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.qcloud.component.my.model.Consignee;
import com.qcloud.component.my.service.ConsigneeService;
import com.qcloud.component.my.web.handler.ConsigneeHandler;
import com.qcloud.component.my.web.vo.ConsigneeVO;
import com.qcloud.component.publicdata.KeyValueVO;
import com.qcloud.component.publicdata.PublicdataClient;
import com.qcloud.pirates.mvc.AceAjaxView;
import com.qcloud.pirates.util.AssertUtil;

@Controller
@RequestMapping(value = AdminConsigneeController.DIR)
public class AdminConsigneeController {

    public static final String DIR = "admin/consignee";

    @Autowired
    private ConsigneeService   consigneeService;

    @Autowired
    private ConsigneeHandler   consigneeHandler;
    
    @Autowired
	private PublicdataClient publicdataClient;

    /**
     * 获取用户的收货地址列表
     * @param userId
     * @return
     */
    @RequestMapping
    public ModelAndView listBySelect(Long userId) {
        List<Consignee> list = consigneeService.listByUser(userId);
        List<ConsigneeVO> voList = consigneeHandler.toVOList(list);
        ModelAndView model = new ModelAndView("admin/my-Consignee-listBySelect");
        model.addObject("result", voList);
        return model;
    }
    
    /**
     * 获取用户默认的收货地址
     * @param userId
     * @return
     */
    @RequestMapping
    public AceAjaxView defautConsignee(Long userId) {
        AssertUtil.assertNotNull(userId, "ID不能为空");
        Consignee consignee = new Consignee();
        List<Consignee> list = consigneeService.listByUser(userId);
        if (list != null && list.size() > 0) {
        	consignee = list.get(0);
        }
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.addObject("consignee", consignee);
        aceAjaxView.setMessage("查询成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }
    
    /**
     * 新增收货地址页面
     * @return
     */
    @RequestMapping
    public ModelAndView toAdd(Long userId) {
        ModelAndView model = new ModelAndView("/admin/my-Consignee-add");
        List<String> strList = publicdataClient.listProvince();
        List<KeyValueVO> voList = publicdataClient.exchageStr(strList, null, "selected");
        model.addObject("provinceList", voList);
        model.addObject("userId", userId);
        return model;
    }
    
    /**
     * 新增收货地址
     * @param consignee
     * @return
     */
    @RequestMapping
    public AceAjaxView add(Consignee consignee) {
        consigneeService.add(consignee);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.addObject("consignee", consignee);
        aceAjaxView.setMessage("添加成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }
    
}
