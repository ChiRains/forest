package com.qcloud.component.marketing.web.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qcloud.component.marketing.model.Slide;
import com.qcloud.component.marketing.service.SlideService;
import com.qcloud.component.marketing.web.handler.SlideHandler;
import com.qcloud.component.marketing.web.vo.SlideVO;
import com.qcloud.component.publicdata.IntKeyValue;
import com.qcloud.pirates.mvc.FrontAjaxView;
import com.qcloud.pirates.util.AssertUtil;

@Controller
@RequestMapping(value = SlideController.DIR)
public class SlideController {

    public static final String DIR = "/slide";

    @Autowired
    private SlideService       slideService;

    @Autowired
    private SlideHandler       slideHandler;

    @RequestMapping
    public FrontAjaxView listSlides(Integer sence) {

        AssertUtil.assertNotNull(sence, "广告区图片场景不能为空.");
        List<IntKeyValue> sencelist = slideService.getSenceTypes();
        boolean exist = false;
        for (IntKeyValue intKeyValue : sencelist) {
            if (intKeyValue.getKey() == sence) {
                exist = true;
            }
        }
        AssertUtil.assertTrue(exist, "广告区图片场景不合法." + sence);
        List<Slide> list = slideService.listBySence(sence);
        List<SlideVO> voList = slideHandler.toVOList(list);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("获取广告区图片成功.");
        view.addObject("slideList", voList);
        return view;
    }
}
