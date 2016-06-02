package com.qcloud.component.commoditycenter.web.controller.admin;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.qcloud.component.admin.AdminClient;
import com.qcloud.component.commoditycenter.exception.CommoditycenterException;
import com.qcloud.component.commoditycenter.model.MerchandiseItem;
import com.qcloud.component.commoditycenter.model.MerchandiseVipDiscount;
import com.qcloud.component.commoditycenter.model.MerchandiseVipDiscountHistory;
import com.qcloud.component.commoditycenter.model.key.TypeEnum.VipPaymentType;
import com.qcloud.component.commoditycenter.model.query.MerchandiseVipDiscountQuery;
import com.qcloud.component.commoditycenter.service.MerchandiseItemService;
import com.qcloud.component.commoditycenter.service.MerchandiseVipDiscountHistoryService;
import com.qcloud.component.commoditycenter.service.MerchandiseVipDiscountService;
import com.qcloud.component.commoditycenter.web.form.MerchandiseVipDiscountForm;
import com.qcloud.component.commoditycenter.web.handler.MerchandiseItemHandler;
import com.qcloud.component.commoditycenter.web.handler.MerchandiseVipDiscountHandler;
import com.qcloud.component.commoditycenter.web.handler.MerchandiseVipDiscountHistoryHandler;
import com.qcloud.component.commoditycenter.web.vo.admin.AdminMerchandiseVipDiscountVO;
import com.qcloud.component.sellercenter.QMerchant;
import com.qcloud.component.sellercenter.SellercenterClient;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.mvc.AceAjaxView;
import com.qcloud.pirates.mvc.AcePagingView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.StringUtil;
import com.qcloud.pirates.web.page.PPage;
import com.qcloud.pirates.web.page.PageParameterUtil;
import com.qcloud.pirates.web.page.PiratesParameterKey;
import com.qcloud.pirates.web.security.annotation.NoReferer;

@Controller
@RequestMapping(value = "/" + AdminMerchandiseVipDiscountController.DIR)
public class AdminMerchandiseVipDiscountController {

    public static final String            DIR = "admin/merchandiseVipDiscount";

    @Autowired
    private MerchandiseVipDiscountService merchandiseVipDiscountService;

    @Autowired
    private MerchandiseVipDiscountHandler merchandiseVipDiscountHandler;

    @Autowired
    private MerchandiseItemService        merchandiseItemService;

    /**
     * 大客户商品折扣or价格列表
     * @param request
     * @param pPage
     * @param query
     * @return
     */
    @RequestMapping
    @NoReferer
    public ModelAndView list(HttpServletRequest request, PPage pPage, MerchandiseVipDiscountQuery query) {

        List<AdminMerchandiseVipDiscountVO> list = new ArrayList<AdminMerchandiseVipDiscountVO>();
        AdminMerchandiseVipDiscountVO vo = null;
        // 获取当前登录用户的所属商家id
        QMerchant merchant = PageParameterUtil.getParameterValues(request, SellercenterClient.MERCHANT_LOGIN_PARAMETER_KEY);
        // 查出商品列表
        Map where = new HashMap();
        where.put("merchantId", merchant.getId());
        Page<MerchandiseItem> page = merchandiseItemService.page(where, pPage.getPageStart(), pPage.getPageSize());
        List<MerchandiseItem> merchandiseList = page.getData();
        for (MerchandiseItem merchandiseItem : merchandiseList) {
            vo = new AdminMerchandiseVipDiscountVO();
            vo.setUserId(query.getUserId()); // 客户ID
            vo.setCompanyName(query.getCompanyName()); // 客户名
            vo.setMerchandiseItemId(merchandiseItem.getId()); // 商品ID
            vo.setMerchandiseItemName(merchandiseItem.getName()); // 商品名称
            vo.setMarketDiscount(merchandiseItem.getDiscount());
            // 通过商品id和userId获取商品的折扣信息
            MerchandiseVipDiscount entity = merchandiseVipDiscountService.get(query.getUserId(), merchandiseItem.getId());
            if (entity != null) {
                vo.setDiscount(entity.getDiscount()); // 折扣
                if (entity.getPrice() <= 0) {
                    vo.setPrice(merchandiseItem.getDiscount());
                } else {
                    vo.setPrice(entity.getPrice()); // 价格
                }
                vo.setPaymentType(entity.getPaymentType()); // 支付方式
            } else {
                vo.setPrice(merchandiseItem.getDiscount()); // 价格
            }
            list.add(vo);
        }
        String pageQueryStr = StringUtil.nullToEmpty((String) PageParameterUtil.getParameterValues(request, PiratesParameterKey.PAGE_QUERY_STRING));
        String queryStr = StringUtil.nullToEmpty((String) PageParameterUtil.getParameterValues(request, PiratesParameterKey.QUERY_STRING));
        AcePagingView pagingView = new AcePagingView("/admin/commoditycenter-MerchandiseVipDiscount-add", DIR + "/list?" + pageQueryStr, pPage.getPageNum(), pPage.getPageSize(), page.getCount());
        pagingView.addObject("result", list);
        pagingView.addObject("queryStr", URLEncoder.encode(queryStr));
        pagingView.addObject("query", query);
        return pagingView;
    }

    @RequestMapping
    public ModelAndView toAdd() {

        ModelAndView model = new ModelAndView("/admin/commoditycenter-MerchandiseVipDiscount-add");
        return model;
    }

    /**
     * 新增大客户的 折扣
     * @param merchandiseVipDiscounts 大客户折扣列表
     * @return
     */
    @RequestMapping
    public AceAjaxView addVipDiscounts(MerchandiseVipDiscountForm form) {

        for (MerchandiseVipDiscount merchandiseVipDiscount : form.getMerchandiseVipDiscounts()) {
            if (merchandiseVipDiscount.getDiscount() != 0) {
                merchandiseVipDiscountService.save(merchandiseVipDiscount);
            }
        }
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("添加成功");
        aceAjaxView.setUrl("admin/merchantUser/vipList");
        return aceAjaxView;
    }

    /**
     * 设置大客户商品的 折扣/价格
     * @param merchandiseVipDiscount
     * @return
     */
    @RequestMapping
    public AceAjaxView add(MerchandiseVipDiscountForm form) {

        for (MerchandiseVipDiscount merchandiseVipDiscount : form.getMerchandiseVipDiscounts()) {
            if (merchandiseVipDiscount.getPrice() > 0) {
                merchandiseVipDiscountService.save(merchandiseVipDiscount);
            }
        }
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("添加成功");
        aceAjaxView.setUrl("admin/merchantUser/vipList");
        return aceAjaxView;
    }

    @RequestMapping
    public ModelAndView toEdit(Long id, String queryStr) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        MerchandiseVipDiscount merchandiseVipDiscount = merchandiseVipDiscountService.get(id);
        AdminMerchandiseVipDiscountVO adminMerchandiseVipDiscountVO = merchandiseVipDiscountHandler.toVO4Admin(merchandiseVipDiscount);
        ModelAndView model = new ModelAndView("/admin/commoditycenter-MerchandiseVipDiscount-edit");
        model.addObject("merchandiseVipDiscount", adminMerchandiseVipDiscountVO);
        model.addObject("queryStr", StringUtil.nullToEmpty(queryStr));
        return model;
    }

    @RequestMapping
    public AceAjaxView edit(MerchandiseVipDiscount merchandiseVipDiscount, String queryStr) {

        merchandiseVipDiscountService.update(merchandiseVipDiscount);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("编辑成功");
        aceAjaxView.setUrl(DIR + "/list?" + StringUtil.nullToEmpty(queryStr));
        return aceAjaxView;
    }

    @RequestMapping
    public AceAjaxView delete(Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        merchandiseVipDiscountService.delete(id);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("删除成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }
}
