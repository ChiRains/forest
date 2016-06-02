package com.qcloud.component.marketing.web.handler;

import java.util.List;

import com.qcloud.component.marketing.model.Slide;
import com.qcloud.component.marketing.web.vo.SlideVO;
import com.qcloud.component.marketing.web.vo.admin.AdminSlideVO;

public interface SlideHandler {

	List<SlideVO> toVOList(List<Slide> list);

	SlideVO toVO(Slide slide);

	List<AdminSlideVO> toVOList4Admin(List<Slide> list);

	AdminSlideVO toVO4Admin(Slide slide);
}
