package com.qcloud.project.forest.web.controller.admin;

import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.qcloud.component.filesdk.FileSDKClient;
import com.qcloud.component.goods.CommoditycenterClient;
import com.qcloud.component.goods.model.UnifiedMerchandise;
import com.qcloud.component.goods.model.key.TypeEnum.QueryItemType;
import com.qcloud.component.goods.model.query.UnifiedMerchandiseQuery;
import com.qcloud.component.goods.service.UnifiedMerchandiseService;
import com.qcloud.component.publicdata.PublicdataClient;
import com.qcloud.component.publicdata.model.Classify;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.mvc.AceAjaxView;
import com.qcloud.pirates.mvc.AcePagingView;
import com.qcloud.pirates.web.page.PPage;
import com.qcloud.project.forest.model.key.TypeEnum.ClassifyType;
import com.qcloud.project.forest.web.handler.PromotionalOffersHandler;
import com.qcloud.project.forest.web.vo.PromotionalOffersVO;

@Controller
@RequestMapping(value = "/" + AdminBrandSalesController.DIR)
public class AdminBrandSalesController {

    public static final String        DIR      = "admin/brandSales";

    public static final int           BRAND    = 1;                 // 品牌

    public static final int           CLASSIFY = 2;                 // 类别

    @Autowired
    private PublicdataClient          publicdataClient;

    @Autowired
    private FileSDKClient             fileSDKClient;

    @Autowired
    private UnifiedMerchandiseService unifiedMerchandiseService;

    @Autowired
    private PromotionalOffersHandler  promotionalOffersHandler;

    @Autowired
    private CommoditycenterClient     commoditycenterClient;

    /**
     * 品牌特卖品牌列表
     * @param pPage
     * @return
     */
    @RequestMapping
    public AcePagingView listBrand(PPage pPage) {

        List<Classify> list = publicdataClient.listClassify((long) ClassifyType.BRANDONSALEBRAND.getKey(), true);
        int i = (pPage.getPageNum() - 1) * pPage.getPageSize() + pPage.getPageSize();
        if (i > list.size()) {
            i = list.size();
        }
        list.subList((pPage.getPageNum() - 1) * pPage.getPageSize(), i);
        for (Classify classify : list) {
            classify.setImage(fileSDKClient.getFileServerUrl() + classify.getImage());
        }
        AcePagingView acePagingView = new AcePagingView("/admin/forest-BrandSales-listBrand", DIR + "/listBrand", pPage.getPageNum(), pPage.getPageSize(), list.size());
        acePagingView.addObject("brands", list);
        return acePagingView;
    }

    /**
     * 品牌特卖类别列表
     * @param pPage
     * @return
     */
    @RequestMapping
    public AcePagingView listClassify(PPage pPage) {

        List<Classify> list = publicdataClient.listClassify((long) ClassifyType.BRANDONSALECLASSIFY.getKey(), true);
        pPage.setPageSize(10);
        int i = (pPage.getPageNum() - 1) * pPage.getPageSize() + pPage.getPageSize();
        if (i > list.size()) {
            i = list.size();
        }
        list.subList((pPage.getPageNum() - 1) * pPage.getPageSize(), i);
        for (Classify classify : list) {
            classify.setImage(fileSDKClient.getFileServerUrl() + classify.getImage());
        }
        AcePagingView acePagingView = new AcePagingView("/admin/forest-BrandSales-listClassify", DIR + "/listClassify", pPage.getPageNum(), pPage.getPageSize(), list.size());
        acePagingView.addObject("brands", list);
        return acePagingView;
    }

    /**
     * 是否启用品牌或类别
     * @param classifyId
     * @param enable
     * @return
     */
    @RequestMapping
    public AceAjaxView enable(Long classifyId, int enable) {

        Classify classify = publicdataClient.getClassify(classifyId);
        classify.setEnable(enable);
        classify.setImage(fileSDKClient.urlToUid(classify.getImage()));
        publicdataClient.update(classify);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("操作成功");
        return aceAjaxView;
    }

    /**
     * 添加品牌或类别
     * @param type
     * @return
     */
    @RequestMapping
    public ModelAndView toAdd(int type) {

        String jsp = "";
        if (type == BRAND) {
            jsp = "/admin/forest-BrandSales-addBrand";
        } else if (type == CLASSIFY) {
            jsp = "/admin/forest-BrandSales-addClassify";
        }
        ModelAndView modelAndView = new ModelAndView(jsp);
        return modelAndView;
    }

    /**
     * 提交添加品牌或类别
     * @param classify
     * @param type
     * @return
     */
    @RequestMapping
    public AceAjaxView add(Classify classify, int type) {

        long classifyType = 0;
        if (type == BRAND) {
            classifyType = ClassifyType.BRANDONSALEBRAND.getKey();
        } else if (type == CLASSIFY) {
            classifyType = ClassifyType.BRANDONSALECLASSIFY.getKey();
        }
        classify.setType(classifyType);
        publicdataClient.addClassify(classify);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("添加成功");
        return aceAjaxView;
    }

    /**
     * 编辑品牌或类别
     * @param id
     * @return
     */
    @RequestMapping
    public ModelAndView toEdit(Long id, Integer type) {

        String jsp = "";
        if (type == BRAND) {
            jsp = "/admin/forest-BrandSales-editBrand";
        } else if (type == CLASSIFY) {
            jsp = "/admin/forest-BrandSales-editClassify";
        }
        Classify classify = publicdataClient.getClassify(id);
        classify.setImage(classify.getImage());
        ModelAndView modelAndView = new ModelAndView(jsp);
        modelAndView.addObject("classify", classify);
        modelAndView.addObject("uid", classify.getImage());
        return modelAndView;
    }

    /**
     * 提交编辑品牌或类别
     * @param classify
     * @return
     */
    @RequestMapping
    public AceAjaxView edit(Classify classify, String uid) {

        if (classify.getImage() != null) {
            if (classify.getImage().equals(uid)) {
                classify.setImage(fileSDKClient.urlToUid(classify.getImage()));
            }
        }
        publicdataClient.update(classify);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("修改成功");
        return aceAjaxView;
    }

    /**
     * 展示品牌特卖的商品
     * @param pPage
     * @param classifyId
     * @return
     */
    @RequestMapping
    public AcePagingView listMerchandise(PPage pPage, Long classifyId) {

        UnifiedMerchandiseQuery unifiedMerchandiseQuery = new UnifiedMerchandiseQuery();
        unifiedMerchandiseQuery.setActivityId(classifyId);
        unifiedMerchandiseQuery.setQueryItemType(QueryItemType.S);
        Page<UnifiedMerchandise> page = unifiedMerchandiseService.page(unifiedMerchandiseQuery, pPage.getPageStart(), pPage.getPageSize());
        Iterator<UnifiedMerchandise> iter = page.getData().iterator();
        while (iter.hasNext()) {
            UnifiedMerchandise s = iter.next();
            if (s.getState() == 5) {
                iter.remove();
            }
        }
        List<PromotionalOffersVO> list = promotionalOffersHandler.toVOList(page.getData());
        AcePagingView pagingView = new AcePagingView("/admin/forest-BrandSales-list", DIR + "/listMerchandise", pPage.getPageNum(), pPage.getPageSize(), page.getCount());
        pagingView.addObject("result", list);
        pagingView.addObject("classifyId", classifyId);
        return pagingView;
    }

    /**
     * 添加品牌特卖的商品
     * @param classifyId
     * @return
     */
    @RequestMapping
    public ModelAndView toAddMerchandise(Long classifyId) {

        ModelAndView modelAndView = new ModelAndView("/admin/forest-BrandSales-add");
        modelAndView.addObject("classifyId", classifyId);
        return modelAndView;
    }

    /**
     * 提交添加品牌特卖的商品
     * @param unifiedMerchandiseId
     * @param image
     * @param discount
     * @param integral
     * @param stock
     * @param classifyId
     * @return
     */
    @RequestMapping
    public AceAjaxView addMerchandise(Long unifiedMerchandiseId, String image, Double discount, Integer integral, Integer stock, Long classifyId) {

        image = fileSDKClient.uidToUrl(image);
        commoditycenterClient.regUnifiedMerchandise(unifiedMerchandiseId, 1, image, discount, integral, stock, classifyId);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("添加成功");
        return aceAjaxView;
    }

    /**
     * 删除品牌特卖的商品
     * @param id
     * @return
     */
    @RequestMapping
    public AceAjaxView deleteMerchandise(Long id) {

        commoditycenterClient.takeDownByUnifiedMerchandise(id);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("删除成功");
        return aceAjaxView;
    }
}
