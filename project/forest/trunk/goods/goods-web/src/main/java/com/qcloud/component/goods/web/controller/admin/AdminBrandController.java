package com.qcloud.component.goods.web.controller.admin;

import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.qcloud.component.filesdk.FileSDKClient;
import com.qcloud.component.goods.CommoditycenterClient;
import com.qcloud.component.goods.model.UnifiedMerchandise;
import com.qcloud.component.goods.model.key.TypeEnum.BrandType;
import com.qcloud.component.goods.model.key.TypeEnum.QueryItemType;
import com.qcloud.component.goods.model.query.UnifiedMerchandiseQuery;
import com.qcloud.component.goods.service.UnifiedMerchandiseService;
import com.qcloud.component.goods.web.handler.UnifiedMerchandiseHandler;
import com.qcloud.component.goods.web.vo.admin.AdminUnifiedMerchandiseVO;
import com.qcloud.component.publicdata.PublicdataClient;
import com.qcloud.component.publicdata.QClassify;
import com.qcloud.component.publicdata.model.Classify;
import com.qcloud.component.publicdata.web.handler.ClassifyHandler;
import com.qcloud.component.publicdata.web.vo.admin.AdminClassifyVO;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.mvc.AceAjaxView;
import com.qcloud.pirates.mvc.AcePagingView;
import com.qcloud.pirates.web.page.PPage;

@Controller
@RequestMapping(value = "/" + AdminBrandController.DIR)
public class AdminBrandController {

    public static final String        DIR = "admin/brand";

    @Autowired
    private PublicdataClient          publicdataClient;

    @Autowired
    private ClassifyHandler           classifyHandler;

    @Autowired
    private FileSDKClient             fileSDKClient;

    @Autowired
    private UnifiedMerchandiseService unifiedMerchandiseService;

    @Autowired
    private CommoditycenterClient     commoditycenterClient;

    @Autowired
    private UnifiedMerchandiseHandler unifiedMerchandiseHandler;

    @RequestMapping
    public ModelAndView list() {

        List<QClassify> classifyList = publicdataClient.listClassifyForTree((long) BrandType.Brand.getKey(), true);
        ModelAndView view = new ModelAndView("/admin/goods-Brand-list");
        view.addObject("result", classifyList);
        view.addObject("type", BrandType.Brand.getKey());
        return view;
    }

    @RequestMapping
    public ModelAndView toAdd() {

        ModelAndView view = new ModelAndView("/admin/goods-Brand-add");
        view.addObject("type", BrandType.Brand.getKey());
        return view;
    }

    @RequestMapping
    public AceAjaxView add(Classify classify) {

        publicdataClient.addClassify(classify);
        AceAjaxView view = new AceAjaxView();
        view.setMessage("添加品牌成功.");
        return view;
    }

    @RequestMapping
    public ModelAndView toEdit(Long id) {

        Classify classify = publicdataClient.getClassify(id);
        AdminClassifyVO vo = classifyHandler.toVO4Admin(classify);
        ModelAndView view = new ModelAndView("/admin/goods-Brand-edit");
        view.addObject("classify", vo);
        return view;
    }

    @RequestMapping
    public AceAjaxView edit(Classify classify) {

        publicdataClient.update(classify);
        AceAjaxView view = new AceAjaxView();
        view.setMessage("修改品牌成功.");
        return view;
    }

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
        List<AdminUnifiedMerchandiseVO> list = unifiedMerchandiseHandler.toVOList4Admin(page.getData());
        AcePagingView pagingView = new AcePagingView("/admin/goods-Brand-listMerchandise", DIR + "/listMerchandise", pPage.getPageNum(), pPage.getPageSize(), page.getCount());
        pagingView.addObject("result", list);
        pagingView.addObject("classifyId", classifyId);
        return pagingView;
    }

    @RequestMapping
    public ModelAndView toAddMerchandise(Long classifyId) {

        ModelAndView modelAndView = new ModelAndView("/admin/goods-Brand-addMerchandise");
        modelAndView.addObject("classifyId", classifyId);
        return modelAndView;
    }

    @RequestMapping
    public AceAjaxView addMerchandise(Long unifiedMerchandiseId, String image, Double discount, Integer integral, Integer stock, Long classifyId) {

        image = fileSDKClient.uidToUrl(image);
        commoditycenterClient.regUnifiedMerchandise(unifiedMerchandiseId, 1, image, discount, integral, stock, classifyId);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("添加成功");
        return aceAjaxView;
    }

    @RequestMapping
    public AceAjaxView deleteMerchandise(Long id) {

        commoditycenterClient.takeDownByUnifiedMerchandise(id);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("删除成功");
        return aceAjaxView;
    }
}
