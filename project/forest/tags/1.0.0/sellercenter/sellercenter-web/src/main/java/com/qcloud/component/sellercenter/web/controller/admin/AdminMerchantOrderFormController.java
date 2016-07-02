package com.qcloud.component.sellercenter.web.controller.admin;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.qcloud.component.my.InvoiceType;
import com.qcloud.component.my.NeedInvoiceType;
import com.qcloud.component.orderform.OrderformClient;
import com.qcloud.component.orderform.QOrder;
import com.qcloud.component.publicdata.KeyValueVO;
import com.qcloud.component.publicdata.util.TreeUtils;
import com.qcloud.component.publicdata.util.TreeUtils.TreeModel;
import com.qcloud.component.sellercenter.QMerchant;
import com.qcloud.component.sellercenter.QStore;
import com.qcloud.component.sellercenter.SellercenterClient;
import com.qcloud.component.sellercenter.model.MerchantOrderForm;
import com.qcloud.component.sellercenter.model.Store;
import com.qcloud.component.sellercenter.model.key.TypeEnum.MerchantOrderStateType;
import com.qcloud.component.sellercenter.model.query.MerchantOrderFormQuery;
import com.qcloud.component.sellercenter.service.MerchantOrderFormService;
import com.qcloud.component.sellercenter.service.StoreService;
import com.qcloud.component.sellercenter.web.handler.MerchantOrderFormHandler;
import com.qcloud.component.sellercenter.web.handler.StoreHandler;
import com.qcloud.component.sellercenter.web.vo.admin.AdminMerchantOrderFormVO;
import com.qcloud.component.sellercenter.web.vo.admin.AdminStoreVO;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.mvc.AceAjaxView;
import com.qcloud.pirates.mvc.AcePagingView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.NumberUtil;
import com.qcloud.pirates.util.RequestUtil;
import com.qcloud.pirates.web.page.PageParameterUtil;
import com.qcloud.pirates.web.security.annotation.NoReferer;

@Controller
@RequestMapping(value = "/" + AdminMerchantOrderFormController.DIR)
public class AdminMerchantOrderFormController {

    public static final String       DIR = "admin/merchantOrderForm";

    @Autowired
    private MerchantOrderFormService merchantOrderFormService;

    @Autowired
    private MerchantOrderFormHandler merchantOrderFormHandler;

    @Autowired
    private StoreService             storeService;

    @Autowired
    private StoreHandler             storeHandler;

    @Autowired(required = false)
    private OrderformClient          orderformClient;

    // @Autowired
    // private AdminFilterService adminFilterService;
    //
    // @Autowired
    // private TokenClient tokenClient;
    // @Autowired
    // private StoreMemberService storeMemberService;
    //
    // @Autowired
    // private StoreMemberHandler storeMemberHandler;
    // @Autowired
    // private MerchantOrderFormGetter merchantOrderFormGetter;
    @RequestMapping
    @NoReferer
    public ModelAndView list(HttpServletRequest request, Integer pageNum, MerchantOrderFormQuery query) {

        // 门店才能进行订单管理
        // long memberId = getMemberId(request);
        // long storeId = getStoreId(memberId);
        QStore store = PageParameterUtil.getParameterValues(request, SellercenterClient.STORE_LOGIN_PARAMETER_KEY);
        final int PAGE_SIZE = 10;
        pageNum = RequestUtil.getPageid(pageNum);
        int start = NumberUtil.getPageStart(pageNum, PAGE_SIZE);
        // Store store = storeService.get(storeId);
        // AssertUtil.assertNotNull(store, "门店不存在." + storeId);
        long merchantId = store.getMerchantId();
        pageNum = RequestUtil.getPageid(pageNum);
        Page<MerchantOrderForm> page = new Page<MerchantOrderForm>();
        if (StringUtils.isNotEmpty(query.getOrderNumber())) {
            AssertUtil.assertTrue(query.getOrderNumber().length() >= 12, "请输入正确的总单号.");
            AssertUtil.assertNotNull(orderformClient, "订单接口尚未实例化.");
            QOrder order = orderformClient.getOrder(query.getOrderNumber());
            MerchantOrderForm merchantOrderForm = merchantOrderFormService.get(order.getId(), merchantId, store.getId());
            List<MerchantOrderForm> mList = new ArrayList<MerchantOrderForm>();
            mList.add(merchantOrderForm);
            page.setData(mList);
            page.setCount(mList.size());
        } else {
            page = merchantOrderFormService.page(query, merchantId, store.getId(), start, PAGE_SIZE);
        }
        List<AdminMerchantOrderFormVO> list = merchantOrderFormHandler.toVOList4Admin(page.getData());
        String param = "state=" + query.getState();
        AcePagingView pagingView = new AcePagingView("/admin/sellercenter-MerchantOrderForm-list", DIR + "/list?" + param, pageNum, PAGE_SIZE, page.getCount());
        pagingView.addObject("result", list);
        pagingView.addObject("stateType", MerchantOrderStateType.values());
        pagingView.addObject("shipType", MerchantOrderStateType.SHIP.getKey());
        // pagingView.addObject("confirmType", MerchantOrderStateType.CONFIRM.getKey());
        pagingView.addObject("query", query);
        pagingView.addObject("needInvoiceType", NeedInvoiceType.values());
        pagingView.addObject("invoiceType", InvoiceType.values());
        // pagingView.addObject("storeKVList", getStoreInfo(request));
        return pagingView;
    }

    @RequestMapping
    @NoReferer
    public ModelAndView listStore(HttpServletRequest request, Integer pageNum, MerchantOrderFormQuery query) {

        // long memberId = getMemberId(request);
        // long storeId = getStoreId(memberId);
        QStore store = PageParameterUtil.getParameterValues(request, SellercenterClient.STORE_LOGIN_PARAMETER_KEY);
        final int PAGE_SIZE = 10;
        pageNum = RequestUtil.getPageid(pageNum);
        int start = NumberUtil.getPageStart(pageNum, PAGE_SIZE);
        // Store store = storeService.get(storeId);
        // AssertUtil.assertNotNull(store, "门店不存在." + storeId);
        Page<MerchantOrderForm> page = merchantOrderFormService.page(query, store.getMerchantId(), store.getId(), start, PAGE_SIZE);
        List<AdminMerchantOrderFormVO> list = merchantOrderFormHandler.toVOList4Admin(page.getData());
        String param = "state=" + query.getState();
        AcePagingView pagingView = new AcePagingView("/admin/sellercenter-MerchantOrderForm-listStore", DIR + "/listStore?" + param, pageNum, PAGE_SIZE, page.getCount());
        pagingView.addObject("result", list);
        pagingView.addObject("stateType", MerchantOrderStateType.values());
        pagingView.addObject("shipType", MerchantOrderStateType.SHIP.getKey());
        // pagingView.addObject("confirmType", MerchantOrderStateType.CONFIRM.getKey());
        pagingView.addObject("query", query);
        List<Store> storeList = storeService.listByMerchant(store.getMerchantId());
        List<TreeModel> treeModelList = new ArrayList<TreeModel>();
        for (Store s : storeList) {
            treeModelList.add(s);
        }
        List<KeyValueVO> sList = TreeUtils.exchangeObj(treeModelList, -1L, "");
        pagingView.addObject("storeKVList", sList);
        pagingView.addObject("needInvoiceType", NeedInvoiceType.values());
        return pagingView;
    }

    @RequestMapping
    public ModelAndView toAdd() {

        ModelAndView model = new ModelAndView("/admin/sellercenter-MerchantOrderForm-add");
        return model;
    }

    @RequestMapping
    public AceAjaxView add(MerchantOrderForm merchantOrderForm) {

        merchantOrderFormService.add(merchantOrderForm);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("添加成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    public ModelAndView toEdit(Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        MerchantOrderForm merchantOrderForm = merchantOrderFormService.get(id);
        AdminMerchantOrderFormVO adminMerchantOrderFormVO = merchantOrderFormHandler.toVO4Admin(merchantOrderForm);
        ModelAndView model = new ModelAndView("/admin/sellercenter-MerchantOrderForm-edit");
        model.addObject("merchantOrderForm", adminMerchantOrderFormVO);
        return model;
    }

    @RequestMapping
    public ModelAndView toEditStore(Long subOrderId, Date orderDate, HttpServletRequest request) {

        List<KeyValueVO> storeKVList = getStoreInfo(request);
        ModelAndView model = new ModelAndView("/admin/sellercenter-MerchantOrderForm-editStore");
        model.addObject("storeKVList", storeKVList);
        model.addObject("subOrderId", subOrderId);
        model.addObject("orderDate", orderDate);
        return model;
    }

    @RequestMapping
    public ModelAndView toAddLogistics() {

        ModelAndView model = new ModelAndView("/admin/sellercenter-MerchantOrderForm-addLogistics");
        return model;
    }

    @RequestMapping
    public AceAjaxView edit(MerchantOrderForm merchantOrderForm) {

        merchantOrderFormService.update(merchantOrderForm);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("编辑成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    public AceAjaxView delete(Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        merchantOrderFormService.delete(id);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("删除成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    private List<KeyValueVO> getStoreInfo(HttpServletRequest request) {

        // List<QMerchant> merchantList = sellercenterClient.listMerchant(memberId);
        // Long merchantId = null;
        // if (!CollectionUtils.isEmpty(merchantList) && merchantList.size() == 1) {
        // merchantId = merchantList.get(0).getId();
        // }
        // AssertUtil.assertNotNull(merchantId, "您尚未属于一家商家.");
        // long memberId = getMemberId(request);
        // long storeId = getStoreId(memberId);
        QStore store = PageParameterUtil.getParameterValues(request, SellercenterClient.STORE_LOGIN_PARAMETER_KEY);
        // Store s = storeService.get(storeId);
        // AssertUtil.assertNotNull(s, "门店不存在." + storeId);
        List<Store> storeList = storeService.listByMerchant(store.getMerchantId());
        List<TreeModel> treeModelList = new ArrayList<TreeModel>();
        for (Store s : storeList) {
            treeModelList.add(s);
        }
        return TreeUtils.exchangeObj(treeModelList, -1L, "");
    }

    // private long getMemberId(HttpServletRequest request) {
    //
    // String tokenId = adminFilterService.getTokenId(request);
    // AssertUtil.assertNotEmpty(tokenId, "获取用户登录信息失败.");
    // String idStr = tokenClient.get(tokenId);
    // AssertUtil.assertNotEmpty(idStr, "获取用户标识失败.");
    // long memberId = Long.parseLong(idStr);
    // return memberId;
    // }
    //
    // private long getStoreId(Long memberId) {
    //
    // long storeId = 0;
    // HashMap where = new HashMap();
    // where.put("memberId", memberId);
    // StoreMember storeMember = storeMemberService.get(where);
    // if (storeMember != null) {
    // storeId = storeMember.getStoreId();
    // }
    // return storeId;
    // }
    // private long getMerchantId(long memberId) {
    //
    // long merchantId = 0;
    // HashMap where = new HashMap();
    // where.put("memberId", memberId);
    // StoreMember storeMember = storeMemberService.get(where);
    // if (storeMember != null) {
    // merchantId = storeMember.getMerchantId();
    // } else {
    // AssertUtil.assertTrue(false, "获取商家ID失败");
    // }
    // return merchantId;
    // }
    @RequestMapping
    public ModelAndView merchantReportForm(HttpServletRequest request, Long type, Long value) {

        double sum = 0.0;
        QMerchant merchant = PageParameterUtil.getParameterValues(request, SellercenterClient.MERCHANT_LOGIN_PARAMETER_KEY);
        Map<String, String> map = getTime(type, value);
        List<MerchantOrderForm> merchantOrderForms = merchantOrderFormService.reportForm4merchant(merchant.getId(), map.get("startDate"), map.get("endDate"));
        List<AdminMerchantOrderFormVO> voList = merchantOrderFormHandler.toVOList4Admin(merchantOrderForms);
        for (AdminMerchantOrderFormVO vo : voList) {
            sum = vo.getSum() + sum;
        }
        ModelAndView view = new ModelAndView("/admin/sellercenter-MerchantOrderForm-merchantReportForm");
        view.addObject("result", voList);
        view.addObject("sum", sum == 0 ? 0 : new DecimalFormat("#.00").format(sum));
        return view;
    }

    @RequestMapping
    public ModelAndView storeReportForm(HttpServletRequest request, Long type, Long value) {

        Map<String, String> map = getTime(type, value);
        QMerchant merchant = PageParameterUtil.getParameterValues(request, SellercenterClient.MERCHANT_LOGIN_PARAMETER_KEY);
        HashMap<String, Object> where = new HashMap<String, Object>();
        where.put("merchantId", merchant.getId());
        List<Store> storeList = storeService.listByMerchant(merchant.getId());
        List<AdminStoreVO> storeVOList = storeHandler.toVOList4Admin(storeList);
        for (AdminStoreVO storeVO : storeVOList) {
            double sum = 0.0;
            List<AdminMerchantOrderFormVO> merchantOrderFormVOList = merchantOrderFormHandler.toVOList4Admin(merchantOrderFormService.reportForm4store(merchant.getId(), storeVO.getId(), map.get("startDate"), map.get("endDate")));
            for (AdminMerchantOrderFormVO adminMerchantOrderFormVO : merchantOrderFormVOList) {
                sum += adminMerchantOrderFormVO.getSum();
            }
            if (sum == 0) {
                storeVO.setSum(0);
            } else {
                sum = Double.valueOf(new DecimalFormat("#.00").format(sum));
                storeVO.setSum(sum);
            }
            storeVO.setMerchantOrderFormVOs(merchantOrderFormVOList);
        }
        ModelAndView view = new ModelAndView("/admin/sellercenter-MerchantOrderForm-storeReportForm");
        view.addObject("result", storeVOList);
        return view;
    }

    public Map<String, String> getTime(Long type, Long valueLong) {

        int value = valueLong.intValue();
        String startDate = "";
        String endDate = "";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        if (type == 1) {
            Calendar calendar = Calendar.getInstance();
            String year = String.valueOf(calendar.get(Calendar.YEAR));
            String month = "-" + String.valueOf(value < 10 ? "0" + value : value);
            String firstDay = "-01";
            startDate = year + month + firstDay;//
            String endDay = "";
            if (value == 1 || value == 3 || value == 5 || value == 7 || value == 8 || value == 10 || value == 10) {
                endDay = "-31";
            } else if (value == 2) {
                endDay = "-28";
            } else {
                endDay = "-31";
            }
            endDate = year + month + endDay; //
        } else if (type == 2) {
            Calendar calendar = Calendar.getInstance();
            String year = String.valueOf(calendar.get(Calendar.YEAR));
            switch (value) {
            case 1:
                startDate = year + "-01-01";
                endDate = year + "-03-31";
                break;
            case 2:
                startDate = year + "-04-01";
                endDate = year + "-06-30";
                break;
            case 3:
                startDate = year + "-07-01";
                endDate = year + "-09-30";
                break;
            case 4:
                startDate = year + "-10-01";
                endDate = year + "-12-31";
                break;
            default:
                startDate = year + "-01-01";
                endDate = year + "-03-31";
                break;
            }
        } else if (type == 3) {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            switch (value) {
            case 1:
                startDate = (year - 1) + "-01-01";
                endDate = (year - 1) + "-12-31";
                break;
            case 2:
                startDate = year + "-01-01";
                endDate = year + "-12-31";
                break;
            default:
                startDate = year + "-01-01";
                endDate = year + "-03-31";
                break;
            }
        } else {// 默认查当月
            // 获取前月的第一天
            Calendar cal_1 = Calendar.getInstance();
            cal_1.add(Calendar.MONTH, 0);
            cal_1.set(Calendar.DAY_OF_MONTH, 1);
            startDate = format.format(cal_1.getTime());
            // 获取前月的最后一天
            Calendar cale = Calendar.getInstance();
            cale.add(Calendar.MONTH, 1);
            cale.set(Calendar.DAY_OF_MONTH, 0);
            endDate = format.format(cale.getTime());
        }
        Map<String, String> map = new HashMap<String, String>();
        map.put("startDate", startDate);
        map.put("endDate", endDate);
        return map;
    }
}
