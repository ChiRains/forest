package com.qcloud.component.my.web.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qcloud.component.my.InvoiceType;
import com.qcloud.component.my.NeedInvoiceType;
import com.qcloud.component.my.model.InvoiceMode;
import com.qcloud.component.my.service.InvoiceModeService;
import com.qcloud.component.my.web.form.InvoiceForm;
import com.qcloud.component.my.web.handler.InvoiceModeHandler;
import com.qcloud.component.my.web.vo.InvoiceModeVO;
import com.qcloud.component.personalcenter.PersonalcenterClient;
import com.qcloud.component.personalcenter.QUser;
import com.qcloud.pirates.mvc.FrontAjaxView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.web.mvc.annotation.PiratesApp;
import com.qcloud.pirates.web.page.PageParameterUtil;

@Controller
@RequestMapping(value = InvoiceModeController.DIR)
public class InvoiceModeController {

    public static final String DIR = "/invoiceMode";

    @Autowired
    private InvoiceModeService invoiceModeService;

    @Autowired
    private InvoiceModeHandler invoiceModeHandler;

    @PiratesApp
    @RequestMapping
    public FrontAjaxView add(HttpServletRequest request, InvoiceForm invoiceForm) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        NeedInvoiceType needInvoiceType = null;
        NeedInvoiceType[] needInvoiceTypes = NeedInvoiceType.values();
        for (NeedInvoiceType nt : needInvoiceTypes) {
            if (nt.getKey() == invoiceForm.getMode()) {
                needInvoiceType = nt;
                break;
            }
        }
        AssertUtil.assertNotNull(needInvoiceType, "是否需要发票数据不正确." + invoiceForm.getMode());
        if (NeedInvoiceType.YES.getKey() == invoiceForm.getMode()) {
            InvoiceType invoiceType = null;
            InvoiceType[] invoiceTypes = InvoiceType.values();
            for (InvoiceType in : invoiceTypes) {
                if (in.getKey() == invoiceForm.getType()) {
                    invoiceType = in;
                    break;
                }
            }
            AssertUtil.assertNotNull(invoiceType, "发票类型数据不正确." + invoiceForm.getType());
        }
        // InvoiceMode invoiceMode = invoiceModeService.getByUser(user.getId());
        boolean result = false;
        // if (invoiceMode == null) {
        // invoiceMode = new InvoiceMode();
        // invoiceMode.setUserId(user.getId());
        // invoiceMode.setContent(invoiceForm.getContent());
        // invoiceMode.setHead(invoiceForm.getHead());
        // invoiceMode.setMode(invoiceForm.getMode());
        // invoiceMode.setType(invoiceForm.getType());
        // result = invoiceModeService.add(invoiceMode);
        // } else {
        // invoiceMode.setContent(invoiceForm.getContent());
        // invoiceMode.setHead(invoiceForm.getHead());
        // invoiceMode.setMode(invoiceForm.getMode());
        // invoiceMode.setType(invoiceForm.getType());
        // result = invoiceModeService.update(invoiceMode);
        // }
        int isDefault = invoiceForm.getIsDefault() < 1 || invoiceForm.getIsDefault() > 2 ? 2 : invoiceForm.getIsDefault();
        InvoiceMode invoiceMode = new InvoiceMode();
        invoiceMode.setUserId(user.getId());
        invoiceMode.setIsDefault(isDefault);
        invoiceMode.setContent(invoiceForm.getContent());
        invoiceMode.setHead(invoiceForm.getHead());
        invoiceMode.setMode(invoiceForm.getMode());
        invoiceMode.setType(invoiceForm.getType());
        result = invoiceModeService.add(invoiceMode);
        //
        AssertUtil.assertTrue(result, "添加发票信息失败.");
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("添加发票信息成功.");
        view.addObject("id", invoiceMode.getId());
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView get(HttpServletRequest request, Long id) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        InvoiceMode invoiceMode = invoiceModeService.get(id);
        AssertUtil.assertNotNull(invoiceMode, "发票信息不存在.");
        AssertUtil.assertTrue(invoiceMode.getUserId() == user.getId(), "不能获取他人的发票信息.");
        InvoiceModeVO invoiceModeVO = invoiceModeHandler.toVO(invoiceMode);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("获取发票信息成功.");
        view.addObject("invoice", invoiceModeVO);
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView list(HttpServletRequest request) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        List<InvoiceMode> list = invoiceModeService.listByUser(user.getId());
        List<InvoiceModeVO> voList = invoiceModeHandler.toVOList(list);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("获取发票信息成功.");
        view.addObject("invoiceList", voList);
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView setDefault(HttpServletRequest request, Long id) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        InvoiceMode invoiceMode = invoiceModeService.get(id);
        AssertUtil.assertNotNull(invoiceMode, "发票信息不存在.");
        AssertUtil.assertTrue(user.getId() == invoiceMode.getUserId(), "不允许修改别人的发票信息.");
        invoiceModeService.setDefault(id);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("设置默认发票信息成功.");
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView getDefault(HttpServletRequest request) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        InvoiceMode invoiceMode = invoiceModeService.getDefault(user.getId());
        if (invoiceMode == null) {
            FrontAjaxView view = new FrontAjaxView();
            view.setMessage("获取发票信息成功.");
            return view;
        }
        InvoiceModeVO invoiceModeVO = invoiceModeHandler.toVO(invoiceMode);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("获取发票信息成功.");
        view.addObject("invoice", invoiceModeVO);
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView delete(HttpServletRequest request, Long id) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        InvoiceMode invoiceMode = invoiceModeService.getDefault(user.getId());
        AssertUtil.assertNotNull(invoiceMode, "发票信息不存在.");
        AssertUtil.assertTrue(user.getId() == invoiceMode.getUserId(), "不允许删除别人的发票信息.");
        invoiceModeService.delete(id);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("删除发票信息成功.");
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView edit(HttpServletRequest request, InvoiceForm invoiceForm) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        InvoiceMode invoiceMode = invoiceModeService.get(invoiceForm.getId());
        AssertUtil.assertNotNull(invoiceMode, "发票信息不存在.");
        AssertUtil.assertTrue(user.getId() == invoiceMode.getUserId(), "不允许修改别人的发票信息.");
        int isDefault = invoiceForm.getIsDefault() < 1 || invoiceForm.getIsDefault() > 2 ? 2 : invoiceForm.getIsDefault();
        invoiceMode.setIsDefault(isDefault);
        invoiceMode.setContent(invoiceForm.getContent());
        invoiceMode.setHead(invoiceForm.getHead());
        invoiceMode.setMode(invoiceForm.getMode());
        invoiceMode.setType(invoiceForm.getType());
        invoiceModeService.update(invoiceMode);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("修改发票信息成功.");
        return view;
    }
}
