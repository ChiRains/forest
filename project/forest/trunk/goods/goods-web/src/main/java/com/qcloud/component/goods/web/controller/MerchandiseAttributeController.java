package com.qcloud.component.goods.web.controller;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qcloud.component.goods.model.Merchandise;
import com.qcloud.component.goods.model.MerchandiseAttribute;
import com.qcloud.component.goods.model.MerchandiseItem;
import com.qcloud.component.goods.service.MerchandiseAttributeService;
import com.qcloud.component.goods.service.MerchandiseItemService;
import com.qcloud.component.goods.service.MerchandiseService;
import com.qcloud.component.goods.web.handler.MerchandiseAttributeHandler;
import com.qcloud.component.goods.web.vo.MerchandiseAttributeVO;
import com.qcloud.pirates.mvc.FrontAjaxView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.StringUtil;

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
    private MerchandiseItemService      merchandiseItemService;

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
        view.setMessage("获取商品属性成功.");
        view.addObject("list", voList);
        return view;
    }

    @RequestMapping
    public FrontAjaxView listAttrByUnifiedMerchandise(Long unifiedMerchandiseId) {

        AssertUtil.assertNotNull(unifiedMerchandiseId, "统一商品ID不能为空.");
        MerchandiseItem merchandiseItem = merchandiseItemService.getByUnifiedMerchandise(unifiedMerchandiseId);
        AssertUtil.assertNotNull(merchandiseItem, "单一商品不存在." + unifiedMerchandiseId);
        return listAttrByMerchandise(merchandiseItem.getMerchandiseId());
    }
}
