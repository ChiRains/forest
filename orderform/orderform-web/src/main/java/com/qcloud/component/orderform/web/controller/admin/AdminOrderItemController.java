package com.qcloud.component.orderform.web.controller.admin;

import org.apache.commons.lang.NotImplementedException;
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
import com.qcloud.component.orderform.model.OrderItem;
import com.qcloud.component.orderform.service.OrderItemService;
import com.qcloud.component.orderform.web.handler.OrderItemHandler;
import com.qcloud.component.orderform.model.query.OrderItemQuery;
import com.qcloud.component.orderform.web.vo.admin.AdminOrderItemVO;

@Controller
@RequestMapping(value = "/" + AdminOrderItemController.DIR)
public class AdminOrderItemController {

    public static final String DIR = "admin/orderItem";

    @Autowired
    private OrderItemService   orderItemService;

    @Autowired
    private OrderItemHandler   orderItemHandler;

    @RequestMapping
    @NoReferer
    public ModelAndView list(Integer pageNum, OrderItemQuery query) {

        final int PAGE_SIZE = 10;
        pageNum = RequestUtil.getPageid(pageNum);
        int start = NumberUtil.getPageStart(pageNum, PAGE_SIZE);
        Page<OrderItem> page = orderItemService.page(query, start, PAGE_SIZE);
        List<AdminOrderItemVO> list = orderItemHandler.toVOList4Admin(page.getData());
        AcePagingView pagingView = new AcePagingView("/admin/orderform-OrderItem-list", DIR + "/list", pageNum, PAGE_SIZE, page.getCount());
        pagingView.addObject("result", list);
        return pagingView;
    }

    // @RequestMapping
    // public ModelAndView toAdd() {
    //
    // ModelAndView model = new ModelAndView("/admin/orderform-OrderItem-add");
    // return model;
    // }
    // @RequestMapping
    // public AceAjaxView add(OrderItem orderItem) {
    //
    // orderItemService.add(orderItem);
    // AceAjaxView aceAjaxView = new AceAjaxView();
    // aceAjaxView.setMessage("添加成功");
    // aceAjaxView.setUrl(DIR + "/list");
    // return aceAjaxView;
    // }
    @RequestMapping
    public ModelAndView toEdit(Long id, Date orderDate) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        OrderItem orderItem = orderItemService.get(id, orderDate);
        AdminOrderItemVO adminOrderItemVO = orderItemHandler.toVO4Admin(orderItem);
        ModelAndView model = new ModelAndView("/admin/orderform-OrderItem-edit");
        model.addObject("orderItem", adminOrderItemVO);
        return model;
    }

    @RequestMapping
    public AceAjaxView edit(OrderItem orderItem, Date orderDate) {
        
        throw new NotImplementedException("方法暂未实现");
//        orderItemService.update(orderItem, orderDate);
//        AceAjaxView aceAjaxView = new AceAjaxView();
//        aceAjaxView.setMessage("编辑成功");
//        aceAjaxView.setUrl(DIR + "/list");
//        return aceAjaxView;
    }

    @RequestMapping
    public AceAjaxView delete(Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        orderItemService.delete(id);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("删除成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }
}
