//package com.qcloud.component.my.web.controller.app;
//
//import javax.servlet.http.HttpServletRequest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import com.qcloud.component.my.web.controller.InvoiceModeController;
//import com.qcloud.component.my.web.controller.form.InvoiceForm;
//import com.qcloud.pirates.mvc.FrontAjaxView;
//
//@Controller
//@RequestMapping(value = AppInvoiceModeController.DIR)
//public class AppInvoiceModeController {
//
//    public static final String    DIR = "/app/invoiceMode";
//
//    @Autowired
//    private InvoiceModeController invoiceModeController;
//
//    @RequestMapping
//    public FrontAjaxView add(HttpServletRequest request, InvoiceForm invoiceForm) {
//
//        return invoiceModeController.add(request, invoiceForm);
//    }
//
//    @RequestMapping
//    public FrontAjaxView get(HttpServletRequest request) {
//
//        return invoiceModeController.get(request);
//    }
//
//    @RequestMapping
//    public FrontAjaxView getDefault(HttpServletRequest request) {
//
//        return invoiceModeController.getDefault(request);
//    }
//}
