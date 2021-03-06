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
@RequestMapping(value = "/" + AdminHomepageSalesController.DIR)
public class AdminHomepageSalesController {

    public static final String        DIR = "admin/homepageSales";

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

    @RequestMapping
    public AcePagingView listHomepageSalesClassify(PPage pPage) {

        List<Classify> list = publicdataClient.listClassify((long) ClassifyType.HOMEPAGESALECLASSIFY.getKey());
        int i = (pPage.getPageNum() - 1) * pPage.getPageSize() + pPage.getPageSize();
        if (i > list.size()) {
            i = list.size();
        }
        list.subList((pPage.getPageNum() - 1) * pPage.getPageSize(), i);
        for (Classify classify : list) {
            classify.setImage(fileSDKClient.getFileServerUrl() + classify.getImage());
        }
        AcePagingView acePagingView = new AcePagingView("/admin/forest-HomepageSalesClassify-list", DIR + "/listHomepageSalesClassify", pPage.getPageNum(), pPage.getPageSize(), list.size());
        acePagingView.addObject("HomepageSalesClassify", list);
        return acePagingView;
    }

    @RequestMapping
    public AceAjaxView enableHomepageSalesClassify(Long classifyId, int enable) {

        Classify classify = publicdataClient.getClassify(classifyId);
        classify.setEnable(enable);
        classify.setImage(fileSDKClient.urlToUid(classify.getImage()));
        publicdataClient.update(classify);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("操作成功");
        return aceAjaxView;
    }

    @RequestMapping
    public ModelAndView toAddHomepageSalesClassify() {

        ModelAndView modelAndView = new ModelAndView("/admin/forest-HomepageSalesClassify-add");
        return modelAndView;
    }

    @RequestMapping
    public AceAjaxView addHomepageSalesClassify(Classify classify) {

        classify.setType(ClassifyType.HOMEPAGESALECLASSIFY.getKey());
        publicdataClient.addClassify(classify);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("添加成功");
        return aceAjaxView;
    }

    @RequestMapping
    public ModelAndView toEditHomepageSalesClassify(Long id) {

        Classify classify = publicdataClient.getClassify(id);
        ModelAndView modelAndView = new ModelAndView("/admin/forest-HomepageSalesClassify-edit");
        modelAndView.addObject("classify", classify);
        return modelAndView;
    }

    @RequestMapping
    public AceAjaxView editHomepageSalesClassify(Classify classify) {

        publicdataClient.update(classify);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("修改成功");
        return aceAjaxView;
    }

    @RequestMapping
    public AcePagingView listHomepageSales(PPage pPage, Long classifyId) {

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
        AcePagingView pagingView = new AcePagingView("/admin/forest-HomepageSales-list", DIR + "/listHomepageSales", pPage.getPageNum(), pPage.getPageSize(), page.getCount());
        pagingView.addObject("result", list);
        pagingView.addObject("classifyId", classifyId);
        return pagingView;
    }

    @RequestMapping
    public ModelAndView toAddHomepageSales(Long classifyId) {

        ModelAndView modelAndView = new ModelAndView("/admin/forest-HomepageSales-add");
        modelAndView.addObject("classifyId", classifyId);
        return modelAndView;
    }

    /**
     * 添加商品
     * @param unifiedMerchandiseId
     * @param image
     * @param discount
     * @param integral
     * @param stock
     * @param classifyId
     * @return
     */
    @RequestMapping
    public AceAjaxView addHomepageSales(Long unifiedMerchandiseId, String image, Double discount, Integer integral, Integer stock, Long classifyId) {

        image = fileSDKClient.uidToUrl(image);
        commoditycenterClient.regUnifiedMerchandise(unifiedMerchandiseId, 1, image, discount, integral, stock, classifyId);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("添加成功");
        return aceAjaxView;
    }

    @RequestMapping
    public AceAjaxView deleteHomepageSales(Long id) {

        commoditycenterClient.takeDownByUnifiedMerchandise(id);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("删除成功");
        return aceAjaxView;
    }
}
