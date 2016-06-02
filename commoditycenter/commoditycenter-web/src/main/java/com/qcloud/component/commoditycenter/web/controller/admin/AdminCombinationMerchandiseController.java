package com.qcloud.component.commoditycenter.web.controller.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.qcloud.component.commoditycenter.CommoditycenterClient;
import com.qcloud.component.commoditycenter.QUnifiedMerchandise;
import com.qcloud.component.commoditycenter.model.CombinationMerchandise;
import com.qcloud.component.commoditycenter.model.CombinationMerchandiseItem;
import com.qcloud.component.commoditycenter.model.UnifiedMerchandise;
import com.qcloud.component.commoditycenter.model.key.TypeEnum;
import com.qcloud.component.commoditycenter.service.CombinationMerchandiseItemService;
import com.qcloud.component.commoditycenter.service.CombinationMerchandiseService;
import com.qcloud.component.commoditycenter.service.UnifiedMerchandiseService;
import com.qcloud.component.commoditycenter.web.form.CombinationMerchandiseForm;
import com.qcloud.component.commoditycenter.web.handler.CombinationMerchandiseHandler;
import com.qcloud.component.commoditycenter.web.vo.admin.AdminCombinationMerchandiseItemVO;
import com.qcloud.component.commoditycenter.web.vo.admin.AdminCombinationMerchandiseVO;
import com.qcloud.component.publicdata.PublicdataClient;
import com.qcloud.component.sellercenter.QMerchant;
import com.qcloud.component.sellercenter.SellercenterClient;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.mvc.AceAjaxView;
import com.qcloud.pirates.mvc.AcePagingView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.NumberUtil;
import com.qcloud.pirates.util.RequestUtil;
import com.qcloud.pirates.web.page.PageParameterUtil;
import com.qcloud.pirates.web.security.annotation.NoReferer;

@Controller
@RequestMapping(value = "/" + AdminCombinationMerchandiseController.DIR)
public class AdminCombinationMerchandiseController {

    public static final String                DIR = "admin/combinationMerchandise";

    @Autowired
    private CombinationMerchandiseService     combinationMerchandiseService;

    @Autowired
    private CombinationMerchandiseHandler     combinationMerchandiseHandler;

    @Autowired
    private CombinationMerchandiseItemService combinationMerchandiseItemService;

    // @Autowired
    // private AdminFilterService adminFilterService;
    //
    // @Autowired
    // private TokenClient tokenClient;
    @Autowired
    private UnifiedMerchandiseService         unifiedMerchandiseService;

    @Autowired
    private CommoditycenterClient             commoditycenterClient;

    // @Autowired
    // private OutdatedSellercenterClient outdatedSellercenterClient;
    @Autowired
    private PublicdataClient                  publicdataClient;

    @RequestMapping
    @NoReferer
    public ModelAndView list(Integer pageNum, HttpServletRequest request) {

        QMerchant merchant = PageParameterUtil.getParameterValues(request, SellercenterClient.MERCHANT_LOGIN_PARAMETER_KEY);
        final int PAGE_SIZE = 10;
        pageNum = RequestUtil.getPageid(pageNum);
        int start = NumberUtil.getPageStart(pageNum, PAGE_SIZE);
        HashMap<String, Object> where = new HashMap<String, Object>();
        where.put("merchantId", merchant.getId());
        Page<CombinationMerchandise> page = combinationMerchandiseService.page(where, start, PAGE_SIZE);
        List<AdminCombinationMerchandiseVO> list = combinationMerchandiseHandler.toVOList4Admin(page.getData());
        AcePagingView pagingView = new AcePagingView("/admin/commoditycenter-CombinationMerchandise-list", DIR + "/list", pageNum, PAGE_SIZE, page.getCount());
        pagingView.addObject("result", list);
        return pagingView;
    }

    @Transactional
    @RequestMapping
    public AceAjaxView update(CombinationMerchandiseForm combinationMerchandiseForm, HttpServletRequest request) {

        QMerchant merchant = PageParameterUtil.getParameterValues(request, SellercenterClient.MERCHANT_LOGIN_PARAMETER_KEY);
        CombinationMerchandise combinationMerchandise = combinationMerchandiseForm.getCombinationMerchandise();
        // TODO 商品库存调整
        if (combinationMerchandise.getId() > 0) {// 更新
            CombinationMerchandise data = combinationMerchandiseService.get(combinationMerchandise.getId());
            if (data != null && data.getMerchantId() == merchant.getId()) {
                // 更新组合商品信息
                if (!data.getName().equals(combinationMerchandise.getName())) {
                    data.setName(combinationMerchandise.getName());
                }
                if (data.getPurchase() != combinationMerchandise.getPurchase()) {
                    data.setPurchase(combinationMerchandise.getPurchase());
                }
                if (data.getPrice() != combinationMerchandise.getPrice()) {
                    data.setPrice(combinationMerchandise.getPrice());
                }
                if (data.getDiscount() != combinationMerchandise.getDiscount()) {
                    data.setDiscount(combinationMerchandise.getDiscount());
                }
                if (data.getStock() != combinationMerchandise.getStock()) {
                    data.setStock(combinationMerchandise.getStock());
                }
                if (data.getImage() != combinationMerchandise.getImage()) {
                    data.setImage(combinationMerchandise.getImage());
                }
                combinationMerchandiseService.update(data);
                // 删除组合商品
                HashMap<String, Object> where = new HashMap<String, Object>();
                where.put("combinationMerchandiseId", data.getId());
                where.put("merchantId", data.getMerchantId());
                combinationMerchandiseItemService.delete(where);
                // 添加组合商品
                HashSet<Long> products = new HashSet<Long>();// 去重
                for (CombinationMerchandiseItem combinationMerchandiseItem : combinationMerchandiseForm.getCombinationMerchandiseItems()) {
                    if (combinationMerchandiseItem.getMerchandiseItemId() > 0 && !products.contains(combinationMerchandiseItem.getMerchandiseItemId())) {
                        combinationMerchandiseItem.setCombinationMerchandiseId(data.getId());
                        combinationMerchandiseItem.setMerchantId(data.getMerchantId());
                        combinationMerchandiseItemService.add(combinationMerchandiseItem);
                        products.add(combinationMerchandiseItem.getMerchandiseItemId());
                    }
                }
            } else {
                AssertUtil.assertTrue(false, "数据错误");
            }
        } else {// 新增
            // 生成组合商品唯一ID
            UnifiedMerchandise unifiedMerchandise = new UnifiedMerchandise();
            unifiedMerchandise.setMerchantId(merchant.getId());
            unifiedMerchandise.setType(TypeEnum.UnifiedMerchandiseType.COMBINAION.getKey());
            unifiedMerchandiseService.add(unifiedMerchandise);
            // 生成组合商品
            combinationMerchandise.setMerchantId(merchant.getId());
            combinationMerchandise.setUnifiedMerchandiseId(unifiedMerchandise.getId());
            combinationMerchandiseService.add(combinationMerchandise);
            for (CombinationMerchandiseItem combinationMerchandiseItem : combinationMerchandiseForm.getCombinationMerchandiseItems()) {
                if (combinationMerchandiseItem.getMerchandiseItemId() > 0) {
                    combinationMerchandiseItem.setCombinationMerchandiseId(combinationMerchandise.getId());
                    combinationMerchandiseItem.setMerchantId(combinationMerchandise.getMerchantId());
                    combinationMerchandiseItemService.add(combinationMerchandiseItem);
                }
            }
        }
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("添加成功");
        aceAjaxView.setUrl("");
        return aceAjaxView;
    }

    @RequestMapping
    public ModelAndView edit(Long id) {

        ModelAndView modelAndView = new ModelAndView("/admin/commoditycenter-CombinationMerchandise-edit");
        if (id > 0) {
            CombinationMerchandise combinationMerchandise = combinationMerchandiseService.get(id);
            AdminCombinationMerchandiseVO adminCombinationMerchandiseVO = combinationMerchandiseHandler.toVO4Admin(combinationMerchandise);
            modelAndView.addObject("combinationMerchandise", adminCombinationMerchandiseVO);
            // 组合商品列表
            HashMap<String, Object> where = new HashMap<String, Object>();
            where.put("combinationMerchandiseId", combinationMerchandise.getId());
            List<CombinationMerchandiseItem> combinationMerchandiseItems = combinationMerchandiseItemService.list(where);
            List<AdminCombinationMerchandiseItemVO> voList = new ArrayList<AdminCombinationMerchandiseItemVO>();
            for (CombinationMerchandiseItem combinationMerchandiseItem : combinationMerchandiseItems) {
                QUnifiedMerchandise qUnifiedMerchandise = commoditycenterClient.getUnifiedMerchandise(combinationMerchandiseItem.getMerchandiseItemId());
                if (qUnifiedMerchandise != null) {
                    AdminCombinationMerchandiseItemVO vo = new AdminCombinationMerchandiseItemVO();
                    vo.setName(qUnifiedMerchandise.getList().get(0).getName());
                    vo.setStock(qUnifiedMerchandise.getStock());
                    vo.setSpecifications(qUnifiedMerchandise.getList().get(0).getSpecifications());
                    vo.setUnid(qUnifiedMerchandise.getId());
                    vo.setNum(combinationMerchandiseItem.getNum());
                    voList.add(vo);
                }
            }
            modelAndView.addObject("voList", voList);
        }
        String fileSize = publicdataClient.getImageInformationByCode("zuheshangpinsuoluetu");
        modelAndView.addObject("fileSize", fileSize);
        return modelAndView;
    }

    @Transactional
    @RequestMapping
    public AceAjaxView delete(Long id) {

        // TODO 库存释放
        AssertUtil.assertNotNull(id, "ID不能为空");
        CombinationMerchandise combinationMerchandise = combinationMerchandiseService.get(id);
        AssertUtil.assertNotNull(combinationMerchandise, "找不到ID");
        unifiedMerchandiseService.delete(combinationMerchandise.getUnifiedMerchandiseId());
        HashMap<String, Object> where = new HashMap<String, Object>();
        where.put("combinationMerchandiseId", combinationMerchandise.getId());
        where.put("merchantId", combinationMerchandise.getMerchantId());
        combinationMerchandiseItemService.delete(where);
        combinationMerchandiseService.delete(combinationMerchandise.getId());
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("删除成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }
    // private long getMemberId(HttpServletRequest request) {
    //
    // String tokenId = adminFilterService.getTokenId(request);
    // AssertUtil.assertNotEmpty(tokenId, "获取用户登录信息失败.");
    // String idStr = tokenClient.get(tokenId);
    // AssertUtil.assertNotEmpty(idStr, "获取用户标识失败.");
    // return Long.parseLong(idStr);
    // }
    // private long getMerchantId(long memberId) {
    //
    // long merchantId = 0;
    // List<QMerchant> qMerchants = outdatedSellercenterClient.listMerchant(memberId);
    // if (qMerchants.size() > 0) {
    // merchantId = qMerchants.get(0).getId();
    // } else {
    // AssertUtil.assertTrue(false, "获取商家ID失败");
    // }
    // return merchantId;
    // }
}
