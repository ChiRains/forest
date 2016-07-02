//package com.qcloud.component.warehouse.web.controller.admin;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//import javax.servlet.http.HttpServletRequest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.servlet.ModelAndView;
//import com.qcloud.component.admin.AdminClient;
//import com.qcloud.component.goods.CommoditycenterClient;
//import com.qcloud.component.goods.OutdatedCommoditycenterClient;
//import com.qcloud.component.goods.QUnifiedMerchandise;
//import com.qcloud.component.goods.model.MerchandiseItem;
//import com.qcloud.component.goods.model.query.MerchandiseItemQuery;
//import com.qcloud.component.sellercenter.OutdatedSellercenterClient;
//import com.qcloud.component.sellercenter.QStore;
//import com.qcloud.component.sellercenter.SellercenterClient;
//import com.qcloud.component.sellercenter.model.query.StoreMemberQuery;
//import com.qcloud.component.warehouse.exception.WarehouseException;
//import com.qcloud.component.warehouse.model.MerchandiseStockState;
//import com.qcloud.component.warehouse.model.StockState;
//import com.qcloud.component.warehouse.model.key.TypeEnum.StockStateType;
//import com.qcloud.component.warehouse.model.query.StockStateQuery;
//import com.qcloud.component.warehouse.service.MerchandiseStockStateService;
//import com.qcloud.component.warehouse.service.StockStateService;
//import com.qcloud.component.warehouse.web.handler.StockStateHandler;
//import com.qcloud.component.warehouse.web.vo.admin.AdminMerchandiseStockStateVO;
//import com.qcloud.component.warehouse.web.vo.admin.AdminStockStateVO;
//import com.qcloud.pirates.data.Page;
//import com.qcloud.pirates.mvc.AceAjaxView;
//import com.qcloud.pirates.mvc.AcePagingView;
//import com.qcloud.pirates.util.AssertUtil;
//import com.qcloud.pirates.util.NumberUtil;
//import com.qcloud.pirates.util.RequestUtil;
//import com.qcloud.pirates.util.StringUtil;
//import com.qcloud.pirates.web.page.PageParameterUtil;
//import com.qcloud.pirates.web.security.annotation.NoReferer;
//
//@Controller
//@RequestMapping(value = "/" + AdminStockStateController.DIR)
//public class AdminStockStateController {
//
//    public static final String            DIR = "admin/stockState";
//
//    @Autowired
//    private StockStateService             stockStateService;
//
//    @Autowired
//    private MerchandiseStockStateService  merchandiseStockStateService;
//
//    @Autowired
//    private StockStateHandler             stockStateHandler;
//
//    @Autowired
//    private OutdatedSellercenterClient    outdatedSellercenterClient;
//
//    @Autowired
//    private SellercenterClient            sellercenterClient;
//
////    @Autowired
////    private AdminFilterService            adminFilterService;
////
////    @Autowired
////    private TokenClient                   tokenClient;
//
//    @Autowired
//    private CommoditycenterClient         commoditycenterClient;
//
//    @Autowired
//    private OutdatedCommoditycenterClient outdatedCommoditycenterClient;
//
//    @RequestMapping
//    @NoReferer
//    public ModelAndView list(Integer pageNum, StockStateQuery query) {
//
//        final int PAGE_SIZE = 10;
//        pageNum = RequestUtil.getPageid(pageNum);
//        int start = NumberUtil.getPageStart(pageNum, PAGE_SIZE);
//        Page<StockState> page = stockStateService.page(query, start, PAGE_SIZE);
//        List<AdminStockStateVO> list = stockStateHandler.toVOList4Admin(page.getData());
//        AcePagingView pagingView = new AcePagingView("/admin/warehouse-StockState-list", DIR + "/list", pageNum, PAGE_SIZE, page.getCount());
//        pagingView.addObject("result", list);
//        return pagingView;
//    }
//
//    @RequestMapping
//    public ModelAndView toAdd(HttpServletRequest request) {
//
////        Long memberId = getMemberId(request);
//        StoreMemberQuery query = new StoreMemberQuery();
////        long merchantId = getMerchantId(memberId);
//        QStore store = PageParameterUtil.getParameterValues(request, SellercenterClient.STORE_LOGIN_PARAMETER_KEY);
//        Long memberId = PageParameterUtil.getParameterValues(request, AdminClient.ADMIN_TOKEN_LOGIN_PARAMETER_KEY);
//        
//        query.setMemberId(memberId);
//        ModelAndView model = new ModelAndView("/admin/warehouse-StockState-add");
//        List<QStore> storeList = sellercenterClient.listStoreByMerchant(store.getMerchantId());
//        model.addObject("storeList", storeList);
//        model.addObject("storeMember", outdatedSellercenterClient.getMemberStore(query));
//        return model;
//    }
//
//    /**
//     * 申请货单
//     * @param stockState
//     * @param merchandiseStockStateVO
//     * @return
//     */
//    @RequestMapping
//    public AceAjaxView add(StockState stockState, AdminMerchandiseStockStateVO merchandiseStockStateVO) {
//
//        List<Long> unifiedMerchandiseIds = merchandiseStockStateVO.getUnifiedMerchandiseIds();
//        List<Integer> numbers = merchandiseStockStateVO.getNumbers();
//        Set<Long> set = new HashSet<Long>();
//        for (Long unifiedMerchandiseId : unifiedMerchandiseIds) {
//            set.add(unifiedMerchandiseId);
//        }
//        // 判断商品是否重复
//        if (set.size() != unifiedMerchandiseIds.size()) {
//            throw new WarehouseException("有重复申请的商品!");
//        }
//        if (unifiedMerchandiseIds.size() != numbers.size()) {
//            throw new WarehouseException("非法操作!");
//        }
//        stockState.setState(StockStateType.APPLY.getKey());
//        // 更新保存
//        stockStateService.add(stockState);
//        Date applyTime = new Date();
//        // 货单商品关联新增
//        for (int i = 0; i < unifiedMerchandiseIds.size(); i++) {
//            MerchandiseStockState mss = new MerchandiseStockState();
//            mss.setStockStateId(stockState.getId());
//            mss.setUnifiedMerchandiseId(unifiedMerchandiseIds.get(i));
//            mss.setNumber(numbers.get(i));
//            mss.setState(StockStateType.APPLY.getKey());
//            mss.setApplyTime(applyTime);
//            merchandiseStockStateService.add(mss);
//        }
//        AceAjaxView aceAjaxView = new AceAjaxView();
//        aceAjaxView.setMessage("添加成功");
//        aceAjaxView.setUrl("admin/merchandiseStockState/list");
//        return aceAjaxView;
//    }
//
//    @RequestMapping
//    public ModelAndView toEdit(Long id) {
//
//        AssertUtil.assertNotNull(id, "ID不能为空");
//        StockState stockState = stockStateService.get(id);
//        AdminStockStateVO adminStockStateVO = stockStateHandler.toVO4Admin(stockState);
//        ModelAndView model = new ModelAndView("/admin/warehouse-StockState-edit");
//        model.addObject("stockState", adminStockStateVO);
//        return model;
//    }
//
//    @RequestMapping
//    public AceAjaxView edit(StockState stockState) {
//
//        stockStateService.update(stockState);
//        AceAjaxView aceAjaxView = new AceAjaxView();
//        aceAjaxView.setMessage("编辑成功");
//        aceAjaxView.setUrl(DIR + "/list");
//        return aceAjaxView;
//    }
//
//    @RequestMapping
//    public AceAjaxView delete(Long id) {
//
//        AssertUtil.assertNotNull(id, "ID不能为空");
//        stockStateService.delete(id);
//        AceAjaxView aceAjaxView = new AceAjaxView();
//        aceAjaxView.setMessage("删除成功");
//        aceAjaxView.setUrl(DIR + "/list");
//        return aceAjaxView;
//    }
//
//    @RequestMapping
//    public ModelAndView selectProductList(Integer pageNum, HttpServletRequest request, StockStateQuery stockStateQuery) {
//
////        long merchantId = getMerchantId(getMemberId(request));
//        
//        QStore store = PageParameterUtil.getParameterValues(request, SellercenterClient.STORE_LOGIN_PARAMETER_KEY);
//        
//        final int PAGE_SIZE = 10;
//        pageNum = RequestUtil.getPageid(pageNum);
//        int start = NumberUtil.getPageStart(pageNum, PAGE_SIZE);
//        MerchandiseItemQuery query = new MerchandiseItemQuery();
//        query.setMerchantId(store.getMerchantId());
//        query.setName(stockStateQuery.getName());
//        Page<MerchandiseItem> page = outdatedCommoditycenterClient.merchandiseItemPage(query, start, PAGE_SIZE);
//        List<MerchandiseItem> list = page.getData();
//        String param = "name=" + StringUtil.nullToEmpty(stockStateQuery.getName());
//        AcePagingView pagingView = new AcePagingView("/admin/warehouse-MerchandiseItem-selectProduct-list", DIR + "/selectProductList.do?" + param, pageNum, PAGE_SIZE, page.getCount());
//        List<QUnifiedMerchandise> qUnifiedMerchandiseList = new ArrayList<QUnifiedMerchandise>();
//        for (MerchandiseItem merchandiseItem : list) {
//            qUnifiedMerchandiseList.add(commoditycenterClient.getUnifiedMerchandise(merchandiseItem.getUnifiedMerchandiseId()));
//        }
//        pagingView.addObject("result", qUnifiedMerchandiseList);
//        pagingView.addObject("query", stockStateQuery);
//        return pagingView;
//    }
//
////    private long getMemberId(HttpServletRequest request) {
////
////        String tokenId = adminFilterService.getTokenId(request);
////        AssertUtil.assertNotEmpty(tokenId, "获取用户登录信息失败.");
////        String idStr = tokenClient.get(tokenId);
////        AssertUtil.assertNotEmpty(idStr, "获取用户标识失败.");
////        long memberId = Long.parseLong(idStr);
////        return memberId;
////    }
////
////    private long getUserMerchantId(long memberId) {
////
////        long merchantId = 0;
////        List<QMerchant> qMerchants = outdatedSellercenterClient.listMerchant(memberId);
////        if (qMerchants.size() > 0) {
////            merchantId = qMerchants.get(0).getId();
////        }
////        return merchantId;
////    }
////
////    private long getStoreId(Long memberId) {
////
////        long storeId = 0;
////        StoreMemberQuery query = new StoreMemberQuery();
////        query.setMemberId(memberId);
////        StoreMember storeMember = outdatedSellercenterClient.getMemberStore(query);
////        if (storeMember != null) {
////            storeId = storeMember.getStoreId();
////        }
////        return storeId;
////    }
////
////    private long getStoreMerchantId(long memberId) {
////
////        long merchantId = 0;
////        StoreMemberQuery query = new StoreMemberQuery();
////        query.setMemberId(memberId);
////        StoreMember storeMember = outdatedSellercenterClient.getMemberStore(query);
////        if (storeMember != null) {
////            merchantId = storeMember.getMerchantId();
////        }
////        return merchantId;
////    }
////
////    private long getMerchantId(long memberId) {
////
////        long merchantId;
////        long storeId = getStoreId(memberId);
////        if (storeId > 0) {
////            merchantId = getStoreMerchantId(memberId);
////        } else {
////            merchantId = getUserMerchantId(memberId);
////        }
////        AssertUtil.assertTrue(merchantId > 0, "获取不到商家ID");
////        return merchantId;
////    }
//}
