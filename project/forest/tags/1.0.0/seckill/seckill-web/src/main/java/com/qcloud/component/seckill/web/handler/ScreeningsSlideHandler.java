package com.qcloud.component.seckill.web.handler;

import java.util.List;

import com.qcloud.component.seckill.model.ScreeningsSlide;
import com.qcloud.component.seckill.web.vo.ScreeningsSlideVO;
import com.qcloud.component.seckill.web.vo.admin.AdminScreeningsSlideVO;

public interface ScreeningsSlideHandler {

	List<ScreeningsSlideVO> toVOList(List<ScreeningsSlide> list);

	ScreeningsSlideVO toVO(ScreeningsSlide screeningsSlide);

	List<AdminScreeningsSlideVO> toVOList4Admin(List<ScreeningsSlide> list);

	AdminScreeningsSlideVO toVO4Admin(ScreeningsSlide screeningsSlide);
}
