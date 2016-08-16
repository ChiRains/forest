package com.qcloud.component.my.web.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qcloud.component.my.exception.MyException;
import com.qcloud.component.my.model.Consignee;
import com.qcloud.component.my.service.ConsigneeService;
import com.qcloud.component.my.web.form.ConsigneeForm;
import com.qcloud.component.my.web.handler.ConsigneeHandler;
import com.qcloud.component.my.web.vo.ConsigneeVO;
import com.qcloud.component.personalcenter.PersonalcenterClient;
import com.qcloud.component.personalcenter.QUser;
import com.qcloud.pirates.mvc.FrontAjaxView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.StringUtil;
import com.qcloud.pirates.web.mvc.annotation.PiratesApp;
import com.qcloud.pirates.web.page.PageParameterUtil;

@Controller
@RequestMapping(value = ConsigneeController.DIR)
public class ConsigneeController {

    public static final String DIR = "/consignee";

    @Autowired
    private ConsigneeService   consigneeService;

    @Autowired
    private ConsigneeHandler   consigneeHandler;

    @PiratesApp
    @RequestMapping
    public FrontAjaxView add(HttpServletRequest request, ConsigneeForm consigneeForm, Integer acquiesce) {

        acquiesce = acquiesce == null || acquiesce < 1 || acquiesce > 2 ? 2 : acquiesce;
        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        Consignee consignee = new Consignee();
        consignee.setProvince(consigneeForm.getProvince());
        consignee.setCity(consigneeForm.getCity());
        consignee.setDistrict(consigneeForm.getDistrict());
        consignee.setAddress(consigneeForm.getAddress());
        consignee.setMobile(consigneeForm.getMobile());
        consignee.setName(consigneeForm.getName());
        consignee.setUserId(user.getId());
        consignee.setZipCode(consigneeForm.getZipCode());
        consignee.setAcquiesce(acquiesce);
        boolean exist = false;
        List<Consignee> list = consigneeService.listByUser(user.getId());
        Long id = -1L;
        for (Consignee c : list) {
            if (StringUtil.nullToEmpty(c.getAddress()).equals(consignee.getAddress()) && StringUtil.nullToEmpty(c.getProvince()).equals(consignee.getProvince()) && StringUtil.nullToEmpty(c.getCity()).equals(consignee.getCity()) && StringUtil.nullToEmpty(c.getDistrict()).equals(consignee.getDistrict()) && StringUtil.nullToEmpty(c.getMobile()).equals(consignee.getMobile()) && StringUtil.nullToEmpty(c.getName()).equals(consignee.getName())) {
                exist = true;
                id = c.getId();
                break;
            }
        }
        AssertUtil.assertTrue(!exist, "地址已经存在.");
        boolean result = consigneeService.add(consignee);
        AssertUtil.assertTrue(result, "添加收货人信息失败.");
        id = consignee.getId();
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("添加收货人信息成功.");
        view.addObject("id", id);
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView update(HttpServletRequest request, Consignee consignee) {

        Consignee con = consigneeService.get(consignee.getId());
        AssertUtil.assertNotNull(con, "收货人信息不存在." + consignee.getId());
        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        if (user.getId() != con.getUserId()) {
            throw new MyException("不能修改其他人的收货信息.");
        }
        consignee.setUserId(user.getId());
        // consignee.setAcquiesce(con.getAcquiesce());
        boolean exist = false;
        List<Consignee> list = consigneeService.listByUser(user.getId());
        for (Consignee c : list) {
            if (c.getId() != consignee.getId() && StringUtil.nullToEmpty(c.getAddress()).equals(consignee.getAddress()) && StringUtil.nullToEmpty(c.getProvince()).equals(consignee.getProvince()) && StringUtil.nullToEmpty(c.getCity()).equals(consignee.getCity()) && StringUtil.nullToEmpty(c.getDistrict()).equals(consignee.getDistrict()) && StringUtil.nullToEmpty(c.getMobile()).equals(consignee.getMobile()) && StringUtil.nullToEmpty(c.getName()).equals(consignee.getName())) {
                exist = true;
                break;
            }
        }
        AssertUtil.assertTrue(!exist, "地址已经存在.");
        boolean result = consigneeService.update(consignee);
        AssertUtil.assertTrue(result, "修改收货人信息失败.");
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("修改收货人信息成功.");
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView delete(HttpServletRequest request, Long consigneeId) {

        Consignee c = consigneeService.get(consigneeId);
        AssertUtil.assertNotNull(c, "收货人信息不存在." + consigneeId);
        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        if (user.getId() != c.getUserId()) {
            throw new MyException("不能删除其他人的收货信息.");
        }
        boolean result = consigneeService.delete(consigneeId);
        AssertUtil.assertTrue(result, "删除收货人信息失败.");
        List<Consignee> list = consigneeService.listByUser(user.getId());
        for (Consignee consignee : list) {
            consigneeService.setDefault(consignee.getId());
            break;
        }
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("删除收货人信息成功.");
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView list(HttpServletRequest request) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        List<Consignee> list = consigneeService.listByUser(user.getId());
        List<ConsigneeVO> voList = consigneeHandler.toVOList(list);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("查询收货人信息成功.");
        view.addObject("list", voList);
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView setDefault(HttpServletRequest request, Long consigneeId) {

        Consignee c = consigneeService.get(consigneeId);
        AssertUtil.assertNotNull(c, "收货人信息不存在." + consigneeId);
        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        if (user.getId() != c.getUserId()) {
            throw new MyException("不能设置其他人的收货信息为本人默认.");
        }
        boolean result = consigneeService.setDefault(consigneeId);
        AssertUtil.assertTrue(result, "设置默认收货人信息失败.");
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("修改收货人信息成功.");
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView get(HttpServletRequest request, Long consigneeId) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        Consignee consignee = consigneeService.get(consigneeId);
        if (consignee == null) {
            FrontAjaxView view = new FrontAjaxView();
            view.addObject("consignee", "");
            view.setMessage("获取收货人信息成功.");
            return view;
        }
        if (user.getId() != consignee.getUserId()) {
            throw new MyException("不能获取其他人的收货信息.");
        }
        ConsigneeVO vo = consigneeHandler.toVO(consignee);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("获取收货人信息成功.");
        view.addObject("consignee", vo);
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView getDefault(HttpServletRequest request) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        Consignee consignee = consigneeService.getDefault(user.getId());
        FrontAjaxView view = new FrontAjaxView();
        if (consignee == null) {
            view.addObject("consignee", null);
            view.setMessage("获取收货人信息成功.");
            return view;
        }
        ConsigneeVO vo = consigneeHandler.toVO(consignee);
        view.setMessage("获取收货人信息成功.");
        view.addObject("consignee", vo);
        return view;
    }
}
