package com.qcloud.component.warehouse.web.controller.admin;

import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.qcloud.component.sellercenter.QStore;
import com.qcloud.component.sellercenter.SellercenterClient;
import com.qcloud.component.warehouse.model.MerchandiseStock;
import com.qcloud.component.warehouse.model.query.MerchandiseStockQuery;
import com.qcloud.component.warehouse.service.MerchandiseStockService;
import com.qcloud.component.warehouse.web.handler.MerchandiseStockHandler;
import com.qcloud.component.warehouse.web.vo.admin.AdminMerchandiseStockVO;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.mvc.AceAjaxView;
import com.qcloud.pirates.mvc.AcePagingView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.NumberUtil;
import com.qcloud.pirates.util.RequestUtil;
import com.qcloud.pirates.util.StringUtil;
import com.qcloud.pirates.web.page.PageParameterUtil;
import com.qcloud.pirates.web.security.annotation.NoReferer;

@Controller
@RequestMapping(value = "/" + AdminMerchandiseStockController.DIR)
public class AdminMerchandiseStockController {

    public static final String      DIR = "admin/merchandiseStock";

    @Autowired
    private MerchandiseStockService merchandiseStockService;

    @Autowired
    private MerchandiseStockHandler merchandiseStockHandler;

    // @Autowired
    // private AdminFilterService adminFilterService;
    //
    // @Autowired
    // private TokenClient tokenClient;
    // @Autowired
    // private StoreMemberService storeMemberService;
    @RequestMapping
    @NoReferer
    public ModelAndView list(Integer pageNum, MerchandiseStockQuery query, HttpServletRequest request) {

        // long storeId = getStoreId(getMemberId(request));
        QStore store = PageParameterUtil.getParameterValues(request, SellercenterClient.STORE_LOGIN_PARAMETER_KEY);
        final int PAGE_SIZE = 10;
        pageNum = RequestUtil.getPageid(pageNum);
        int start = NumberUtil.getPageStart(pageNum, PAGE_SIZE);
        HashMap where = new HashMap();
        where.put("storeId", store.getId());
        where.put("merchandiseName", query.getMerchandiseName());
        Page<MerchandiseStock> page = merchandiseStockService.page(where, start, PAGE_SIZE);
        List<AdminMerchandiseStockVO> list = merchandiseStockHandler.toVOList4Admin(page.getData());
        String param = "merchandiseName=" + StringUtil.nullToEmpty(query.getMerchandiseName());
        AcePagingView pagingView = new AcePagingView("/admin/warehouse-MerchandiseStock-list", DIR + "/list?" + param, pageNum, PAGE_SIZE, page.getCount());
        pagingView.addObject("result", list);
        pagingView.addObject("query", query);
        return pagingView;
    }

    @RequestMapping
    public ModelAndView edit(Long id) {

        ModelAndView modelAndView = new ModelAndView("/admin/warehouse-MerchandiseStock-edit");
        if (id > 0) {
            MerchandiseStock merchandiseStock = merchandiseStockService.get(id);
            if (merchandiseStock != null) {
                modelAndView.addObject("merchandiseStock", merchandiseStockService.get(id));
            } else {
                modelAndView = new ModelAndView("merchandiseStock/list");
            }
        }
        return modelAndView;
    }

    @RequestMapping
    public AceAjaxView update(MerchandiseStock merchandiseStock, HttpServletRequest request) {

        // long memberId = getMemberId(request);
        // long storeId = getStoreId(memberId);
        // long merchantId = getMerchantId(memberId);
        QStore store = PageParameterUtil.getParameterValues(request, SellercenterClient.STORE_LOGIN_PARAMETER_KEY);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("错误");
        if (merchandiseStock.getId() > 0) {
            MerchandiseStock data = merchandiseStockService.get(merchandiseStock.getId());
            if (data != null) {
                if (merchandiseStockService.addStock(data.getId(), merchandiseStock.getStock())) {
                    aceAjaxView.setMessage("更新成功");
                }
            }
        } else {
            HashMap where = new HashMap();
            where.put("unifiedMerchandiseId", merchandiseStock.getUnifiedMerchandiseId());
            where.put("storeId", store.getId());
            where.put("merchantId", store.getMerchant().getId());
            if (merchandiseStockService.get(where) != null) {
                AssertUtil.assertTrue(false, "已存在相同商品！");
            }
            merchandiseStock.setStoreId(store.getId());
            merchandiseStock.setMerchantId(store.getMerchant().getId());
            merchandiseStockService.add(merchandiseStock);
            if (merchandiseStock.getId() > 0) {
                aceAjaxView.setMessage("新增成功");
            }
        }
        return aceAjaxView;
    }

    @RequestMapping
    public AceAjaxView delete(Long id, HttpServletRequest request) {

        // long memberId = getMemberId(request);
        // long storeId = getStoreId(memberId);
        // long merchantId = getMerchantId(memberId);
        // QStore store = PageParameterUtil.getParameterValues(request, SellercenterClient.STORE_LOGIN_PARAMETER_KEY);
        AssertUtil.assertNotNull(id, "ID不能为空");
        AceAjaxView aceAjaxView = new AceAjaxView();
        // HashMap where = new HashMap();
        // where.put("merchantId", store.getMerchantId());
        // where.put("storeId", store.getId());
        // 删除条件
        if (merchandiseStockService.delete(id)) {
            aceAjaxView.setMessage("删除成功");
        } else {
            aceAjaxView.setMessage("删除失败");
            aceAjaxView.setStatus(0);
        }
        return aceAjaxView;
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
    // // private long getMerchantId(long memberId) {
    // // long merchantId = 0;
    // // List<QMerchant> qMerchants = sellercenterClient.listMerchant(memberId);
    // // if (qMerchants.size() > 0) {
    // // merchantId = qMerchants.get(0).getId();
    // // } else {
    // // AssertUtil.assertTrue(false, "获取商家ID失败");
    // // }
    // // return merchantId;
    // // }
    // private long getStoreId(Long memberId) {
    //
    // long storeId = 0;
    // HashMap where = new HashMap();
    // where.put("memberId", memberId);
    // StoreMember storeMember = storeMemberService.get(where);
    // if (storeMember != null) {
    // storeId = storeMember.getStoreId();
    // } else {
    // AssertUtil.assertTrue(false, "获取门店ID失败");
    // }
    // return storeId;
    // }
    //
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
}
