package com.qcloud.component.seckill.web.controller.admin;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.qcloud.component.seckill.model.MerchandiseSeckill;
import com.qcloud.component.seckill.model.Screenings;
import com.qcloud.component.seckill.model.query.ScreeningsQuery;
import com.qcloud.component.seckill.service.MerchandiseSeckillService;
import com.qcloud.component.seckill.service.ScreeningsService;
import com.qcloud.component.seckill.web.handler.ScreeningsHandler;
import com.qcloud.component.seckill.web.vo.admin.AdminScreeningsVO;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.mvc.AceAjaxView;
import com.qcloud.pirates.mvc.AcePagingView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.DateUtil;
import com.qcloud.pirates.util.NumberUtil;
import com.qcloud.pirates.util.RequestUtil;
import com.qcloud.pirates.web.security.annotation.NoReferer;
		
@Controller
@RequestMapping(value = "/" + AdminScreeningsController.DIR)
public class AdminScreeningsController {
	
	public static final String DIR = "admin/screenings";
	
	@Autowired
	private ScreeningsService screeningsService;
	@Autowired
	private ScreeningsHandler screeningsHandler;
	
	@Autowired
	private MerchandiseSeckillService merchandiseSeckillService;
	
	@RequestMapping
	@NoReferer
	public ModelAndView list(Integer pageNum, ScreeningsQuery query) {
		final int PAGE_SIZE = 10;
		pageNum = RequestUtil.getPageid(pageNum);
		int start = NumberUtil.getPageStart(pageNum, PAGE_SIZE);
		
		Page<Screenings> page = screeningsService.page(query, start, PAGE_SIZE);
		List<AdminScreeningsVO> list = screeningsHandler.toVOList4Admin(page.getData());
				
		AcePagingView pagingView = new AcePagingView("/admin/seckill-Screenings-list", DIR
				+ "/list", pageNum, PAGE_SIZE, page.getCount());		
		pagingView.addObject("result", list);
		pagingView.addObject("query", query);
	
		return pagingView;
	}
	
	@RequestMapping
	public ModelAndView toAdd() {
		ModelAndView model = new ModelAndView("/admin/seckill-Screenings-add");
		return model;
	}
	
	@RequestMapping
	public AceAjaxView add(Screenings screenings) {
	    AssertUtil.assertNotNull(screenings.getBeginTime(), "开始时间不能为空");
	    AssertUtil.assertNotNull(screenings.getEndTime(), "结束时间不能为空");
        //
	    Date beginTime=screenings.getBeginTime();
	    Date endTime=screenings.getEndTime();
	    Date today=DateUtil.str2Date(DateUtil.getDate(new Date())+" 00:00:00");
	    //
	    String subBegin=DateUtil.date2String(screenings.getBeginTime(),"yyyy-MM-dd");
	    String subEnd=DateUtil.date2String(screenings.getEndTime(),"yyyy-MM-dd");
	    String subToday=DateUtil.date2String(today,"yyyy-MM-dd");
	    if(today.before(beginTime)||subToday.equals(subBegin)){
	        if(subBegin.equals(subEnd)){
	            if(beginTime.before(endTime)){
//	                Screenings temp=screeningsService.getByBeginTime(DateUtil.date2String(screenings.getBeginTime()));
//	            	AssertUtil.assertTrue(null==temp, "秒杀活动已经存在");
	            	List<Screenings> list = screeningsService.getRepeatByTime(beginTime, endTime);
	            	if (list != null && list.size() > 0) {
						AssertUtil.assertTrue(false, "秒杀活动已经存在");
					}
	                screeningsService.add(screenings);
	            }else{
	                AssertUtil.assertTrue(false, "结束时间必须大于开始时间");
	            }
	        } else{
	            AssertUtil.assertTrue(false, "开始时间，结束时间必须在同一天");
	        }
	    }else{
            AssertUtil.assertTrue(false, "开始时间，结束时间必须大于或等于当天0点");
        }
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("添加成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}
	
	@RequestMapping
	public ModelAndView toEdit(Long id) {
		AssertUtil.assertNotNull(id, "ID不能为空");
		Screenings screenings=screeningsService.get(id);
		AdminScreeningsVO adminScreeningsVO=screeningsHandler.toVO4Admin(screenings);
		ModelAndView model = new ModelAndView("/admin/seckill-Screenings-edit");
		model.addObject("screenings", adminScreeningsVO);
		return model;
	}
	
	@RequestMapping
	public AceAjaxView edit(Screenings screenings) {
		screeningsService.update(screenings);		
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("编辑成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}
	
	@RequestMapping
	public AceAjaxView delete(Long id) {
		AssertUtil.assertNotNull(id, "ID不能为空");
		AceAjaxView aceAjaxView = new AceAjaxView();
		//秒杀商品列表
		List<MerchandiseSeckill> list = merchandiseSeckillService.listByScreenings(id);
		//已经开始的场次不能删除
		Screenings screenings = screeningsService.get(id);
		if (screenings.getBeginTime().getTime() <= new Date().getTime()) {
			aceAjaxView.setMessage("删除失败，此秒杀活动已经开始");
			aceAjaxView.setStatus(0);
		} else if (list != null && list.size() > 0) {
			aceAjaxView.setMessage("删除失败，请先删除该场次下的秒杀商品");
			aceAjaxView.setStatus(0);
		} else {
			screenings.setEnable(2);
			screeningsService.update(screenings);
//			screeningsService.delete(id);
			aceAjaxView.setMessage("删除成功");
		}
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}	
}
