package com.qcloud.project.forest.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qcloud.component.filesdk.FileSDKClient;
import com.qcloud.component.goods.QMerchandise;
import com.qcloud.component.my.model.MyCoupon;
import com.qcloud.component.my.web.vo.MyCouponVO;
import com.qcloud.component.organization.OrganizationClient;
import com.qcloud.component.personalcenter.PersonalcenterClient;
import com.qcloud.component.personalcenter.QUser;
import com.qcloud.component.publicdata.PublicdataClient;
import com.qcloud.component.publicdata.QClassify;
import com.qcloud.component.publicdata.model.Classify;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.mvc.FrontAjaxView;
import com.qcloud.pirates.mvc.FrontPagingView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.web.mvc.annotation.PiratesApp;
import com.qcloud.pirates.web.page.PPage;
import com.qcloud.pirates.web.page.PageParameterUtil;
import com.qcloud.project.forest.model.PartsMerchandise;
import com.qcloud.project.forest.model.key.TypeConstant;
import com.qcloud.project.forest.model.key.TypeEnum.ClassifyType;
import com.qcloud.project.forest.model.query.PartsMerchandiseQuery;
import com.qcloud.project.forest.service.PartsMerchandiseService;
import com.qcloud.project.forest.web.handler.PartsMerchandiseHandler;
import com.qcloud.project.forest.web.vo.PartsMerchandiseVO;

@Controller
@RequestMapping(value = PartsMerchandiseController.DIR)
public class PartsMerchandiseController {

    public static final String      DIR = "/partsMerchandise";

    @Autowired
    private PartsMerchandiseService partsMerchandiseService;

    @Autowired
    private PartsMerchandiseHandler partsMerchandiseHandler;

    @Autowired
    private PublicdataClient        publicdataClient;

    @Autowired
    private FileSDKClient           fileSDKClient;

    @Autowired
    private OrganizationClient      organizationClient;

    @PiratesApp
    @RequestMapping
    public FrontAjaxView listParts(HttpServletRequest request, Long classifyId) {

        List<QClassify> classifyList = new ArrayList<QClassify>();
        if (classifyId > 0) {
            classifyList = publicdataClient.listClassifyForTreeByParent(classifyId, TypeConstant.PART_TYPE);
        } else {
            classifyList = publicdataClient.listClassifyForTree(TypeConstant.PART_TYPE);
        }
        FrontAjaxView view = new FrontAjaxView();
        view.addObject("classifyList", classifyList);
        view.setMessage("获取分类成功");
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontPagingView list(HttpServletRequest request, PPage pPage, PartsMerchandiseQuery query) {

        AssertUtil.greatZero(query.getClassifyId(), "分类id不能为空.");
        Page<PartsMerchandise> page = partsMerchandiseService.page(query, pPage.getPageStart(), pPage.getPageSize());
        List<PartsMerchandiseVO> voList = partsMerchandiseHandler.toVOList(page.getData());
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        for (PartsMerchandiseVO vo : voList) {
            Map<String, Object> map = new HashMap<String, Object>();
            QMerchandise qMerchandise = vo.getqMerchandise();
            map.put("departmentName", organizationClient.getDepartment(qMerchandise.getMerchantId()).getName());
            map.put("discount", qMerchandise.getLowDiscount());
            map.put("goodEvaluationRate", qMerchandise.getHpRate());
            map.put("image", !StringUtils.isEmpty(qMerchandise.getImage()) ? fileSDKClient.getFileServerUrl() + qMerchandise.getImage() : "");
            map.put("label", qMerchandise.getLabel());
            map.put("merchandiseId", qMerchandise.getId());
            map.put("name", qMerchandise.getName());
            map.put("price", qMerchandise.getLowPrice());
            map.put("salesVolume", qMerchandise.getTotalSalesVolume());
            mapList.add(map);
        }
        FrontPagingView view = new FrontPagingView(pPage.getPageNum(), pPage.getPageSize(), page.getCount());
        view.setMessage("获取商品列表成功.");
        view.setList(mapList);
        return view;
    }
}
