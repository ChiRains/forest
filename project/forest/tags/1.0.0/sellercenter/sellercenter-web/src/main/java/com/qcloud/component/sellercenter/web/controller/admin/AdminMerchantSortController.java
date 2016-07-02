package com.qcloud.component.sellercenter.web.controller.admin;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.qcloud.component.publicdata.PublicdataClient;
import com.qcloud.component.sellercenter.model.MerchantSort;
import com.qcloud.component.sellercenter.model.query.MerchantSortQuery;
import com.qcloud.component.sellercenter.service.MerchantSortService;
import com.qcloud.component.sellercenter.web.handler.MerchantSortHandler;
import com.qcloud.component.sellercenter.web.vo.admin.AdminMerchantSortVO;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.mvc.AceAjaxView;
import com.qcloud.pirates.mvc.AcePagingView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.NumberUtil;
import com.qcloud.pirates.util.RequestUtil;
import com.qcloud.pirates.web.security.annotation.NoReferer;

@Controller
@RequestMapping(value="/"+AdminMerchantSortController.DIR)
public class AdminMerchantSortController {
    
    public static final String DIR="admin/merchantSort";
    
    @Autowired
    private MerchantSortService merchantSortService;
    
    @Autowired
    private MerchantSortHandler merchantSortHandler;
    
    @Autowired
    private PublicdataClient        publicdataClient;
    
    @RequestMapping
    @NoReferer
    public ModelAndView list(Integer pageNum,MerchantSortQuery query){
        final int PAGE_SIZE = 10;
        pageNum=RequestUtil.getPageid(pageNum);
        int start=NumberUtil.getPageStart(pageNum, PAGE_SIZE);
        Page<MerchantSort> page=merchantSortService.page(query, start, PAGE_SIZE);
        List<AdminMerchantSortVO> list=merchantSortHandler.toVOList4Admin(page.getData());
        AcePagingView pagingView=new AcePagingView("/admin/sellercenter-MerchantSort-list", DIR+"/list", pageNum, PAGE_SIZE,page.getCount() );
        pagingView.addObject("result",list);
        pagingView.addObject("query",query);
        
        return pagingView;
    }
    
    @RequestMapping
    public ModelAndView toAdd(){
        ModelAndView model=new ModelAndView("/admin/sellercenter-MerchantSort-add");
        String fileSize=publicdataClient.getImageInformationByCode("shangjiashangbiao");
        model.addObject("fileSize",fileSize);
        return model;
    }
    
    @RequestMapping
    public AceAjaxView add(MerchantSort merchantSort){
        merchantSortService.add(merchantSort);
        AceAjaxView aceAjaxView=new AceAjaxView();
        aceAjaxView.setMessage("添加成功");
        aceAjaxView.setUrl(DIR+"/list");
        return aceAjaxView;
    }
    
    @RequestMapping
    public ModelAndView toEdit(Long id) {
        AssertUtil.assertNotNull(id, "ID不能为空");
        MerchantSort merchantSort = merchantSortService.get(id);
        AdminMerchantSortVO adminMerchantSortVO = merchantSortHandler.toVO4Admin(merchantSort);
        ModelAndView model = new ModelAndView("/admin/sellercenter-MerchantSort-edit");
        model.addObject("merchantSort", adminMerchantSortVO);
        String fileSize=publicdataClient.getImageInformationByCode("shangjiashangbiao");
        model.addObject("fileSize",fileSize);
        return model;
    }
    
    @RequestMapping
    public AceAjaxView edit(MerchantSort merchantSort){
        merchantSortService.update(merchantSort);
        AceAjaxView aceAjaxView=new AceAjaxView();
        aceAjaxView.setMessage("编辑成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }
    
    @RequestMapping
    public AceAjaxView delete(Long id){
        AssertUtil.assertNotNull(id, "ID不能为空");
        AceAjaxView aceAjaxView=new AceAjaxView();
        if(merchantSortService.delete(id)){
            aceAjaxView.setMessage("删除成功");
        }else{
            aceAjaxView.setMessage("删除失败");
            aceAjaxView.setStatus(0);
        }
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }
   
}
