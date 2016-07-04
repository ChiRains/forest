package com.qcloud.component.orderform.web.controller.admin;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.qcloud.component.my.DeliveryModeType;
import com.qcloud.component.my.InvoiceType;
import com.qcloud.component.my.NeedInvoiceType;
import com.qcloud.component.orderform.PaymentModeType;
import com.qcloud.component.orderform.exception.OrderformException;
import com.qcloud.component.orderform.model.CollectOrder;
import com.qcloud.component.orderform.model.OrderDiscount;
import com.qcloud.component.orderform.model.OrderItem;
import com.qcloud.component.orderform.model.OrderItemDetail;
import com.qcloud.component.orderform.model.SubOrder;
import com.qcloud.component.orderform.model.query.SubOrderQuery;
import com.qcloud.component.orderform.service.CollectOrderService;
import com.qcloud.component.orderform.service.OrderDiscountService;
import com.qcloud.component.orderform.service.OrderItemDetailService;
import com.qcloud.component.orderform.service.OrderItemService;
import com.qcloud.component.orderform.service.SubOrderService;
import com.qcloud.component.orderform.web.handler.OrderItemDetailHandler;
import com.qcloud.component.orderform.web.handler.SubOrderHandler;
import com.qcloud.component.orderform.web.util.OrderStateType;
import com.qcloud.component.orderform.web.vo.admin.AdminOrderItemDetailVO;
import com.qcloud.component.orderform.web.vo.admin.AdminSubOrderVO;
import com.qcloud.component.sellercenter.QStore;
import com.qcloud.component.sellercenter.SellercenterClient;
import com.qcloud.component.sellercenter.model.MerchantOrderForm;
import com.qcloud.component.sellercenter.model.key.TypeEnum.MerchantOrderStateType;
import com.qcloud.component.sellercenter.service.MerchantOrderFormService;
import com.qcloud.component.warehouse.WarehouseClient;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.mvc.AceAjaxView;
import com.qcloud.pirates.mvc.AcePagingView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.NumberUtil;
import com.qcloud.pirates.util.RequestUtil;
import com.qcloud.pirates.web.page.PageParameterUtil;
import com.qcloud.pirates.web.security.annotation.NoReferer;

@Controller
@RequestMapping(value = "/" + AdminSubOrderController.DIR)
public class AdminSubOrderController {

    public static final String       DIR = "admin/subOrder";

    @Autowired
    private MerchantOrderFormService merchantOrderFormService;

    @Autowired
    private CollectOrderService      collectOrderService;

    @Autowired
    private SubOrderService          subOrderService;

    @Autowired
    private OrderItemService         orderItemService;

    @Autowired
    private OrderItemDetailService   orderItemDetailService;

    @Autowired
    private OrderItemDetailHandler   orderItemDetailHandler;

    @Autowired
    private SubOrderHandler          subOrderHandler;

    @Autowired
    private OrderDiscountService     orderDiscountService;

    // @Autowired
    // private AdminFilterService adminFilterService;
    //
    // @Autowired
    // private TokenClient tokenClient;
    @Autowired
    private SellercenterClient       sellercenterClient;

    @Autowired
    private WarehouseClient          warehouseClient;

    @RequestMapping
    @NoReferer
    public ModelAndView list(Integer pageNum, SubOrderQuery query) {

        final int PAGE_SIZE = 10;
        pageNum = RequestUtil.getPageid(pageNum);
        int start = NumberUtil.getPageStart(pageNum, PAGE_SIZE);
        Page<SubOrder> page = subOrderService.page(query, start, PAGE_SIZE);
        List<AdminSubOrderVO> list = subOrderHandler.toVOList4Admin(page.getData());
        AcePagingView pagingView = new AcePagingView("/admin/orderform-SubOrder-list", DIR + "/list", pageNum, PAGE_SIZE, page.getCount());
        pagingView.addObject("result", list);
        return pagingView;
    }

    @RequestMapping
    public ModelAndView toAdd() {

        ModelAndView model = new ModelAndView("/admin/orderform-SubOrder-add");
        return model;
    }

    // @RequestMapping
    // public AceAjaxView add(SubOrder subOrder, Date orderDate) {
    //
    // subOrderService.add(subOrder, orderDate);
    // AceAjaxView aceAjaxView = new AceAjaxView();
    // aceAjaxView.setMessage("添加成功");
    // aceAjaxView.setUrl(DIR + "/list");
    // return aceAjaxView;
    // }
    @RequestMapping
    public ModelAndView toEdit(Long id, Date orderDate) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        SubOrder subOrder = subOrderService.get(id, orderDate);
        AdminSubOrderVO adminSubOrderVO = subOrderHandler.toVO4Admin(subOrder);
        ModelAndView model = new ModelAndView("/admin/orderform-SubOrder-edit");
        model.addObject("subOrder", adminSubOrderVO);
        return model;
    }

    @RequestMapping
    public AceAjaxView edit(SubOrder subOrder, Date orderDate) {

        throw new NotImplementedException("方法暂未实现");
        // subOrderService.update(subOrder, orderDate);
        // AceAjaxView aceAjaxView = new AceAjaxView();
        // aceAjaxView.setMessage("编辑成功");
        // aceAjaxView.setUrl(DIR + "/list");
        // return aceAjaxView;
    }

    @RequestMapping
    public AceAjaxView delete(Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        subOrderService.delete(id);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("删除成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    public AceAjaxView changeStore(HttpServletRequest request, Long id, Date orderDate, Long storeId) {

        // StoreMemberQuery query = new StoreMemberQuery();
        // query.setStoreId(storeId);
        // StoreMember storeMember = sellercenterClient.getMemberStore(query);
        QStore qStore = sellercenterClient.getStore(storeId);
        AssertUtil.assertNotNull(qStore, "门店不存在!");
        long merchantId = qStore.getMerchant().getId();
        SubOrder subOrder = subOrderService.get(id, orderDate);
        AssertUtil.assertNotNull(subOrder, "子单不存在." + id);
        // 判断商品是否有库存
        List<OrderItemDetail> list = orderItemDetailService.listBySubOrder(subOrder.getId(), orderDate);
        for (OrderItemDetail orderItemDetail : list) {
            long unifiedMerchandiseId = orderItemDetail.getUnifiedMerchandiseId();
            int stock = warehouseClient.getStock(merchantId, storeId, unifiedMerchandiseId);
            if (stock < orderItemDetail.getNumber()) {
                throw new OrderformException("门店商品." + orderItemDetail.getName() + "库存不足!");
            }
        }
        subOrderService.updateStore(id, orderDate, storeId);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("修改子单发货门店成功");
        return aceAjaxView;
    }

    /**
     * 商家子订单详细
     * @return
     */
    @RequestMapping
    public ModelAndView listDetail(HttpServletRequest request, Integer pageNum, SubOrderQuery query) {

        // String tokenId = adminFilterService.getTokenId(request);
        // AssertUtil.assertNotEmpty(tokenId, "获取用户登录信息失败.");
        // String idStr = tokenClient.get(tokenId);
        // AssertUtil.assertNotEmpty(idStr, "获取用户标识失败.");
        // long memberId = Long.parseLong(idStr);
        //
        //
        //
        // StoreMemberQuery smq = new StoreMemberQuery();
        // smq.setMemberId(memberId);
        // List<QMerchant> merchantList = outdatedSellercenterClient.listMerchant(memberId);
        // Long merchantId = null;
        // if (!CollectionUtils.isEmpty(merchantList) && merchantList.size() == 1) {
        // merchantId = merchantList.get(0).getId();
        // }
        // if (merchantId == null) {
        // StoreMember storeMember = outdatedSellercenterClient.getMemberStore(smq);
        // AssertUtil.assertNotNull(storeMember, "您尚未属于一家门店.");
        // merchantId = storeMember.getMerchantId();
        // }
        QStore store = PageParameterUtil.getParameterValues(request, SellercenterClient.STORE_LOGIN_PARAMETER_KEY);
        final int PAGE_SIZE = 10;
        pageNum = RequestUtil.getPageid(pageNum);
        int start = NumberUtil.getPageStart(pageNum, PAGE_SIZE);
        Page<SubOrder> page = subOrderService.page(query, start, PAGE_SIZE);
        List<AdminSubOrderVO> list = subOrderHandler.toVOList4Admin(page.getData());
        AcePagingView pagingView = new AcePagingView("/admin/orderform-MerchantOrder-listDetail", DIR + "/listDetail", pageNum, PAGE_SIZE, page.getCount());
        MerchantOrderForm merchantOrderForm = merchantOrderFormService.getBySubOrder(store.getMerchant().getId(), query.getSubOrderId());
        if (merchantOrderForm == null) {
            throw new OrderformException("未查询到此订单");
        }
        // 子订单
        SubOrder subOrder = subOrderService.get(query.getSubOrderId(), query.getTime());
        CollectOrder collectOrder = collectOrderService.get(subOrder.getOrderId(), query.getTime());
        // 订单明细
        List<OrderItemDetail> orderItemDetailList = orderItemDetailService.listBySubOrder(query.getSubOrderId(), query.getTime());
        List<AdminOrderItemDetailVO> orderItemDetailVOList = orderItemDetailHandler.toVOList4Admin(orderItemDetailList, subOrder.getStoreId());
        // 订单项
        Map<Long, OrderItem> orderItemDetailMap = new HashMap<Long, OrderItem>();
        for (OrderItemDetail orderItemDetail : orderItemDetailList) {
            OrderItem orderItem = orderItemService.get(orderItemDetail.getOrderItemId(), query.getTime());
            orderItemDetailMap.put(orderItemDetail.getId(), orderItem);
        }
        // 优惠券
        List<OrderDiscount> orderDiscountList = orderDiscountService.listBySubOrder(subOrder.getId());
        pagingView.addObject("result", list);
        pagingView.addObject("merchantOrderForm", merchantOrderForm);
        pagingView.addObject("subOrder", subOrder);
        pagingView.addObject("collectOrder", collectOrder);
        pagingView.addObject("orderItemDetailList", orderItemDetailVOList);
        pagingView.addObject("orderItemDetailMap", orderItemDetailMap);
        pagingView.addObject("merchantOrderStateType", MerchantOrderStateType.values());
        pagingView.addObject("shipType", MerchantOrderStateType.SHIP.getKey());
        pagingView.addObject("paymentModeType", PaymentModeType.values());
        pagingView.addObject("orderStateType", OrderStateType.values());
        pagingView.addObject("storeList", sellercenterClient.listStoreByMerchant(store.getMerchant().getId()));
        pagingView.addObject("needInvoiceType", NeedInvoiceType.values());
        pagingView.addObject("invoiceType", InvoiceType.values());
        pagingView.addObject("deliveryModeType", DeliveryModeType.values());
        pagingView.addObject("orderDiscountList", orderDiscountList);
        return pagingView;
    }

    @RequestMapping
    public ModelAndView toAddLogistics() {

        ModelAndView model = new ModelAndView("/admin/orderform-SubOrder-addLogistics");
        return model;
    }
}
