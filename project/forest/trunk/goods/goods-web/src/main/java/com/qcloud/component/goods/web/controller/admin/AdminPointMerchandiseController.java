package com.qcloud.component.goods.web.controller.admin;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.qcloud.component.goods.UnifiedMerchandiseType;
import com.qcloud.component.goods.model.UnifiedMerchandise;
import com.qcloud.component.goods.model.key.TypeEnum.QueryItemType;
import com.qcloud.component.goods.model.query.UnifiedMerchandiseQuery;
import com.qcloud.component.goods.service.PointMerchandiseService;
import com.qcloud.component.goods.service.UnifiedMerchandiseService;
import com.qcloud.component.goods.web.form.UnifiedMerchandiseListForm;
import com.qcloud.component.goods.web.handler.UnifiedMerchandiseHandler;
import com.qcloud.component.goods.web.vo.admin.AdminUnifiedMerchandiseVO;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.mvc.AceAjaxView;
import com.qcloud.pirates.mvc.AcePagingView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.web.page.PPage;

@Controller
@RequestMapping(value = "/" + AdminPointMerchandiseController.DIR)
public class AdminPointMerchandiseController {

    public static final String        DIR = "admin/pointMerchandise";

    @Autowired
    private UnifiedMerchandiseService unifiedMerchandiseService;

    @Autowired
    private UnifiedMerchandiseHandler unifiedMerchandiseHandler;

    @Autowired
    private PointMerchandiseService   pointMerchandiseService;

    @RequestMapping
    public AcePagingView list(PPage pPage, UnifiedMerchandiseQuery query) {

        query.setQueryItemType(QueryItemType.S);
        query.setType(UnifiedMerchandiseType.Factory.get(PointMerchandiseService.unifiedMerchandise_type).getKey());
        Page<UnifiedMerchandise> pages = unifiedMerchandiseService.page4Back(query, pPage.getPageStart(), pPage.getPageSize());
        List<UnifiedMerchandise> list = pages.getData();
        List<AdminUnifiedMerchandiseVO> voList = unifiedMerchandiseHandler.toVOList4Admin(list);
        AcePagingView view = new AcePagingView("/admin/goods-PointMerchandise-list", DIR + "/list", pPage.getPageNum(), pPage.getPageSize(), pages.getCount());
        view.addObject("result", voList);
        view.addObject("query", query);
        return view;
    }

    @RequestMapping
    public ModelAndView toAdd(Long activityId) {

        ModelAndView view = new ModelAndView("/admin/goods-PointMerchandise-add");
        view.addObject("activityId", activityId);
        return view;
    }

    @RequestMapping
    public AceAjaxView add(UnifiedMerchandiseListForm form, Long activityId) {

        AssertUtil.assertNotEmpty(form.getMerchandiseList(), "待生成积分商品列表不允许为空.");
        List<UnifiedMerchandise> list = form.getMerchandiseList();
        for (UnifiedMerchandise unifiedMerchandise : list) {
            unifiedMerchandise.setActivityId(activityId);
        }
        pointMerchandiseService.createList(list);
        AceAjaxView view = new AceAjaxView();
        view.setUrl(DIR + "/list?activityId=" + activityId);
        return view;
    }

    @RequestMapping
    public ModelAndView toEdit(Long id) {

        UnifiedMerchandise unifiedMerchandise = unifiedMerchandiseService.get(id);
        AssertUtil.assertNotNull(unifiedMerchandise, "商品不存在.");
        AdminUnifiedMerchandiseVO unifiedMerchandiseVO = unifiedMerchandiseHandler.toVO4Admin(unifiedMerchandise);
        ModelAndView view = new ModelAndView("/admin/goods-PointMerchandise-edit");
        view.addObject("unifiedMerchandise", unifiedMerchandiseVO);
        return view;
    }

    @RequestMapping
    public AceAjaxView edit(Long id, Double discount, Integer integral) {

        UnifiedMerchandise unifiedMerchandise = unifiedMerchandiseService.get(id);
        AssertUtil.assertNotNull(unifiedMerchandise, "商品不存在.");
        unifiedMerchandise.setDiscount(discount);
        unifiedMerchandise.setIntegral((int) integral);
        unifiedMerchandiseService.update(unifiedMerchandise);
        AceAjaxView view = new AceAjaxView();
        return view;
    }
}
