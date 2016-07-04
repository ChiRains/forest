//package com.qcloud.component.sellercenter.web.controller.admin;
//
//import java.util.ArrayList;
//import java.util.List;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.servlet.ModelAndView;
//import com.qcloud.component.publicdata.ClassifyType;
//import com.qcloud.component.publicdata.PublicdataClient;
//import com.qcloud.component.publicdata.QClassify;
//import com.qcloud.component.publicdata.model.Classify;
//import com.qcloud.component.sellercenter.model.Merchant;
//import com.qcloud.component.sellercenter.model.MerchantClassify;
//import com.qcloud.component.sellercenter.model.query.MerchantQuery;
//import com.qcloud.component.sellercenter.service.MerchantClassifyService;
//import com.qcloud.component.sellercenter.service.MerchantService;
//import com.qcloud.component.sellercenter.web.handler.MerchantClassifyHandler;
//import com.qcloud.component.sellercenter.web.vo.admin.AdminMerchantClassifyVO;
//import com.qcloud.pirates.mvc.AceAjaxView;
//import com.qcloud.pirates.mvc.RedirectView;
//import com.qcloud.pirates.util.AssertUtil;
//import com.qcloud.pirates.web.security.annotation.NoReferer;
//
//@Controller
//@RequestMapping(value = "/" + AdminMerchantClassifyController.DIR)
//public class AdminMerchantClassifyController {
//
//    //
//    public static final String      DIR = "admin/classifyMerchant";
//
//    @Autowired
//    private MerchantClassifyService merchantClassifyService;
//
//    @Autowired
//    private MerchantClassifyHandler merchantClassifyHandler;
//
//    @Autowired
//    private PublicdataClient        publicdataClient;
//
//    @Autowired
//    private MerchantService         merchantService;
//
//    @RequestMapping
//    @NoReferer
//    public ModelAndView toSetClassifys(Long merchantId) {
//
//        AssertUtil.assertNotNull(merchantId, "必须指定商家.");
//        Merchant m = merchantService.get(merchantId);
//        AssertUtil.assertNotNull(m, "指定商家不存在." + merchantId);
//        List<AdminMerchantClassifyVO> voList = new ArrayList<AdminMerchantClassifyVO>();
//        if (merchantId != null && merchantId > 0) {
//            List<MerchantClassify> mcList = merchantClassifyService.listByMerchant(merchantId);
//            List<Long> keyList = new ArrayList<Long>();
//            for (MerchantClassify merchantClassify : mcList) {
//                keyList.add(merchantClassify.getClassifyId());
//            }
//            List<Classify> list = publicdataClient.listClassify(ClassifyType.MERCHANDISE);
//            voList = merchantClassifyHandler.toVOList4Admin(list, keyList);
//        }
//        ModelAndView modelAndView = new ModelAndView("/admin/sellercenter-MerchantClassify-setClassifys");
//        modelAndView.addObject("result", voList);
//        modelAndView.addObject("merchant", m);
//        return modelAndView;
//    }
//
//    @RequestMapping
//    public AceAjaxView setClassify(Long classifyId, Long merchantId, Integer value) {
//
//        AssertUtil.assertNotNull(classifyId, "ClassifyId不能为空.");
//        AssertUtil.assertNotNull(merchantId, "MerchantId不能为空.");
//        AssertUtil.assertNotNull(value, "Value开关不能为空.");
//        MerchantClassify mc = merchantClassifyService.get(classifyId, merchantId);
//        if (value == 1) {
//            if (mc == null) {
//                Classify classify = publicdataClient.getClassify(classifyId);
//                AssertUtil.assertNotNull(classify, "分类不存在." + classifyId);
//                Merchant merchant = merchantService.get(merchantId);
//                AssertUtil.assertNotNull(merchant, "商家不存在." + merchantId);
//                MerchantClassify merchantClassify = new MerchantClassify();
//                merchantClassify.setClassifyId(classifyId);
//                merchantClassify.setMerchantId(merchantId);
//                merchantClassifyService.add(merchantClassify);
//            }
//        } else {
//            if (mc != null) {
//                merchantClassifyService.delete(mc.getId());
//            }
//        }
//        AceAjaxView aceAjaxView = new AceAjaxView();
//        aceAjaxView.setMessage("设置成功.");
//        return aceAjaxView;
//    }
//
//    // 只做一个入口,然后跳转
//    @RequestMapping
//    @NoReferer
//    public ModelAndView list() {
//
//        List<QClassify> qClassify = publicdataClient.listClassifyForTree(ClassifyType.MERCHANT.getKey(), true);
//        ModelAndView view = new ModelAndView("/admin/sellercenter-MerchantClassify-list");
//        view.addObject("qclassify", qClassify);
//        view.addObject("classType", ClassifyType.MERCHANT.getKey());
//        return view;
//        // return new RedirectView("/admin/classify4Type/list.do?type=" + ClassifyType.MERCHANT.getKey());
//    }
//
//    @RequestMapping
//    public AceAjaxView deleteMerchantClassify(Long id) {
//
//        AceAjaxView aceAjaxView = new AceAjaxView();
//        AssertUtil.assertNotNull(id, "id不能为空");
//        Classify classify = publicdataClient.getClassify(id);
//        AssertUtil.assertNotNull(classify, "指定分类不存在");
//        List<Classify> classifies = publicdataClient.listClassify(classify.getType());
//        List<Classify> listChildrenIncludeMe = new ArrayList<Classify>();
//        for (Classify c : classifies) {
//            if (c.getBsid().startsWith(classify.getBsid())) {
//                listChildrenIncludeMe.add(c);
//            }
//        }
//        if (listChildrenIncludeMe.size() > 1) {
//            aceAjaxView.setStatus(0);
//            aceAjaxView.setMessage("删除失败,该分类包含" + (listChildrenIncludeMe.size() - 1) + "个子类");
//        } else {
//            int count = 0;
//            for (Classify c : listChildrenIncludeMe) {
//                count += merchantService.count4DeleteByClassifyId(c.getId());
//            }
//            if (count > 0) {
//                aceAjaxView.setStatus(0);
//                aceAjaxView.setMessage("删除失败,该分类或其子类关联了" + count + "家商家");
//            } else {
//                publicdataClient.delete(id);
//                aceAjaxView.setMessage("删除成功");
//            }
//        }
//        return aceAjaxView;
//    }
//}
