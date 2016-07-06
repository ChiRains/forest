package com.qcloud.component.goods.web.controller.admin;

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
import com.qcloud.component.goods.CommoditycenterClient;
import com.qcloud.component.goods.QUnifiedMerchandise;
import com.qcloud.component.goods.UnifiedMerchandiseType;
import com.qcloud.component.goods.model.CombinationMerchandiseItem;
import com.qcloud.component.goods.model.UnifiedMerchandise;
import com.qcloud.component.goods.model.query.UnifiedMerchandiseQuery;
import com.qcloud.component.goods.service.CombinationMerchandiseItemService;
import com.qcloud.component.goods.service.UnifiedMerchandiseService;
import com.qcloud.component.goods.web.form.CombinationMerchandiseForm;
import com.qcloud.component.goods.web.handler.CombinationMerchandiseHandler;
import com.qcloud.component.goods.web.vo.admin.AdminCombinationMerchandiseItemVO;
import com.qcloud.component.goods.web.vo.admin.AdminCombinationMerchandiseVO;
import com.qcloud.component.publicdata.PublicdataClient;
import com.qcloud.component.sellercenter.QMerchant;
import com.qcloud.component.sellercenter.SellercenterClient;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.mvc.AceAjaxView;
import com.qcloud.pirates.mvc.AcePagingView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.web.page.PPage;
import com.qcloud.pirates.web.page.PageParameterUtil;
import com.qcloud.pirates.web.security.annotation.NoReferer;

@Controller
@RequestMapping(value = "/" + AdminCombinationMerchandiseController.DIR)
public class AdminCombinationMerchandiseController {

    public static final String                DIR = "admin/combinationMerchandise";

    @Autowired
    private CombinationMerchandiseHandler     combinationMerchandiseHandler;

    @Autowired
    private CombinationMerchandiseItemService combinationMerchandiseItemService;

    @Autowired
    private UnifiedMerchandiseService         unifiedMerchandiseService;

    @Autowired
    private CommoditycenterClient             commoditycenterClient;

    @Autowired
    private PublicdataClient                  publicdataClient;

    @RequestMapping
    @NoReferer
    public ModelAndView list(PPage pPage, HttpServletRequest request) {

        QMerchant merchant = PageParameterUtil.getParameterValues(request, SellercenterClient.MERCHANT_LOGIN_PARAMETER_KEY);
        UnifiedMerchandiseQuery query = new UnifiedMerchandiseQuery();
        query.setType(UnifiedMerchandiseType.COMBINATION.getKey());
        query.setMerchantId(merchant.getId());
        Page<UnifiedMerchandise> page = unifiedMerchandiseService.page(query, pPage.getPageStart(), pPage.getPageSize());
        List<AdminCombinationMerchandiseVO> list = combinationMerchandiseHandler.toVOList4Admin(page.getData());
        AcePagingView pagingView = new AcePagingView("/admin/goods-CombinationMerchandise-list", DIR + "/list", pPage.getPageNum(), pPage.getPageSize(), page.getCount());
        pagingView.addObject("result", list);
        return pagingView;
    }

    @Transactional
    @RequestMapping
    public AceAjaxView update(CombinationMerchandiseForm combinationMerchandiseForm, HttpServletRequest request) {

        QMerchant merchant = PageParameterUtil.getParameterValues(request, SellercenterClient.MERCHANT_LOGIN_PARAMETER_KEY);
        UnifiedMerchandise combinationMerchandise = combinationMerchandiseForm.getCombinationMerchandise();
        // TODO 商品库存调整
        if (combinationMerchandise.getId() > 0) {// 更新
            UnifiedMerchandise data = unifiedMerchandiseService.get(combinationMerchandise.getId());
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
                unifiedMerchandiseService.update(data);
                // 删除组合商品
                HashMap<String, Object> where = new HashMap<String, Object>();
                where.put("combinationUnifiedMerchandiseId", data.getId());
                where.put("merchantId", data.getMerchantId());
                combinationMerchandiseItemService.delete(where);
                // 添加组合商品
                HashSet<Long> products = new HashSet<Long>();// 去重
                for (CombinationMerchandiseItem combinationMerchandiseItem : combinationMerchandiseForm.getCombinationMerchandiseItems()) {
                    if (combinationMerchandiseItem.getRelaUnifiedMerchandiseId() > 0 && !products.contains(combinationMerchandiseItem.getRelaUnifiedMerchandiseId())) {
                        combinationMerchandiseItem.setRelaUnifiedMerchandiseId(data.getId());
                        combinationMerchandiseItem.setMerchantId(data.getMerchantId());
                        combinationMerchandiseItemService.add(combinationMerchandiseItem);
                        products.add(combinationMerchandiseItem.getRelaUnifiedMerchandiseId());
                    }
                }
            } else {
                AssertUtil.assertTrue(false, "数据错误");
            }
        } else {// 新增
            // 生成组合商品唯一ID
            combinationMerchandise.setType(UnifiedMerchandiseType.COMBINATION.getKey());
            combinationMerchandise.setMerchantId(merchant.getId());
            unifiedMerchandiseService.add(combinationMerchandise);
            // 生成组合商品
            for (CombinationMerchandiseItem combinationMerchandiseItem : combinationMerchandiseForm.getCombinationMerchandiseItems()) {
                if (combinationMerchandiseItem.getRelaUnifiedMerchandiseId() > 0) {
                    combinationMerchandiseItem.setCombinationUnifiedMerchandiseId(combinationMerchandise.getId());
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

        ModelAndView modelAndView = new ModelAndView("/admin/goods-CombinationMerchandise-edit");
        if (id > 0) {
            UnifiedMerchandise combinationMerchandise = unifiedMerchandiseService.get(id);
            AdminCombinationMerchandiseVO adminCombinationMerchandiseVO = combinationMerchandiseHandler.toVO4Admin(combinationMerchandise);
            modelAndView.addObject("combinationMerchandise", adminCombinationMerchandiseVO);
            // 组合商品列表
            HashMap<String, Object> where = new HashMap<String, Object>();
            where.put("combinationUnifiedMerchandiseId", combinationMerchandise.getId());
            List<CombinationMerchandiseItem> combinationMerchandiseItems = combinationMerchandiseItemService.list(where);
            List<AdminCombinationMerchandiseItemVO> voList = new ArrayList<AdminCombinationMerchandiseItemVO>();
            for (CombinationMerchandiseItem combinationMerchandiseItem : combinationMerchandiseItems) {
                QUnifiedMerchandise qUnifiedMerchandise = commoditycenterClient.getUnifiedMerchandise(combinationMerchandiseItem.getRelaUnifiedMerchandiseId());
                if (qUnifiedMerchandise != null) {
                    AdminCombinationMerchandiseItemVO vo = new AdminCombinationMerchandiseItemVO();
                    vo.setName(qUnifiedMerchandise.getList().get(0).getName());
                    vo.setStock(qUnifiedMerchandise.getStock());
                    vo.setSpecifications(qUnifiedMerchandise.getList().get(0).getSpecifications());
                    vo.setUnid(qUnifiedMerchandise.getId());
                    vo.setNum(combinationMerchandiseItem.getNumber());
                    voList.add(vo);
                }
            }
            modelAndView.addObject("voList", voList);
        }
        String fileSize = publicdataClient.getImageInformationByCode("zuheshangpinsuoluetu");
        modelAndView.addObject("fileSize", fileSize);
        return modelAndView;
    }

    @RequestMapping
    public AceAjaxView delete(Long id) {

        // TODO 库存释放
        AssertUtil.assertNotNull(id, "ID不能为空");
        UnifiedMerchandise combinationMerchandise = unifiedMerchandiseService.get(id);
        AssertUtil.assertNotNull(combinationMerchandise, "找不到ID");
        HashMap<String, Object> where = new HashMap<String, Object>();
        where.put("combinationUnifiedMerchandiseId", combinationMerchandise.getId());
        where.put("merchantId", combinationMerchandise.getMerchantId());
        combinationMerchandiseItemService.delete(where);
        unifiedMerchandiseService.delete(combinationMerchandise.getId());
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("删除成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }
}
