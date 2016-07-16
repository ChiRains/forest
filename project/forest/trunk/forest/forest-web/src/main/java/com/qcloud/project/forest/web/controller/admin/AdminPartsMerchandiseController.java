package com.qcloud.project.forest.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import com.qcloud.component.goods.CommoditycenterClient;
import com.qcloud.component.goods.QMerchandise;
import com.qcloud.component.publicdata.PublicdataClient;
import com.qcloud.component.publicdata.QClassify;
import com.qcloud.component.publicdata.model.Classify;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.mvc.AceAjaxView;
import com.qcloud.pirates.mvc.AcePagingView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.StringUtil;
import com.qcloud.pirates.web.page.PPage;
import com.qcloud.pirates.web.page.PageParameterUtil;
import com.qcloud.pirates.web.page.PiratesParameterKey;
import com.qcloud.pirates.web.security.annotation.NoReferer;
import com.qcloud.project.forest.model.PartsMerchandise;
import com.qcloud.project.forest.service.PartsMerchandiseService;
import com.qcloud.project.forest.web.handler.PartsMerchandiseHandler;
import com.qcloud.project.forest.model.key.TypeConstant;
import com.qcloud.project.forest.model.query.PartsMerchandiseQuery;
import com.qcloud.project.forest.web.vo.admin.AdminPartsMerchandiseVO;

@Controller
@RequestMapping(value = "/" + AdminPartsMerchandiseController.DIR)
public class AdminPartsMerchandiseController {

    public static final String      DIR = "admin/partsMerchandise";

    @Autowired
    private PartsMerchandiseService partsMerchandiseService;

    @Autowired
    private PartsMerchandiseHandler partsMerchandiseHandler;

    @Autowired
    private PublicdataClient        publicdataClient;

    @Autowired
    private CommoditycenterClient   commoditycenterClient;

    @RequestMapping
    @NoReferer
    public ModelAndView list(HttpServletRequest request, PPage pPage, PartsMerchandiseQuery query) {

        Page<PartsMerchandise> page = partsMerchandiseService.page(query, pPage.getPageStart(), pPage.getPageSize());
        List<AdminPartsMerchandiseVO> list = partsMerchandiseHandler.toVOList4Admin(page.getData());
        String pageQueryStr = StringUtil.nullToEmpty((String) PageParameterUtil.getParameterValues(request, PiratesParameterKey.PAGE_QUERY_STRING));
        String queryStr = StringUtil.nullToEmpty((String) PageParameterUtil.getParameterValues(request, PiratesParameterKey.QUERY_STRING));
        AcePagingView pagingView = new AcePagingView("/admin/forest-PartsMerchandise-list", DIR + "/list?" + pageQueryStr, pPage.getPageNum(), pPage.getPageSize(), page.getCount());
        pagingView.addObject("result", list);
        pagingView.addObject("queryStr", URLEncoder.encode(queryStr));
        return pagingView;
    }

    @RequestMapping
    public ModelAndView toAdd() {

        ModelAndView model = new ModelAndView("/admin/forest-PartsMerchandise-add");
        return model;
    }

    @RequestMapping
    public AceAjaxView add(PartsMerchandise partsMerchandise) {

        partsMerchandiseService.add(partsMerchandise);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("添加成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    public ModelAndView toEdit(Long id, String queryStr) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        PartsMerchandise partsMerchandise = partsMerchandiseService.get(id);
        AdminPartsMerchandiseVO adminPartsMerchandiseVO = partsMerchandiseHandler.toVO4Admin(partsMerchandise);
        ModelAndView model = new ModelAndView("/admin/forest-PartsMerchandise-edit");
        model.addObject("partsMerchandise", adminPartsMerchandiseVO);
        model.addObject("queryStr", StringUtil.nullToEmpty(queryStr));
        return model;
    }

    @RequestMapping
    public AceAjaxView edit(PartsMerchandise partsMerchandise, String queryStr) {

        partsMerchandiseService.update(partsMerchandise);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("编辑成功");
        aceAjaxView.setUrl(DIR + "/list?" + StringUtil.nullToEmpty(queryStr));
        return aceAjaxView;
    }

    @RequestMapping
    public AceAjaxView delete(Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        partsMerchandiseService.delete(id);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("删除成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    /**
     * 部位列表
     * @return
     */
    @RequestMapping
    @NoReferer
    public ModelAndView listParts() {

        ModelAndView model = new ModelAndView("/admin/forest-PartsMerchandise-listParts");
        List<QClassify> qclassify = publicdataClient.listClassifyForTree(TypeConstant.PART_TYPE);
        model.addObject("qclassify", qclassify);
        return model;
    }

    @RequestMapping
    public ModelAndView toAddParts(Long classifyId) {

        AssertUtil.greatZero(classifyId, "分类id不能为空.");
        ModelAndView model = new ModelAndView("/admin/forest-PartsMerchandise-addParts");
        model.addObject("classify", publicdataClient.getClassify(classifyId));
        return model;
    }

    /**
     * 添加部位
     * @param classifyId
     * @param name
     * @return
     */
    @RequestMapping
    public AceAjaxView addParts(Long classifyId, String name) {

        Classify classify = new Classify();
        classify.setName(name);
        classify.setType(TypeConstant.PART_TYPE);
        classify.setParentId(classifyId);
        publicdataClient.addClassify(classify);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("添加成功");
        aceAjaxView.setUrl(DIR + "/listParts");
        return aceAjaxView;
    }

    @RequestMapping
    public ModelAndView toEditParts(Long classifyId) {

        AssertUtil.assertNotNull(classifyId, "ID不能为空");
        Classify classify = publicdataClient.getClassify(classifyId);
        ModelAndView model = new ModelAndView("/admin/forest-PartsMerchandise-editParts");
        model.addObject("classify", classify);
        return model;
    }

    /**
     * 编辑部位
     * @param classifyId
     * @param name
     * @return
     */
    @RequestMapping
    public AceAjaxView editParts(Long classifyId, String name) {

        Classify classify = publicdataClient.getClassify(classifyId);
        AssertUtil.assertNotNull(classify, "分类不存在.");
        classify.setName(name);
        publicdataClient.update(classify);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("编辑成功");
        aceAjaxView.setUrl(DIR + "/listParts");
        return aceAjaxView;
    }

    @RequestMapping
    public ModelAndView toEditMerchandise(Long classifyId) {

        AssertUtil.assertNotNull(classifyId, "ID不能为空");
        List<PartsMerchandise> list = partsMerchandiseService.listByClassifyId(classifyId);
        List<QMerchandise> qMerchandises = new ArrayList<QMerchandise>();
        for (PartsMerchandise pm : list) {
            Long merchandiseId = pm.getMerchandiseId();
            QMerchandise qMerchandise = commoditycenterClient.getMerchandise(merchandiseId);
            qMerchandises.add(qMerchandise);
        }
        ModelAndView model = new ModelAndView("/admin/forest-PartsMerchandise-editMerchandise");
        model.addObject("qMerchandises", qMerchandises);
        model.addObject("classify", publicdataClient.getClassify(classifyId));
        return model;
    }

    @RequestMapping
    @Transactional
    public AceAjaxView editMerchandise(PartsMerchandiseQuery query) {

        AssertUtil.greatZero(query.getClassifyId(), "分类不能为空.");
        // 先删除
        partsMerchandiseService.deleteByClassify(query.getClassifyId());
        for (Long merchandiseId : query.getMerchandiseIds()) {
            PartsMerchandise partsMerchandise = new PartsMerchandise();
            partsMerchandise.setMerchandiseId(merchandiseId);
            partsMerchandise.setClassifyId(query.getClassifyId());
            QMerchandise qMerchandise = commoditycenterClient.getMerchandise(merchandiseId);
            AssertUtil.assertNotNull(qMerchandise, "请检查数据，商品不存在." + merchandiseId);
            partsMerchandise.setState(qMerchandise.getState());
            partsMerchandiseService.add(partsMerchandise);
        }
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("编辑成功");
        aceAjaxView.setUrl(DIR + "/toEditMerchandise?classifyId=" + query.getClassifyId());
        return aceAjaxView;
    }

    /**
     * 刷新商品上下架状态
     * @return
     */
    @RequestMapping
    public AceAjaxView flushState() {

        List<PartsMerchandise> partsMerchandises = partsMerchandiseService.listAll();
        for (PartsMerchandise p : partsMerchandises) {
            QMerchandise qMerchandise = commoditycenterClient.getMerchandise(p.getMerchandiseId());
            AssertUtil.assertNotNull(qMerchandise, "请检查数据，商品不存在." + p.getMerchandiseId());
            p.setState(qMerchandise.getState());
            partsMerchandiseService.update(p);
        }
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("更新成功");
        return aceAjaxView;
    }
}
