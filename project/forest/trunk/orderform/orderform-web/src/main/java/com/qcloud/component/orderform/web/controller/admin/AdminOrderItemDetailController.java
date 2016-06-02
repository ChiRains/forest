package com.qcloud.component.orderform.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import java.util.Date;
import java.util.List;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.mvc.AceAjaxView;
import com.qcloud.pirates.mvc.AcePagingView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.NumberUtil;
import com.qcloud.pirates.util.RequestUtil;
import com.qcloud.pirates.web.security.annotation.NoReferer;
import com.qcloud.component.orderform.model.OrderItemDetail;
import com.qcloud.component.orderform.service.OrderItemDetailService;
import com.qcloud.component.orderform.web.handler.OrderItemDetailHandler;
import com.qcloud.component.orderform.model.query.OrderItemDetailQuery;
import com.qcloud.component.orderform.web.vo.admin.AdminOrderItemDetailVO;

@Controller
@RequestMapping(value = "/" + AdminOrderItemDetailController.DIR)
public class AdminOrderItemDetailController {

    public static final String     DIR = "admin/orderItemDetail";

    @Autowired
    private OrderItemDetailService orderItemDetailService;

    @Autowired
    private OrderItemDetailHandler orderItemDetailHandler;

    // @RequestMapping
    // @NoReferer
    // public ModelAndView list(Integer pageNum, OrderItemDetailQuery query) {
    // final int PAGE_SIZE = 10;
    // pageNum = RequestUtil.getPageid(pageNum);
    // int start = NumberUtil.getPageStart(pageNum, PAGE_SIZE);
    // Page<OrderItemDetail> page = orderItemDetailService.page(query, start, PAGE_SIZE);
    // List<AdminOrderItemDetailVO> list = orderItemDetailHandler.toVOList4Admin(page.getData());
    // AcePagingView pagingView = new AcePagingView("/admin/orderform-OrderItemDetail-list", DIR + "/list", pageNum, PAGE_SIZE, page.getCount());
    // pagingView.addObject("result", list);
    // return pagingView;
    // }
    // @RequestMapping
    // public ModelAndView toAdd() {
    //
    // ModelAndView model = new ModelAndView("/admin/orderform-OrderItemDetail-add");
    // return model;
    // }
    // @RequestMapping
    // public AceAjaxView add(OrderItemDetail orderItemDetail) {
    // orderItemDetailService.add(orderItemDetail);
    //
    // AceAjaxView aceAjaxView = new AceAjaxView();
    // aceAjaxView.setMessage("添加成功");
    // aceAjaxView.setUrl(DIR + "/list");
    // return aceAjaxView;
    // }
    @RequestMapping
    public ModelAndView toEdit(Long id, Date orderDate) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        OrderItemDetail orderItemDetail = orderItemDetailService.get(id, orderDate);
        AdminOrderItemDetailVO adminOrderItemDetailVO = orderItemDetailHandler.toVO4Admin(orderItemDetail);
        ModelAndView model = new ModelAndView("/admin/orderform-OrderItemDetail-edit");
        model.addObject("orderItemDetail", adminOrderItemDetailVO);
        return model;
    }

    @RequestMapping
    public AceAjaxView edit(OrderItemDetail orderItemDetail, Date orderDate) {

        orderItemDetailService.update(orderItemDetail, orderDate);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("编辑成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    public AceAjaxView delete(Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        orderItemDetailService.delete(id);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("删除成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }
}
