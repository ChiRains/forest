package com.qcloud.component.my.web.controller;

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
        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        InvoiceMode invoiceMode = invoiceModeService.getByUser(user.getId());
        boolean result = false;
        if (invoiceMode == null) {
            invoiceMode = new InvoiceMode();
            invoiceMode.setUserId(user.getId());
            invoiceMode.setContent(invoiceForm.getContent());
            invoiceMode.setHead(invoiceForm.getHead());
            invoiceMode.setMode(invoiceForm.getMode());
            invoiceMode.setType(invoiceForm.getType());
            result = invoiceModeService.add(invoiceMode);
        } else {
            invoiceMode.setContent(invoiceForm.getContent());
            invoiceMode.setHead(invoiceForm.getHead());
            invoiceMode.setMode(invoiceForm.getMode());
            invoiceMode.setType(invoiceForm.getType());
            result = invoiceModeService.update(invoiceMode);
        }
        AssertUtil.assertTrue(result, "添加发票信息失败.");
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("添加发票信息成功.");
        view.addObject("id", invoiceMode.getId());
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView get(HttpServletRequest request) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        InvoiceMode invoiceMode = invoiceModeService.getByUser(user.getId());
        if (invoiceMode == null) {
            invoiceMode = new InvoiceMode();
            invoiceMode.setType(NeedInvoiceType.NO.getKey());
            invoiceMode.setMode(InvoiceType.COMMON.getKey());
        }
        InvoiceModeVO invoiceModeVO = invoiceModeHandler.toVO(invoiceMode);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("获取发票信息成功.");
        view.addObject("invoice", invoiceModeVO);
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView getDefault(HttpServletRequest request) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        InvoiceMode invoiceMode = invoiceModeService.getByUser(user.getId());
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
}
