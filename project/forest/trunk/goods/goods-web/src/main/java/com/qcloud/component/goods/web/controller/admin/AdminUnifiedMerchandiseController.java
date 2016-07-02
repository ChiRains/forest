package com.qcloud.component.goods.web.controller.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.qcloud.component.goods.CommoditycenterClient;
import com.qcloud.component.goods.QUnifiedMerchandise;
import com.qcloud.component.goods.model.UnifiedMerchandise;
import com.qcloud.component.goods.model.query.UnifiedMerchandiseQuery;
import com.qcloud.component.goods.service.UnifiedMerchandiseService;
import com.qcloud.component.goods.web.handler.UnifiedMerchandiseHandler;
import com.qcloud.component.goods.web.vo.admin.AdminUnifiedMerchandiseVO;
import com.qcloud.component.sellercenter.QMerchant;
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
@RequestMapping(value = "/" + AdminUnifiedMerchandiseController.DIR)
public class AdminUnifiedMerchandiseController {

    public static final String        DIR = "admin/unifiedMerchandise";

    @Autowired
    private UnifiedMerchandiseService unifiedMerchandiseService;

    @Autowired
    private UnifiedMerchandiseHandler unifiedMerchandiseHandler;

    @Autowired
    private CommoditycenterClient     commoditycenterClient;

    @RequestMapping
    public ModelAndView selectProductList(PPage pPage, HttpServletRequest request, String kwd) {

        QMerchant merchant = PageParameterUtil.getParameterValues(request, SellercenterClient.MERCHANT_LOGIN_PARAMETER_KEY);
        UnifiedMerchandiseQuery query = new UnifiedMerchandiseQuery();
        Map<String, Object> where = new HashMap<String, Object>();
        query.setMerchantId(merchant.getId());
        if (kwd != null && !kwd.trim().equals("")) {
            query.setName(kwd);
        }
        Page<UnifiedMerchandise> page = unifiedMerchandiseService.select(query, pPage.getPageStart(), pPage.getPageSize());
        List<UnifiedMerchandise> list = page.getData();
        List<QUnifiedMerchandise> result = new ArrayList<QUnifiedMerchandise>();
        for (UnifiedMerchandise unifiedMerchandise : list) {
            result.add(commoditycenterClient.getUnifiedMerchandise(unifiedMerchandise.getId()));
        }
        AcePagingView pagingView = new AcePagingView("/admin/goods-UnifiedMerchandise-selectProduct-list", DIR + "/selectProductList.do" + (where.containsKey("kwd_like") ? "?kwd=" + kwd : ""), pPage.getPageNum(), pPage.getPageSize(), page.getCount());
        pagingView.addObject("result", result);
        if (where.containsKey("kwd_like")) {
            pagingView.addObject("kwd", kwd);
        }
        return pagingView;
    }

    @RequestMapping
    @NoReferer
    public ModelAndView list(Integer pageNum, UnifiedMerchandiseQuery query) {

        final int PAGE_SIZE = 10;
        pageNum = RequestUtil.getPageid(pageNum);
        int start = NumberUtil.getPageStart(pageNum, PAGE_SIZE);
        Page<UnifiedMerchandise> page = unifiedMerchandiseService.page(query, start, PAGE_SIZE);
        List<AdminUnifiedMerchandiseVO> list = unifiedMerchandiseHandler.toVOList4Admin(page.getData());
        AcePagingView pagingView = new AcePagingView("/admin/goods-UnifiedMerchandise-list", DIR + "/list", pageNum, PAGE_SIZE, page.getCount());
        pagingView.addObject("result", list);
        return pagingView;
    }

    @RequestMapping
    public ModelAndView toAdd() {

        ModelAndView model = new ModelAndView("/admin/goods-UnifiedMerchandise-add");
        return model;
    }

    @RequestMapping
    public AceAjaxView add(UnifiedMerchandise unifiedMerchandise) {

        unifiedMerchandiseService.add(unifiedMerchandise);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("添加成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    public ModelAndView toEdit(Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        UnifiedMerchandise unifiedMerchandise = unifiedMerchandiseService.get(id);
        AdminUnifiedMerchandiseVO adminUnifiedMerchandiseVO = unifiedMerchandiseHandler.toVO4Admin(unifiedMerchandise);
        ModelAndView model = new ModelAndView("/admin/goods-UnifiedMerchandise-edit");
        model.addObject("unifiedMerchandise", adminUnifiedMerchandiseVO);
        return model;
    }

    @RequestMapping
    public AceAjaxView edit(UnifiedMerchandise unifiedMerchandise) {

        unifiedMerchandiseService.update(unifiedMerchandise);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("编辑成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    public AceAjaxView delete(Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        unifiedMerchandiseService.delete(id);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("删除成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }
}
