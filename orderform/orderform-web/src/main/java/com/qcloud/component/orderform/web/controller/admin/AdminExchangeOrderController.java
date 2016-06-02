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
import com.qcloud.component.orderform.model.ExchangeOrder;
import com.qcloud.component.orderform.model.ExchangeOrderItemDetail;
import com.qcloud.component.orderform.model.OrderItem;
import com.qcloud.component.orderform.model.SubOrder;
import com.qcloud.component.orderform.model.query.ExchangeOrderQuery;
import com.qcloud.component.orderform.service.ExchangeOrderItemDetailService;
import com.qcloud.component.orderform.service.ExchangeOrderService;
import com.qcloud.component.orderform.service.OrderItemService;
import com.qcloud.component.orderform.service.SubOrderService;
import com.qcloud.component.orderform.web.handler.ExchangeOrderHandler;
import com.qcloud.component.orderform.web.handler.ExchangeOrderItemDetailHandler;
import com.qcloud.component.orderform.web.util.ExchangeOrderStateType;
import com.qcloud.component.orderform.web.vo.admin.AdminExchangeOrderItemDetailVO;
import com.qcloud.component.orderform.web.vo.admin.AdminExchangeOrderVO;
import com.qcloud.component.sellercenter.QMerchant;
import com.qcloud.component.sellercenter.QStore;
import com.qcloud.component.sellercenter.SellercenterClient;
import com.qcloud.component.sellercenter.model.Store;
import com.qcloud.component.sellercenter.service.StoreService;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.mvc.AceAjaxView;
import com.qcloud.pirates.mvc.AcePagingView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.NumberUtil;
import com.qcloud.pirates.util.RequestUtil;
import com.qcloud.pirates.web.page.PageParameterUtil;
import com.qcloud.pirates.web.security.annotation.NoReferer;

@Controller
@RequestMapping(value = "/" + AdminExchangeOrderController.DIR)
public class AdminExchangeOrderController {

    public static final String             DIR = "admin/exchangeOrder";

    @Autowired
    private ExchangeOrderService           exchangeOrderService;

    @Autowired
    private ExchangeOrderHandler           exchangeOrderHandler;

    @Autowired
    private ExchangeOrderItemDetailService exchangeOrderItemDetailService;

    @Autowired
    private ExchangeOrderItemDetailHandler exchangeOrderItemDetailHandler;

//    @Autowired
//    private AdminFilterService             adminFilterService;

//    @Autowired
//    private TokenClient                    tokenClient;

//    @Autowired
//    private StoreMemberService             storeMemberService;

    @Autowired
    private SubOrderService                subOrderService;

    @Autowired
    private StoreService                   storeService;

//    @Autowired
//    private MerchantMemberService          merchantMemberService;

    @Autowired
    private OrderItemService               orderItemService;

    @Autowired
    private OrderformClient                orderformClient;

    @Autowired
    private FileSDKClient                  fileSDKClient;

    @RequestMapping
    @NoReferer
    public ModelAndView list(HttpServletRequest request, Integer pageNum, ExchangeOrderQuery query) {

        int state = query.getState();
        query.setState(getChangeState(state));
//        long memberId = getMemberId(request);
//        long storeId = getStoreId(memberId);
        
        QStore store = PageParameterUtil.getParameterValues(request, SellercenterClient.STORE_LOGIN_PARAMETER_KEY);
                
        query.setStoreId(store.getId());
        final int PAGE_SIZE = 10;
        pageNum = RequestUtil.getPageid(pageNum);
        int start = NumberUtil.getPageStart(pageNum, PAGE_SIZE);
        Page<ExchangeOrder> page = exchangeOrderService.page(query, start, PAGE_SIZE);
        List<AdminExchangeOrderVO> list = exchangeOrderHandler.toVOList4Admin(page.getData());
        String param = "state=" + state;
        AcePagingView pagingView = new AcePagingView("/admin/orderform-ExchangeOrder-list", DIR + "/list?" + param, pageNum, PAGE_SIZE, page.getCount());
        pagingView.addObject("result", list);
        pagingView.addObject("state", state);
        pagingView.addObject("orderStateType", ExchangeOrderStateType.values());
        pagingView.addObject("query", query);
        return pagingView;
    }

    @RequestMapping
    public ModelAndView toAdd() {

        ModelAndView model = new ModelAndView("/admin/orderform-ExchangeOrder-add");
        return model;
    }

    @RequestMapping
    public AceAjaxView add(ExchangeOrder exchangeOrder) {

        exchangeOrderService.add(exchangeOrder);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("添加成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    public ModelAndView toEdit(Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        ExchangeOrder exchangeOrder = exchangeOrderService.get(id);
        AdminExchangeOrderVO adminExchangeOrderVO = exchangeOrderHandler.toVO4Admin(exchangeOrder);
        ModelAndView model = new ModelAndView("/admin/orderform-ExchangeOrder-edit");
        model.addObject("exchangeOrder", adminExchangeOrderVO);
        return model;
    }

    @RequestMapping
    public AceAjaxView edit(ExchangeOrder exchangeOrder) {

        exchangeOrderService.update(exchangeOrder);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("编辑成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    public AceAjaxView delete(Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        exchangeOrderService.delete(id);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("删除成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    public ModelAndView getDetails(Long id) {

        AssertUtil.assertNotNull(id, "id不能为空");
        ExchangeOrder exchangeOrder = exchangeOrderService.get(id);
        AdminExchangeOrderVO orderVO = exchangeOrderHandler.toVO4Admin(exchangeOrder);
        // 明细
        List<ExchangeOrderItemDetail> details = exchangeOrderItemDetailService.listByExchange(orderVO.getId());
        List<AdminExchangeOrderItemDetailVO> adminDetailVOs = exchangeOrderItemDetailHandler.toVOList4Admin(details);
        double sum = 0.0;
        Set<Long> idSet = new HashSet<Long>();
        Map<Long, Double> sumMap = new HashMap<Long, Double>();
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        for (AdminExchangeOrderItemDetailVO vo : adminDetailVOs) {
            if (idSet.add(vo.getOrderItemId())) {
                OrderItem orderItem = orderItemService.get(vo.getOrderItemId(), exchangeOrder.getOrderDate());
                sum += orderItem.getDiscount() * vo.getNumber();
                sumMap.put(orderItem.getId(), new Double(orderItem.getDiscount() * vo.getNumber() * 100).longValue() / 100d);
            }
        }
        //
        for (Long orderItemId : idSet) {
            Map<String, Object> map = new HashMap<String, Object>();
            OrderItem orderItem = orderItemService.get(orderItemId, exchangeOrder.getOrderDate());
            map.put("itemId", orderItemId);
            map.put("itemName", orderItem.getName());
            map.put("itemSum", sumMap.get(orderItemId));
            //
            List<AdminExchangeOrderItemDetailVO> itemDetails = new ArrayList<AdminExchangeOrderItemDetailVO>();
            for (AdminExchangeOrderItemDetailVO vo : adminDetailVOs) {
                if (orderItemId == vo.getOrderItemId()) {
                    itemDetails.add(vo);
                }
            }
            map.put("itemDetails", itemDetails);
            mapList.add(map);
        }
        //
        SubOrder subOrder = subOrderService.get(orderVO.getSubOrderId(), orderVO.getOrderDate());
        Store store = storeService.get(orderVO.getStoreId());
        // List<ExchangeOrder> records = exchangeOrderService.listBySubOrder(orderVO.getSubOrderId());
        // for (int i = 0; i < records.size(); i++) {
        // if (records.get(i).getId() == orderVO.getId()) {
        // records.remove(i);
        // continue;
        // }
        // }
        // // 记录
        // List<ExchangeOrderItemDetail> orderItemsRecords = new ArrayList<ExchangeOrderItemDetail>();
        // for (ExchangeOrder record : records) {
        // List<ExchangeOrderItemDetail> temp = exchangeOrderItemDetailService.listByExchange(record.getId());
        // orderItemsRecords.addAll(temp);
        // }
        // List<AdminExchangeOrderItemDetailVO> orderItemsRecordsVo = exchangeOrderItemDetailHandler.toVOList4Admin(orderItemsRecords);
        QMerchantOrder merchantOrder = orderformClient.getMerchantOrder(subOrder.getId(), exchangeOrder.getOrderDate());
        List<AdminExchangeOrderItemDetailVO> itemDetailList = new ArrayList<AdminExchangeOrderItemDetailVO>();
        List<QOrderItem> list = merchantOrder.getOrderItemList();
        for (QOrderItem qOrderItem : list) {
            AdminExchangeOrderItemDetailVO detailVO = new AdminExchangeOrderItemDetailVO();
            detailVO.setName(qOrderItem.getName());
            detailVO.setImage(fileSDKClient.getFileServerUrl() + qOrderItem.getImage());
            detailVO.setNumber(qOrderItem.getNumber());          
            detailVO.setSpecifications(qOrderItem.getOrderItemDetailList().get(0).getSpecifications());
            itemDetailList.add(detailVO);
        }
        ModelAndView view = new ModelAndView("/admin/orderform-ExchangeOrder-details");
        view.addObject("order", orderVO);
        view.addObject("orderItemsCombinationRecords", mapList);
        view.addObject("orderItemsRecords", itemDetailList);
        view.addObject("orderStateType", ExchangeOrderStateType.values());
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

        ModelAndView view = new ModelAndView("/admin/orderform-ExchangeOrder-addLogistics");
        return view;
    }

//    private long getMemberId(HttpServletRequest request) {
//
//        String tokenId = adminFilterService.getTokenId(request);
//        AssertUtil.assertNotEmpty(tokenId, "获取用户登录信息失败.");
//        String idStr = tokenClient.get(tokenId);
//        AssertUtil.assertNotEmpty(idStr, "获取用户标识失败.");
//        long memberId = Long.parseLong(idStr);
//        return memberId;
//    }
//
//    private long getStoreId(Long memberId) {
//
//        long storeId = 0;
//        HashMap where = new HashMap();
//        where.put("memberId", memberId);
//        StoreMember storeMember = storeMemberService.get(where);
//        if (storeMember != null) {
//            storeId = storeMember.getStoreId();
//        } else {
//            AssertUtil.assertTrue(false, "当前登录用户不属于门店!");
//        }
//        return storeId;
//    }
//
//    private long getMerchantId(Long memberId) {
//
//        long merchantId = 0;
//        HashMap where = new HashMap();
//        where.put("memberId", memberId);
//        List<MerchantMember> merchantMembers = merchantMemberService.listByMember(memberId);
//        if (merchantMembers.size() > 0) {
//            merchantId = merchantMembers.get(0).getMerchantId();
//        } else {
//            AssertUtil.assertTrue(false, "当前登录用户不是商家!");
//        }
//        return merchantId;
//    }

    @RequestMapping
    public ModelAndView list4Merchant(HttpServletRequest request, Integer pageNum, ExchangeOrderQuery query) {

        int state = query.getState();
        query.setState(getChangeState(state));
//        long memberId = getMemberId(request);
//        long merchantId = getMerchantId(memberId);
        
        QMerchant merchant = PageParameterUtil.getParameterValues(request, SellercenterClient.MERCHANT_LOGIN_PARAMETER_KEY);
        
        query.setMerchantId(merchant.getId());
        final int PAGE_SIZE = 10;
        pageNum = RequestUtil.getPageid(pageNum);
        int start = NumberUtil.getPageStart(pageNum, PAGE_SIZE);
        Page<ExchangeOrder> page = exchangeOrderService.page(query, start, PAGE_SIZE);
        List<AdminExchangeOrderVO> list = exchangeOrderHandler.toVOList4Admin(page.getData());
        String param = "state=" + state;
        AcePagingView pagingView = new AcePagingView("/admin/orderform-ExchangeOrder-list4Merchant", DIR + "/list4Merchant?" + param, pageNum, PAGE_SIZE, page.getCount());
        pagingView.addObject("result", list);
        pagingView.addObject("state", state);
        pagingView.addObject("orderStateType", ExchangeOrderStateType.values());
        pagingView.addObject("query", query);
        return pagingView;
    }

    // TODO
    private static int getChangeState(int Qstate) {

        int state = 0;
        switch (Qstate) {
        case 1:
            state = ExchangeOrderStateType.EXCHANGE.getKey();
            break;
        case 2:
            state = ExchangeOrderStateType.EXCHANGE_REFUSE.getKey();
            break;
        case 3:
            // state = ExchangeOrderStateType.EXCHANGE_FAIL.getKey();
            break;
        case 4:
            state = ExchangeOrderStateType.EXCHANGE_CONFIRM.getKey();
            break;
        case 5:
            state = ExchangeOrderStateType.EXCHANGE_SHIPPED.getKey();
            break;
        case 6:
            state = ExchangeOrderStateType.EXCHANGE_SIGN.getKey();
            break;
        case 7:
            state = ExchangeOrderStateType.EXCHANGE_SHIPPED_AGAIN.getKey();
            break;
        case 8:
            state = ExchangeOrderStateType.EXCHANGE_SIGN_AGAIN.getKey();
            break;
        case 9:
            // state = ExchangeOrderStateType.EXCHANGE_SUCCESS.getKey();
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
        ExchangeOrder exchangeOrder = exchangeOrderService.get(id);
        AdminExchangeOrderVO orderVO = exchangeOrderHandler.toVO4Admin(exchangeOrder);
        // 明细
        List<ExchangeOrderItemDetail> details = exchangeOrderItemDetailService.listByExchange(orderVO.getId());
        List<AdminExchangeOrderItemDetailVO> adminDetailVOs = exchangeOrderItemDetailHandler.toVOList4Admin(details);
        double sum = 0.0;
        Set<Long> idSet = new HashSet<Long>();
        Map<Long, Double> sumMap = new HashMap<Long, Double>();
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        for (AdminExchangeOrderItemDetailVO vo : adminDetailVOs) {
            if (idSet.add(vo.getOrderItemId())) {
                OrderItem orderItem = orderItemService.get(vo.getOrderItemId(), exchangeOrder.getOrderDate());
                sum += orderItem.getDiscount() * vo.getNumber();
                sumMap.put(orderItem.getId(), new Double(orderItem.getDiscount() * vo.getNumber() * 100).longValue() / 100d);
            }
        }
        //
        for (Long orderItemId : idSet) {
            Map<String, Object> map = new HashMap<String, Object>();
            OrderItem orderItem = orderItemService.get(orderItemId, exchangeOrder.getOrderDate());
            map.put("itemId", orderItemId);
            map.put("itemName", orderItem.getName());
            map.put("itemSum", sumMap.get(orderItemId));
            //
            List<AdminExchangeOrderItemDetailVO> itemDetails = new ArrayList<AdminExchangeOrderItemDetailVO>();
            for (AdminExchangeOrderItemDetailVO vo : adminDetailVOs) {
                if (orderItemId == vo.getOrderItemId()) {
                    itemDetails.add(vo);
                }
            }
            map.put("itemDetails", itemDetails);
            mapList.add(map);
        }
        //
        SubOrder subOrder = subOrderService.get(orderVO.getSubOrderId(), orderVO.getOrderDate());
        Store store = storeService.get(orderVO.getStoreId());
        // List<ExchangeOrder> records = exchangeOrderService.listBySubOrder(orderVO.getSubOrderId());
        // for (int i = 0; i < records.size(); i++) {
        // if (records.get(i).getId() == orderVO.getId()) {
        // records.remove(i);
        // continue;
        // }
        // }
        // // 记录
        // List<ExchangeOrderItemDetail> orderItemsRecords = new ArrayList<ExchangeOrderItemDetail>();
        // for (ExchangeOrder record : records) {
        // List<ExchangeOrderItemDetail> temp = exchangeOrderItemDetailService.listByExchange(record.getId());
        // orderItemsRecords.addAll(temp);
        // }
        // List<AdminExchangeOrderItemDetailVO> orderItemsRecordsVo = exchangeOrderItemDetailHandler.toVOList4Admin(orderItemsRecords);
        QMerchantOrder merchantOrder = orderformClient.getMerchantOrder(subOrder.getId(), exchangeOrder.getOrderDate());
        List<AdminExchangeOrderItemDetailVO> itemDetailList = new ArrayList<AdminExchangeOrderItemDetailVO>();
        List<QOrderItem> list = merchantOrder.getOrderItemList();
        for (QOrderItem qOrderItem : list) {
            AdminExchangeOrderItemDetailVO detailVO = new AdminExchangeOrderItemDetailVO();
            detailVO.setName(qOrderItem.getName());
            detailVO.setImage(fileSDKClient.getFileServerUrl() + qOrderItem.getImage());
            detailVO.setNumber(qOrderItem.getNumber());           
            detailVO.setSpecifications(qOrderItem.getOrderItemDetailList().get(0).getSpecifications());
            itemDetailList.add(detailVO);
        }
        ModelAndView view = new ModelAndView("/admin/orderform-ExchangeOrder-details4Merchant");
        view.addObject("order", orderVO);
        view.addObject("orderItemsCombinationRecords", mapList);
        view.addObject("orderItemsRecords", itemDetailList);
        view.addObject("orderStateType", ExchangeOrderStateType.values());
        view.addObject("subOrder", subOrder);
        view.addObject("store", store);
        view.addObject("cash", subOrder.getSum());
        view.addObject("returnCash", new Double(sum * 100).longValue() / 100d);
        // view.addObject("orderItems", adminDetailVOs);
        // view.addObject("orderItemsRecords", orderItemsRecordsVo);
        return view;
    }
}
