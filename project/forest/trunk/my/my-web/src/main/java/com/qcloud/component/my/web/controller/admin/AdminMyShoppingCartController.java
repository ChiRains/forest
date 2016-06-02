package com.qcloud.component.my.web.controller.admin;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.qcloud.component.my.model.MyShoppingCart;
import com.qcloud.component.my.model.query.MyShoppingCartQuery;
import com.qcloud.component.my.service.MyShoppingCartService;
import com.qcloud.component.my.web.handler.MyShoppingCartHandler;
import com.qcloud.component.my.web.vo.admin.AdminMyShoppingCartVO;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.mvc.AceAjaxView;
import com.qcloud.pirates.mvc.AcePagingView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.NumberUtil;
import com.qcloud.pirates.util.RequestUtil;
import com.qcloud.pirates.web.security.annotation.NoReferer;
		
@Controller
@RequestMapping(value = "/" + AdminMyShoppingCartController.DIR)
public class AdminMyShoppingCartController {
	
	public static final String DIR = "admin/myShoppingCart";
	
	@Autowired
	private MyShoppingCartService myShoppingCartService;
	@Autowired
	private MyShoppingCartHandler myShoppingCartHandler;
	
	@RequestMapping
	@NoReferer
	public ModelAndView list(Integer pageNum, MyShoppingCartQuery query) {
		final int PAGE_SIZE = 10;
		pageNum = RequestUtil.getPageid(pageNum);
		int start = NumberUtil.getPageStart(pageNum, PAGE_SIZE);
		
		Page<MyShoppingCart> page = myShoppingCartService.page(query, start, PAGE_SIZE);
		List<AdminMyShoppingCartVO> list = myShoppingCartHandler.toVOList4Admin(page.getData());
				
		AcePagingView pagingView = new AcePagingView("/admin/personalcenter-MyShoppingCart-list", DIR
				+ "/list", pageNum, PAGE_SIZE, page.getCount());		
		pagingView.addObject("result", list);
	
		return pagingView;
	}
	
	@RequestMapping
	public ModelAndView toAdd() {
		ModelAndView model = new ModelAndView("/admin/personalcenter-MyShoppingCart-add");
		return model;
	}
	
	@RequestMapping
	public AceAjaxView add(MyShoppingCart myShoppingCart) {
		myShoppingCartService.add(myShoppingCart);
		
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("添加成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}
	
	@RequestMapping
	public ModelAndView toEdit(Long id) {
		AssertUtil.assertNotNull(id, "ID不能为空");
		MyShoppingCart myShoppingCart=myShoppingCartService.get(id);
		AdminMyShoppingCartVO adminMyShoppingCartVO=myShoppingCartHandler.toVO4Admin(myShoppingCart);
		ModelAndView model = new ModelAndView("/admin/personalcenter-MyShoppingCart-edit");
		model.addObject("myShoppingCart", adminMyShoppingCartVO);
		return model;
	}
	
	@RequestMapping
	public AceAjaxView edit(MyShoppingCart myShoppingCart) {
		myShoppingCartService.update(myShoppingCart);		
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("编辑成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}
	
	@RequestMapping
	public AceAjaxView delete(Long id) {
		AssertUtil.assertNotNull(id, "ID不能为空");
		myShoppingCartService.delete(id);
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("删除成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}	
}
