//package com.qcloud.component.warehouse.web.controller.admin;
//
//import java.util.Date;
//import java.util.List;
//import javax.servlet.http.HttpServletRequest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.servlet.ModelAndView;
//import com.qcloud.component.goods.CommoditycenterClient;
//import com.qcloud.component.goods.QUnifiedMerchandise;
//import com.qcloud.component.sellercenter.OutdatedSellercenterClient;
//import com.qcloud.component.sellercenter.QStore;
//import com.qcloud.component.sellercenter.SellercenterClient;
//import com.qcloud.component.warehouse.exception.WarehouseException;
//import com.qcloud.component.warehouse.model.MerchandiseStockState;
//import com.qcloud.component.warehouse.model.StockState;
//import com.qcloud.component.warehouse.model.key.TypeEnum.StockStateType;
//import com.qcloud.component.warehouse.model.query.MerchandiseStockStateQuery;
//import com.qcloud.component.warehouse.service.MerchandiseStockStateService;
//import com.qcloud.component.warehouse.service.StockStateService;
//import com.qcloud.component.warehouse.web.handler.MerchandiseStockStateHandler;
//import com.qcloud.component.warehouse.web.vo.admin.AdminMerchandiseStockStateVO;
//import com.qcloud.pirates.data.Page;
//import com.qcloud.pirates.mvc.AceAjaxView;
//import com.qcloud.pirates.mvc.AcePagingView;
//import com.qcloud.pirates.util.AssertUtil;
//import com.qcloud.pirates.util.RequestUtil;
//import com.qcloud.pirates.web.page.PageParameterUtil;
//import com.qcloud.pirates.web.security.annotation.NoReferer;
//
//@Controller
//@RequestMapping(value = "/" + AdminMerchandiseStockStateController.DIR)
//public class AdminMerchandiseStockStateController {
//
//    public static final String           DIR = "admin/merchandiseStockState";
//
//    @Autowired
//    private MerchandiseStockStateService merchandiseStockStateService;
//
//    @Autowired
//    private MerchandiseStockStateHandler merchandiseStockStateHandler;
//
//    @Autowired
//    private StockStateService            stockStateService;
//
//    @Autowired
//    private OutdatedSellercenterClient   outdatedSellercenterClient;
//
//    @Autowired
//    private SellercenterClient           sellercenterClient;
//
////    @Autowired
////    private AdminFilterService           adminFilterService;
////
////    @Autowired
////    private TokenClient                  tokenClient;
//
//    @Autowired
//    private CommoditycenterClient        commoditycenterClient;
//
//    /**
//     * 要货单列表
//     * @param request
//     * @param pageNum
//     * @param query
//     * @return
//     */
//    @RequestMapping
//    @NoReferer
//    public ModelAndView list(HttpServletRequest request, Integer pageNum, MerchandiseStockStateQuery query) {
//
//        // 成员、商家、门店
////        long memberId = getMemberId(request);
////        long merchantId = getMerchantId(memberId);
////        long storeId = getStoreId(memberId);
//        
//        QStore store = PageParameterUtil.getParameterValues(request, SellercenterClient.STORE_LOGIN_PARAMETER_KEY);
//        
//        List<QStore> storeList = sellercenterClient.listStoreByMerchant(store.getMerchantId());
//        final int PAGE_SIZE = 10;
//        pageNum = RequestUtil.getPageid(pageNum);
//        query.setStoreId(store.getId());
//        Page<MerchandiseStockState> page = merchandiseStockStateService.page(query, pageNum, PAGE_SIZE, true);
//        List<AdminMerchandiseStockStateVO> list = merchandiseStockStateHandler.toVOList4Admin(page.getData());
//        String param = "state=" + query.getState();
//        AcePagingView pagingView = new AcePagingView("/admin/warehouse-MerchandiseStockState-list", DIR + "/list?" + param, pageNum, PAGE_SIZE, page.getCount());
//        pagingView.addObject("result", list);
//        pagingView.addObject("storeList", storeList);
//        pagingView.addObject("stockStateType", StockStateType.values());
//        pagingView.addObject("query", query);
//        return pagingView;
//    }
//
//    @RequestMapping
//    public ModelAndView toAdd() {
//
//        ModelAndView model = new ModelAndView("/admin/warehouse-MerchandiseStockState-add");
//        return model;
//    }
//
//    /**
//     * 要货单编辑跳转
//     * @param request
//     * @param id
//     * @return
//     */
//    @RequestMapping
//    public ModelAndView toEdit(HttpServletRequest request, Long id) {
//
////        long memberId = getMemberId(request);
////        long storeId = getStoreId(memberId);
//        
//        QStore store = PageParameterUtil.getParameterValues(request, SellercenterClient.STORE_LOGIN_PARAMETER_KEY);
//        
//        AssertUtil.assertNotNull(id, "ID不能为空");
//        MerchandiseStockState merchandiseStockState = merchandiseStockStateService.get(id);
//        StockState stockState = stockStateService.get(merchandiseStockState.getStockStateId());
//        if (stockState.getToStoreId() != store.getId()) {
//            throw new WarehouseException("非法操作!");
//        }
//        AdminMerchandiseStockStateVO adminMerchandiseStockStateVO = merchandiseStockStateHandler.toVO4Admin(merchandiseStockState);
//        QUnifiedMerchandise qUnifiedMerchandise = commoditycenterClient.getUnifiedMerchandise(adminMerchandiseStockStateVO.getUnifiedMerchandiseId());
//        ModelAndView model = new ModelAndView("/admin/warehouse-MerchandiseStockState-edit");
//        model.addObject("merchandiseStockState", adminMerchandiseStockStateVO);
//        model.addObject("stockState", stockState);
//        model.addObject("qUnifiedMerchandise", qUnifiedMerchandise);
//        model.addObject("store", sellercenterClient.getStore(stockState.getFormStoreId()));
//        return model;
//    }
//
//    /**
//     * 要货单编辑
//     * @param merchandiseStockState
//     * @return
//     */
//    @RequestMapping
//    public AceAjaxView edit(MerchandiseStockState merchandiseStockState) {
//
//        MerchandiseStockState mss = merchandiseStockStateService.get(merchandiseStockState.getId());
//        if (mss.getState() != StockStateType.APPLY.getKey()) {
//            throw new WarehouseException("修改失败, 您未拥有修改的权限!");
//        }
//        mss.setNumber(merchandiseStockState.getNumber());
//        merchandiseStockStateService.update(mss);
//        AceAjaxView aceAjaxView = new AceAjaxView();
//        aceAjaxView.setMessage("编辑成功");
//        aceAjaxView.setUrl(DIR + "/list");
//        return aceAjaxView;
//    }
//
//    /**
//     * 要货单删除
//     * @param id
//     * @return
//     */
//    @RequestMapping
//    public AceAjaxView delete(Long id) {
//
//        AssertUtil.assertNotNull(id, "ID不能为空");
//        merchandiseStockStateService.delete(id);
//        AceAjaxView aceAjaxView = new AceAjaxView();
//        aceAjaxView.setMessage("删除成功");
//        aceAjaxView.setUrl(DIR + "/list");
//        return aceAjaxView;
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
//
////    private long getUserMerchantId(long memberId) {
////
////        long merchantId = 0;
////        List<QMerchant> qMerchants = outdatedSellercenterClient.listMerchant(memberId);
////        if (qMerchants.size() > 0) {
////            merchantId = qMerchants.get(0).getId();
////        }
////        return merchantId;
////    }
//
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
//
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
//
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
//
//    /**
//     * 出货单列表
//     * @param request
//     * @param pageNum
//     * @param query
//     * @return
//     */
//    @RequestMapping
//    @NoReferer
//    public ModelAndView outList(HttpServletRequest request, Integer pageNum, MerchandiseStockStateQuery query) {
//
//        // 成员、商家、门店
////        long memberId = getMemberId(request);
////        long merchantId = getMerchantId(memberId);
////        long storeId = getStoreId(memberId);
//        
//        QStore store = PageParameterUtil.getParameterValues(request, SellercenterClient.STORE_LOGIN_PARAMETER_KEY);
//        
//        List<QStore> storeList = sellercenterClient.listStoreByMerchant(store.getMerchantId());
//        final int PAGE_SIZE = 10;
//        pageNum = RequestUtil.getPageid(pageNum);
//        query.setStoreId(store.getId());
//        Page<MerchandiseStockState> page = merchandiseStockStateService.page(query, pageNum, PAGE_SIZE, false);
//        List<AdminMerchandiseStockStateVO> list = merchandiseStockStateHandler.toVOList4Admin(page.getData());
//        String param = "state=" + query.getState();
//        AcePagingView pagingView = new AcePagingView("/admin/warehouse-MerchandiseStockStateOut-list", DIR + "/outList?" + param, pageNum, PAGE_SIZE, page.getCount());
//        pagingView.addObject("result", list);
//        pagingView.addObject("storeList", storeList);
//        pagingView.addObject("query", query);
//        pagingView.addObject("stockStateType", StockStateType.values());
//        return pagingView;
//    }
//
//    /**
//     * 出货单编辑跳转
//     * @param request
//     * @param id
//     * @return
//     */
//    @RequestMapping
//    public ModelAndView toOutEdit(HttpServletRequest request, Long id) {
//
////        long memberId = getMemberId(request);
////        long storeId = getStoreId(memberId);
//        
//        QStore store = PageParameterUtil.getParameterValues(request, SellercenterClient.STORE_LOGIN_PARAMETER_KEY);
//        
//        AssertUtil.assertNotNull(id, "ID不能为空");
//        MerchandiseStockState merchandiseStockState = merchandiseStockStateService.get(id);
//        StockState stockState = stockStateService.get(merchandiseStockState.getStockStateId());
//        if (stockState.getToStoreId() != store.getId()) {
//            throw new WarehouseException("非法操作!");
//        }
//        AdminMerchandiseStockStateVO adminMerchandiseStockStateVO = merchandiseStockStateHandler.toVO4Admin(merchandiseStockState);
//        QUnifiedMerchandise qUnifiedMerchandise = commoditycenterClient.getUnifiedMerchandise(adminMerchandiseStockStateVO.getUnifiedMerchandiseId());
//        ModelAndView model = new ModelAndView("/admin/warehouse-MerchandiseStockStateOut-edit");
//        model.addObject("merchandiseStockState", adminMerchandiseStockStateVO);
//        model.addObject("stockState", stockState);
//        model.addObject("qUnifiedMerchandise", qUnifiedMerchandise);
//        model.addObject("store", sellercenterClient.getStore(stockState.getFormStoreId()));
//        return model;
//    }
//
//    /**
//     * 出货单编辑
//     * @param merchandiseStockState
//     * @return
//     */
//    @RequestMapping
//    public AceAjaxView outEdit(MerchandiseStockState merchandiseStockState) {
//
//        MerchandiseStockState mss = merchandiseStockStateService.get(merchandiseStockState.getId());
//        if (mss.getState() == StockStateType.SIGN.getKey()) {
//            throw new WarehouseException("修改失败, 您未拥有修改的权限!");
//        }
//        mss.setNumber(merchandiseStockState.getNumber());
//        merchandiseStockStateService.update(mss);
//        AceAjaxView aceAjaxView = new AceAjaxView();
//        aceAjaxView.setMessage("编辑成功");
//        aceAjaxView.setUrl(DIR + "/outList");
//        return aceAjaxView;
//    }
//
//    /**
//     * 确定货单
//     * @param id
//     * @return
//     */
//    @RequestMapping
//    public AceAjaxView confirm(Long id) {
//
//        AssertUtil.assertNotNull(id, "ID不能为空");
//        MerchandiseStockState merchandiseStockState = merchandiseStockStateService.get(id);
//        merchandiseStockState.setState(StockStateType.CONFIRM.getKey());
//        merchandiseStockState.setConfirmTime(new Date());
//        merchandiseStockStateService.update(merchandiseStockState);
//        AceAjaxView aceAjaxView = new AceAjaxView();
//        aceAjaxView.setMessage("操作成功!");
//        aceAjaxView.setUrl(DIR + "/list");
//        return aceAjaxView;
//    }
//
//    /**
//     * 签收货单
//     * @param id
//     * @return
//     */
//    @RequestMapping
//    public AceAjaxView sign(Long id) {
//
//        AssertUtil.assertNotNull(id, "ID不能为空");
//        MerchandiseStockState merchandiseStockState = merchandiseStockStateService.get(id);
//        merchandiseStockState.setState(StockStateType.SIGN.getKey());
//        merchandiseStockState.setSignTime(new Date());
//        merchandiseStockStateService.update(merchandiseStockState);
//        AceAjaxView aceAjaxView = new AceAjaxView();
//        aceAjaxView.setMessage("操作成功!");
//        aceAjaxView.setUrl(DIR + "/list");
//        return aceAjaxView;
//    }
//}
