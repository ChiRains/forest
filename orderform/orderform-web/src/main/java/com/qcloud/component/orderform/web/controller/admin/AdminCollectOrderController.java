package com.qcloud.component.orderform.web.controller.admin;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.qcloud.component.orderform.PaymentModeType;
import com.qcloud.component.orderform.model.CollectOrder;
import com.qcloud.component.orderform.model.OrderItem;
import com.qcloud.component.orderform.model.query.CollectOrderQuery;
import com.qcloud.component.orderform.service.CollectOrderService;
import com.qcloud.component.orderform.service.OrderItemService;
import com.qcloud.component.orderform.web.handler.CollectOrderHandler;
import com.qcloud.component.orderform.web.handler.OrderItemHandler;
import com.qcloud.component.orderform.web.util.OrderStateType;
import com.qcloud.component.orderform.web.vo.admin.AdminCollectOrderVO;
import com.qcloud.component.orderform.web.vo.admin.AdminOrderItemVO;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.mvc.AceAjaxView;
import com.qcloud.pirates.mvc.AcePagingView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.NumberUtil;
import com.qcloud.pirates.util.RequestUtil;
import com.qcloud.pirates.web.security.annotation.NoReferer;

@Controller
@RequestMapping(value = "/" + AdminCollectOrderController.DIR)
public class AdminCollectOrderController {

    public static final String  DIR = "admin/collectOrder";

    @Autowired
    private CollectOrderService collectOrderService;
    
    @Autowired
    private CollectOrderHandler collectOrderHandler;
    
    @Autowired
    private OrderItemService orderItemService;
    
    @Autowired
    private OrderItemHandler orderItemHandler;
    
    
    @RequestMapping
    @NoReferer
    public ModelAndView list(Integer pageNum, CollectOrderQuery query) {
        final int PAGE_SIZE = 10;
        pageNum = RequestUtil.getPageid(pageNum);
        int start = NumberUtil.getPageStart(pageNum, PAGE_SIZE);
        Page<CollectOrder> page = collectOrderService.page(query, start, PAGE_SIZE);
        List<AdminCollectOrderVO> list = collectOrderHandler.toVOList4Admin(page.getData());
        AcePagingView pagingView = new AcePagingView("/admin/orderform-CollectOrder-list", DIR + "/list?dateType="+query.getDateType(),pageNum, PAGE_SIZE, page.getCount());
        pagingView.addObject("result", list);
        pagingView.addObject("query",query);
        pagingView.addObject("orderStateType",OrderStateType.values() );
        pagingView.addObject("paymentModeType",PaymentModeType.values() );
        return pagingView;
    }


    @RequestMapping
    public ModelAndView toEdit(Long id, Date orderDate) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        CollectOrder collectOrder = collectOrderService.get(id, orderDate);
        AdminCollectOrderVO adminCollectOrderVO = collectOrderHandler.toVO4Admin(collectOrder);
        ModelAndView model = new ModelAndView("/admin/orderform-CollectOrder-edit");
        model.addObject("collectOrder", adminCollectOrderVO);
        return model;
    }

    @RequestMapping
    public AceAjaxView edit(CollectOrder collectOrder) {

//        collectOrderService.update(collectOrder);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("编辑成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    public AceAjaxView delete(Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        collectOrderService.delete(id);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("删除成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }
    
    @RequestMapping
    public ModelAndView getDetails(Long id,Date orderDate){
        AssertUtil.assertNotNull(id, "ID不能为空");
        ModelAndView modelAndView=new ModelAndView("/admin/orderform-CollectOrder-details");
        CollectOrder collectOrder=collectOrderService.get(id, orderDate);
        System.out.println(collectOrder.getOrderNumber());
        //获取orderItem列表
        List<OrderItem> orderItems=orderItemService.listByCollectOrder(id, orderDate);
        List<AdminOrderItemVO> itemVOs=orderItemHandler.toVOList4Admin(orderItems);
        /* for (AdminOrderItemVO adminOrderItemVO : itemVOs) {
            MerchandiseItem m=merchandiseItemService.get(adminOrderItemVO.getUnifiedMerchandiseId());
            adminOrderItemVO.setItem(m);
            Merchant merchant=merchantService.get(adminOrderItemVO.getMerchantId());
            adminOrderItemVO.setMerchant(merchant);    
        }*/
        
        modelAndView.addObject("collectOrder" ,collectOrder);
        modelAndView.addObject("itemVOs",itemVOs);
        modelAndView.addObject("orderStateType",OrderStateType.values() );
        modelAndView.addObject("paymentModeType",PaymentModeType.values() );
       
        return modelAndView;
    }
}
