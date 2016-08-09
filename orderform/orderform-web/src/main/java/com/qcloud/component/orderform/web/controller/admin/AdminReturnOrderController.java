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
import com.qcloud.component.orderform.model.ReturnOrder;
import com.qcloud.component.orderform.model.ReturnOrderItem;
import com.qcloud.component.orderform.model.ReturnOrderItemDetail;
import com.qcloud.component.orderform.model.SubOrder;
import com.qcloud.component.orderform.model.query.ReturnOrderQuery;
import com.qcloud.component.orderform.service.OrderItemService;
import com.qcloud.component.orderform.service.ReturnOrderItemDetailService;
import com.qcloud.component.orderform.service.ReturnOrderItemService;
import com.qcloud.component.orderform.service.ReturnOrderService;
import com.qcloud.component.orderform.service.SubOrderService;
import com.qcloud.component.orderform.web.handler.ReturnOrderHandler;
import com.qcloud.component.orderform.web.handler.ReturnOrderItemDetailHandler;
import com.qcloud.component.orderform.web.handler.ReturnOrderItemHandler;
import com.qcloud.component.orderform.web.util.ReturnOrderStateType;
import com.qcloud.component.orderform.web.vo.admin.AdminReturnOrderItemDetailVO;
import com.qcloud.component.orderform.web.vo.admin.AdminReturnOrderItemVO;
import com.qcloud.component.orderform.web.vo.admin.AdminReturnOrderVO;
import com.qcloud.component.sellercenter.QMerchant;
import com.qcloud.component.sellercenter.QStore;
import com.qcloud.component.sellercenter.SellercenterClient;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.mvc.AceAjaxView;
import com.qcloud.pirates.mvc.AcePagingView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.NumberUtil;
import com.qcloud.pirates.util.RequestUtil;
import com.qcloud.pirates.web.page.PPage;
import com.qcloud.pirates.web.page.PageParameterUtil;
import com.qcloud.pirates.web.security.annotation.NoReferer;

@Controller
@RequestMapping(value = "/" + AdminReturnOrderController.DIR)
public class AdminReturnOrderController {

    public static final String           DIR = "admin/returnOrder";

    @Autowired
    private ReturnOrderService           returnOrderService;

    @Autowired
    private ReturnOrderHandler           returnOrderHandler;

    @Autowired
    private ReturnOrderItemService       returnOrderItemService;

    @Autowired
    private ReturnOrderItemHandler       returnOrderItemHandler;

    @Autowired
    private SubOrderService              subOrderService;

    @Autowired
    private SellercenterClient           sellercenterClient;

    @Autowired
    private OrderItemService             orderItemService;

    @Autowired
    private OrderformClient              orderformClient;

    @Autowired
    private FileSDKClient                fileSDKClient;

    @Autowired
    private ReturnOrderItemDetailService returnOrderItemDetailService;

    @Autowired
    private ReturnOrderItemDetailHandler returnOrderItemDetailHandler;

    @RequestMapping
    @NoReferer
    public ModelAndView list(HttpServletRequest request, Integer pageNum, ReturnOrderQuery query) {

        int state = query.getState();
        query.setState(getChangeState(state));
        // long memberId = getMemberId(request);
        // long storeId = getStoreId(memberId);
        QStore store = PageParameterUtil.getParameterValues(request, SellercenterClient.STORE_LOGIN_PARAMETER_KEY);
        query.setStoreId(store.getId());
        final int PAGE_SIZE = 10;
        pageNum = RequestUtil.getPageid(pageNum);
        int start = NumberUtil.getPageStart(pageNum, PAGE_SIZE);
        Page<ReturnOrder> page = returnOrderService.page(query, start, PAGE_SIZE);
        List<AdminReturnOrderVO> list = returnOrderHandler.toVOList4Admin(page.getData());
        String param = "state=" + state;
        AcePagingView pagingView = new AcePagingView("/admin/orderform-ReturnOrder-list", DIR + "/list?" + param, pageNum, PAGE_SIZE, page.getCount());
        pagingView.addObject("result", list);
        pagingView.addObject("orderStateType", ReturnOrderStateType.values());
        pagingView.addObject("state", state);
        pagingView.addObject("query", query);
        return pagingView;
    }

    @RequestMapping
    public ModelAndView toAdd() {

        ModelAndView model = new ModelAndView("/admin/orderform-ReturnOrder-add");
        return model;
    }

    @RequestMapping
    public AceAjaxView add(ReturnOrder returnOrder) {

        returnOrderService.add(returnOrder);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("添加成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    public ModelAndView toEdit(Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        ReturnOrder returnOrder = returnOrderService.get(id);
        AdminReturnOrderVO adminReturnOrderVO = returnOrderHandler.toVO4Admin(returnOrder);
        ModelAndView model = new ModelAndView("/admin/orderform-ReturnOrder-edit");
        model.addObject("returnOrder", adminReturnOrderVO);
        return model;
    }

    @RequestMapping
    public AceAjaxView edit(ReturnOrder returnOrder) {

        returnOrderService.update(returnOrder);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("编辑成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    public AceAjaxView delete(Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        returnOrderService.delete(id);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("删除成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    public ModelAndView getDetails(Long id) {

        AssertUtil.assertNotNull(id, "id不能为空");
        ReturnOrder returnOrder = returnOrderService.get(id);
        AdminReturnOrderVO returnOrderVO = returnOrderHandler.toVO4Admin(returnOrder);
        // 明细
        List<ReturnOrderItem> details = returnOrderItemService.listByReturn(returnOrderVO.getId());
        List<AdminReturnOrderItemVO> adminDetailVOs = returnOrderItemHandler.toVOList4Admin(details);
        //
        double sum = 0.0;
        Set<Long> idSet = new HashSet<Long>();
        Map<Long, Double> sumMap = new HashMap<Long, Double>();
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        for (AdminReturnOrderItemVO adminReturnOrderItemDetailVO : adminDetailVOs) {
            if (idSet.add(adminReturnOrderItemDetailVO.getOrderItemId())) {
                OrderItem orderItem = orderItemService.get(adminReturnOrderItemDetailVO.getOrderItemId(), returnOrder.getOrderDate());
                sum += orderItem.getDiscount() * adminReturnOrderItemDetailVO.getNumber();
                sumMap.put(orderItem.getId(), new Double(orderItem.getDiscount() * adminReturnOrderItemDetailVO.getNumber() * 100).longValue() / 100d);
            }
        }
        //
        for (Long orderItemId : idSet) {
            Map<String, Object> map = new HashMap<String, Object>();
            OrderItem orderItem = orderItemService.get(orderItemId, returnOrder.getOrderDate());
            map.put("itemId", orderItemId);
            map.put("itemName", orderItem.getName());
            map.put("itemSum", sumMap.get(orderItemId));
            //
            List<AdminReturnOrderItemVO> itemDetails = new ArrayList<AdminReturnOrderItemVO>();
            for (AdminReturnOrderItemVO adminReturnOrderItemDetailVO : adminDetailVOs) {
                if (orderItemId == adminReturnOrderItemDetailVO.getOrderItemId()) {
                    itemDetails.add(adminReturnOrderItemDetailVO);
                }
            }
            map.put("itemDetails", itemDetails);
            mapList.add(map);
        }
        //
        SubOrder subOrder = subOrderService.get(returnOrderVO.getSubOrderId(), returnOrderVO.getOrderDate());
        QStore store = sellercenterClient.getStore(returnOrderVO.getStoreId());
        // List<ReturnOrder> records = returnOrderService.listBySubOrder(returnOrderVO.getSubOrderId());
        // for (int i = 0; i < records.size(); i++) {
        // if (records.get(i).getId() == returnOrderVO.getId()) {
        // records.remove(i);
        // continue;
        // }
        // }
        // 记录
        // List<ReturnOrderItemDetail> orderItemsRecords = new ArrayList<ReturnOrderItemDetail>();
        // for (ReturnOrder order : records) {
        // List<ReturnOrderItemDetail> temp = returnOrderItemDetailService.listByReturn(order.getId());
        // orderItemsRecords.addAll(temp);
        // }
        // List<AdminReturnOrderItemDetailVO> orderItemsRecordsVo = returnOrderItemDetailHandler.toVOList4Admin(orderItemsRecords);
        // 这里仅仅处理了单品,组合套餐的还未处理
        QMerchantOrder merchantOrder = orderformClient.getMerchantOrder(subOrder.getId(), returnOrderVO.getOrderDate());
        List<AdminReturnOrderItemVO> itemDetailList = new ArrayList<AdminReturnOrderItemVO>();
        List<QOrderItem> list = merchantOrder.getOrderItemList();
        for (QOrderItem qOrderItem : list) {
            AdminReturnOrderItemVO detailVO = new AdminReturnOrderItemVO();
            detailVO.setName(qOrderItem.getName());
            detailVO.setImage(fileSDKClient.getFileServerUrl() + qOrderItem.getImage());
            detailVO.setNumber(qOrderItem.getNumber());
            detailVO.setSum(qOrderItem.getSum());
            detailVO.setSpecifications(qOrderItem.getOrderItemDetailList().get(0).getSpecifications());
            itemDetailList.add(detailVO);
        }
        // 这里仅仅处理了单品,组合套餐的还未处理
        ModelAndView view = new ModelAndView("/admin/orderform-ReturnOrder-details");
        view.addObject("order", returnOrderVO);
        view.addObject("orderItemsCombinationRecords", mapList);
        view.addObject("orderItemsRecords", itemDetailList);
        view.addObject("orderStateType", ReturnOrderStateType.values());
        view.addObject("subOrder", subOrder);
        view.addObject("store", store);
        view.addObject("cash", subOrder.getSum());
        view.addObject("returnCash", new Double(sum * 100).longValue() / 100d);
        // view.addObject("orderItems", adminDetailVOs);
        // view.addObject("orderItemsRecords", orderItemsRecordsVo);
        return view;
    }

    @RequestMapping
    public ModelAndView addLogistics() {

        ModelAndView view = new ModelAndView("/admin/orderform-ReturnOrder-addLogistics");
        return view;
    }

    @RequestMapping
    public ModelAndView addMoney(Double cash) {

        ModelAndView view = new ModelAndView("/admin/orderform-ReturnOrder-addMoney");
        view.addObject("cash", cash);
        return view;
    }

    @RequestMapping
    @NoReferer
    public ModelAndView list4Merchant(HttpServletRequest request, PPage pPage, ReturnOrderQuery query) {

        int state = query.getState();
        query.setState(getChangeState(state));
        QMerchant merchant = PageParameterUtil.getParameterValues(request, SellercenterClient.MERCHANT_LOGIN_PARAMETER_KEY);
        query.setMerchantId(merchant.getId());
        Page<ReturnOrder> page = returnOrderService.page(query, pPage.getPageStart(), pPage.getPageSize());
        List<AdminReturnOrderVO> list = returnOrderHandler.toVOList4Admin(page.getData());
        String param = "state=" + state;
        AcePagingView pagingView = new AcePagingView("/admin/orderform-ReturnOrder-list4Merchant", DIR + "/list4Merchant?" + param, pPage.getPageNum(), pPage.getPageSize(), page.getCount());
        pagingView.addObject("result", list);
        pagingView.addObject("orderStateType", ReturnOrderStateType.values());
        pagingView.addObject("state", state);
        pagingView.addObject("query", query);
        return pagingView;
    }

    private static int getChangeState(int Qstate) {

        int state = 0;
        switch (Qstate) {
        case 1:
            state = ReturnOrderStateType.RETURN.getKey();
            break;
        case 2:
            state = ReturnOrderStateType.RETURN_REFUSE.getKey();
            break;
        case 3:
            // state = ReturnOrderStateType.RETURN_FAIL.getKey();
            break;
        case 4:
            state = ReturnOrderStateType.RETURN_CONFIRM.getKey();
            break;
        case 5:
            state = ReturnOrderStateType.RETURN_SHIPPED.getKey();
            break;
        case 6:
            state = ReturnOrderStateType.RETURN_SIGN.getKey();
            break;
        case 7:
            state = ReturnOrderStateType.RETURN_PAID.getKey();
            break;
        case 8:
            // state = ReturnOrderStateType.RETURN_CONFIRM_PAID.getKey();
            break;
        case 9:
            // state = ReturnOrderStateType.RETURN_SUCCESS.getKey();
            break;
        default:
            state = Qstate;
            break;
        }
        return state;
    }

    @RequestMapping
    public ModelAndView getDetails4Merchant(Long id) {

        AssertUtil.assertNotNull(id, "id不能为空");
        ReturnOrder returnOrder = returnOrderService.get(id);
        AdminReturnOrderVO returnOrderVO = returnOrderHandler.toVO4Admin(returnOrder);
        List<ReturnOrderItemDetail> detailsList = returnOrderItemDetailService.listByReturn(returnOrderVO.getId());
        List<AdminReturnOrderItemDetailVO> adminDetailList = returnOrderItemDetailHandler.toVOList4Admin(detailsList);
        
        // 明细
        List<ReturnOrderItem> details = returnOrderItemService.listByReturn(returnOrderVO.getId());
        List<AdminReturnOrderItemVO> adminDetailVOs = returnOrderItemHandler.toVOList4Admin(details);
        //
        double sum = 0.0;
        Set<Long> idSet = new HashSet<Long>();
        Map<Long, Double> sumMap = new HashMap<Long, Double>();
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        for (AdminReturnOrderItemVO adminReturnOrderItemDetailVO : adminDetailVOs) {
            if (idSet.add(adminReturnOrderItemDetailVO.getOrderItemId())) {
                OrderItem orderItem = orderItemService.get(adminReturnOrderItemDetailVO.getOrderItemId(), returnOrder.getOrderDate());
                sum += orderItem.getDiscount() * adminReturnOrderItemDetailVO.getNumber();
                sumMap.put(orderItem.getId(), new Double(orderItem.getDiscount() * adminReturnOrderItemDetailVO.getNumber() * 100).longValue() / 100d);
            }
        }
        //
        for (Long orderItemId : idSet) {
            Map<String, Object> map = new HashMap<String, Object>();
            OrderItem orderItem = orderItemService.get(orderItemId, returnOrder.getOrderDate());
            map.put("itemId", orderItemId);
            map.put("itemName", orderItem.getName());
            map.put("itemSum", sumMap.get(orderItemId));
            //
            List<AdminReturnOrderItemVO> itemDetails = new ArrayList<AdminReturnOrderItemVO>();
            for (AdminReturnOrderItemVO adminReturnOrderItemDetailVO : adminDetailVOs) {
                if (orderItemId == adminReturnOrderItemDetailVO.getOrderItemId()) {
                    itemDetails.add(adminReturnOrderItemDetailVO);
                }
            }
            map.put("itemDetails", itemDetails);
            mapList.add(map);
        }
        //
        SubOrder subOrder = subOrderService.get(returnOrderVO.getSubOrderId(), returnOrderVO.getOrderDate());
        QStore store = sellercenterClient.getStore(returnOrderVO.getStoreId());
        // List<ReturnOrder> records = returnOrderService.listBySubOrder(returnOrderVO.getSubOrderId());
        // for (int i = 0; i < records.size(); i++) {
        // if (records.get(i).getId() == returnOrderVO.getId()) {
        // records.remove(i);
        // continue;
        // }
        // }
        // 记录
        // List<ReturnOrderItemDetail> orderItemsRecords = new ArrayList<ReturnOrderItemDetail>();
        // for (ReturnOrder order : records) {
        // List<ReturnOrderItemDetail> temp = returnOrderItemDetailService.listByReturn(order.getId());
        // orderItemsRecords.addAll(temp);
        // }
        // List<AdminReturnOrderItemDetailVO> orderItemsRecordsVo = returnOrderItemDetailHandler.toVOList4Admin(orderItemsRecords);
        // 这里仅仅处理了单品,组合套餐的还未处理
        QMerchantOrder merchantOrder = orderformClient.getMerchantOrder(subOrder.getId(), returnOrderVO.getOrderDate());
        List<AdminReturnOrderItemVO> itemDetailList = new ArrayList<AdminReturnOrderItemVO>();
        List<QOrderItem> list = merchantOrder.getOrderItemList();
        for (QOrderItem qOrderItem : list) {
            AdminReturnOrderItemVO detailVO = new AdminReturnOrderItemVO();
            detailVO.setName(qOrderItem.getName());
            detailVO.setImage(fileSDKClient.getFileServerUrl() + qOrderItem.getImage());
            detailVO.setNumber(qOrderItem.getNumber());
            detailVO.setSum(qOrderItem.getSum());
            detailVO.setSpecifications(qOrderItem.getOrderItemDetailList().get(0).getSpecifications());
            itemDetailList.add(detailVO);
        }
        // 这里仅仅处理了单品,组合套餐的还未处理
        ModelAndView view = new ModelAndView("/admin/orderform-ReturnOrder-details4Merchant");
        view.addObject("order", returnOrderVO);
        view.addObject("orderItemsCombinationRecords", mapList);
        view.addObject("orderItemsRecords", itemDetailList);
        view.addObject("orderStateType", ReturnOrderStateType.values());
        view.addObject("subOrder", subOrder);
        view.addObject("store", store);
        view.addObject("cash", subOrder.getSum());
        view.addObject("returnCash", new Double(sum * 100).longValue() / 100d);
        // view.addObject("orderItems", adminDetailVOs);
        // view.addObject("orderItemsRecords", orderItemsRecordsVo);
        return view;
    }
}
