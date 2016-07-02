package com.qcloud.component.marketing.web.controller.admin;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.qcloud.component.marketing.model.Slide;
import com.qcloud.component.marketing.model.query.SlideQuery;
import com.qcloud.component.marketing.service.SlideService;
import com.qcloud.component.marketing.web.handler.SlideHandler;
import com.qcloud.component.marketing.web.vo.admin.AdminSlideVO;
import com.qcloud.component.publicdata.IntKeyValue;
import com.qcloud.component.publicdata.KeyValueVO;
import com.qcloud.component.publicdata.PublicdataClient;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.mvc.AceAjaxView;
import com.qcloud.pirates.mvc.AcePagingView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.NumberUtil;
import com.qcloud.pirates.util.RequestUtil;
import com.qcloud.pirates.web.security.annotation.NoReferer;

@Controller
@RequestMapping(value = "/" + AdminSlideController.DIR)
public class AdminSlideController {

    public static final String DIR = "admin/slide";

    @Autowired
    private SlideService       slideService;

    @Autowired
    private SlideHandler       slideHandler;

    @Autowired
    private PublicdataClient   publicdataClient;

    @RequestMapping
    @NoReferer
    public ModelAndView list(Integer pageNum, SlideQuery query) {

        final int PAGE_SIZE = 10;
        pageNum = RequestUtil.getPageid(pageNum);
        int start = NumberUtil.getPageStart(pageNum, PAGE_SIZE);
        Page<Slide> page = slideService.page(query, start, PAGE_SIZE);
        List<AdminSlideVO> list = slideHandler.toVOList4Admin(page.getData());
        AcePagingView pagingView = new AcePagingView("/admin/marketing-Slide-list", DIR + "/list", pageNum, PAGE_SIZE, page.getCount());
        pagingView.addObject("result", list);
        return pagingView;
    }

    @RequestMapping
    public ModelAndView toAdd() {

        List<IntKeyValue> senceTypeList = slideService.getSenceTypes();
        List<KeyValueVO> senceTypeVOList = publicdataClient.exchageObj(senceTypeList, -1, "");
        List<String> orderNumStrList = new ArrayList<String>();
        for (int index = 1; index <= 10; index++) {
            orderNumStrList.add(String.valueOf(index));
        }
        List<KeyValueVO> orderNumList = publicdataClient.exchageStr(orderNumStrList, "5", "selected");
        ModelAndView model = new ModelAndView("/admin/marketing-Slide-add");
        model.addObject("senceTypeList", senceTypeVOList);
        model.addObject("orderNumList", orderNumList);
        String fileSize=publicdataClient.getImageInformationByCode("shouyelunbo");
        model.addObject("fileSize",fileSize);
        return model;
    }

    @RequestMapping
    public AceAjaxView add(Slide slide) {

        slideService.add(slide);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("添加成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    public ModelAndView toEdit(Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        Slide slide = slideService.get(id);
        AssertUtil.assertNotNull(slide, "广告图片不存在");
        List<IntKeyValue> senceTypeList = slideService.getSenceTypes();
        List<KeyValueVO> senceTypeVOList = publicdataClient.exchageObj(senceTypeList, slide.getSence(), "selected");
        List<String> orderNumStrList = new ArrayList<String>();
        for (int index = 1; index <= 10; index++) {
            orderNumStrList.add(String.valueOf(index));
        }
        List<KeyValueVO> orderNumList = publicdataClient.exchageStr(orderNumStrList, String.valueOf(slide.getOrderNum()), "selected");
        AdminSlideVO adminSlideVO = slideHandler.toVO4Admin(slide);
        ModelAndView model = new ModelAndView("/admin/marketing-Slide-edit");
        model.addObject("slide", adminSlideVO);
        model.addObject("senceTypeList", senceTypeVOList);
        model.addObject("orderNumList", orderNumList);
        String fileSize=publicdataClient.getImageInformationByCode("shouyelunbo");
        model.addObject("fileSize",fileSize);
        return model;
    }

    @RequestMapping
    public AceAjaxView edit(Slide slide) {

        slideService.update(slide);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("编辑成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    public AceAjaxView delete(Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        slideService.delete(id);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("删除成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }
}
