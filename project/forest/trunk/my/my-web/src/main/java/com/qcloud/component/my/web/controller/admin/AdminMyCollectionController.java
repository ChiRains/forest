package com.qcloud.component.my.web.controller.admin;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.qcloud.component.my.model.MyCollection;
import com.qcloud.component.my.model.query.MyCollectionQuery;
import com.qcloud.component.my.service.MyCollectionService;
import com.qcloud.component.my.web.handler.MyCollectionHandler;
import com.qcloud.component.my.web.vo.admin.AdminMyCollectionVO;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.mvc.AceAjaxView;
import com.qcloud.pirates.mvc.AcePagingView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.NumberUtil;
import com.qcloud.pirates.util.RequestUtil;
import com.qcloud.pirates.web.security.annotation.NoReferer;
		
@Controller
@RequestMapping(value = "/" + AdminMyCollectionController.DIR)
public class AdminMyCollectionController {
	
	public static final String DIR = "admin/myCollection";
	
	@Autowired
	private MyCollectionService myCollectionService;
	@Autowired
	private MyCollectionHandler myCollectionHandler;
	
	@RequestMapping
	@NoReferer
	public ModelAndView list(Integer pageNum, MyCollectionQuery query) {
		final int PAGE_SIZE = 10;
		pageNum = RequestUtil.getPageid(pageNum);
		int start = NumberUtil.getPageStart(pageNum, PAGE_SIZE);
		
		Page<MyCollection> page = myCollectionService.page(query, start, PAGE_SIZE);
		List<AdminMyCollectionVO> list = myCollectionHandler.toVOList4Admin(page.getData());
				
		AcePagingView pagingView = new AcePagingView("/admin/personalcenter-MyCollection-list", DIR
				+ "/list", pageNum, PAGE_SIZE, page.getCount());		
		pagingView.addObject("result", list);
	
		return pagingView;
	}
	
	@RequestMapping
	public ModelAndView toAdd() {
		ModelAndView model = new ModelAndView("/admin/personalcenter-MyCollection-add");
		return model;
	}
	
	@RequestMapping
	public AceAjaxView add(MyCollection myCollection) {
		myCollectionService.add(myCollection);
		
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("添加成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}
	
	@RequestMapping
	public ModelAndView toEdit(Long id) {
		AssertUtil.assertNotNull(id, "ID不能为空");
		MyCollection myCollection=myCollectionService.get(id);
		AdminMyCollectionVO adminMyCollectionVO=myCollectionHandler.toVO4Admin(myCollection);
		ModelAndView model = new ModelAndView("/admin/personalcenter-MyCollection-edit");
		model.addObject("myCollection", adminMyCollectionVO);
		return model;
	}
	
	@RequestMapping
	public AceAjaxView edit(MyCollection myCollection) {
		myCollectionService.update(myCollection);		
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("编辑成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}
	
	@RequestMapping
	public AceAjaxView delete(Long id) {
		AssertUtil.assertNotNull(id, "ID不能为空");
		myCollectionService.delete(id);
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("删除成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}	
}
