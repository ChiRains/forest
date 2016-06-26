package com.qcloud.component.goods.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qcloud.component.goods.CommoditycenterClient;
import com.qcloud.component.goods.QUnifiedMerchandise;
import com.qcloud.component.goods.QUnifiedMerchandise.MerchandiseType;
import com.qcloud.component.goods.model.CombinationMerchandise;
import com.qcloud.component.goods.model.CombinationMerchandiseItem;
import com.qcloud.component.goods.service.CombinationMerchandiseItemService;
import com.qcloud.component.goods.service.CombinationMerchandiseService;
import com.qcloud.component.goods.web.handler.CombinationMerchandiseHandler;
import com.qcloud.component.goods.web.vo.CombinationMerchandiseVO;
import com.qcloud.pirates.mvc.FrontAjaxView;
import com.qcloud.pirates.util.AssertUtil;

@Controller
@RequestMapping(value = CombinationMerchandiseController.DIR)
public class CombinationMerchandiseController {

    public static final String                DIR = "/combinationMerchandise";

    @Autowired
    private CombinationMerchandiseService     combinationMerchandiseService;

    @Autowired
    private CombinationMerchandiseHandler     combinationMerchandiseHandler;

    @Autowired
    private CombinationMerchandiseItemService combinationMerchandiseItemService;

    @Autowired
    private CommoditycenterClient             commoditycenterClient;

    @RequestMapping
    public FrontAjaxView existByUnifiedMerchandise(Long unifiedMerchandiseId) {

        AssertUtil.assertNotNull(unifiedMerchandiseId, "统一商品ID不能为空.");
        QUnifiedMerchandise unifiedMerchandise = commoditycenterClient.getUnifiedMerchandise(unifiedMerchandiseId);
        AssertUtil.assertNotNull(unifiedMerchandise, "商品不存在." + unifiedMerchandiseId);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("获取商品组合套餐成功.");
        if (MerchandiseType.SINGLE.equals(unifiedMerchandise.getType())) {
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
        if (MerchandiseType.SINGLE.equals(unifiedMerchandise.getType())) {
            List<CombinationMerchandiseItem> list = combinationMerchandiseItemService.listByMerchandiseItem(unifiedMerchandise.getList().get(0).getId(), 0, 10);
            if (list.size() > 0) {
                int index = new Random().nextInt(list.size());
                CombinationMerchandiseItem combinationMerchandiseItem = list.get(index);
                CombinationMerchandise combinationMerchandise = combinationMerchandiseService.get(combinationMerchandiseItem.getCombinationMerchandiseId());
                CombinationMerchandiseVO combinationMerchandiseVO = combinationMerchandiseHandler.toVO(combinationMerchandise);
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
        CombinationMerchandise combinationMerchandise = combinationMerchandiseService.getByUnifiedMerchandise(unifiedMerchandiseId);
        AssertUtil.assertNotNull(combinationMerchandise, "组合商品不存在." + unifiedMerchandiseId);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("获取商品组合套餐列表成功.");
        List<CombinationMerchandise> list = new ArrayList<CombinationMerchandise>();
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
        if (MerchandiseType.COMBINATION.equals(unifiedMerchandise.getType())) {
            List<CombinationMerchandiseItem> itemLlist = combinationMerchandiseItemService.listByMerchandiseItem(unifiedMerchandise.getList().get(0).getId(), 0, 10);
            List<CombinationMerchandise> list = new ArrayList<CombinationMerchandise>();
            for (CombinationMerchandiseItem combinationMerchandiseItem : itemLlist) {
                CombinationMerchandise combinationMerchandise = combinationMerchandiseService.get(combinationMerchandiseItem.getCombinationMerchandiseId());
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
