package com.qcloud.component.goods.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qcloud.component.goods.CommoditycenterClient;
import com.qcloud.component.goods.QUnifiedMerchandise;
import com.qcloud.component.goods.UnifiedMerchandiseType;
import com.qcloud.component.goods.model.CombinationMerchandiseItem;
import com.qcloud.component.goods.model.UnifiedMerchandise;
import com.qcloud.component.goods.service.CombinationMerchandiseItemService;
import com.qcloud.component.goods.service.UnifiedMerchandiseService;
import com.qcloud.component.goods.web.handler.CombinationMerchandiseHandler;
import com.qcloud.component.goods.web.vo.CombinationMerchandiseVO;
import com.qcloud.pirates.mvc.FrontAjaxView;
import com.qcloud.pirates.util.AssertUtil;

@Controller
@RequestMapping(value = CombinationMerchandiseController.DIR)
public class CombinationMerchandiseController {

    public static final String                DIR = "/combinationMerchandise";

    @Autowired
    private CombinationMerchandiseHandler     combinationMerchandiseHandler;

    @Autowired
    private CombinationMerchandiseItemService combinationMerchandiseItemService;

    @Autowired
    private CommoditycenterClient             commoditycenterClient;

    @Autowired
    private UnifiedMerchandiseService         unifiedMerchandiseService;

    @RequestMapping
    public FrontAjaxView existByUnifiedMerchandise(Long unifiedMerchandiseId) {

        AssertUtil.assertNotNull(unifiedMerchandiseId, "统一商品ID不能为空.");
        QUnifiedMerchandise unifiedMerchandise = commoditycenterClient.getUnifiedMerchandise(unifiedMerchandiseId);
        AssertUtil.assertNotNull(unifiedMerchandise, "商品不存在." + unifiedMerchandiseId);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("获取商品组合套餐成功.");
        if (UnifiedMerchandiseType.SINGLE.equals(unifiedMerchandise.getType())) {
            int count = combinationMerchandiseItemService.countByMerchandiseItem(unifiedMerchandise.getList().get(0).getId());
            view.addObject("exist", count > 0);
        } else {
            view.addObject("exist", Boolean.FALSE.toString());
        }
        return view;
    }

    @RequestMapping
    public FrontAjaxView getBySingleUnifiedMerchandise(Long unifiedMerchandiseId) {

        AssertUtil.assertNotNull(unifiedMerchandiseId, "统一商品ID不能为空.");
        QUnifiedMerchandise unifiedMerchandise = commoditycenterClient.getUnifiedMerchandise(unifiedMerchandiseId);
        AssertUtil.assertNotNull(unifiedMerchandise, "商品不存在." + unifiedMerchandiseId);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("获取商品组合套餐成功.");
        if (UnifiedMerchandiseType.SINGLE.equals(unifiedMerchandise.getType())) {
            List<CombinationMerchandiseItem> list = combinationMerchandiseItemService.listByMerchandiseItem(unifiedMerchandise.getList().get(0).getId(), 0, Integer.MAX_VALUE);
            if (list.size() > 0) {
                int index = new Random().nextInt(list.size());
                CombinationMerchandiseItem combinationMerchandiseItem = list.get(index);
                UnifiedMerchandise combinationUnifiedMerchandise = unifiedMerchandiseService.get(combinationMerchandiseItem.getCombinationUnifiedMerchandiseId());
                CombinationMerchandiseVO combinationMerchandiseVO = combinationMerchandiseHandler.toVO(combinationUnifiedMerchandise);
                view.addObject("combination", combinationMerchandiseVO);
            } else {
                view.addObject("combination", "");
            }
            view.addObject("exist", list.size() > 0);
        } else {
            view.addObject("combination", null);
            view.addObject("exist", Boolean.FALSE.toString());
        }
        return view;
    }

    @RequestMapping
    public FrontAjaxView getByUnifiedMerchandise(Long unifiedMerchandiseId) {

        AssertUtil.assertNotNull(unifiedMerchandiseId, "统一商品ID不能为空.");
        UnifiedMerchandise combinationMerchandise = unifiedMerchandiseService.get(unifiedMerchandiseId);
        AssertUtil.assertNotNull(combinationMerchandise, "组合商品不存在." + unifiedMerchandiseId);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("获取商品组合套餐列表成功.");
        List<UnifiedMerchandise> list = new ArrayList<UnifiedMerchandise>();
        list.add(combinationMerchandise);
        List<CombinationMerchandiseVO> voList = combinationMerchandiseHandler.toVOList(list);
        view.addObject("combinationList", voList);
        view.addObject("exist", "true");
        return view;
    }

    @RequestMapping
    public FrontAjaxView listByUnifiedMerchandise(Long unifiedMerchandiseId) {

        AssertUtil.assertNotNull(unifiedMerchandiseId, "统一商品ID不能为空.");
        QUnifiedMerchandise unifiedMerchandise = commoditycenterClient.getUnifiedMerchandise(unifiedMerchandiseId);
        AssertUtil.assertNotNull(unifiedMerchandise, "商品不存在." + unifiedMerchandiseId);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("获取商品组合套餐列表成功.");
        if (UnifiedMerchandiseType.COMBINATION.equals(unifiedMerchandise.getType())) {
            List<CombinationMerchandiseItem> itemLlist = combinationMerchandiseItemService.listByMerchandiseItem(unifiedMerchandise.getList().get(0).getId(), 0, 10);
            List<UnifiedMerchandise> list = new ArrayList<UnifiedMerchandise>();
            for (CombinationMerchandiseItem combinationMerchandiseItem : itemLlist) {
                UnifiedMerchandise combinationMerchandise = unifiedMerchandiseService.get(combinationMerchandiseItem.getCombinationUnifiedMerchandiseId());
                list.add(combinationMerchandise);
            }
            List<CombinationMerchandiseVO> voList = combinationMerchandiseHandler.toVOList(list);
            view.addObject("combinationList", voList);
            view.addObject("exist", itemLlist.size() > 0);
        } else {
            view.addObject("combinationList", new ArrayList<CombinationMerchandiseVO>());
            view.addObject("exist", Boolean.FALSE.toString());
        }
        return view;
    }
}
