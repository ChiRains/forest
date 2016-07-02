package com.qcloud.component.sellercenter.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.qcloud.pirates.mvc.AceAjaxView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.web.security.annotation.NoReferer;
import com.qcloud.component.publicdata.ClassifyType;
import com.qcloud.component.publicdata.PublicdataClient;
import com.qcloud.component.publicdata.model.Classify;
import com.qcloud.component.sellercenter.model.MerchantMerchandiseClassify;
import com.qcloud.component.sellercenter.service.MerchantMerchandiseClassifyService;
import com.qcloud.component.sellercenter.util.Common;
import com.qcloud.component.sellercenter.web.form.AdminListForm;
import com.qcloud.component.sellercenter.web.handler.MerchantMerchandiseClassifyHandler;
import com.qcloud.component.sellercenter.model.query.MerchantMerchandiseClassifyQuery;
import com.qcloud.component.sellercenter.web.vo.MerchantMerchandiseClassifyVO;
import com.qcloud.component.sellercenter.web.vo.admin.AdminMerchantMerchandiseClassifyVO;

@Controller
@RequestMapping(value = "/" + AdminMerchantMerchandiseClassifyController.DIR)
public class AdminMerchantMerchandiseClassifyController {

    public static final String                 DIR = "admin/merchantMerchandiseClassify";

    @Autowired
    private MerchantMerchandiseClassifyService merchantMerchandiseClassifyService;

    @Autowired
    private MerchantMerchandiseClassifyHandler merchantMerchandiseClassifyHandler;

    @Autowired
    private PublicdataClient                   publicdataClient;

    @RequestMapping
    @NoReferer
    public ModelAndView list(MerchantMerchandiseClassifyQuery query) {

        List<MerchantMerchandiseClassify> list = merchantMerchandiseClassifyService.listByMerchantId(query.getMerchantId());
        List<Classify> classifyList = publicdataClient.listClassify(ClassifyType.MERCHANDISE);
        List<MerchantMerchandiseClassifyVO> voList = merchantMerchandiseClassifyHandler.classifyToVoList(list, classifyList);
        ModelAndView ajaxView = new ModelAndView("/admin/sellercenter-MerchantMerchandiseClassify-list");
        List<Map> result = Common.listToTree(voList, "id", "parentId", "children", -1);
        ajaxView.addObject("result", result);
        ajaxView.addObject("merchantId", query.getMerchantId());
        return ajaxView;
    }

    @RequestMapping
    public ModelAndView toAdd(Long merchantId) {

        ModelAndView model = new ModelAndView("/admin/sellercenter-MerchantMerchandiseClassify-add");
        model.addObject("merchantId", merchantId);
        return model;
    }

    @RequestMapping
    public AceAjaxView add(MerchantMerchandiseClassify merchantMerchandiseClassify) {

        merchantMerchandiseClassifyService.add(merchantMerchandiseClassify);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("添加成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    public ModelAndView toEdit(Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        MerchantMerchandiseClassify merchantMerchandiseClassify = merchantMerchandiseClassifyService.get(id);
        AdminMerchantMerchandiseClassifyVO adminMerchantMerchandiseClassifyVO = merchantMerchandiseClassifyHandler.toVO4Admin(merchantMerchandiseClassify);
        ModelAndView model = new ModelAndView("/admin/sellercenter-MerchantMerchandiseClassify-edit");
        model.addObject("merchantMerchandiseClassify", adminMerchantMerchandiseClassifyVO);
        model.addObject("merchantId", adminMerchantMerchandiseClassifyVO.getMerchantId());
        return model;
    }

    @Transactional
    @RequestMapping
    public AceAjaxView edit(Long merchantId, AdminListForm form) {

        if (form.getLongList() == null) {
            merchantMerchandiseClassifyService.deleteByMerchantId(merchantId);
        } else {
            // 三级分类
            List<Long> longList = form.getLongList();
            // 先删除
            merchantMerchandiseClassifyService.deleteByMerchantId(merchantId);
            // 后添加
            for (Long id : longList) {
                MerchantMerchandiseClassify merchantMerchandiseClassify = new MerchantMerchandiseClassify();
                merchantMerchandiseClassify.setClassifyId(id);
                merchantMerchandiseClassify.setMerchantId(merchantId);
                merchantMerchandiseClassifyService.add(merchantMerchandiseClassify);
            }
        }
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("编辑成功");
        return aceAjaxView;
    }

    public List<Classify> listChildren(Classify classify) {

        List<Classify> classifyList = publicdataClient.listClassify(ClassifyType.MERCHANDISE);
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
        merchantMerchandiseClassifyService.delete(id);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("删除成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }
}
