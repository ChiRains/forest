package com.qcloud.component.orderform.web.controller.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.qcloud.component.filesdk.FileSDKClient;
import com.qcloud.component.orderform.OrderformClient;
import com.qcloud.component.orderform.QMerchantOrder;
import com.qcloud.component.orderform.QOrderItem;
import com.qcloud.component.orderform.model.OrderItem;
import com.qcloud.component.orderform.model.RefundOrder;
import com.qcloud.component.orderform.model.RefundOrderItem;
import com.qcloud.component.orderform.model.SubOrder;
import com.qcloud.component.orderform.model.query.RefundOrderQuery;
import com.qcloud.component.orderform.service.OrderItemService;
import com.qcloud.component.orderform.service.RefundOrderItemService;
import com.qcloud.component.orderform.service.RefundOrderService;
import com.qcloud.component.orderform.service.SubOrderService;
import com.qcloud.component.orderform.web.handler.RefundOrderHandler;
import com.qcloud.component.orderform.web.handler.RefundOrderItemHandler;
import com.qcloud.component.orderform.web.util.RefundOrderStateType;
import com.qcloud.component.orderform.web.vo.admin.AdminRefundOrderItemVO;
import com.qcloud.component.orderform.web.vo.admin.AdminRefundOrderVO;
import com.qcloud.component.sellercenter.QMerchant;
import com.qcloud.component.sellercenter.QStore;
import com.qcloud.component.sellercenter.SellercenterClient;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.mvc.AceAjaxView;
import com.qcloud.pirates.mvc.AcePagingView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.NumberUtil;
import com.qcloud.pirates.util.RequestUtil;
import com.qcloud.pirates.web.page.PageParameterUtil;
import com.qcloud.pirates.web.security.annotation.NoReferer;

@Controller
@RequestMapping(value = "/" + AdminRefundOrderController.DIR)
public class AdminRefundOrderController {

    public static final String     DIR = "admin/refundOrder";

    @Autowired
    private RefundOrderService     refundOrderService;

    @Autowired
    private RefundOrderHandler     refundOrderHandler;

    @Autowired
    private RefundOrderItemService refundOrderItemService;

    @Autowired
    private RefundOrderItemHandler refundOrderItemHandler;

    // @Autowired
    // private AdminFilterService adminFilterService;
    //
    // @Autowired
    // private TokenClient tokenClient;
    // @Autowired
    // private StoreMemberService storeMemberService;
    @Autowired
    private SubOrderService        subOrderService;

    @Autowired
    private SellercenterClient     sellercenterClient;

    // @Autowired
    // private MerchantMemberService merchantMemberService;
    // @Autowired
    // private CollectOrderService collectOrderService;
    @Autowired
    private OrderItemService       orderItemService;

    @Autowired
    private OrderformClient        orderformClient;

    @Autowired
    private FileSDKClient          fileSDKClient;

    @RequestMapping
    @NoReferer
    public ModelAndView list(HttpServletRequest request, Integer pageNum, RefundOrderQuery query) {

        int state = query.getState();
        QStore store = PageParameterUtil.getParameterValues(request, SellercenterClient.STORE_LOGIN_PARAMETER_KEY);
        query.setStoreId(store.getId());
        final int PAGE_SIZE = 10;
        pageNum = RequestUtil.getPageid(pageNum);
        int start = NumberUtil.getPageStart(pageNum, PAGE_SIZE);
        Page<RefundOrder> page = refundOrderService.page(query, start, PAGE_SIZE);
        List<AdminRefundOrderVO> list = refundOrderHandler.toVOList4Admin(page.getData());
        String param = "state=" + state;
        AcePagingView pagingView = new AcePagingView("/admin/orderform-RefundOrder-list", DIR + "/list?" + param, pageNum, PAGE_SIZE, page.getCount());
        pagingView.addObject("result", list);
        pagingView.addObject("orderStateType", RefundOrderStateType.values());
        pagingView.addObject("state", query.getState());
        pagingView.addObject("query", query);
        return pagingView;
    }

    @RequestMapping
    public ModelAndView toAdd() {

        ModelAndView model = new ModelAndView("/admin/orderform-RefundOrder-add");
        return model;
    }

    @RequestMapping
    public AceAjaxView add(RefundOrder refundOrder) {

        refundOrderService.add(refundOrder);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("添加成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    public ModelAndView toEdit(Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        RefundOrder refundOrder = refundOrderService.get(id);
        AdminRefundOrderVO adminRefundOrderVO = refundOrderHandler.toVO4Admin(refundOrder);
        ModelAndView model = new ModelAndView("/admin/orderform-RefundOrder-edit");
        model.addObject("refundOrder", adminRefundOrderVO);
        return model;
    }

    @RequestMapping
    public AceAjaxView edit(RefundOrder refundOrder) {

        refundOrderService.update(refundOrder);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("编辑成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    public AceAjaxView delete(Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        refundOrderService.delete(id);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("删除成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    public ModelAndView getDetails(Long id) {

        AssertUtil.assertNotNull(id, "id不能为空");
        RefundOrder refundOrder = refundOrderService.get(id);
        AssertUtil.assertNotNull(refundOrder, "退款订单不存在." + id);
        AdminRefundOrderVO refundOrderVO = refundOrderHandler.toVO4Admin(refundOrder);
        // 明细
        List<RefundOrderItem> details = refundOrderItemService.listByRefund(id);
        List<AdminRefundOrderItemVO> detailVOs = refundOrderItemHandler.toVOList4Admin(details);
        //
        double sum = 0.0;
        Set<Long> idSet = new HashSet<Long>();
        Map<Long, Double> sumMap = new HashMap<Long, Double>();
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        for (AdminRefundOrderItemVO vo : detailVOs) {
            if (idSet.add(vo.getOrderItemId())) {
                OrderItem orderItem = orderItemService.get(vo.getOrderItemId(), refundOrder.getOrderDate());
                sum += orderItem.getDiscount() * vo.getNumber();
                sumMap.put(orderItem.getId(), new Double(orderItem.getDiscount() * vo.getNumber() * 100).longValue() / 100d);
            }
        }
        //
        for (Long orderItemId : idSet) {
            Map<String, Object> map = new HashMap<String, Object>();
            OrderItem orderItem = orderItemService.get(orderItemId, refundOrder.getOrderDate());
            map.put("itemId", orderItemId);
            map.put("itemName", orderItem.getName());
            map.put("itemSum", sumMap.get(orderItemId));
            //
            List<AdminRefundOrderItemVO> itemDetails = new ArrayList<AdminRefundOrderItemVO>();
            for (AdminRefundOrderItemVO vo : detailVOs) {
                if (orderItemId == vo.getOrderItemId()) {
                    itemDetails.add(vo);
                }
            }
            map.put("itemDetails", itemDetails);
            mapList.add(map);
        }
        //
        SubOrder subOrder = subOrderService.get(refundOrderVO.getSubOrderId(), refundOrderVO.getOrderDate());
        QStore store = sellercenterClient.getStore(refundOrder.getStoreId());
        // List<RefundOrder> records = refundOrderService.listBySubOrder(refundOrderVO.getSubOrderId());
        // for (int i = 0; i < records.size(); i++) {
        // if (records.get(i).getId() == refundOrderVO.getId()) {
        // records.remove(i);
        // continue;
        // }
        // }
        // List<RefundOrderItemDetail> orderItemsRecords = new ArrayList<RefundOrderItemDetail>();
        // for (RefundOrder order : records) {
        // List<RefundOrderItemDetail> temp = refundOrderItemDetailService.listByRefund(order.getId());
        // orderItemsRecords.addAll(temp);
        // }
        // List<AdminRefundOrderItemDetailVO> orderItemsRecordsVo = refundOrderItemDetailHandler.toVOList4Admin(orderItemsRecords);
        QMerchantOrder merchantOrder = orderformClient.getMerchantOrder(subOrder.getId(), refundOrderVO.getOrderDate());
        List<AdminRefundOrderItemVO> itemDetailList = new ArrayList<AdminRefundOrderItemVO>();
        List<QOrderItem> list = merchantOrder.getOrderItemList();
        for (QOrderItem qOrderItem : list) {
            AdminRefundOrderItemVO detailVO = new AdminRefundOrderItemVO();
            detailVO.setName(qOrderItem.getName());
            detailVO.setSum(qOrderItem.getSum());
            detailVO.setImage(fileSDKClient.getFileServerUrl() + qOrderItem.getImage());
            detailVO.setNumber(qOrderItem.getNumber());
            detailVO.setSpecifications(qOrderItem.getOrderItemDetailList().get(0).getSpecifications());
            itemDetailList.add(detailVO);
        }
        ModelAndView view = new ModelAndView("/admin/orderform-RefundOrder-details");
        view.addObject("order", refundOrderVO);
        view.addObject("orderItemsCombinationRecords", mapList);
        view.addObject("orderItemsRecords", itemDetailList);
        view.addObject("orderStateType", RefundOrderStateType.values());
        view.addObject("subOrder", subOrder);
        view.addObject("store", store);
        view.addObject("cash", subOrder.getSum());
        view.addObject("returnCash", new Double(sum * 100).longValue() / 100d);
        // view.addObject("orderItems", detailVOs);
        // view.addObject("orderItemsRecords", orderItemsRecordsVo);
        return view;
    }

    @RequestMapping
    @NoReferer
    public ModelAndView list4Merchant(HttpServletRequest request, Integer pageNum, RefundOrderQuery query) {

        int state = query.getState();
        QMerchant merchant = PageParameterUtil.getParameterValues(request, SellercenterClient.MERCHANT_LOGIN_PARAMETER_KEY);
        query.setMerchantId(merchant.getId());
        final int PAGE_SIZE = 10;
        pageNum = RequestUtil.getPageid(pageNum);
        int start = NumberUtil.getPageStart(pageNum, PAGE_SIZE);
        Page<RefundOrder> page = refundOrderService.page(query, start, PAGE_SIZE);
        List<AdminRefundOrderVO> list = refundOrderHandler.toVOList4Admin(page.getData());
        String param = "state=" + state;
        AcePagingView pagingView = new AcePagingView("/admin/orderform-RefundOrder-list4Merchant", DIR + "/list4Merchant?" + param, pageNum, PAGE_SIZE, page.getCount());
        pagingView.addObject("result", list);
        pagingView.addObject("orderStateType", RefundOrderStateType.values());
        pagingView.addObject("state", query.getState());
        pagingView.addObject("query", query);
        return pagingView;
    }

    @RequestMapping
    public ModelAndView getDetails4Merchant(Long id) {

        AssertUtil.assertNotNull(id, "id不能为空");
        RefundOrder refundOrder = refundOrderService.get(id);
        AssertUtil.assertNotNull(refundOrder, "退款订单不存在." + id);
        AdminRefundOrderVO refundOrderVO = refundOrderHandler.toVO4Admin(refundOrder);
        // 明细
        List<RefundOrderItem> details = refundOrderItemService.listByRefund(id);
        List<AdminRefundOrderItemVO> detailVOs = refundOrderItemHandler.toVOList4Admin(details);
        //
        double sum = 0.0;
        Set<Long> idSet = new HashSet<Long>();
        Map<Long, Double> sumMap = new HashMap<Long, Double>();
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        for (AdminRefundOrderItemVO vo : detailVOs) {
            if (idSet.add(vo.getOrderItemId())) {
                OrderItem orderItem = orderItemService.get(vo.getOrderItemId(), refundOrder.getOrderDate());
                sum += orderItem.getDiscount() * vo.getNumber();
                sumMap.put(orderItem.getId(), new Double(orderItem.getDiscount() * vo.getNumber() * 100).longValue() / 100d);
            }
        }
        //
        for (Long orderItemId : idSet) {
            Map<String, Object> map = new HashMap<String, Object>();
            OrderItem orderItem = orderItemService.get(orderItemId, refundOrder.getOrderDate());
            map.put("itemId", orderItemId);
            map.put("itemName", orderItem.getName());
            map.put("itemSum", sumMap.get(orderItemId));
            //
            List<AdminRefundOrderItemVO> itemDetails = new ArrayList<AdminRefundOrderItemVO>();
            for (AdminRefundOrderItemVO vo : detailVOs) {
                if (orderItemId == vo.getOrderItemId()) {
                    itemDetails.add(vo);
                }
            }
            map.put("itemDetails", itemDetails);
            mapList.add(map);
        }
        //
        SubOrder subOrder = subOrderService.get(refundOrderVO.getSubOrderId(), refundOrderVO.getOrderDate());
        QStore store = sellercenterClient.getStore(refundOrder.getStoreId());
        // List<RefundOrder> records = refundOrderService.listBySubOrder(refundOrderVO.getSubOrderId());
        // for (int i = 0; i < records.size(); i++) {
        // if (records.get(i).getId() == refundOrderVO.getId()) {
        // records.remove(i);
        // continue;
        // }
        // }
        // List<RefundOrderItemDetail> orderItemsRecords = new ArrayList<RefundOrderItemDetail>();
        // for (RefundOrder order : records) {
        // List<RefundOrderItemDetail> temp = refundOrderItemDetailService.listByRefund(order.getId());
        // orderItemsRecords.addAll(temp);
        // }
        // List<AdminRefundOrderItemDetailVO> orderItemsRecordsVo = refundOrderItemDetailHandler.toVOList4Admin(orderItemsRecords);
        QMerchantOrder merchantOrder = orderformClient.getMerchantOrder(subOrder.getId(), refundOrderVO.getOrderDate());
        List<AdminRefundOrderItemVO> itemDetailList = new ArrayList<AdminRefundOrderItemVO>();
        List<QOrderItem> list = merchantOrder.getOrderItemList();
        for (QOrderItem qOrderItem : list) {
            AdminRefundOrderItemVO detailVO = new AdminRefundOrderItemVO();
            detailVO.setName(qOrderItem.getName());
            detailVO.setImage(fileSDKClient.getFileServerUrl() + qOrderItem.getImage());
            detailVO.setNumber(qOrderItem.getNumber());
            detailVO.setSum(qOrderItem.getSum());
            detailVO.setSpecifications(qOrderItem.getOrderItemDetailList().get(0).getSpecifications());
            itemDetailList.add(detailVO);
        }
        ModelAndView view = new ModelAndView("/admin/orderform-RefundOrder-details4Merchant");
        view.addObject("order", refundOrderVO);
        view.addObject("orderItemsCombinationRecords", mapList);
        view.addObject("orderItemsRecords", itemDetailList);
        view.addObject("orderStateType", RefundOrderStateType.values());
        view.addObject("subOrder", subOrder);
        view.addObject("store", store);
        view.addObject("cash", subOrder.getSum());
        view.addObject("returnCash", new Double(sum * 100).longValue() / 100d);
        // view.addObject("orderItems", detailVOs);
        // view.addObject("orderItemsRecords", orderItemsRecordsVo);
        return view;
    }
}
