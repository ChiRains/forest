package com.qcloud.project.forest.web.handler;

import java.util.List;

import com.qcloud.project.forest.model.PromotionalOffers;
import com.qcloud.project.forest.web.vo.PromotionalOffersVO;
import com.qcloud.project.forest.web.vo.admin.AdminPromotionalOffersVO;

public interface PromotionalOffersHandler {

	List<PromotionalOffersVO> toVOList(List<PromotionalOffers> list);

	PromotionalOffersVO toVO(PromotionalOffers promotionalOffers);

	List<AdminPromotionalOffersVO> toVOList4Admin(List<PromotionalOffers> list);

	AdminPromotionalOffersVO toVO4Admin(PromotionalOffers promotionalOffers);
}
