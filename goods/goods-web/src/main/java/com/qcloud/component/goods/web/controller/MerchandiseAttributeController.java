package com.qcloud.component.goods.web.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qcloud.component.goods.model.Merchandise;
import com.qcloud.component.goods.model.MerchandiseAttribute;
import com.qcloud.component.goods.model.UnifiedMerchandise;
import com.qcloud.component.goods.service.MerchandiseAttributeService;
import com.qcloud.component.goods.service.MerchandiseService;
import com.qcloud.component.goods.service.UnifiedMerchandiseService;
import com.qcloud.component.goods.web.handler.MerchandiseAttributeHandler;
import com.qcloud.component.goods.web.vo.MerchandiseAttributeVO;
import com.qcloud.pirates.mvc.FrontAjaxView;
import com.qcloud.pirates.util.AssertUtil;

@Controller
@RequestMapping(value = MerchandiseAttributeController.DIR)
public class MerchandiseAttributeController {

    public static final String          DIR = "/merchandiseAttribute";

    @Autowired
    private MerchandiseAttributeService merchandiseAttributeService;

    @Autowired
    private MerchandiseAttributeHandler merchandiseAttributeHandler;

    @Autowired
    private MerchandiseService          merchandiseService;

    @Autowired
    private UnifiedMerchandiseService   unifiedMerchandiseService;

    @RequestMapping
    public FrontAjaxView listAttrByMerchandise(Long merchandiseId) {

        Merchandise merchandise = merchandiseService.get(merchandiseId);
        AssertUtil.assertNotNull(merchandise, "商品不存在." + merchandiseId);
        List<MerchandiseAttribute> list = merchandiseAttributeService.listByMerchandise(merchandiseId);
        List<MerchandiseAttributeVO> voList = merchandiseAttributeHandler.toVOList(list);
        FrontAjaxView view = new FrontAjaxView();
        for (MerchandiseAttributeVO merchandiseAttributeVO : voList) {
            view.addObject(merchandiseAttributeVO.getCode(), merchandiseAttributeVO);
        }
        // MerchandiseAttributeVO vo1 = new MerchandiseAttributeVO();
        // vo1.setCode("adasdasd");
        // vo1.setName("adasdaASDASDsd");
        // vo1.setValue("adasdaADAsd");
        // voList.add(vo1);
        // MerchandiseAttributeVO vo2 = new MerchandiseAttributeVO();
        // vo2.setCode("adasdasd");
        // vo2.setName("adasdDASDsd");
        // vo2.setValue("adasdassdalksdyhfijsyhftijkweyroi719278340931yr1434647657867867867815245631");
        // voList.add(vo2);
        view.setMessage("获取商品属性成功.");
        view.addObject("list", voList);
        return view;
    }

    @RequestMapping
    public FrontAjaxView listAttrByUnifiedMerchandise(Long unifiedMerchandiseId) {

        AssertUtil.assertNotNull(unifiedMerchandiseId, "统一商品ID不能为空.");
        UnifiedMerchandise unifiedMerchandise = unifiedMerchandiseService.get(unifiedMerchandiseId);
        AssertUtil.assertNotNull(unifiedMerchandise, "统一商品不存在." + unifiedMerchandiseId);
        return listAttrByMerchandise(unifiedMerchandise.getMerchandiseId());
    }
}
