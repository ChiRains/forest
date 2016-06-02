package com.qcloud.component.my.web.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qcloud.component.my.model.MyCollection;
import com.qcloud.component.my.model.key.TypeEnum.CollectionType;
import com.qcloud.component.my.web.handler.MyCollectionHandler;
import com.qcloud.component.my.web.vo.MyMerchantCollectionVO;
import com.qcloud.component.sellercenter.QMerchant;
import com.qcloud.component.sellercenter.SellercenterClient;
import com.qcloud.pirates.mvc.FrontAjaxView;
import com.qcloud.pirates.web.mvc.annotation.PiratesApp;
import com.qcloud.pirates.web.page.PPage;

@Controller
@RequestMapping(value = MyMerchantCollectionController.DIR)
public class MyMerchantCollectionController {

    public static final String     DIR = "/myMerchantCollection";

    @Autowired
    private MyCollectionHandler    myCollectionHandler;

    @Autowired
    private MyCollectionController myCollectionController;

    @Autowired
    private SellercenterClient     sellercenterClient;

    @PiratesApp
    @RequestMapping
    public FrontAjaxView collect(HttpServletRequest request, Long merchantId) {

        QMerchant merchant = sellercenterClient.getMerchant(merchantId);
        return myCollectionController.collect(request, merchantId, merchant.getClassifyId(), CollectionType.MERCHANT);
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView isCollect(HttpServletRequest request, Long merchantId) {

        return myCollectionController.isCollect(request, merchantId, CollectionType.MERCHANT);
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView remove(HttpServletRequest request, Long id) {

        return myCollectionController.remove(request, id);
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView removeByMerchant(HttpServletRequest request, Long merchantId) {

        return myCollectionController.removeByObject(request, merchantId, CollectionType.MERCHANT);
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView list(HttpServletRequest request, PPage pPage) {

        List<MyCollection> list = myCollectionController.list(request, pPage, CollectionType.MERCHANT, -1L);
        List<MyMerchantCollectionVO> voList = myCollectionHandler.toMerchantMyCollectionVOList(list);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("获取我的店铺收藏成功.");
        view.addObject("data", voList);
        return view;
    }
}
