package com.qcloud.component.goods.web.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qcloud.component.publicdata.PublicdataClient;
import com.qcloud.component.publicdata.QClassify;
import com.qcloud.pirates.mvc.FrontAjaxView;
@Controller
@RequestMapping(value = MerchandiseClassifyController.DIR)
public class MerchandiseClassifyController {
    //
    public static final String DIR = "/classifyMerchandise";
    @Autowired
    private PublicdataClient   publicdataClient;

    // 商城分类
    @RequestMapping
    public FrontAjaxView listClassify() {
        List<QClassify> classifyList = publicdataClient.listClassifyForTree(2L);
        FrontAjaxView view = new FrontAjaxView();
        view.addObject("classifyList", classifyList);
        view.setMessage("获取商城商品分类成功");
        return view;
    }

    // 商家商品分类
    @RequestMapping
    public FrontAjaxView listClassifyForMerchant(Long merchantId) {
        List<QClassify> classifyList = publicdataClient.listClassifyForTree(merchantId);
        FrontAjaxView view = new FrontAjaxView();
        view.addObject("classifyList", classifyList);
        view.setMessage("获取商家商品分类成功");
        return view;
    }
}
