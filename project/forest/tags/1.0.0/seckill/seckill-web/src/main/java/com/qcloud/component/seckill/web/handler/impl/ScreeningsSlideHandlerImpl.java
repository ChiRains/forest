package com.qcloud.component.seckill.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

import com.qcloud.pirates.core.json.Json;
import com.qcloud.component.seckill.web.handler.ScreeningsSlideHandler;
import com.qcloud.component.seckill.model.ScreeningsSlide;
import com.qcloud.component.seckill.web.vo.ScreeningsSlideVO;
import com.qcloud.component.seckill.web.vo.admin.AdminScreeningsSlideVO;

@Component
public class ScreeningsSlideHandlerImpl implements ScreeningsSlideHandler {

	@Override
	public List<ScreeningsSlideVO> toVOList(List<ScreeningsSlide> list){
		List<ScreeningsSlideVO> voList = new ArrayList<ScreeningsSlideVO>();
		for (ScreeningsSlide screeningsSlide : list) {
			voList.add(toVO(screeningsSlide));
		}
		return voList;
	}

	@Override
	public ScreeningsSlideVO toVO(ScreeningsSlide screeningsSlide){
		String json = Json.toJson(screeningsSlide);
		return Json.toObject(json, ScreeningsSlideVO.class, true);

	}

	@Override
	public List<AdminScreeningsSlideVO> toVOList4Admin(List<ScreeningsSlide> list){
		List<AdminScreeningsSlideVO> voList = new ArrayList<AdminScreeningsSlideVO>();
		for (ScreeningsSlide adminScreeningsSlide : list) {
			voList.add(toVO4Admin(adminScreeningsSlide));
		}
		return voList;
	}

	@Override
	public AdminScreeningsSlideVO toVO4Admin(ScreeningsSlide screeningsSlide){
		String json = Json.toJson(screeningsSlide);
		return Json.toObject(json, AdminScreeningsSlideVO.class, true);
	}
}
