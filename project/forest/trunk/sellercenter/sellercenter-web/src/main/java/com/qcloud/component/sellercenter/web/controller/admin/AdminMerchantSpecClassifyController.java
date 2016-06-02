package com.qcloud.component.sellercenter.web.controller.admin;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import com.qcloud.pirates.mvc.AceAjaxView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.web.security.annotation.NoReferer;
import com.qcloud.component.publicdata.ClassifyType;
import com.qcloud.component.publicdata.PublicdataClient;
import com.qcloud.component.publicdata.model.Classify;
import com.qcloud.component.sellercenter.model.MerchantSpecClassify;
import com.qcloud.component.sellercenter.service.MerchantSpecClassifyService;
import com.qcloud.component.sellercenter.util.Common;
import com.qcloud.component.sellercenter.web.form.AdminListForm;
import com.qcloud.component.sellercenter.web.handler.MerchantSpecClassifyHandler;
import com.qcloud.component.sellercenter.model.query.MerchantSpecClassifyQuery;
import com.qcloud.component.sellercenter.web.vo.admin.AdminMerchantSpecClassifyVO;

@Controller
@RequestMapping(value = "/" + AdminMerchantSpecClassifyController.DIR)
public class AdminMerchantSpecClassifyController {

    public static final String          DIR = "admin/merchantSpecClassify";

    @Autowired
    private MerchantSpecClassifyService merchantSpecClassifyService;

    @Autowired
    private MerchantSpecClassifyHandler merchantSpecClassifyHandler;

    @Autowired
    private PublicdataClient            publicdataClient;

    @RequestMapping
    @NoReferer
    public ModelAndView list(MerchantSpecClassifyQuery query) {

        List<MerchantSpecClassify> list = merchantSpecClassifyService.listByMerchant(query.getMerchantId());
        List<Classify> classifyList = publicdataClient.listClassify(ClassifyType.SPECIFICATIONS);
        List<AdminMerchantSpecClassifyVO> voList = merchantSpecClassifyHandler.classifyToVoList(list, classifyList);
        ModelAndView ajaxView = new ModelAndView("/admin/sellercenter-MerchantSpecClassify-list");
        List<Map> result = Common.listToTree(voList, "id", "parentId", "children", -1);
        ajaxView.addObject("result", result);
        ajaxView.addObject("merchantId", query.getMerchantId());
        return ajaxView;
    }

    @RequestMapping
    public ModelAndView toAdd(Long merchantId) {

        ModelAndView model = new ModelAndView("/admin/sellercenter-MerchantSpecClassify-add");
        model.addObject("merchantId", merchantId);
        return model;
    }

    @RequestMapping
    public AceAjaxView add(MerchantSpecClassify merchantSpecClassify) {

        merchantSpecClassifyService.add(merchantSpecClassify);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("添加成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    public ModelAndView toEdit(Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        MerchantSpecClassify merchantSpecClassify = merchantSpecClassifyService.get(id);
        AdminMerchantSpecClassifyVO adminMerchantSpecClassifyVO = merchantSpecClassifyHandler.toVO4Admin(merchantSpecClassify);
        ModelAndView model = new ModelAndView("/admin/sellercenter-MerchantSpecClassify-edit");
        model.addObject("merchantMerchandiseClassify", adminMerchantSpecClassifyVO);
        model.addObject("merchantId", adminMerchantSpecClassifyVO.getMerchantId());
        return model;
    }

    @Transactional
    @RequestMapping
    public AceAjaxView edit(Long merchantId, AdminListForm form) {

        if (form.getLongList() == null) {
            merchantSpecClassifyService.deleteByMerchant(merchantId);
        } else {
            List<Long> longList = form.getLongList();
            List<Long> tempList = new ArrayList<Long>();
            for (Long id : longList) {
                Classify classify = publicdataClient.getClassify(id);
                if (classify.getParentId() == -1) {
                    List<Classify> twoChildren = listChildren(classify);
                    if (CollectionUtils.isNotEmpty(twoChildren)) {
                        for (Classify two : twoChildren) {
                            List<Classify> threeChildren = listChildren(two);
                            if (CollectionUtils.isNotEmpty(threeChildren)) {
                                for (Classify three : threeChildren) {
                                    if (!tempList.contains(three.getId())) {
                                        tempList.add(three.getId());
                                    }
                                }
                            } else {
                                if (!tempList.contains(two.getId())) {
                                    tempList.add(two.getId());
                                }
                            }
                        }
                    } else {
                        if (!tempList.contains(classify.getId())) {
                            tempList.add(classify.getId());
                        }
                    }
                } else {
                    Classify parent = publicdataClient.getClassify(classify.getParentId());
                    if (parent.getParentId() == -1) {// 二级
                        List<Classify> threeChildren = listChildren(classify);
                        if (CollectionUtils.isNotEmpty(threeChildren)) {
                            for (Classify three : threeChildren) {
                                if (!tempList.contains(three.getId())) {
                                    tempList.add(three.getId());
                                }
                            }
                        } else {
                            if (!tempList.contains(classify.getId())) {
                                tempList.add(classify.getId());
                            }
                        }
                    } else {// 三级
                        if (!tempList.contains(classify.getId())) {
                            tempList.add(classify.getId());
                        }
                    }
                }
            }
            // 先删除
            merchantSpecClassifyService.deleteByMerchant(merchantId);
            // 后添加
            for (Long id : tempList) {
                MerchantSpecClassify merchantSpecClassify = new MerchantSpecClassify();
                merchantSpecClassify.setClassifyId(id);
                merchantSpecClassify.setMerchantId(merchantId);
                merchantSpecClassifyService.add(merchantSpecClassify);
            }
        }
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("编辑成功");
        return aceAjaxView;
    }

    public List<Classify> listChildren(Classify classify) {

        List<Classify> classifyList = publicdataClient.listClassify(ClassifyType.SPECIFICATIONS);
        List<Classify> tempList = new ArrayList<Classify>();
        for (Classify c : classifyList) {
            if (c.getBsid().startsWith(classify.getBsid()) && c.getId() != classify.getId()) {
                tempList.add(c);
            }
        }
        return tempList;
    }

    @RequestMapping
    public AceAjaxView delete(Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        merchantSpecClassifyService.delete(id);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("删除成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }
}
